/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import csgo.gameintegration.GameInfo;
import csgo.gameintegration.MapInfo;
import csgo.gameintegration.PlayerInfo;
import csgo.gameintegration.RoundInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Christoffer Andersson
 */
public class CSGOServer implements HttpHandler
{
    private final HttpServer httpServer;
    private final GameInfo game;
    private final MapInfo map;
    private final RoundInfo round;
    private final PlayerInfo player;
    private final ArrayList<PlayerInfo> players;
    
    public CSGOServer() throws IOException
    {
        this.game = new GameInfo();
        this.map = new MapInfo();
        this.round = new RoundInfo();
        this.player = new PlayerInfo();
        this.players = new ArrayList<>();
        
        this.httpServer = HttpServer.create(new InetSocketAddress("127.0.0.1", 4449), 1);
        this.httpServer.createContext("/", this);
        this.httpServer.setExecutor(null);
        this.httpServer.start();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {
        if(httpExchange.getRequestMethod().equals("POST"))
        {
            JSONObject jsonObject = new JSONObject(getBody(httpExchange.getRequestBody()));
            if(jsonObject.has("provider"))
            {
                this.game.updateInformation(jsonObject.getJSONObject("provider"));
            }
            if(jsonObject.has("map"))
            {
                this.map.updateInformation(jsonObject.getJSONObject("map"));
            }
            if(jsonObject.has("round"))
            {
                this.round.updateInformation(jsonObject.getJSONObject("round"));
            }
            if(jsonObject.has("player"))
            {
                this.player.updateInformation(jsonObject.getJSONObject("player"), true);
            }
            if(jsonObject.has("allplayers"))
            {
                JSONObject playersObject = jsonObject.getJSONObject("allplayers");
                ArrayList<Long> steamIds = new ArrayList<>();
                for(Object steamId : playersObject.names())
                {
                    steamIds.add(Long.parseLong((String) steamId));
                }
                for(int i = players.size() - 1; i > -1; i--)
                {
                    int index = steamIds.indexOf(players.get(i).getSteamId());
                    
                    if(index == -1)
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnLeft, null, players.get(i));
                        this.players.remove(i);
                    }
                    else
                    {
                        this.players.get(i).updateInformation(playersObject.getJSONObject(steamIds.get(index).toString()), steamIds.get(index), false);
                        steamIds.remove(index);
                    }
                }
                for(long steamId : steamIds)
                {
                    this.players.add(new PlayerInfo(playersObject.getJSONObject("" + steamId), steamId, false));
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnJoined, players.get(players.size() - 1), null);
                }
            }
            EventManager.fireEvents();
            String response = "\"Content-Type\": \"text/html\"";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
    
    private static String getBody(InputStream inputStream) throws IOException
    {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try
        {
            if (inputStream != null)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0)
                {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
            else
            {
                stringBuilder.append("");
            }
        } 
        catch (IOException ex)
        {
            throw ex;
        } 
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException ex)
                {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import org.json.JSONObject;

/**
 *
 * @author Christoffer Andersson
 */
public final class GameInfo
{
    private String name;
    private int id;
    private int version;
    private long steamId;

    public GameInfo()
    {
        this.name = "";
    }
    
    public GameInfo(JSONObject json)
    {
        this();
        this.updateInformation(json);
    }
    
    public GameInfo(String name, int id, int version, long steamId)
    {
        this.name = name;
        this.id = id;
        this.version = version;
        this.steamId = steamId;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public int getVersion()
    {
        return version;
    }

    public long getSteamId()
    {
        return steamId;
    }
    
    public void updateInformation(JSONObject json)
    {
        if(json.has("name"))
        {
            String next = json.getString("name");
            if(!name.equals(next))
            {
                EventManager.addEvent(EventManager.GAME_OnGameChange, next, name);
                this.name = next;
            }
        }
        if(json.has("appid"))
        {
            int next = json.getInt("appid");
            if(id != next)
            { 
                EventManager.addEvent(EventManager.GAME_OnGameChange, next, id);
                this.id = next;
            }
        }
        if(json.has("version"))
        {
            int next = json.getInt("version");
            if(version != next)
            {
                EventManager.addEvent(EventManager.GAME_OnVersionChange, next, version);
                this.version = next;
            }
        }
        if(json.has("steamid"))
        {
            long next = json.getLong("steamid");
            if(steamId != next)
            {
                this.steamId = next;
            }
        }
    }

    @Override
    public String toString()
    {
        return "Game{" + "name=" + name + ", id=" + id + ", version=" + version + ", steamId=" + steamId + '}';
    }
}
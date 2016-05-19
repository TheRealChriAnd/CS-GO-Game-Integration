/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import org.json.JSONObject;

/**
 *
 * @author Christoffer Andersson & Herman Söderlund
 */
public final class MapInfo
{
    private Gamemode gamemode;
    private Map name;
    private MapState state;
    private int round;
    private TeamInfo teamCT;
    private TeamInfo teamT;

    public MapInfo()
    {
        this.gamemode = Gamemode.Unknown;
        this.name = Map.Unknown;
        this.state = MapState.Unknown;
        this.teamCT = new TeamInfo();
        this.teamT = new TeamInfo();
    }
    
    public MapInfo(JSONObject json)
    {
        this();
        this.updateInformation(json);
    }

    public MapInfo(Gamemode gamemode, Map name, MapState state, int round, TeamInfo teamCT, TeamInfo teamT)
    {
        this.gamemode = gamemode;
        this.name = name;
        this.state = state;
        this.round = round;
        this.teamCT = teamCT;
        this.teamT = teamT;
    }

    public Gamemode getGamemode()
    {
        return gamemode;
    }

    public Map getName()
    {
        return name;
    }

    public MapState getState()
    {
        return state;
    }

    public int getRound()
    {
        return round;
    }

    public TeamInfo getTeamCT()
    {
        return teamCT;
    }

    public TeamInfo getTeamT()
    {
        return teamT;
    }
    
    public void updateInformation(JSONObject json)
    {
        if(json.has("mode"))
        {
            Gamemode next = Gamemode.getMode(json.getString("mode"));
            if(gamemode != next)
            {
                EventManager.addEvent(EventManager.MAP_OnGamemodeChange, next, gamemode);
                this.gamemode = next;
            }
        }
        if(json.has("name"))
        {
            Map next = Map.getType(json.getString("name"));
            if(name != next)
            {
                EventManager.addEvent(EventManager.MAP_OnMapChange, next, name);
                this.name = next;
            }
        }
        if(json.has("phase"))
        {
            MapState next = MapState.getState(json.getString("phase"));
            if(state != next)
            {
                EventManager.addEvent(EventManager.MAP_OnStateChange, next, state);
                this.state = next;
            }
        }
        if(json.has("round"))
        {
            int next = json.getInt("round");
            if(round != next)
            {
                EventManager.addEvent(EventManager.MAP_OnRoundChange, next, round);
                this.round = next;
            }
        }
        if(json.has("team_ct"))
        {
            this.teamCT.updateInformation(json.getJSONObject("team_ct"), TeamType.CounterTerrorist);
        }
        if(json.has("team_t"))
        {
            this.teamT.updateInformation(json.getJSONObject("team_t"), TeamType.Terrorist);
        }
    }

    @Override
    public String toString()
    {
        return "Map{" + "gamemode=" + gamemode + ", name=" + name + ", state=" + state + ", round=" + round + ", teamCT=" + teamCT + ", teamT=" + teamT + '}';
    }
}
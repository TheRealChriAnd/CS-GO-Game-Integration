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
public final class RoundInfo
{
    private RoundState state;
    private TeamType winningTeam;
    private BombState bombstate;

    public RoundInfo()
    {
        this.state = RoundState.Unknown;
        this.winningTeam = TeamType.Unknown;
        this.bombstate = BombState.Unknown;
    }

    public RoundInfo(JSONObject json)
    {
        this.updateInformation(json);
    }
    
    public RoundInfo(RoundState state, TeamType winningTeam, BombState bombstate)
    {
        this.state = state;
        this.winningTeam = winningTeam;
        this.bombstate = bombstate;
    }

    public RoundState getState()
    {
        return state;
    }

    public TeamType getWinningTeam()
    {
        return winningTeam;
    }

    public BombState getBombstate()
    {
        return bombstate;
    }
    
    public void updateInformation(JSONObject json)
    {
        if(json.has("phase"))
        {
            RoundState next = RoundState.getState(json.getString("phase"));
            if(state != next)
            {
                EventManager.addEvent(EventManager.MAP_OnGamemodeChange, next, state);
                this.state = next;
            }
        }
        if(json.has("win_team"))
        {
            TeamType next = TeamType.getType(json.getString("win_team"));
            if(winningTeam != next)
            {
                EventManager.addEvent(EventManager.MAP_OnGamemodeChange, next, winningTeam);
                this.winningTeam = next;
            }
        }
        if(json.has("bomb"))
        {
            BombState next = BombState.getState(json.getString("bomb"));
            if(bombstate != next)
            {
                EventManager.addEvent(EventManager.MAP_OnGamemodeChange, next, bombstate);
                this.bombstate = next;
            }
        } 
    }

    @Override
    public String toString()
    {
        return "Round{" + "state=" + state + ", winningTeam=" + winningTeam + ", bombstate=" + bombstate + '}';
    }
}
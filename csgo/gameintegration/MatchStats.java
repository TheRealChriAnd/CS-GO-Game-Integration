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
public final class MatchStats
{
    private int kills;
    private int assists;
    private int deaths;
    private int mvps;
    private int score;

    public MatchStats()
    {
        
    }
    
    public MatchStats(JSONObject json)
    {
        this.updateInformation(json);
    }

    public MatchStats(int kills, int assists, int deaths, int mvps, int score)
    {
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.mvps = mvps;
        this.score = score;
    }

    public int getKills()
    {
        return kills;
    }

    public int getAssists()
    {
        return assists;
    }

    public int getDeaths()
    {
        return deaths;
    }

    public int getMvps()
    {
        return mvps;
    }

    public int getScore()
    {
        return score;
    }
    
    public void updateInformation(JSONObject json)
    {
        if(json.has("kills"))
        {
            int next = json.getInt("kills");
            if(kills != next)
            {
                EventManager.addEvent(EventManager.PLAYER_ALL_OnKillsChange, next, kills);
                this.kills = next;
            }
        }
        if(json.has("assists"))
        {
            int next = json.getInt("assists");
            if(assists != next)
            {
                EventManager.addEvent(EventManager.PLAYER_ALL_OnAssisstsChange, next, assists);
                if(next > assists)
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnAssisst, next, assists);
                }
                this.assists = next;
            }
        }
        if(json.has("deaths"))
        {
            int next = json.getInt("deaths");
            if(deaths != next)
            {
                EventManager.addEvent(EventManager.PLAYER_ALL_OnDeathsChange, next, deaths);
                if(next > deaths)
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnDeath, next, deaths);
                }
                this.deaths = next;
            }
        }
        if(json.has("mvps"))
        {
            int next = json.getInt("mvps");
            if(mvps != next)
            {
                EventManager.addEvent(EventManager.PLAYER_ALL_OnMVPsChange, next, mvps);
                if(mvps != next)
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnMVP, next, mvps);
                }
                this.mvps = next;
            }
        }
        if(json.has("score"))
        {
            int next = json.getInt("score");
            if(score != next)
            {
                EventManager.addEvent(EventManager.PLAYER_ALL_OnScoreChange, next, score);
                if(score != next)
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnScore, next, score);
                }
                this.score = next;
            }
        } 
    }

    @Override
    public String toString()
    {
        return "MatchStats{" + "kills=" + kills + ", assists=" + assists + ", deaths=" + deaths + ", mvps=" + mvps + ", score=" + score + '}';
    }
}
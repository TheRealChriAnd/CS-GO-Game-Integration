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
public final class TeamInfo
{
    private int score;
    private String name;
    private TeamType teamType;

    public TeamInfo()
    {
        this.teamType = TeamType.Unknown;
        this.name = "";
    }
    
    public TeamInfo(JSONObject json, TeamType teamType)
    {
        this();
        this.updateInformation(json, teamType);
    }

    public TeamInfo(int score, String name, TeamType teamType)
    {
        this();
        this.score = score;
        this.name = name;
        this.teamType = teamType;
    }

    public int getScore()
    {
        return score;
    }

    public String getName()
    {
        return name;
    }
    
    public void updateInformation(JSONObject json, TeamType teamType)
    {
        if(json.has("score"))
        {
            int next = json.getInt("score");
            if(score != next)
            {
                EventManager.addEvent(EventManager.TEAM_OnScoreChange, next, score);
                this.score = next;
            }
        }
        if(json.has("name"))
        {
            String next = json.getString("name");
            if(!name.equals(next))
            {
                EventManager.addEvent(EventManager.TEAM_OnNameChange, next, name);
                this.name = next;
            }
        }
        if(this.teamType != teamType)
        {
            EventManager.addEvent(EventManager.TEAM_OnNameChange, teamType, this.teamType);
            this.teamType = teamType;
        }
    }

    @Override
    public String toString()
    {
        return "Team{" + "score=" + score + ", name=" + name + ", teamType=" + teamType + '}';
    }
}

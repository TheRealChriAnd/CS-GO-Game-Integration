/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Christoffer Andersson
 */
public final class PlayerInfo
{
    private long steamId;
    private String clan;
    private String name;
    private TeamType team;
    private ActivityState activityState;
    private RoundStats roundStats;
    private ArrayList<WeaponInfo> weapons;
    private MatchStats matchStats;

    public PlayerInfo()
    {
        this.team = TeamType.Unknown;
        this.activityState = ActivityState.Unknown;
        this.roundStats = new RoundStats();
        this.weapons = new ArrayList<>();
        this.matchStats = new MatchStats();
        this.clan = "";
        this.name = "";
    }
    
    public PlayerInfo(JSONObject json, boolean local)
    {
        this();
        this.updateInformation(json, local);
    }
    
    public PlayerInfo(JSONObject json, long steamId, boolean local)
    {
        this();
        this.updateInformation(json, steamId, local);
    }

    public PlayerInfo(long steamId, String clan, String name, TeamType team, ActivityState activityState, RoundStats roundStats, ArrayList<WeaponInfo> weapons, MatchStats matchStats)
    {
        this.steamId = steamId;
        this.clan = clan;
        this.name = name;
        this.team = team;
        this.activityState = activityState;
        this.roundStats = roundStats;
        this.weapons = weapons;
        this.matchStats = matchStats;
    }

    public long getSteamId()
    {
        return steamId;
    }

    public String getClan()
    {
        return clan;
    }

    public String getName()
    {
        return name;
    }

    public TeamType getTeam()
    {
        return team;
    }

    public ActivityState getActivityState()
    {
        return activityState;
    }

    public RoundStats getRoundStats()
    {
        return roundStats;
    }

    public ArrayList<WeaponInfo> getWeapons()
    {
        return weapons;
    }

    public MatchStats getMatchStats()
    {
        return matchStats;
    }
    
    public WeaponInfo getActiveWeapon()
    {
        for(WeaponInfo weaponInfo : weapons)
        {
            if(weaponInfo.getState() == WeaponState.Active)
            {
                return weaponInfo;
            }
        }
        return null;
    }
    
    public void updateInformation(JSONObject json, boolean local)
    {
        if(json.has("steamid"))
        {
            this.updateInformation(json, json.getLong("steamid"), local);
        }
        else
        {
            this.updateInformation(json, -1, local);
        }
    }
    
    public void updateInformation(JSONObject json, long steamId, boolean local)
    {
        if(this.steamId != steamId)
        {
            this.steamId = steamId;
        }
        if(json.has("clan"))
        {
            String next = json.getString("clan");
            if(!clan.equals(next))
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnClanChange, next, clan);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnClanChange, next, clan);
                }
                this.clan = next;
            }
        }
        if(json.has("name"))
        {
            String next = json.getString("name");
            if(!name.equals(next))
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnNameChange, next, name);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnNameChange, next, name);
                }
                this.name = next;
            }
        }
        if(json.has("team"))
        {
            TeamType next = TeamType.getType(json.getString("team"));
            if(team != next)
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnTeamChange, next, team);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnTeamChange, next, team);
                }
                this.team = next;
            }
        }
        if(json.has("activity"))
        {
            ActivityState next = ActivityState.getState(json.getString("activity"));
            if(activityState != next)
            {
                EventManager.addEvent(EventManager.PLAYER_OnActivityChange, next, activityState);
                this.activityState = next;
            }
        }
        if(json.has("state"))
        {
            this.roundStats.updateInformation(json.getJSONObject("state"), local);
        }
        if(json.has("weapons"))
        {
            ArrayList<Weapon> newWeapons = new ArrayList<>();
            JSONObject weaponsObject = json.getJSONObject("weapons");
            for(int i = 0; i < 10; i++)
            {
                if(weaponsObject.has("weapon_" + i))
                {
                    JSONObject weaponObject = weaponsObject.getJSONObject("weapon_" + i);
                    newWeapons.add(Weapon.getType(weaponObject.getString("name")));
                }
            }
            WeaponInfo currentWeaponInfo = getActiveWeapon();
            Weapon currentWeapon = currentWeaponInfo == null ? Weapon.Unknown : currentWeaponInfo.getWeapon();
            if(newWeapons.size() != weapons.size())
            {
                EventManager.addEvent(EventManager.PLAYER_OnWeaponsChange, newWeapons.size(), weapons.size());
                for(int i = 0; i < weapons.size(); i++)
                {
                    if(!newWeapons.contains(weapons.get(i).getWeapon()))
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnWeaponDroped, null, weapons.get(i).getWeapon());
                        this.weapons.remove(i);
                    }
                }
                for(int i = 0; i < newWeapons.size(); i++)
                {
                    boolean exist = false;
                    for (WeaponInfo weapon : weapons)
                    {
                        if (weapon.getWeapon() == newWeapons.get(i))
                        {
                            exist = true;
                            break;
                        }
                    }
                    if(!exist)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnWeaponPickedUp, newWeapons.get(i), null);
                        this.weapons.add(new WeaponInfo(weaponsObject.getJSONObject("weapon_" + i), newWeapons.get(i)));
                    }
                }  
            }
            for (WeaponInfo weapon : weapons)
            {
                weapon.updateInformation(weaponsObject.getJSONObject("weapon_" + newWeapons.indexOf(weapon.getWeapon())), weapon.getWeapon());
            }
            WeaponInfo newWeaponInfo = getActiveWeapon();
            Weapon newWeapon = newWeaponInfo == null ? Weapon.Unknown : newWeaponInfo.getWeapon();
            if(currentWeapon != newWeapon)
            {
                EventManager.addEvent(EventManager.PLAYER_OnWeaponChange, newWeapon, currentWeapon);
            }
        }
        if(json.has("match_stats"))
        {
            this.matchStats.updateInformation(json.getJSONObject("match_stats"));
        }
    }

    @Override
    public String toString()
    {
        return "Player{" + "steamId=" + steamId + ", clan=" + clan + ", name=" + name + ", team=" + team + ", activityState=" + activityState + ", roundStats=" + roundStats + ", weapons=" + weapons + ", matchStats=" + matchStats + '}';
    }
}
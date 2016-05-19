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
public final class RoundStats
{
    private int health;
    private int armor;
    private boolean helmet;
    private int flashed;
    private int smoked;
    private int burning;
    private int money;
    private int kills;
    private int headshots;

    public RoundStats()
    {
        
    }
    
    public RoundStats(JSONObject json, boolean local)
    {
        this.updateInformation(json, local);
    }

    public RoundStats(int health, int armor, boolean helmet, int flashed, int smoked, int burning, int money, int kills, int headshots)
    {
        this.health = health;
        this.armor = armor;
        this.helmet = helmet;
        this.flashed = flashed;
        this.smoked = smoked;
        this.burning = burning;
        this.money = money;
        this.kills = kills;
        this.headshots = headshots;
    }

    public int getHealth()
    {
        return health;
    }

    public int getArmor()
    {
        return armor;
    }

    public boolean hasHelmet()
    {
        return helmet;
    }

    public int getFlashed()
    {
        return flashed;
    }

    public int getSmoked()
    {
        return smoked;
    }

    public int getBurning()
    {
        return burning;
    }

    public int getMoney()
    {
        return money;
    }

    public int getKills()
    {
        return kills;
    }

    public int getHeadshots()
    {
        return headshots;
    }
    
    public void updateInformation(JSONObject json, boolean local)
    {
        if(json.has("health"))
        {
            int next = json.getInt("health");
            if(health != next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnHealthChange, next, health);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnHealthChange, next, health);
                }
                if(health > next)
                { 
                    if(local)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnDamaged, next, health);
                    }
                    else
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnDamaged, next, health);
                    }
                }
                this.health = next;
            }
        }
        if(json.has("armor"))
        {
            int next = json.getInt("armor");
            if(armor != next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnArmorChange, next, armor);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnArmorChange, next, armor);
                }
                if(armor > next)
                { 
                    if(local)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnArmorDamaged, next, armor);
                    }
                    else
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnArmorDamaged, next, armor);
                    }
                }
                this.armor = next;
            }
        }
        if(json.has("helmet"))
        {
            boolean next = json.getBoolean("helmet");
            if(helmet != next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnHelmetChange, next, helmet);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnHelmetChange, next, helmet);
                }
                if(!next)
                { 
                    if(local)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnHelmetDamaged, next, helmet);
                    }
                    else
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnHelmetDamaged, next, helmet);
                    }
                }
                this.helmet = next;
            }
        }
        if(json.has("flashed"))
        {
            int next = json.getInt("flashed");
            if(next > flashed && next > 30)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnFlashed, next, flashed); 
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnFlashed, next, flashed); 
                }
            }
            else if(flashed > 0 && next == 0)
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnNotFlashed, next, flashed);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnNotFlashed, next, flashed);
                }
            }
            this.flashed = next;
        }
        if(json.has("smoked"))
        {
            int next = json.getInt("smoked");
            if(next > smoked && next > 30)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnSmoked, next, smoked);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnSmoked, next, smoked);
                } 
            }
            else if(smoked > 0 && next == 0)
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnNotSmoked, next, smoked);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnNotSmoked, next, smoked);
                } 
            }
            this.smoked = next;
        }
        if(json.has("burning"))
        {
            int next = json.getInt("burning");
            if(next > burning)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnBurned, next, burning);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnBurned, next, burning);
                }    
            }
            else if(burning > 0 && next == 0)
            {
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnNotBurning, next, burning);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnNotBurning, next, burning);
                }
            }
            this.burning = next;
        }
        if(json.has("money"))
        {
            int next = json.getInt("money");
            if(money != next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnMoneyChange, next, money);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnMoneyChange, next, money);
                }
                if(money > next)
                { 
                    if(local)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnMoneySpent, next, money);
                    }
                    else
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnMoneySpent, next, money);
                    }
                }
                else if(money < next)
                { 
                    if(local)
                    {
                        EventManager.addEvent(EventManager.PLAYER_OnMoneyReceived, next, money);
                    }
                    else
                    {
                        EventManager.addEvent(EventManager.PLAYER_ALL_OnMoneyReceived, next, money);
                    } 
                }
                this.money = next;
            }
        }
        if(json.has("round_kills"))
        {
            int next = json.getInt("round_kills");
            if(kills < next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnKill, next, kills);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnKill, next, kills);
                }
                this.kills = next;
            }
            else if(kills > next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnTeamKill, next, kills);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnTeamKill, next, kills);
                }
                this.kills = next;
            }
        }
        if(json.has("round_killhs"))
        {
            int next = json.getInt("round_killhs");
            if(headshots != next)
            { 
                if(local)
                {
                    EventManager.addEvent(EventManager.PLAYER_OnHeadshot, next, headshots);
                }
                else
                {
                    EventManager.addEvent(EventManager.PLAYER_ALL_OnHeadshot, next, headshots);
                }
                this.headshots = next;
            }
        }
    }

    @Override
    public String toString()
    {
        return "RoundStats{" + "health=" + health + ", armor=" + armor + ", helmet=" + helmet + ", flashed=" + flashed + ", smoked=" + smoked + ", burning=" + burning + ", money=" + money + ", kills=" + kills + ", headshots=" + headshots + '}';
    }
}
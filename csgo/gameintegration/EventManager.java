/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author Christoffer Andersson
 */
public abstract class EventManager
{
    public static final int GAME_OnGameChange                       = 0;
    public static final int GAME_OnVersionChange                    = 1;
    
    public static final int MAP_OnGamemodeChange                    = 2;
    public static final int MAP_OnMapChange                         = 3;
    public static final int MAP_OnLive                              = 4;
    public static final int MAP_OnWarmup                            = 5;
    public static final int MAP_OnStateChange                       = 6;
    public static final int MAP_OnRoundChange                       = 7;
    
    public static final int TEAM_OnScoreChange                      = 8;
    public static final int TEAM_OnNameChange                       = 9;
    public static final int TEAM_OnTeamChange                       = 10; 
    
    public static final int PLAYER_ALL_OnKill                       = 11;
    public static final int PLAYER_ALL_OnTeamKill                   = 12;
    public static final int PLAYER_ALL_OnHeadshot                   = 13;
    public static final int PLAYER_ALL_OnAssisst                    = 14;
    public static final int PLAYER_ALL_OnMVP                        = 15;
    public static final int PLAYER_ALL_OnScore                      = 16;
    public static final int PLAYER_ALL_OnDeath                      = 17;
    public static final int PLAYER_ALL_OnSmoked                     = 18;
    public static final int PLAYER_ALL_OnFlashed                    = 19;
    public static final int PLAYER_ALL_OnBurned                     = 20;
    public static final int PLAYER_ALL_OnNotSmoked                  = 21;
    public static final int PLAYER_ALL_OnNotFlashed                 = 22;
    public static final int PLAYER_ALL_OnNotBurning                 = 23;
    public static final int PLAYER_ALL_OnDamaged                    = 24;
    public static final int PLAYER_ALL_OnArmorDamaged               = 25;
    public static final int PLAYER_ALL_OnHelmetDamaged              = 26;
    public static final int PLAYER_ALL_OnMoneySpent                 = 27;
    public static final int PLAYER_ALL_OnMoneyReceived              = 28;
    public static final int PLAYER_ALL_OnClanChange                 = 29;
    public static final int PLAYER_ALL_OnNameChange                 = 30;
    public static final int PLAYER_ALL_OnTeamChange                 = 31;
    public static final int PLAYER_ALL_OnArmorChange                = 32;
    public static final int PLAYER_ALL_OnHelmetChange               = 33;
    public static final int PLAYER_ALL_OnMoneyChange                = 34;
    public static final int PLAYER_ALL_OnHealthChange               = 35;
    public static final int PLAYER_ALL_OnKillsChange                = 36;
    public static final int PLAYER_ALL_OnAssisstsChange             = 37;
    public static final int PLAYER_ALL_OnDeathsChange               = 38;
    public static final int PLAYER_ALL_OnMVPsChange                 = 39;
    public static final int PLAYER_ALL_OnScoreChange                = 40;
    public static final int PLAYER_ALL_OnJoined                     = 41;
    public static final int PLAYER_ALL_OnLeft                       = 42;
    public static final int PLAYER_ALL_OnPalyersChange              = 43;
    
    public static final int PLAYER_OnKill                           = 44;
    public static final int PLAYER_OnTeamKill                       = 45;
    public static final int PLAYER_OnHeadshot                       = 46;
    public static final int PLAYER_OnAssisst                        = 47;
    public static final int PLAYER_OnMVP                            = 48;
    public static final int PLAYER_OnScore                          = 49;
    public static final int PLAYER_OnDeath                          = 50;
    public static final int PLAYER_OnSmoked                         = 51;
    public static final int PLAYER_OnFlashed                        = 52;
    public static final int PLAYER_OnBurned                         = 53;
    public static final int PLAYER_OnNotSmoked                      = 54;
    public static final int PLAYER_OnNotFlashed                     = 55;
    public static final int PLAYER_OnNotBurning                     = 56;
    public static final int PLAYER_OnDamaged                        = 57;
    public static final int PLAYER_OnArmorDamaged                   = 58;
    public static final int PLAYER_OnHelmetDamaged                  = 59;
    public static final int PLAYER_OnMoneySpent                     = 60;
    public static final int PLAYER_OnMoneyReceived                  = 61;
    public static final int PLAYER_OnClanChange                     = 62;
    public static final int PLAYER_OnNameChange                     = 63;
    public static final int PLAYER_OnTeamChange                     = 64;
    public static final int PLAYER_OnArmorChange                    = 65;
    public static final int PLAYER_OnHelmetChange                   = 66;
    public static final int PLAYER_OnMoneyChange                    = 67;
    public static final int PLAYER_OnHealthChange                   = 68;
    public static final int PLAYER_OnKillsChange                    = 69;
    public static final int PLAYER_OnAssisstsChange                 = 70;
    public static final int PLAYER_OnDeathsChange                   = 71;
    public static final int PLAYER_OnMVPsChange                     = 72;
    public static final int PLAYER_OnScoreChange                    = 73;
    
    public static final int PLAYER_OnActivityChange                 = 74;
    public static final int PLAYER_OnWeaponDroped                   = 75;
    public static final int PLAYER_OnWeaponPickedUp                 = 76;
    public static final int PLAYER_OnGrenadeThrown                  = 77;
    public static final int PLAYER_OnGrenadePickedUp                = 78;
    public static final int PLAYER_OnWeaponChange                   = 79;
    public static final int PLAYER_OnWeaponsChange                  = 80;
 
    public static final int WEAPON_OnFired                          = 81;
    public static final int WEAPON_OnReloaded                       = 82;
    public static final int WEAPON_OnActive                         = 83;
    public static final int WEAPON_OnHolstered                      = 84;
    public static final int WEAPON_OnReloading                      = 85;
    public static final int WEAPON_OnSkinNameChange                 = 86;
    public static final int WEAPON_OnAmmoClipChange                 = 87;
    public static final int WEAPON_OnAmmoReservChange               = 88;
    public static final int WEAPON_OnStateChange                    = 89;
    
    private static final ArrayList<EventRules> listeners = new ArrayList<>();
    private static final ArrayList<CSGOEvent> eventBuffer = new ArrayList<>();
    private static final ArrayList<String> eventNames = new ArrayList<>();
    
    private EventManager()
    {
        
    }
    
    public static void init()
    {
        for(Field field : EventManager.class.getDeclaredFields())
        {
            String name = field.getName();
            if(name.startsWith("G") || name.startsWith("M") || name.startsWith("T") || name.startsWith("P") || name.startsWith("W"))
            {
                EventManager.eventNames.add(name);
            }
        }
    }
    
    public static ArrayList<String> getEventNames()
    {
        return eventNames;
    }
    
    public static String getEventName(int event)
    {
        return eventNames.get(event);
    }
    
    public static void addListener(ICSGOEvent listener, Integer... events)
    {
        for(int i = 0; i < listeners.size(); i++)
        {
            if(listeners.get(i).getListener() == listener)
            {
                return;
            }
        }
        EventManager.listeners.add(new EventRules(events, listener));
    }
    
    public static void removeListener(ICSGOEvent listener)
    {
        for(int i = 0; i < listeners.size(); i++)
        {
            if(listeners.get(i).getListener() == listener)
            {
                EventManager.listeners.remove(i);
                return;
            }
        }
    }
    
    public static void subscribe(ICSGOEvent listener, Integer... events)
    {
        for(int i = 0; i < listeners.size(); i++)
        {
            if(listeners.get(i).getListener() == listener)
            {
                EventManager.listeners.get(i).addEvents(events);
                return;
            }
        }
    }
    
    public static void unsubscribe(ICSGOEvent listener, Integer... events)
    {
        for(int i = 0; i < listeners.size(); i++)
        {
            if(listeners.get(i).getListener() == listener)
            {
                EventManager.listeners.get(i).removeEvents(events);
                return;
            }
        }
    }
    
    public static Integer[] getEvents(ICSGOEvent listener)
    {
        for(int i = 0; i < listeners.size(); i++)
        {
            if(listeners.get(i).getListener() == listener)
            {
                return listeners.get(i).getEvents();
            }
        }
        return null;
    }
    
    public static void addEvent(Integer event, Object newValue, Object oldValue, Object... userData)
    {
        EventManager.eventBuffer.add(new CSGOEvent(event, newValue, oldValue, userData));
    }
    
    public static void fireEvents()
    {
        for(CSGOEvent event : eventBuffer)
        {
            for (EventRules listener : listeners)
            {
                if(listener != null)
                {
                    listener.onEventFired(event);
                }
            }
        }
        EventManager.eventBuffer.clear();
    }
}
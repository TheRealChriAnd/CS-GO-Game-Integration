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
public final class WeaponInfo
{
    private Weapon weapon;
    private String skinName;
    private WeaponType type;
    private int ammoClip;
    private int ammoClipMax;
    private int ammoReserve;
    private WeaponState state;

    public WeaponInfo()
    {
        this.type = WeaponType.Unknown;
        this.state = WeaponState.Unknown;
        this.skinName = "";
    }
    
    public WeaponInfo(JSONObject json, Weapon weapon)
    {
        this();
        this.updateInformation(json, weapon);
    }

    public WeaponInfo(Weapon weapon, String skinName, WeaponType type, int ammoClip, int ammoClipMax, int ammoReserve, WeaponState state)
    {
        this.weapon = weapon;
        this.skinName = skinName;
        this.type = type;
        this.ammoClip = ammoClip;
        this.ammoClipMax = ammoClipMax;
        this.ammoReserve = ammoReserve;
        this.state = state;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public String getSkinName()
    {
        return skinName;
    }

    public WeaponType getType()
    {
        return type;
    }

    public int getAmmoClip()
    {
        return ammoClip;
    }

    public int getAmmoClipMax()
    {
        return ammoClipMax;
    }

    public int getAmmoReserve()
    {
        return ammoReserve;
    }

    public WeaponState getState()
    {
        return state;
    }
    
    public void updateInformation(JSONObject json, Weapon weapon)
    {
        this.weapon = weapon;
        if(json.has("paintkit"))
        {
            String next = json.getString("paintkit");
            if(!skinName.equals(next))
            {
                EventManager.addEvent(EventManager.WEAPON_OnSkinNameChange, next, skinName);
                this.skinName = next;
            }
        }
        if(json.has("type"))
        {
            WeaponType next = WeaponType.getType(json.getString("type"));
            if(type != next)
            {
                this.type = next;
            }
        }
        if(json.has("ammo_clip"))
        {
            int next = json.getInt("ammo_clip");
            if(ammoClip != next)
            {
                EventManager.addEvent(EventManager.WEAPON_OnAmmoClipChange, next, ammoClip);
                if(ammoClip > next)
                {
                    EventManager.addEvent(EventManager.WEAPON_OnFired, next, ammoClip);
                }
                else if(ammoClip < next)
                {
                    EventManager.addEvent(EventManager.WEAPON_OnReloaded, next, ammoClip);
                }
                this.ammoClip = next;
            }
        }
        if(json.has("ammo_clip_max"))
        {
            int next = json.getInt("ammo_clip_max");
            if(ammoClipMax != next)
            {
                this.ammoClipMax = next;
            }
        }
        if(json.has("ammo_reserve"))
        {
            int next = json.getInt("ammo_reserve");
            if(ammoReserve != next)
            {
                EventManager.addEvent(EventManager.WEAPON_OnAmmoReservChange, next, ammoReserve);
                this.ammoReserve = next;
            }
        }
        if(json.has("state"))
        {
            WeaponState next = WeaponState.getState(json.getString("state"));
            if(state != next)
            {
                EventManager.addEvent(EventManager.WEAPON_OnStateChange, next, state);
                if(next == WeaponState.Active)
                {
                    EventManager.addEvent(EventManager.WEAPON_OnActive, next, state);
                }
                else if(next == WeaponState.Holstered)
                {
                    EventManager.addEvent(EventManager.WEAPON_OnHolstered, next, state);
                }
                else if(next == WeaponState.Reloading)
                {
                    EventManager.addEvent(EventManager.WEAPON_OnReloading, next, state);
                }
                this.state = next;
            }
        }
    }

    @Override
    public String toString()
    {
        return "Weapon{" + "nam=" + weapon + ", skinName=" + skinName + ", type=" + type + ", ammoClip=" + ammoClip + ", ammoClipMax=" + ammoClipMax + ", ammoReserve=" + ammoReserve + ", state=" + state + '}';
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

/**
 *
 * @author Christoffer Andersson
 */
public enum WeaponState
{
    Unknown,
    Active,
    Holstered,
    Reloading;
    
    public String getName()
    {
        switch(this)
        {
            case Active : return "active";
            case Holstered : return "holstered";
            case Reloading : return "reloading";
            default : return "unknown";
        }
    }
    
    public static WeaponState getState(String name)
    {
        switch(name)
        {
            case "active" : return Active;
            case "holstered" : return Holstered;
            case "reloading" : return Reloading;
            default : return Unknown;
        }
    }
}
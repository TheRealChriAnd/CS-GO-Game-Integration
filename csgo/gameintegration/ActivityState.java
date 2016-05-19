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
public enum ActivityState
{
    Unknown,
    Menu,
    Playing,
    Console;
    
    public String getName()
    {
        switch(this)
        {
            case Menu : return "menu";
            case Playing : return "playing";
            case Console : return "console";
            default : return "unknown";
        }
    }
    
    public static ActivityState getState(String name)
    {
        switch(name)
        {
            case "menu" : return Menu;
            case "playing" : return Playing;
            case "console" : return Console;
            default : return Unknown;
        }
    }
}
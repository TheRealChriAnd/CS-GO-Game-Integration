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
public enum RoundState
{
    Unknown,
    Live,
    Freezetime,
    Over;
    
    public String getName()
    {
        switch(this)
        {
            case Live : return "live";
            case Freezetime : return "freezetime";
            case Over : return "over";
            default : return "unknown";
        }
    }
    
    public static RoundState getState(String name)
    {
        switch(name)
        {
            case "live" : return Live;
            case "freezetime" : return Freezetime;
            case "over" : return Over;
            default : return Unknown;
        }
    }
}
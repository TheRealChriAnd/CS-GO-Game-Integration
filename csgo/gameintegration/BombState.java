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
public enum BombState
{
    Unknown,
    Planted,
    Exploded,
    Defused;
    
    public String getName()
    {
        switch(this)
        {
            case Planted : return "planted";
            case Exploded : return "exploded";
            case Defused : return "defused";
            default : return "unknown";
        }
    }
    
    public static BombState getState(String name)
    {
        switch(name)
        {
            case "planted" : return Planted;
            case "exploded" : return Exploded;
            case "defused" : return Defused;
            default : return Unknown;
        }
    }
}
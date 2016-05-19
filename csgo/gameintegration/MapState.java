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
public enum MapState
{
    Unknown,
    Live,
    Warmup;
    
    public String getName()
    {
        switch(this)
        {
            case Live : return "live";
            case Warmup : return "warmup";
            default : return "unknown";
        }
    }
    
    public static MapState getState(String name)
    {
        switch(name)
        {
            case "live" : return Live;
            case "warmup" : return Warmup;
            default : return Unknown;
        }
    }
}
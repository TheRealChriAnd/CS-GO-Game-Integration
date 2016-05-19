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
public enum TeamType
{
    Unknown,
    Terrorist,
    CounterTerrorist;
    
    public String getName()
    {
        switch(this)
        {
            case Terrorist : return "T";
            case CounterTerrorist : return "CT";
            default : return "unknown";
        }
    }
    
    public static TeamType getType(String name)
    {
        switch(name)
        {
            case "T" : return Terrorist;
            case "CT" : return CounterTerrorist;
            default : return Unknown;
        }
    }
}
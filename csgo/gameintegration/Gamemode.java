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
public enum Gamemode
{
    Unknown,
    Deathmatch,
    ArmsRace,
    Demolition,
    Casual,
    Competitive;
    
    public String getName()
    {
        switch(this)
        {
            case Deathmatch : return "deathmatch";
            case ArmsRace : return "gungameprogressive";
            case Demolition : return "gungametrbomb";
            case Casual : return "casual";
            case Competitive : return "competitive";
            default : return "unknown";
        }
    }
    
    public static Gamemode getMode(String name)
    {
        switch(name)
        {
            case "deathmatch" : return Deathmatch;
            case "gungameprogressive" : return ArmsRace;
            case "gungametrbomb" : return Demolition;
            case "casual" : return Casual;
            case "competitive" : return Competitive;
            default : return Unknown;
        }
    }
}
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
public enum Map
{
    Unknown,
    Monastery,
    Shoots,
    Baggage,
    Lake,
    Stmarc,
    Sugarcane,
    Bank,
    Safehouse,
    Shortdust,
    Shorttrain,
    Dust2,
    Train,
    Mirage,
    Nuke,
    Cobblestone,
    Overpass,
    Cache,
    Inferno,
    Aztec,
    Dust,
    Vertigo,
    Office,
    Italy,
    Assault,
    Milita;
    
    public String getName()
    {
        switch(this)
        {
            case Monastery : return "ar_monastery";
            case Shoots : return "ar_shoots";
            case Baggage : return "ar_baggage";
            case Lake : return "de_lake";
            case Stmarc : return "de_stmarc";
            case Sugarcane : return "de_sugarcane";
            case Bank : return "de_bank";
            case Safehouse : return "de_safehouse";
            case Shortdust : return "de_shortdust";
            case Shorttrain : return "de_shorttrain";
            case Dust2 : return "de_dust2";
            case Train : return "de_train";
            case Mirage : return "de_mirage";
            case Nuke : return "de_nuke";
            case Cobblestone : return "de_cbble";
            case Overpass : return "de_overpass";
            case Cache : return "de_cache";
            case Inferno : return "de_inferno";
            case Aztec : return "de_aztec";
            case Dust : return "de_dust";
            case Vertigo : return "de_vertigo";
            case Office : return "cs_office";
            case Italy : return "cs_italy";
            case Assault : return "cs_assault";
            case Milita : return "cs_militia";             
            default : return "unknown";
        }
    }
    
    public static Map getType(String name)
    {
        switch(name)
        {
            case "ar_monastery" : return Monastery;
            case "ar_shoots" : return Shoots;
            case "ar_baggage" : return Baggage;
            case "de_lake" : return Lake;
            case "de_stmarc" : return Stmarc;
            case "de_sugarcane" : return Sugarcane;
            case "de_bank" : return Bank;
            case "de_safehouse" : return Safehouse;
            case "de_shortdust" : return Shortdust;
            case "de_shorttrain" : return Shorttrain;
            case "de_dust2" : return Dust2;
            case "de_train" : return Train;
            case "de_mirage" : return Mirage;
            case "de_nuke" : return Nuke;
            case "de_cbble" : return Cobblestone;
            case "de_overpass" : return Overpass;
            case "de_cache" : return Cache;
            case "de_inferno" : return Inferno;
            case "de_aztec" : return Aztec;
            case "de_dust" : return Dust;
            case "de_vertigo" : return Vertigo;
            case "cs_office" : return Office;
            case "cs_italy" : return Italy;
            case "cs_assault" : return Assault;
            case "cs_militia" : return Milita;
            default : return Unknown;
        }
    }
}
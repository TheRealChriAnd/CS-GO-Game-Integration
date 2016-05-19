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
public enum WeaponType
{
    Unknown,
    Knife,
    Pistol,
    SubmachineGun,
    Rifle,
    MachineGun,
    SniperRifle,
    Shotgun,
    Grenade,
    C4;
    
    public String getName()
    {
        switch(this)
        {
            case Knife : return "Knife";
            case Pistol : return "Pistol";
            case SubmachineGun : return "Submachine Gun";
            case Rifle : return "Rifle";
            case MachineGun : return "Machine Gun";
            case SniperRifle : return "SniperRifle";
            case Shotgun : return "Shotgun";
            case Grenade : return "Grenade";
            case C4 : return "C4";
            default : return "unknown";
        }
    }
    
    public static WeaponType getType(String name)
    {
        switch(name)
        {
            case "Knife" : return Knife;
            case "Pistol" : return Pistol;
            case "Submachine Gun" : return SubmachineGun;
            case "Rifle" : return Rifle;
            case "Machine Gun" : return MachineGun;
            case "SniperRifle" : return SniperRifle;
            case "Shotgun" : return Shotgun;
            case "Grenade" : return Grenade;
            case "C4" : return C4;
            default : return Unknown;
        }
    }
}
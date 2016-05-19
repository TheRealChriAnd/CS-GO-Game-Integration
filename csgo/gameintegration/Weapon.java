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
public enum Weapon
{
    Unknown,
    AK47,
    AUG,
    AWP,
    Bizon,
    C4,
    CZ75A,
    Deagle,
    Decoy,
    Elite,
    Famas,
    FiveSeven,
    Flashbang,
    G3SG1,
    Galilar,
    Glock,
    HEgrenade,
    Incendiarygrenade,
    P2000,
    Knife,
    M249,
    M4A4,
    M4A1S,
    MAC10,
    MAG7,
    Molotov,
    MP7,
    MP9,
    Negev,
    Nova,
    P250,
    P90,
    Sawedoff,
    Scar17,
    Scar20,
    Scout,
    SG553,
    SSG08,
    Smokegrenade,
    Taser,
    TEC9,
    UMP45,
    USP,
    USPS,
    XM1014,
    Revolver;
    
    public String getName()
    {
        switch(this)
        {
            case AK47 : return "weapon_ak47";
            case AUG : return "weapon_aug";
            case AWP : return "weapon_awp";
            case Bizon : return "weapon_bizon";
            case C4 : return "weapon_c4";
            case CZ75A : return "weapon_cz75a";
            case Deagle : return "weapon_deagle";
            case Decoy : return "weapon_decoy";
            case Elite : return "weapon_elite";
            case Famas : return "weapon_famas";
            case FiveSeven : return "weapon_fiveseven";
            case Flashbang : return "weapon_flashbang";
            case G3SG1 : return "weapon_g3sg1";
            case Galilar : return "weapon_galilar";
            case Glock : return "weapon_glock";
            case HEgrenade : return "weapon_hegrenade";
            case Incendiarygrenade : return "weapon_incgrenade";
            case P2000 : return "weapon_hkp2000";
            case Knife : return "weapon_knife";
            case M249 : return "weapon_m249";
            case M4A4 : return "weapon_m4a1";
            case M4A1S : return "weapon_m4a1_silencer";
            case MAC10 : return "weapon_mac10";
            case MAG7 : return "weapon_mag7";
            case Molotov : return "weapon_molotov";
            case MP7 : return "weapon_mp7";
            case MP9 : return "weapon_mp9";
            case Negev : return "weapon_negev";
            case Nova : return "weapon_nova";
            case P250 : return "weapon_p250";
            case P90 : return "weapon_p90";
            case Sawedoff : return "weapon_sawedoff";
            case Scar17 : return "weapon_scar17";
            case Scar20 : return "weapon_scar20";
            case Scout : return "weapon_scout";
            case SG553 : return "weapon_sg556";
            case SSG08 : return "weapon_ssg08";
            case Smokegrenade : return "weapon_smokegrenade";
            case Taser : return "weapon_taser";
            case TEC9 : return "weapon_tec9";
            case UMP45 : return "weapon_ump45";
            case USP : return "weapon_usp";
            case USPS : return "weapon_usp_silencer";
            case XM1014 : return "weapon_xm1014";
            case Revolver : return "weapon_revolver";
            default : return "unknown";
        }
    }
    
    public static Weapon getType(String name)
    {
        switch(name)
        {
            case "weapon_ak47" : return AK47;
            case "weapon_aug" : return AUG;
            case "weapon_awp" : return AWP;
            case "weapon_bizon" : return Bizon;
            case "weapon_c4" : return C4;
            case "weapon_cz75a" : return CZ75A;
            case "weapon_deagle" : return Deagle;
            case "weapon_decoy" : return Decoy;
            case "weapon_elite" : return Elite;
            case "weapon_famas" : return Famas;
            case "weapon_fiveseven" : return FiveSeven;
            case "weapon_flashbang" : return Flashbang;
            case "weapon_g3sg1" : return G3SG1;
            case "weapon_galilar" : return Galilar;
            case "weapon_glock" : return Glock;
            case "weapon_hegrenade" : return HEgrenade;
            case "weapon_incgrenade" : return Incendiarygrenade;
            case "weapon_hkp2000" : return P2000;
            case "weapon_knife" : return Knife;
            case "weapon_m249" : return M249;
            case "weapon_m4a1" : return M4A4;
            case "weapon_m4a1_silencer" : return M4A1S;
            case "weapon_mac10" : return MAC10;
            case "weapon_mag7" : return MAG7;
            case "weapon_molotov" : return Molotov;
            case "weapon_mp7" : return MP7;
            case "weapon_mp9" : return MP9;
            case "weapon_negev" : return Negev;
            case "weapon_nova" : return Nova;
            case "weapon_p250" : return P250;
            case "weapon_p90" : return P90;
            case "weapon_sawedoff" : return Sawedoff;
            case "weapon_scar17" : return Scar17;
            case "weapon_scar20" : return Scar20;
            case "weapon_scout" : return Scout;
            case "weapon_sg556" : return SG553;
            case "weapon_ssg08" : return SSG08;
            case "weapon_smokegrenade" : return Smokegrenade;
            case "weapon_taser" : return Taser;
            case "weapon_tec9" : return TEC9;
            case "weapon_ump45" : return UMP45;
            case "weapon_usp" : return USP;
            case "weapon_usp_silencer" : return USPS;
            case "weapon_xm1014" : return XM1014;
            case "weapon_revolver" : return Revolver;
            default : return Unknown;
        }
    }
}
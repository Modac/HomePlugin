package org.myftp.p_productions.HomePlugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

import static org.bukkit.ChatColor.WHITE;

// TODO: Locales
@SuppressWarnings("unused")
public class Messages {
	
	private final static String PREFIX_NOCOLOR = "[Home] ";
	private final static String PREFIX_COLOR = ChatColor.AQUA+PREFIX_NOCOLOR+ChatColor.RESET;
	
	static String getPrefix(){
		return getPrefix(true);
	}
	
	static String getPrefix(boolean color){
		return color?PREFIX_COLOR:PREFIX_NOCOLOR;
	}
	
	private final static String PLUGIN_ACTIVATED_DE = "Home Plugin aktiviert";
	private final static String PLUGIN_ACTIVATED_EN = "Home Plugin activated";
	
	static String getPluginActivated(boolean color){
		return (color?getPrefix():"")+PLUGIN_ACTIVATED_DE;
	}
	
	private final static String PLUGIN_DEACTIVATED_DE = "Home Plugin deaktiviert";
	private final static String PLUGIN_DEACTIVATED_EN = "Home Plugin disabled";
	
	static String getPluginDeactivated(boolean color){
		return (color?getPrefix():"")+PLUGIN_DEACTIVATED_DE;
	}
	
	
	private final static String LOCATION_COORDS_COLOR = GOLD+"%.2f "+WHITE+"/"+GOLD+" %.2f "+WHITE+"/"+GOLD+" %.2f "+WHITE+"in"+YELLOW+" %s";
	private final static String LOCATION_COORDS_NOCOLOR = "%.2f / %.2f / %.2f in %s";
	
	static String getLocationCoords(boolean color){
		return color?LOCATION_COORDS_COLOR:LOCATION_COORDS_NOCOLOR;
	}
	
	static String getLocationCoords(Location loc, boolean color){
		return String.format(getLocationCoords(color), loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName());
	}
	
	private final static String SETHOME_CMD_SUCCESS_DE = "Dein neues Zuhause:\n    " + LOCATION_COORDS_COLOR;
	private final static String SETHOME_CMD_SUCCESS_EN = "Your new home was set to:\n    " + LOCATION_COORDS_COLOR;
	
	static String getSetHomeCmdSuccess(){
		return getPrefix()+SETHOME_CMD_SUCCESS_DE;
	}
	
	private final static String SETHOME_CMD_FAIL_DE = "Konnte dein Zuhause nicht speichern. Sprich mit dem Owner.";
	private final static String SETHOME_CMD_FAIL_EN = "Failed to save your home. Speek with the owner.";
	
	static String getSetHomeCmdFail(){
		return getPrefix()+SETHOME_CMD_FAIL_DE;
	}
	
	private final static String SETHOME_CMD_NOPLAYERSENDER_DE = "Ich kann nur das Zuhause von Spielern setzen";
	private final static String SETHOME_CMD_NOPLAYERSENDER_EN = "Sorry, I can only set the homes of players";
	
	static String getSetHomeCmdNoPlayerSender(){
		return getPrefix(false)+SETHOME_CMD_NOPLAYERSENDER_DE;
	}
	
	private final static String GETHOME_CMD_MISSINGPLAYER_DE = "Keinen Spieler angegeben.\n    /%s <Spieler>";
	private final static String GETHOME_CMD_MISSINGPLAYER_EN = "I am missing a player here.\n    /%s <player>";
	
	static String getGetHomeCmdMissingPlayer(boolean color){
		return getPrefix(color)+GETHOME_CMD_MISSINGPLAYER_DE;
	}

	private final static String GETHOME_CMD_PLAYERNOTFOUND_DE = "Der Spieler wurde nicht gefunden.";
	private final static String GETHOME_CMD_PLAYERNOTFOUND_EN = "The given player wasn't found.";
	
	static String getGetHomeCmdPlayerNotFound(boolean color){
		return getPrefix(color)+GETHOME_CMD_PLAYERNOTFOUND_DE;
	}
	
	private final static String GETHOME_CMD_NOHOMEFOUND_RAW_EN = "I found no home for ";
	private final static String GETHOME_CMD_NOHOMEFOUND_4SELF_EN = GETHOME_CMD_NOHOMEFOUND_RAW_EN+"you.";
	
	private final static String GETHOME_CMD_NOHOMEFOUND_RAW_DE = "Kein Zuhause f\u00FCr %s gefunden.";
	private final static String GETHOME_CMD_NOHOMEFOUND_4SELF_DE = String.format(GETHOME_CMD_NOHOMEFOUND_RAW_DE, "dich");
	
	static String getGetHomeCmdNoHomeFound4Self(){
		return getPrefix()+GETHOME_CMD_NOHOMEFOUND_4SELF_DE;
	}
	
	private final static String GETHOME_CMD_NOHOMEFOUND_4OTHER_DE = String.format(GETHOME_CMD_NOHOMEFOUND_RAW_DE, "diesen Spieler");
	private final static String GETHOME_CMD_NOHOMEFOUND_4OTHER_EN = GETHOME_CMD_NOHOMEFOUND_RAW_EN+"that player.";
	
	static String getGetHomeCmdNoHomeFound4Other(boolean color){
		return getPrefix(color)+GETHOME_CMD_NOHOMEFOUND_4OTHER_DE;
	}
	
	private final static String GETHOME_CMD_SUCCESS_4SELF_DE = "Dein %d. Zuhause:\n  ";
	private final static String GETHOME_CMD_SUCCESS_4SELF_EN = "Your %d. home:\n  ";
	
	static String getGetHomeCmdSuccess4Self(){
		return getPrefix()+GETHOME_CMD_SUCCESS_4SELF_DE+getLocationCoords(true);
	}
	
	static String getGetHomeCmdSuccess4Self(int homeNumber, String playerName, Location loc){
		return getPrefix()+String.format(GETHOME_CMD_SUCCESS_4SELF_DE, homeNumber, playerName)+getLocationCoords(loc, true);
	}
	
	private final static String GETHOME_CMD_SUCCESS_4OTHER_DE = "%d. Zuhause von %s:\n  ";
	private final static String GETHOME_CMD_SUCCESS_4OTHER_EN = "%d. home of %s:\n  ";
	
	static String getGetHomeCmdSuccess4Other(boolean color){
		return getPrefix(color)+GETHOME_CMD_SUCCESS_4OTHER_DE+getLocationCoords(color);
	}
	
	static String getGetHomeCmdSuccess4Other(int homeNumber, String playerName, Location loc, boolean color){
		return getPrefix(color)+String.format(GETHOME_CMD_SUCCESS_4OTHER_DE, homeNumber, playerName)+getLocationCoords(loc, color);
	}
	
	private final static String HOMENUMBEROUTOFBOUNDS_DE = "Die angegebene Home Nummer ist zu groß oder zu klein. Max: %s";
	private final static String HOMENUMBEROUTOFBOUNDS_EN = "The specified home nummber is too large oder to small. Max: %s";
	
	static String getHomeNumberOutOfBounds(boolean color){
		return getPrefix(color)+String.format(HOMENUMBEROUTOFBOUNDS_DE, Home.maxHomes);
	}
	
	private final static String HOMENUMBERNONUMBER_DE = "Die angegebene Home Nummer ist keine Nummer."; 
	private final static String HOMENUMBERNONUMBER_EN = "The specified home nummber is no number.";
	
	static String getHomeNumberNoNumber(boolean color){
		return getPrefix(color)+HOMENUMBERNONUMBER_DE;
	}

	private final static String DELHOME_CMD_SUCCESS_4OTHER_DE = "Das %d. Zuhause von %s wurde entfernt.";
	private final static String DELHOME_CMD_SUCCESS_4OTHER_EN = "%s's %d. home was removed.";

	static String getDelhomeCmdSuccess4Other(int homeNumber, String playerName, boolean color){
		return getPrefix(color)+String.format(DELHOME_CMD_SUCCESS_4OTHER_DE, homeNumber, playerName);
	}

	private final static String DELHOME_CMD_SUCCESS_4SELF_DE = "Dein %d. Zuhause wurde entfernt.";
	private final static String DELHOME_CMD_SUCCESS_4SELF_EN = "Your %d. home was removed.";

	static String getDelhomeCmdSuccess4Self(int homeNumber, boolean color){
		return getPrefix(color)+String.format(DELHOME_CMD_SUCCESS_4SELF_DE, homeNumber);
	}
}

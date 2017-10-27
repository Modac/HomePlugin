package org.myftp.p_productions.HomePlugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

import static org.bukkit.ChatColor.WHITE;

// TODO: Locales
@SuppressWarnings("unused")
public class Messages {

	private Home plugin;
	private static Messages instance = null;

	private Messages(Home homePlugin){

		plugin = homePlugin;
	}

	public static void init(Home plugin){
		if(instance==null){
			instance=new Messages(plugin);
		}
	}

	public static Messages getInstance(){
		return instance;
	}
	
	private final String PREFIX_NOCOLOR = "[Home] ";
	private final String PREFIX_COLOR = ChatColor.AQUA+PREFIX_NOCOLOR+ChatColor.RESET;
	
	String getPrefix(){
		return getPrefix(true);
	}
	
	String getPrefix(boolean color){
		return color?PREFIX_COLOR:PREFIX_NOCOLOR;
	}
	
	private final String PLUGIN_ACTIVATED_DE = "Home Plugin aktiviert";
	private final String PLUGIN_ACTIVATED_EN = "Home Plugin activated";
	
	String getPluginActivated(boolean color){
		return (color?getPrefix():"")+PLUGIN_ACTIVATED_DE;
	}
	
	private final String PLUGIN_DEACTIVATED_DE = "Home Plugin deaktiviert";
	private final String PLUGIN_DEACTIVATED_EN = "Home Plugin disabled";
	
	String getPluginDeactivated(boolean color){
		return (color?getPrefix():"")+PLUGIN_DEACTIVATED_DE;
	}

	private final static String LOCATION_COORDS_COLOR = GOLD+"%.2f "+WHITE+"/"+GOLD+" %.2f "+WHITE+"/"+GOLD+" %.2f "+WHITE+"in"+YELLOW+" %s";
	private final static String LOCATION_COORDS_NOCOLOR = "%.2f / %.2f / %.2f in %s";

	String getLocationCoords(boolean color){
		return color?LOCATION_COORDS_COLOR:LOCATION_COORDS_NOCOLOR;
	}
	
	String getLocationCoords(Location loc, boolean color){
		return String.format(getLocationCoords(color), loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName());
	}
	
	private final String SETHOME_CMD_SUCCESS_DE = "Dein neues Zuhause:\n    " + LOCATION_COORDS_COLOR;
	private final String SETHOME_CMD_SUCCESS_EN = "Your new home was set to:\n    " + LOCATION_COORDS_COLOR;
	
	String getSetHomeCmdSuccess(){
		return getPrefix()+SETHOME_CMD_SUCCESS_DE;
	}
	
	private final String SETHOME_CMD_FAIL_DE = "Konnte dein Zuhause nicht speichern. Sprich mit dem Owner.";
	private final String SETHOME_CMD_FAIL_EN = "Failed to save your home. Speek with the owner.";
	
	String getSetHomeCmdFail(){
		return getPrefix()+SETHOME_CMD_FAIL_DE;
	}
	
	private final String SETHOME_CMD_NOPLAYERSENDER_DE = "Ich kann nur das Zuhause von Spielern setzen";
	private final String SETHOME_CMD_NOPLAYERSENDER_EN = "Sorry, I can only set the homes of players";
	
	String getSetHomeCmdNoPlayerSender(){
		return getPrefix(false)+SETHOME_CMD_NOPLAYERSENDER_DE;
	}
	
	private final String GETHOME_CMD_MISSINGPLAYER_DE = "Keinen Spieler angegeben.\n    /%s <Spieler>";
	private final String GETHOME_CMD_MISSINGPLAYER_EN = "I am missing a player here.\n    /%s <player>";
	
	String getGetHomeCmdMissingPlayer(boolean color){
		return getPrefix(color)+GETHOME_CMD_MISSINGPLAYER_DE;
	}

	private final String GETHOME_CMD_PLAYERNOTFOUND_DE = "Der Spieler wurde nicht gefunden.";
	private final String GETHOME_CMD_PLAYERNOTFOUND_EN = "The given player wasn't found.";
	
	String getGetHomeCmdPlayerNotFound(boolean color){
		return getPrefix(color)+GETHOME_CMD_PLAYERNOTFOUND_DE;
	}
	
	private final String GETHOME_CMD_NOHOMEFOUND_RAW_EN = "I found no home for ";
	private final String GETHOME_CMD_NOHOMEFOUND_4SELF_EN = GETHOME_CMD_NOHOMEFOUND_RAW_EN+"you.";
	
	private final String GETHOME_CMD_NOHOMEFOUND_RAW_DE = "Kein Zuhause f\u00FCr %s gefunden.";
	private final String GETHOME_CMD_NOHOMEFOUND_4SELF_DE = String.format(GETHOME_CMD_NOHOMEFOUND_RAW_DE, "dich");
	
	String getGetHomeCmdNoHomeFound4Self(){
		return getPrefix()+GETHOME_CMD_NOHOMEFOUND_4SELF_DE;
	}
	
	private final String GETHOME_CMD_NOHOMEFOUND_4OTHER_DE = String.format(GETHOME_CMD_NOHOMEFOUND_RAW_DE, "diesen Spieler");
	private final String GETHOME_CMD_NOHOMEFOUND_4OTHER_EN = GETHOME_CMD_NOHOMEFOUND_RAW_EN+"that player.";
	
	String getGetHomeCmdNoHomeFound4Other(boolean color){
		return getPrefix(color)+GETHOME_CMD_NOHOMEFOUND_4OTHER_DE;
	}
	
	private final String GETHOME_CMD_SUCCESS_4SELF_DE = "Dein %d. Zuhause:\n  ";
	private final String GETHOME_CMD_SUCCESS_4SELF_EN = "Your %d. home:\n  ";
	
	String getGetHomeCmdSuccess4Self(){
		return getPrefix()+GETHOME_CMD_SUCCESS_4SELF_DE+getLocationCoords(true);
	}
	
	String getGetHomeCmdSuccess4Self(int homeNumber, String playerName, Location loc){
		return getPrefix()+String.format(GETHOME_CMD_SUCCESS_4SELF_DE, homeNumber, playerName)+getLocationCoords(loc, true);
	}
	
	private final String GETHOME_CMD_SUCCESS_4OTHER_DE = "%d. Zuhause von %s:\n  ";
	private final String GETHOME_CMD_SUCCESS_4OTHER_EN = "%d. home of %s:\n  ";
	
	String getGetHomeCmdSuccess4Other(boolean color){
		return getPrefix(color)+GETHOME_CMD_SUCCESS_4OTHER_DE+getLocationCoords(color);
	}
	
	String getGetHomeCmdSuccess4Other(int homeNumber, String playerName, Location loc, boolean color){
		return getPrefix(color)+String.format(GETHOME_CMD_SUCCESS_4OTHER_DE, homeNumber, playerName)+getLocationCoords(loc, color);
	}
	
	private final String HOMENUMBEROUTOFBOUNDS_DE = "Die angegebene Home Nummer ist zu groß oder zu klein. Max: %s";
	private final String HOMENUMBEROUTOFBOUNDS_EN = "The specified home nummber is too large oder to small. Max: %s";
	
	String getHomeNumberOutOfBounds(boolean color){
		return getPrefix(color)+String.format(HOMENUMBEROUTOFBOUNDS_DE, plugin.getHomeConfig().getMaxHomes());
	}
	
	private final String HOMENUMBERNONUMBER_DE = "Die angegebene Home Nummer ist keine Nummer."; 
	private final String HOMENUMBERNONUMBER_EN = "The specified home nummber is no number.";
	
	String getHomeNumberNoNumber(boolean color){
		return getPrefix(color)+HOMENUMBERNONUMBER_DE;
	}

	private final String DELHOME_CMD_SUCCESS_4OTHER_DE = "Das %d. Zuhause von %s wurde entfernt.";
	private final String DELHOME_CMD_SUCCESS_4OTHER_EN = "%s's %d. home was removed.";

	String getDelHomeCmdSuccess4Other(int homeNumber, String playerName, boolean color){
		return getPrefix(color)+String.format(DELHOME_CMD_SUCCESS_4OTHER_DE, homeNumber, playerName);
	}

	private final String DELHOME_CMD_SUCCESS_4SELF_DE = "Dein %d. Zuhause wurde entfernt.";
	private final String DELHOME_CMD_SUCCESS_4SELF_EN = "Your %d. home was removed.";

	String getDelHomeCmdSuccess4Self(int homeNumber, boolean color){
		return getPrefix(color)+String.format(DELHOME_CMD_SUCCESS_4SELF_DE, homeNumber);
	}

	private final String DELHOME_CMD_NO_HOME_4OTHER_DE = "Das %d. Zuhause von %s ist nicht gesetzt.";
	private final String DELHOME_CMD_NO_HOME_4OTHER_EN = "%s's %d. home is'nt set.";

	String getDelHomeCmdNoHome4Other(int homeNumber, String playerName, boolean color) {
		return getPrefix(color)+String.format(DELHOME_CMD_NO_HOME_4OTHER_DE, homeNumber, playerName);
	}

	private final String DELHOME_CMD_NO_HOME_4SELF_DE = "Dein %d. Zuhause ist nicht gesetzt.";
	private final String DELHOME_CMD_NO_HOME_4SELF_EN = "Your %d. home is'nt set.";

	String getDelHomeCmdNoHome4Self(int homeNumber, boolean color) {
		return getPrefix(color)+String.format(DELHOME_CMD_NO_HOME_4OTHER_DE, homeNumber);
	}

	private final String GOHOME_CMD_INSTANT_DE = "Nach Hause telefonieren";
	private final String GOHOME_CMD_INSTANT_EN = "Phone home";

	String getGoHomeCmdInstant(boolean color) {
		return getPrefix(color)+GOHOME_CMD_INSTANT_DE;
	}

	private final String GOHOME_CMD_START_DE = "Teleportiere zu deinem %d. Zuhause";
	private final String GOHOME_CMD_START_EN = "Teleport to your %d. home";

	String getGoHomeCmdStart(boolean color) {
		return getPrefix(color)+GOHOME_CMD_START_DE;
	}


	private final String GOHOME_CMD_NOT_ONLINE_DE = "Du bist nicht online?";
	private final String GOHOME_CMD_NOT_ONLINE_EN = "You're not online?";

	String getGoHomeCmdNotOnline(boolean color) {
		return getPrefix(color)+GOHOME_CMD_NOT_ONLINE_DE;
	}
}

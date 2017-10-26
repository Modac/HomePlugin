package org.myftp.p_productions.HomePlugin;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;
import org.myftp.p_productions.HomePlugin.exceptions.NoHomeFoundException;

public class GoHomeExecutor implements CommandExecutor {

	Home plugin;
	
	public GoHomeExecutor(Home home) {
		plugin=home;
	}

	private static String usage="Teleport home:\n"
			  				  + "  /%s [homeNumber]\n";

	private static void printUsage(CommandSender sender, String label){
		if(sender instanceof Player)
			Arrays.stream(usage.split("\n")).forEach(line->sender.sendMessage(Messages.getInstance().getPrefix()+String.format(line,label)));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(args.length>1){
				printUsage(sender, label);
				return true;
			}
			try{
				sender.sendMessage(goHome(player, args.length==1?Integer.valueOf(args[0]):1));
			} catch(NumberFormatException e){
				sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(true));
			}
			return true;
		} else {
			return new GetHomeExecutor(plugin).onCommand(sender, cmd, label, args);
		}
	}
	
	private String goHome(OfflinePlayer player, int number){
		return goHomeNew(player, number);
	}
	
	private String goHomeNew(OfflinePlayer player, int number) {
		
		
		Location loc;
		try {
			loc = GetHomeExecutor.getHome(plugin, player, number);
		} catch (NoHomeFoundException e) {
			return Messages.getInstance().getGetHomeCmdNoHomeFound4Self();
		} catch (HomeNumberOutOfBoundsException e) {
			return Messages.getInstance().getHomeNumberOutOfBounds(true);
		}
		
		// TODO: move to Messages
		
		if(player.isOnline()){
			if(((Player) player).hasPermission("homeplugin.instant")) {
				((Player) player).teleport(loc);
				return ChatColor.AQUA + "Nach Hause telefonieren";
			} else {
				GoHomeTask.start(plugin, (Player) player, loc);
				return ChatColor.AQUA+"Teleportiere zu deinem " + number + ". Zuhause";
			}
		}
		else
			return "You're not online?";
		/*} catch(Exception e){
			e.printStackTrace();
			return "Failed to retrieve "+(playerSend?"youre":"the player's")+" home.";
		}*/
	}
	
	/*
	@Deprecated
	private String goHomeOld(OfflinePlayer player, boolean playerSend){
		String home=null;
		if((home=plugin.homeData.getString(String.format(Home.homePath,player.getUniqueId())))==null){
			return("I found no home for "+(playerSend?"you.":"that player."));
		}
		try{
			
			// TODO: Add yaw 'n pitch
			
			String[] homeParts=home.split(";");
			// DEBUG
			// plugin.getServer().broadcastMessage(Arrays.toString(homeParts));
			// DEBUG
			Location loc=new Location(plugin.getServer().getWorld(homeParts[3]), Double.valueOf(homeParts[0]), Double.valueOf(homeParts[1]), Double.valueOf(homeParts[2]));
			if(playerSend){
				if(player.isOnline()){
					GoHomeTask.start(plugin, (Player) player, loc);
					return ChatColor.AQUA+"Teleporting in "+ChatColor.GREEN+"3"+ChatColor.AQUA+" seconds.";
				}
				else
					return "You're not online?";
			} else{
				return String.format("Home of %s: %2$.2f; %3$.2f; %4$.2f in %5$s", player.getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName());
			}
		} catch(Exception e){
			e.printStackTrace();
			return "Failed to retrieve "+(playerSend?"youre":"the player's")+" home.";
		}
	}
	*/

}

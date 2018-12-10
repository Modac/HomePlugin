package org.myftp.p_productions.HomePlugin;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;
import org.myftp.p_productions.HomePlugin.exceptions.NoHomeFoundException;

public class GetHomeExecutor implements CommandExecutor {
	
	Home plugin;
	
	public GetHomeExecutor(Home homePlugin) {
		plugin=homePlugin;
	}

	private static void printUsage(CommandSender sender, String label, boolean color,
			boolean printPlayer, boolean playerOptional, boolean printNumber) {

		String msg = "Delete a Home:\n" +
				"  /%s" + (printPlayer ? String.format(playerOptional ? " [%s]" : " <%s>", "player") : "")
				+ (printNumber ? " [homeNumber]" : "");

		for (String line : msg.split("\n")) {
			sender.sendMessage(Messages.getInstance().getPrefix(color) + String.format(line, label));
		}
	}

	private static void printUsage(CommandSender sender, String label) {
		if (sender instanceof Player) {
			if (((Player) sender).hasPermission("homeplugin.delhome.other")) {
				printUsage(sender, label, true, true, true, true);
			} else {
				printUsage(sender, label, true, false, false, true);
			}
		} else {
			printUsage(sender, label, false, true, false, true);
		}
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			if(args.length>1){
				printUsage(sender, label);
				return true;
			}
			try {
				OfflinePlayer player = (OfflinePlayer) sender;
				int number = args.length==1?Integer.valueOf(args[0]):1;
				sender.sendMessage(Messages.getInstance().getGetHomeCmdSuccess4Self(number, player.getName(), getHome(player, number)));
			} catch (NumberFormatException e) {
				sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(true));
				printUsage(sender, label);
			} catch (NoHomeFoundException e) {
				sender.sendMessage(Messages.getInstance().getGetHomeCmdNoHomeFound4Self());
			} catch (HomeNumberOutOfBoundsException e) {
				sender.sendMessage(Messages.getInstance().getHomeNumberOutOfBounds(true));
			}
			return true;
		} else {
			if(args.length<1){
				sender.sendMessage(String.format(Messages.getInstance().getGetHomeCmdMissingPlayer(false), label));
				printUsage(sender, label);
				return true;
			} else if(args.length>2){
				printUsage(sender, label);
				return true;
			}
			
			OfflinePlayer player;
			if((player=plugin.getServer().getOfflinePlayer(args[0]))==null){
				sender.sendMessage(Messages.getInstance().getGetHomeCmdPlayerNotFound(false));
			}else{
				try {
					int number = args.length==2?Integer.valueOf(args[1]):1;
					sender.sendMessage(Messages.getInstance().getGetHomeCmdSuccess4Other(number, player.getName(), getHome(player, number), false));
				} catch (NumberFormatException e) {
					sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(false));
					printUsage(sender, label);
				} catch (NoHomeFoundException e) {
					sender.sendMessage(Messages.getInstance().getGetHomeCmdNoHomeFound4Other(false));
				} catch (HomeNumberOutOfBoundsException e) {
					sender.sendMessage(Messages.getInstance().getHomeNumberOutOfBounds(false));
				}
			}
			return true;
		}
	}
	
	public static Location getHome(Home homePlugin, OfflinePlayer player, int number) throws NoHomeFoundException, HomeNumberOutOfBoundsException{
		if(number > homePlugin.getHomeConfig().getMaxHomes() || number <= 0) throw new HomeNumberOutOfBoundsException();
		
		ConfigurationSection home=null;
		if((home=homePlugin.homeData.getConfigurationSection(String.format(Home.homePath,player.getUniqueId(), number)))==null){
			throw new NoHomeFoundException();
		}
			
		return Location.deserialize(home.getValues(false));
	}
	
	private Location getHome(OfflinePlayer player, int number) throws NoHomeFoundException, HomeNumberOutOfBoundsException{
		return getHome(plugin, player, number);
	}

}

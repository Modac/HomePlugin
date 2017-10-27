package org.myftp.p_productions.HomePlugin;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;
import org.myftp.p_productions.HomePlugin.exceptions.NoHomeFoundException;

import java.util.Arrays;

public class GetHomesExecutor implements CommandExecutor {

	Home plugin;

	public GetHomesExecutor(Home homePlugin) {
		plugin=homePlugin;
	}

	private static String usage="Get all homes:\n"
			  				  + "  /%s\n";

	private static String usageConsole="Get all homes:\n"
									 + "  /%s <player>\n";

	private static void printUsage(CommandSender sender, String label){
		String us = usageConsole;
		boolean color[] = new boolean[]{false};
		if(sender instanceof Player){
			us = usage;
			color[0] = true;
		}
		Arrays.stream(us.split("\n")).forEach(line->sender.sendMessage(Messages.getInstance().getPrefix(color[0])+String.format(line,label)));
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			try {
				OfflinePlayer player = (OfflinePlayer) sender;
				int setHomes=0;
				for (int i=1; i<=plugin.getHomeConfig().getMaxHomes(); i++) {
					try {
						sender.sendMessage(Messages.getInstance().getGetHomeCmdSuccess4Self(i, player.getName(), getHome(player, i)));
						setHomes++;
					} catch(NoHomeFoundException nhfe) { //just continue
					}
				}
				if(setHomes==0) sender.sendMessage(Messages.getInstance().getGetHomeCmdNoHomeFound4Self());
			} catch (NumberFormatException e) {
				sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(true));
				printUsage(sender, label);
			} catch (HomeNumberOutOfBoundsException e) {
				sender.sendMessage(Messages.getInstance().getHomeNumberOutOfBounds(true));
			}
			return true;
		} else {
			if(args.length<1) {
				sender.sendMessage(String.format(Messages.getInstance().getGetHomeCmdMissingPlayer(false), label));
				printUsage(sender, label);
				return true;
			}
			
			OfflinePlayer player;
			if((player=plugin.getServer().getOfflinePlayer(args[0]))==null){
				sender.sendMessage(Messages.getInstance().getGetHomeCmdPlayerNotFound(false));
			}else{
				try {
					int setHomes=0;
					for (int i=1; i<=plugin.getHomeConfig().getMaxHomes(); i++) {
						try {
							sender.sendMessage(Messages.getInstance().getGetHomeCmdSuccess4Other(i, player.getName(), getHome(player, i), false));
							setHomes++;
						} catch(NoHomeFoundException nhfe) { //just continue
						}
						if(setHomes==0) sender.sendMessage(Messages.getInstance().getGetHomeCmdNoHomeFound4Self());
					}
				} catch (NumberFormatException e) {
					sender.sendMessage(Messages.getInstance().getHomeNumberNoNumber(false));
					printUsage(sender, label);
				} catch (HomeNumberOutOfBoundsException e) {
					sender.sendMessage(Messages.getInstance().getHomeNumberOutOfBounds(false));
				}
			}
			return true;
		}
	}


	private Location getHome(OfflinePlayer player, int number) throws NoHomeFoundException, HomeNumberOutOfBoundsException{
		return GetHomeExecutor.getHome(plugin, player, number);
	}

}

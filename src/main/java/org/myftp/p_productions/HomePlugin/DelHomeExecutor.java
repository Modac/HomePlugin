package org.myftp.p_productions.HomePlugin;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;
import org.myftp.p_productions.HomePlugin.exceptions.NoHomeFoundException;

import java.io.IOException;
import java.util.Arrays;

public class DelHomeExecutor implements CommandExecutor {

	Home plugin;

	public DelHomeExecutor(Home homePlugin) {
		plugin=homePlugin;
	}

	private static String usage="Delete a home:\n"
			  				  + "  /%s [homeNumber]\n";

	private static String usageConsole="Delete a home:\n"
									 + "  /%s <player> [homeNumber]\n";

	private static void printUsage(CommandSender sender, String label){
		String us = usageConsole;
		boolean color[] = new boolean[]{false};
		if(sender instanceof Player){
			us = usage;
			color[0] = true;
		}
		Arrays.stream(us.split("\n")).forEach(line->sender.sendMessage(Messages.getPrefix(color[0])+String.format(line,label)));
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
				if(delHome(this.plugin, player, number)){
					sender.sendMessage(Messages.getDelhomeCmdSuccess4Self(number, true));
				} else {
					// TODO: there was no home to remove
				}
			} catch (NumberFormatException e) {
				sender.sendMessage(Messages.getHomeNumberNoNumber(true));
				printUsage(sender, label);
			} catch (NoHomeFoundException e) {
				sender.sendMessage(Messages.getGetHomeCmdNoHomeFound4Self());
			} catch (HomeNumberOutOfBoundsException e) {
				sender.sendMessage(Messages.getHomeNumberOutOfBounds(true));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			if(args.length<1){
				sender.sendMessage(String.format(Messages.getGetHomeCmdMissingPlayer(false), label));
				printUsage(sender, label);
				return true;
			} else if(args.length>2){
				printUsage(sender, label);
				return true;
			}

			OfflinePlayer player;
			if((player=plugin.getServer().getOfflinePlayer(args[0]))==null){
				sender.sendMessage(Messages.getGetHomeCmdPlayerNotFound(false));
			}else{
				try {
					int number = args.length==2?Integer.valueOf(args[1]):1;
					if(delHome(this.plugin, player, number)){
						sender.sendMessage(Messages.getDelhomeCmdSuccess4Other(number, player.getName(), false));
					} else {
						// TODO: there was no home to remove
					}
				} catch (NumberFormatException e) {
					sender.sendMessage(Messages.getHomeNumberNoNumber(false));
					printUsage(sender, label);
				} catch (NoHomeFoundException e) {
					sender.sendMessage(Messages.getGetHomeCmdNoHomeFound4Other(false));
				} catch (HomeNumberOutOfBoundsException e) {
					sender.sendMessage(Messages.getHomeNumberOutOfBounds(false));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
	}


	private boolean delHome(Home homeplugin, OfflinePlayer player, int number) throws NoHomeFoundException, HomeNumberOutOfBoundsException, IOException {

		homeplugin.homeData.set(String.format(Home.homePath,player.getUniqueId(), number), null);

		plugin.homeData.save(plugin.dataFile);
		return true;
	}

}

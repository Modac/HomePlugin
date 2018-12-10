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

	private Home plugin;
	
	public GoHomeExecutor(Home home) {
		plugin=home;
	}

	private static final String usage="Teleport home:\n"
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


		if(player.isOnline()){
			// If player has instant teleport permission or if ops can teleport instantly and player is op
			Player oPlayer = player.getPlayer();
			if(teleportInstant(oPlayer)) {
				oPlayer.teleport(loc);
				return Messages.getInstance().getGoHomeCmdInstant(true);
			} else {
				GoHomeTask.start(plugin, oPlayer, loc);
				return Messages.getInstance().getGoHomeCmdStart(number, true);
			}
		}
		else
			return Messages.getInstance().getGoHomeCmdNotOnline(true);
	}

	private boolean teleportInstant(Player player){
		return player.isPermissionSet("homplugin.instant")?
						player.hasPermission("homeplugin.instant"):
						plugin.getHomeConfig().isOpInstantTeleport() && player.isOp();
	}
}

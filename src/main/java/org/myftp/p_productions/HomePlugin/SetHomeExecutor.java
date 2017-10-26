package org.myftp.p_productions.HomePlugin;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.exceptions.HomeNumberOutOfBoundsException;

public class SetHomeExecutor implements CommandExecutor {
	Home plugin;
	
	
	public SetHomeExecutor(Home home) {
		plugin=home;
	}
	
	private static String usage="Set your homes:\n"
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
			}
			
			try {
				Location loc = setHome(player, args.length==1?Integer.valueOf(args[0]):1);
				player.sendMessage(String.format(Messages.getInstance().getSetHomeCmdSuccess(),loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName()));
			} catch (IOException e) {
				e.printStackTrace();
				sender.sendMessage(Messages.getInstance().getSetHomeCmdFail());
			} catch (NumberFormatException e) {
				printUsage(sender, label);
			} catch (HomeNumberOutOfBoundsException e) {
				sender.sendMessage(Messages.getInstance().getHomeNumberOutOfBounds(true));
			}
			
			return true;
		} else {
			sender.sendMessage(Messages.getInstance().getSetHomeCmdNoPlayerSender());
			return true;
		}
	}
	
	public Location setHome(Player player, int number) throws IOException, HomeNumberOutOfBoundsException{
		if(number > plugin.getMaxHomes() || number <= 0) throw new HomeNumberOutOfBoundsException();
		
		plugin.homeData.set(String.format(Home.lastNamePath,player.getUniqueId()), player.getName());
		
		Location loc = player.getLocation();
		//plugin.homeData.set(String.format(Home.homePath,player.getUniqueId()), loc.getX()+"/"+loc.getY()+"/"+loc.getZ()+"/"+loc.getWorld().getName());
		plugin.homeData.createSection(String.format(Home.homePath,player.getUniqueId(), number), loc.serialize());
		
		plugin.homeData.save(plugin.dataFile);
		return loc;
	}

}

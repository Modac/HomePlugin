package org.myftp.p_productions.HomePlugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GoHomeTask extends BukkitRunnable {

	private static int period=20;
	private static int maxCount=3;
	private Player player;
	private Location loc;
	private DontMoveListener listener;
	private int count;
	
	public GoHomeTask(JavaPlugin plugin, Player player, Location loc) {
		this.player=player;
		this.loc=loc;
		this.listener=new DontMoveListener(player, this);
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
		count=maxCount;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// DEBUG
		// player.getServer().broadcastMessage("TP");
		// DEBUG
		if(count<=0){
			player.teleport(loc);
			PlayerMoveEvent.getHandlerList().unregister(listener);
			this.cancel();
		} else {
			player.sendTitle(null, ChatColor.AQUA + "Teleportiere in "+
									ChatColor.GREEN + count-- +
									ChatColor.AQUA + " Sekunde"+(count==0?"":"n"));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static BukkitTask start(JavaPlugin plugin, Player player, Location loc){
		player.sendTitle(ChatColor.GOLD+"Nicht bewegen", null);
		return new GoHomeTask(plugin, player, loc).runTaskTimer(plugin, 0, period);
	}
	
	private class DontMoveListener implements Listener{
		
		Player player;
		BukkitRunnable task;
		Location origLoc;
		
		public DontMoveListener(Player player, BukkitRunnable task) {
			this.player=player;
			this.task=task;
			origLoc=player.getLocation();
		}
		
		@SuppressWarnings("deprecation")
		@EventHandler
		public void onPlayerMove(PlayerMoveEvent event){
			if(event.getPlayer().getUniqueId().equals(player.getUniqueId()) && didMove(event.getTo())){
				PlayerMoveEvent.getHandlerList().unregister(this);
				player.sendTitle(ChatColor.RED+"Teleport abgebrochen", "");
				task.cancel();
			}
		}
		
		private boolean didMove(Location to){
			return origLoc.distanceSquared(to)>=2.25;
		}
	}
	
	
	
}

package org.myftp.p_productions.HomePlugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Home extends JavaPlugin {
	
	private static String filename="homes.yml";
	static String lastNamePath="%s.LastName";
	static String homePath="%s.Home%d";
	static int maxHomes=3;
	
	FileConfiguration homeData;
	File dataFile;
	
	public Home() {
		dataFile=new File(getDataFolder(), filename);
		homeData = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	@Override
	public void onEnable() {

		saveDefaultConfig();

		getCommand("sethome").setExecutor(new SetHomeExecutor(this));
		getCommand("home").setExecutor(new GoHomeExecutor(this));
		getCommand("gethome").setExecutor(new GetHomeExecutor(this));
		getCommand("gethomes").setExecutor(new GetHomesExecutor(this));
		getCommand("delhome").setExecutor(new DelHomeExecutor(this));


		getLogger().info(Messages.getPluginActivated(false));
	}
	
	@Override
	public void onDisable() {
		
		getLogger().info(Messages.getPluginDeactivated(false));

		saveConfig();
	}
	
	public static int getMaxHomes(){
		return maxHomes;
	}
}

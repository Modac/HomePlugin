package org.myftp.p_productions.HomePlugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfig;
import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfigManager;

public class Home extends JavaPlugin {
	
	private static String filename="homes.yml";
	static String lastNamePath="%s.LastName";
	static String homePath="%s.Home%d";
	int maxHomes;

	public SimpleConfigManager manager;
	public SimpleConfig config;

	FileConfiguration homeData;
	File dataFile;
	
	public Home() {
		dataFile=new File(getDataFolder(), filename);
		homeData = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	@Override
	public void onEnable() {

		Messages.getInstance().init(this);

		initDefaultConfig();
		loadConfig();

		getCommand("sethome").setExecutor(new SetHomeExecutor(this));
		getCommand("home").setExecutor(new GoHomeExecutor(this));
		getCommand("gethome").setExecutor(new GetHomeExecutor(this));
		getCommand("gethomes").setExecutor(new GetHomesExecutor(this));
		getCommand("delhome").setExecutor(new DelHomeExecutor(this));


		getLogger().info(Messages.getInstance().getPluginActivated(false));
	}

	private void initDefaultConfig() {

		this.manager = new SimpleConfigManager(this);
		this.manager.prepareFile("config.yml", "config.yml");
		this.config = manager.getNewConfig("config.yml");
		this.config.saveConfig();

	}

	private void loadConfig() {
		maxHomes = this.config.getInt("maxHomes");

	}

	@Override
	public void onDisable() {
		
		getLogger().info(Messages.getInstance().getPluginDeactivated(false));

		this.config.saveConfig();
	}
	
	public int getMaxHomes(){
		return maxHomes;
	}
}

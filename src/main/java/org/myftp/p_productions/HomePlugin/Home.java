package org.myftp.p_productions.HomePlugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfig;
import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfigManager;

public class Home extends JavaPlugin {

    private static String filename = "homes.yml";
    static String lastNamePath = "%s.LastName";
    static String homePath = "%s.Home%d";

    FileConfiguration homeData;
    File dataFile;

    Configuration config;

    public Home() {
        dataFile = new File(getDataFolder(), filename);
        homeData = YamlConfiguration.loadConfiguration(dataFile);
    }

    @Override
    public void onEnable() {

        Messages.init(this);

        config = new Configuration(this);
        config.initConfig();

        getCommand("sethome").setExecutor(new SetHomeExecutor(this));
        getCommand("home").setExecutor(new GoHomeExecutor(this));
        getCommand("gethome").setExecutor(new GetHomeExecutor(this));
        getCommand("gethomes").setExecutor(new GetHomesExecutor(this));
        getCommand("delhome").setExecutor(new DelHomeExecutor(this));


        getLogger().info(Messages.getInstance().getPluginActivated(false));
    }

    public Configuration getHomeConfig() {
        return this.config;
    }

    @Override
    public void onDisable() {

        getLogger().info(Messages.getInstance().getPluginDeactivated(false));

        this.config.saveConfig();
    }
}

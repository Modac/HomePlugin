package org.myftp.p_productions.HomePlugin;

import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfig;
import org.myftp.p_productions.HomePlugin.SimpleConfig.SimpleConfigManager;

public class Configuration {

    private Home plugin;
    private final String confFile = "config.yml";

    private SimpleConfigManager manager;
    private SimpleConfig config;

    Configuration(Home homePlugin) {
        this.plugin = homePlugin;
    }

    public void initConfig() {
        initDefaultConfig();
        loadConfig();
    }

    private void initDefaultConfig() {

        this.manager = new SimpleConfigManager(plugin);
        this.manager.prepareFile("config.yml", "config.yml");
        this.config = manager.getNewConfig("config.yml");
        this.config.saveConfig();

    }

    private int maxHomes;
    private boolean opInstantTeleport;


    private void loadConfig() {
        maxHomes = this.config.getInt("maxHomes");
        opInstantTeleport = this.config.getBoolean("opInstantTeleport");

    }

    public void saveConfig() {
        this.config.saveConfig();
    }

    public int getMaxHomes() {
        return maxHomes;
    }

    public boolean isOpInstantTeleport() {
        return opInstantTeleport;
    }
}

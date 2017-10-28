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
    private double maxMoveDist;
    private String sourceParticleWhile;
    private String targetParticleWhile;
    private boolean particleFollow;
    private String sourceParticleAfter;
    private String targetParticleAfter;


    private void loadConfig() {
        maxHomes = this.config.getInt("maxHomes", 3);
        opInstantTeleport = this.config.getBoolean("opInstantTeleport", true);
        maxMoveDist = this.config.getDouble("maxMoveDist", 0.5);
        sourceParticleWhile = this.config.getString("sourceParticleWhile", "spiral");
        targetParticleWhile = this.config.getString("targetParticleWhile", "spiral");
        particleFollow = this.config.getBoolean("particleFollow", false);
        sourceParticleAfter = this.config.getString("sourceParticleAfter", "cloud");
        targetParticleAfter = this.config.getString("targetParticleAfter", "cloud");
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

    public double getMaxMoveDist() {
        return maxMoveDist;
    }

    public String getSourceParticleWhile() {
        return sourceParticleWhile;
    }

    public String getTargetParticleWhile() {
        return targetParticleWhile;
    }

    public boolean isParticleFollow() {
        return particleFollow;
    }

    public String getSourceParticleAfter() {
        return sourceParticleAfter;
    }

    public String getTargetParticleAfter() {
        return targetParticleAfter;
    }
}

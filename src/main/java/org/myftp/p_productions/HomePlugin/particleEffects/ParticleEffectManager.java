package org.myftp.p_productions.HomePlugin.particleEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.Home;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.Ender;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.Spiral;

public class ParticleEffectManager {

    private Home plugin;
    private Player player;
    private Location sourceLoc;
    private Location targetLoc;

    private ParticleEffect targetAfter;
    private ParticleEffect targetWhile;
    private ParticleEffect sourceAfter;
    private ParticleEffect sourceWhile;

    public ParticleEffectManager(Home homePlugin, Player player, Location sourceLocation, Location targetLocation){
        this.plugin = homePlugin;
        this.player = player;
        this.sourceLoc = sourceLocation;
        this.targetLoc = targetLocation;

        initParticleEffects();
    }

    private void initParticleEffects() {
        plugin.getLogger().info("init");
        switch(plugin.getHomeConfig().getSourceParticleWhile().toLowerCase()) {
            case "ender":
                sourceWhile = new Ender(plugin, player, sourceLoc, true);
                break;
            case "spiral":
                plugin.getLogger().info("Spiral");
                sourceWhile = new Spiral(plugin, player, sourceLoc, true);
                break;
            default:
                sourceWhile = null;
        }

        switch(plugin.getHomeConfig().getTargetParticleWhile().toLowerCase()) {
            case "ender":
                targetWhile = new Ender(plugin, player, targetLoc, false);
                break;
            case "spiral":
                targetWhile = new Spiral(plugin, player, targetLoc, false);
                break;
            default:
                targetWhile = null;
        }
    }

    public void notifyParticleEffects(int count){
        if(sourceAfter!=null) notifyParticleEffectSourceAfter(count);
        if(sourceWhile!=null) notifyParticleEffectSourceWhile(count);
        if(targetAfter!=null) notifyParticleEffectTargetAfter(count);
        if(targetWhile!=null) notifyParticleEffectTargetWhile(count);
    }

    void notifyParticleEffectTargetAfter(int count){

    }

    void notifyParticleEffectSourceWhile(int count){
        sourceWhile.notifyParticleEffect(count);
    }

    void notifyParticleEffectSourceAfter(int count){

    }

    void notifyParticleEffectTargetWhile(int count){
        targetWhile.notifyParticleEffect(count);
    }

    void abortParticleEffects(int count){

    }



}

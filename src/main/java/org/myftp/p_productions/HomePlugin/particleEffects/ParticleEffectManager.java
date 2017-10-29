package org.myftp.p_productions.HomePlugin.particleEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.myftp.p_productions.HomePlugin.Home;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.Cage;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.ConstantSpiral;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.Ender;
import org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport.ProgressiveSpiral;

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
        sourceWhile = getParticleEffectWhile(plugin.getHomeConfig().getSourceParticleWhile().toLowerCase(), true);
        targetWhile = getParticleEffectWhile(plugin.getHomeConfig().getTargetParticleWhile().toLowerCase(), false);

        // TODO: add After teleport effects
    }

    private ParticleEffect getParticleEffectWhile(String particleEffectWhile, boolean atSource) {
        switch(particleEffectWhile) {
            case "ender":
                return new Ender(plugin, player, atSource?sourceLoc:targetLoc, atSource);
            case "constantspiral":
                return new ConstantSpiral(plugin, player, atSource?sourceLoc:targetLoc, atSource);
            case "progressivespiral":
                return new ProgressiveSpiral(plugin, player, atSource?sourceLoc:targetLoc, atSource);
            case "cage":
                return new Cage(plugin, player, atSource?sourceLoc:targetLoc, atSource);
            default:
                return null;
        }
    }

    public void notifyParticleEffects(int count){
        if(sourceAfter!=null) notifyParticleEffectSourceAfter(count);
        if(sourceWhile!=null) notifyParticleEffectSourceWhile(count);
        if(targetAfter!=null) notifyParticleEffectTargetAfter(count);
        if(targetWhile!=null) notifyParticleEffectTargetWhile(count);
    }

    private void notifyParticleEffectTargetAfter(int count){

    }

    private void notifyParticleEffectSourceWhile(int count){
        sourceWhile.notifyParticleEffect(count);
    }

    private void notifyParticleEffectSourceAfter(int count){

    }

    private void notifyParticleEffectTargetWhile(int count){
        targetWhile.notifyParticleEffect(count);
    }

    public void abortParticleEffects(){
        if(sourceAfter!=null) sourceAfter.abortParticleEffect();
        if(sourceWhile!=null) sourceWhile.abortParticleEffect();
        if(targetAfter!=null) targetAfter.abortParticleEffect();
        if(targetWhile!=null) targetWhile.abortParticleEffect();
    }



}

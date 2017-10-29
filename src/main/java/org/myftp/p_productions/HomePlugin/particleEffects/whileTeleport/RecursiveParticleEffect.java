package org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.myftp.p_productions.HomePlugin.Home;
import org.myftp.p_productions.HomePlugin.particleEffects.ParticleEffect;

public abstract class RecursiveParticleEffect implements ParticleEffect{

    protected Home plugin;
    protected Player player;
    Location loc;
    boolean isAtSource;
    BukkitRunnable particleTask;

    RecursiveParticleEffect(Home homePlugin, Player player, Location location, boolean isAtSource){
        this.plugin = homePlugin;
        this.player = player;
        this.loc = location;
        this.isAtSource = isAtSource;
        initTask();
    }

    abstract void initTask();

    @Override
    public void notifyParticleEffect(int count) {
        if(count == 3) {
            particleTask.runTaskTimer(plugin, 0, 1);

        }
    }

    @Override
    public void abortParticleEffect() {
        particleTask.cancel();
    }

}

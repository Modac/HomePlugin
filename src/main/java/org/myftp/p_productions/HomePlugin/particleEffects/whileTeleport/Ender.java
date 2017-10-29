package org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.myftp.p_productions.HomePlugin.Home;
import org.myftp.p_productions.HomePlugin.particleEffects.ParticleEffect;

public class Ender implements ParticleEffect{

    private Home plugin;
    private Player player;
    private Location loc;
    private boolean isAtSource;

    public Ender(Home homePlugin, Player player, Location location, boolean isAtSource){
        this.plugin = homePlugin;
        this.player = player;
        this.loc = location;
        this.isAtSource = isAtSource;
    }

    @Override
    public void notifyParticleEffect(int count) {
        if(count == 3) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location particleLoc = (isAtSource && plugin.getHomeConfig().isParticleFollow())?player.getLocation():loc;
                    player.getWorld().spawnParticle(Particle.PORTAL, particleLoc.add(0,1,0), 500, 0.2, 0.4, 0.2, 2);
                }
            }.runTaskLater(plugin, 10);

        }
    }

    @Override
    public void abortParticleEffect() {
        // Cannot abort ender effect
    }
}

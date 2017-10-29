package org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.myftp.p_productions.HomePlugin.Home;

public class Cage extends RecursiveParticleEffect {

    public Cage(Home homePlugin, Player player, Location location, boolean isAtSource) {
        super(homePlugin, player, location, isAtSource);
    }

    @Override
    void initTask() {
        particleTask = new BukkitRunnable() {

            @Override
            public void run() {

                for (int angdeg = 0; angdeg<360; angdeg+=60){
                    for (double y=0; y<=2.0; y+=0.2){
                        Location particleLoc = new Location(loc.getWorld(), 0.6*Math.cos(Math.toRadians(angdeg)), y, 0.6*Math.sin(Math.toRadians(angdeg)));
                        particleLoc.add((isAtSource && plugin.getHomeConfig().isParticleFollow()) ? player.getLocation() : loc);

                        player.getWorld().spawnParticle(Particle.FLAME, particleLoc, 5, 0, 0, 0, 0);

                    }
                }
            }
        };
    }
}

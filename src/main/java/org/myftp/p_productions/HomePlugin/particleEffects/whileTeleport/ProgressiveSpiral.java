package org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.myftp.p_productions.HomePlugin.Home;

public class ProgressiveSpiral extends RecursiveParticleEffect {

    public ProgressiveSpiral(Home homePlugin, Player player, Location location, boolean isAtSource) {
        super(homePlugin, player, location, isAtSource);
    }

    @Override
    protected void initTask() {
        particleTask = new BukkitRunnable() {
            Location helixPoint = new Location(loc.getWorld(), 0, 0, 0);
            double t = 0.0;

            @Override
            public void run() {
                // Wikipedia: Helix
                helixPoint.setX(0.5 * Math.cos(2 * Math.PI * t));
                helixPoint.setZ(0.5 * Math.sin(2 * Math.PI * t));
                helixPoint.setY(2.0 / 3 * t);
                helixPoint.add((isAtSource && plugin.getHomeConfig().isParticleFollow()) ? player.getLocation() : loc);

                player.getWorld().spawnParticle(Particle.FLAME, helixPoint, 10, 0, 0, 0, 0);
                t += 0.05;
            }
        };
    }

}

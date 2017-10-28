package org.myftp.p_productions.HomePlugin.particleEffects.whileTeleport;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.myftp.p_productions.HomePlugin.Home;
import org.myftp.p_productions.HomePlugin.particleEffects.ParticleEffect;

public class Spiral implements ParticleEffect{

    private Home plugin;
    private Player player;
    private Location loc;
    private boolean isAtSource;

    // TODO: Add particle following
    public Spiral(Home homePlugin, Player player, Location location, boolean isAtSource){
        this.plugin = homePlugin;
        this.player = player;
        this.loc = location;
        this.isAtSource = isAtSource;
    }
    // TODO: Fine tune values
    @Override
    public void notifyParticleEffect(int count) {
        plugin.getLogger().info("Notify Spiral" + count);
        if(count == 3) {
            new BukkitRunnable() {
                Location helixPoint = new Location(loc.getWorld(), 0, 0, 0);
                double t=0.0;
                @Override
                public void run() {
                    // Wikipedia: Helix
                    helixPoint.setX(0.75*Math.cos(2*Math.PI*t));
                    helixPoint.setZ(0.75*Math.sin(2*Math.PI*t));
                    helixPoint.setY(2.0/3*t);
                    helixPoint.add(loc);
                    //plugin.getLogger().info(helixPoint.toString());
                    player.getWorld().spawnParticle(Particle.FLAME, helixPoint, 10, 0, 0, 0, 0);
                    t+=0.05;
                    if (t>=3) this.cancel();
                }
            }.runTaskTimer(plugin, 0, 1);

        }
    }
}

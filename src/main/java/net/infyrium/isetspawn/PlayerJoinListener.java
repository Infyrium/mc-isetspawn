package net.infyrium.isetspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Location spawnLocation;

    private final boolean teleportOnJoin;

    public PlayerJoinListener(iSetSpawnMain plugin) {
        var config = plugin.getConfig();

        this.teleportOnJoin = config.getBoolean("settings.teleportOnJoin");

        String worldName = config.getString("settings.teleportLocation.world");
        if (worldName == null || worldName.isEmpty()) {
            plugin.getLogger().warning("Spawn world name is missing in config.");
            this.spawnLocation = null;
            return;
        }

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            plugin.getLogger().warning("World '" + worldName + "' not found. Players will not be teleported.");
            this.spawnLocation = null;
            return;
        }

        double x = config.getDouble("settings.teleportLocation.x");
        double y = config.getDouble("settings.teleportLocation.y");
        double z = config.getDouble("settings.teleportLocation.z");
        float yaw = (float) config.getDouble("settings.teleportLocation.yaw");
        float pitch = (float) config.getDouble("settings.teleportLocation.pitch");

        this.spawnLocation = new Location(world, x, y, z, yaw, pitch);
        world.setSpawnLocation(spawnLocation.getBlockX(), spawnLocation.getBlockY(), spawnLocation.getBlockZ());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!teleportOnJoin || spawnLocation == null) return;
        event.getPlayer().teleport(spawnLocation);
    }
}

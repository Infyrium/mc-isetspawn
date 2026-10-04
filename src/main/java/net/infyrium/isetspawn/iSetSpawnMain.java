package net.infyrium.isetspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import net.infyrium.isetspawn.commands.SetSpawnCommand;
import net.infyrium.isetspawn.commands.SpawnCommand;
import net.infyrium.isetspawn.listeners.PlayerJoinListener;
import net.infyrium.isetspawn.managers.Messages;
import net.infyrium.isetspawn.utils.ConfigUpdater;


public class iSetSpawnMain extends JavaPlugin {

    private static final String PATH = "settings.teleportLocation.";

    private Messages messages;

    @Override
    public void onEnable() {
        ConfigUpdater.backupIfOutdated(this, "config.yml");
        saveDefaultConfig();

        messages = new Messages(this);
        messages.load();

        Location spawnLocation = getSpawnLocation();
        if (spawnLocation == null) {
            getLogger().warning("Spawn world '" + getConfig().getString(PATH + "world") + "' not found. Players will not be teleported.");
        } else {
            updateWorldSpawn(spawnLocation);
        }

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));

        getLogger().info("Plugin has been enabled!");
        getLogger().info("Plugin developed by: " + String.join(", ", getPluginMeta().getAuthors()));
        getLogger().info("Website: " + getPluginMeta().getWebsite());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled!");
    }

    public Messages getMessages() {
        return messages;
    }

    /**
     * Returns the spawn location from config, or null if the world is not found.
     */
    public Location getSpawnLocation() {
        String worldName = getConfig().getString(PATH + "world");
        World world = worldName == null ? null : Bukkit.getWorld(worldName);
        if (world == null) return null;

        return new Location(
                world,
                getConfig().getDouble(PATH + "x"),
                getConfig().getDouble(PATH + "y"),
                getConfig().getDouble(PATH + "z"),
                (float) getConfig().getDouble(PATH + "yaw"),
                (float) getConfig().getDouble(PATH + "pitch")
        );
    }

    /**
     * Saves the spawn location to config and updates the world spawn.
     */
    public void setSpawnLocation(Location location) {
        getConfig().set(PATH + "x", location.getX());
        getConfig().set(PATH + "y", location.getY());
        getConfig().set(PATH + "z", location.getZ());
        getConfig().set(PATH + "pitch", location.getPitch());
        getConfig().set(PATH + "yaw", location.getYaw());
        getConfig().set(PATH + "world", location.getWorld().getName());
        saveConfig();

        updateWorldSpawn(location);
    }

    private void updateWorldSpawn(Location location) {
        location.getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}

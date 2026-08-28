package net.infyrium.isetspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.infyrium.isetspawn.commands.SpawnCommand;


public class iSetSpawnMain extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getCommand("spawn").setExecutor(new SpawnCommand(this));

        getLogger().info("Plugin has been enabled!");
        getLogger().info("Plugin developed by: " + String.join(", ", getPluginMeta().getAuthors()));
        getLogger().info("Website: " + getPluginMeta().getWebsite());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled!");
    }
}

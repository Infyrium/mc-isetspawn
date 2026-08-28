package net.infyrium.isetspawn.commands;

import net.infyrium.isetspawn.iSetSpawnMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final iSetSpawnMain plugin;

    public SpawnCommand(iSetSpawnMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        String path = "settings.teleportLocation.";
        String worldName = plugin.getConfig().getString(path + "world");
        World world = worldName == null ? null : Bukkit.getWorld(worldName);

        if (world == null) {
            player.sendMessage("Spawn world was not found.");
            return true;
        }

        Location spawnLocation = new Location(
                world,
                plugin.getConfig().getDouble(path + "x"),
                plugin.getConfig().getDouble(path + "y"),
                plugin.getConfig().getDouble(path + "z"),
                (float) plugin.getConfig().getDouble(path + "yaw"),
                (float) plugin.getConfig().getDouble(path + "pitch")
        );

        player.teleport(spawnLocation);
        player.sendMessage("Teleported to spawn!");
        return true;
    }
}

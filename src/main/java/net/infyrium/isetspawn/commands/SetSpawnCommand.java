package net.infyrium.isetspawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.infyrium.isetspawn.iSetSpawnMain;

public class SetSpawnCommand implements CommandExecutor {

    private final iSetSpawnMain plugin;

    public SetSpawnCommand(iSetSpawnMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        if (!player.hasPermission("isetspawn.setspawn")) {
            player.sendMessage("You don't have permission to use this command.");
            return true;
        }

        plugin.setSpawnLocation(player.getLocation());
        player.sendMessage("Spawn has been set!");
        return true;
    }
}

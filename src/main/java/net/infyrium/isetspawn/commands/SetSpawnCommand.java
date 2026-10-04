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
            plugin.getMessages().send(sender, "player-only");
            return true;
        }

        if (!player.hasPermission("isetspawn.setspawn")) {
            plugin.getMessages().send(player, "no-permission");
            return true;
        }

        plugin.setSpawnLocation(player.getLocation());
        plugin.getMessages().send(player, "setspawn");
        return true;
    }
}

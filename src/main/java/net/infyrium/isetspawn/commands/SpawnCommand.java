package net.infyrium.isetspawn.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.infyrium.isetspawn.iSetSpawnMain;

public class SpawnCommand extends TargetCommand {

    public SpawnCommand(iSetSpawnMain plugin) {
        super(plugin, "spawn");
    }

    @Override
    protected String execute(Player target) {
        Location spawnLocation = plugin.getSpawnLocation();
        if (spawnLocation == null) {
            return "Spawn world was not found.";
        }

        target.teleport(spawnLocation);
        return "Teleported to spawn!";
    }
}

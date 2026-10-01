package net.infyrium.isetspawn.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.infyrium.isetspawn.iSetSpawnMain;

public class SpawnCommand extends TargetCommand {

    public SpawnCommand(iSetSpawnMain plugin) {
        super(plugin, "spawn");
    }

    @Override
    protected Result execute(Player target) {
        Location spawnLocation = plugin.getSpawnLocation();
        if (spawnLocation == null) {
            return new Result("Spawn world was not found.", "Spawn world was not found.");
        }

        target.teleport(spawnLocation);
        return new Result("Teleported to spawn!", target.getName() + " was teleported to spawn!");
    }
}

package net.infyrium.isetspawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final iSetSpawnMain plugin;

    private final boolean teleportOnJoin;

    private final boolean teleportOnFirstJoin;

    public PlayerJoinListener(iSetSpawnMain plugin) {
        this.plugin = plugin;
        this.teleportOnJoin = plugin.getConfig().getBoolean("settings.teleportOnJoin");
        this.teleportOnFirstJoin = plugin.getConfig().getBoolean("settings.teleportOnFirstJoin");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        boolean firstJoin = !player.hasPlayedBefore();

        if (!teleportOnJoin && !(teleportOnFirstJoin && firstJoin)) return;

        Location spawnLocation = plugin.getSpawnLocation();
        if (spawnLocation == null) return;

        player.teleport(spawnLocation);
    }
}

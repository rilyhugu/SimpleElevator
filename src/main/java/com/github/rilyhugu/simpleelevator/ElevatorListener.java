package com.github.rilyhugu.simpleelevator;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import static com.github.rilyhugu.simpleelevator.SimpleElevator.getInstance;

public class ElevatorListener implements Listener {

    private final FileConfiguration config = getInstance().getConfig();

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e) {
        Player player = e.getPlayer();
        Block belowBlock = player.getLocation().add(0, -1, 0).getBlock();

        if(belowBlock.getType() != Material.IRON_BLOCK) return;

        Location searchLoc = e.getPlayer().getLocation().add(0, 2, 0);
        for(int i = 0; i < config.getInt("searchDistance"); i++) {
            if(searchLoc.getBlock().getType() != Material.IRON_BLOCK) {
                searchLoc.add(0, 1, 0);
            } else {
                player.teleport(searchLoc.add(0, 1, 0));
                e.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        if(!e.isSneaking()) return;

        Player player = e.getPlayer();
        Block belowBlock = player.getLocation().add(0, -1, 0).getBlock();

        if(belowBlock.getType() != Material.IRON_BLOCK) return;

        Location searchLoc = e.getPlayer().getLocation().add(0, -2, 0);
        for(int i = 0; i < config.getInt("searchDistance"); i++) {
            if(searchLoc.getBlock().getType() != Material.IRON_BLOCK) {
                searchLoc.add(0, -1, 0);
            } else {
                player.teleport(searchLoc.add(0, 1, 0));
                e.setCancelled(true);
                break;
            }
        }
    }
}
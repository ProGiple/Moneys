package org.satellite.dev.progiple.moneys.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.satellite.dev.progiple.moneys.economy.SalaryRunnable;

public class PlayerQuitHandler implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (!SalaryRunnable.getTasks().containsKey(player)) return;

        int taskId = SalaryRunnable.getTasks().get(player);
        if (Bukkit.getScheduler().isQueued(taskId) ||
            Bukkit.getScheduler().isCurrentlyRunning(taskId)) Bukkit.getScheduler().cancelTask(taskId);
    }
}

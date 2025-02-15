package org.satellite.dev.progiple.moneys.handlers;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.satellite.dev.progiple.moneys.Moneys;
import org.satellite.dev.progiple.moneys.economy.SalaryRunnable;
import org.satellite.dev.progiple.moneys.configs.config.ConfigManager;

@AllArgsConstructor
public class PlayerJoinHandler implements Listener {
    private final Moneys plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        long time = ConfigManager.getInt("config.salaryTime") * 20L;
        Player player = e.getPlayer();

        SalaryRunnable.getTasks().put(player, new SalaryRunnable(e.getPlayer()).runTaskTimerAsynchronously(
                this.plugin, time, time).getTaskId());
    }
}

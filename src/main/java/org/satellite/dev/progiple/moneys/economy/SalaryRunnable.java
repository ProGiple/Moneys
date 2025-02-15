package org.satellite.dev.progiple.moneys.economy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.satellite.dev.progiple.moneys.other.Utils;
import org.satellite.dev.progiple.moneys.other.configs.config.ConfigManager;
import org.satellite.dev.progiple.moneys.other.configs.data.DataManager;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SalaryRunnable extends BukkitRunnable {
    @Getter private static final Map<Player, Integer> tasks = new HashMap<>();
    private final Player player;

    @Override
    public void run() {
        double multiplier = Utils.getMultiplier(this.player);
        double money = ConfigManager.getInt("config.salary") * multiplier;
        DataManager.setBalance(this.player.getName(), DataManager.getBalance(this.player.getName()) + money);
        this.player.sendMessage(ConfigManager.getMessage("salary")
                .replace("{money}", String.valueOf(money))
                .replace("{bonus}", String.valueOf(multiplier)));
    }
}

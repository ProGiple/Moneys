package org.satellite.dev.progiple.moneys;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.satellite.dev.progiple.moneys.other.configs.config.ConfigManager;
import org.satellite.dev.progiple.moneys.other.configs.data.DataManager;

import java.util.List;
import java.util.stream.Collectors;

public class Command implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length >= 1) {
            boolean hasAdminPermission = sender.hasPermission("moneys.admin") || sender.isOp();
            switch (strings[0]) {
                case "balance" -> this.checkBalance(sender);
                case "reload" -> {
                    if (hasAdminPermission) {
                        ConfigManager.reload();
                        DataManager.reload();
                        sender.sendMessage(ConfigManager.getMessage("reload"));
                    }
                    else sender.sendMessage(ConfigManager.getMessage("noPermission"));
                }
                case "give", "take", "set" -> {
                    if (hasAdminPermission) {
                        if (strings.length >= 3) {
                            double aMoney = Double.parseDouble(strings[1]);
                            double balance = DataManager.getBalance(strings[2]);
                            DataManager.setBalance(strings[2],
                                    (strings[0].equalsIgnoreCase("give") ? balance + Math.abs(aMoney) :
                                            strings[0].equalsIgnoreCase("take") ? balance - Math.abs(aMoney) : aMoney));
                            DataManager.save();

                            sender.sendMessage(ConfigManager.getMessage("edited").replace("{player}", strings[2]));
                        }
                        else sender.sendMessage(ConfigManager.getMessage("noArgs"));
                    }
                    else sender.sendMessage(ConfigManager.getMessage("noPermission"));
                }
            }
        }
        else this.checkBalance(sender);
        return true;
    }

    private void checkBalance(CommandSender sender) {
        if (!(sender instanceof Player)) return;

        if (sender.hasPermission("moneys.balance")) {
            sender.sendMessage(ConfigManager.getMessage("balance")
                    .replace("{money}", String.valueOf(DataManager.getBalance(sender.getName()))));
        }
        else sender.sendMessage(ConfigManager.getMessage("noPermission"));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            return List.of("give", "set", "take", "balance", "reload");
        }
        else if (List.of("give", "set", "take").contains(strings[0])) {
            if (strings.length == 2) return List.of("<сумма>");
            else if (strings.length == 3)
                return Bukkit.getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(text -> text.toLowerCase().startsWith(strings[2].toLowerCase()))
                        .collect(Collectors.toList());
        }
        return List.of();
    }
}

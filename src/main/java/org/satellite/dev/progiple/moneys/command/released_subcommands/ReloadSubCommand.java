package org.satellite.dev.progiple.moneys.command.released_subcommands;

import org.bukkit.command.CommandSender;
import org.satellite.dev.progiple.moneys.command.SubCommand;
import org.satellite.dev.progiple.moneys.configs.config.ConfigManager;
import org.satellite.dev.progiple.moneys.configs.data.DataManager;

import java.util.List;

public class ReloadSubCommand implements SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("moneys.admin") || sender.isOp()) {
            ConfigManager.reload();
            DataManager.reload();
            sender.sendMessage(ConfigManager.getMessage("reload"));
        }
        else sender.sendMessage(ConfigManager.getMessage("noPermission"));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}

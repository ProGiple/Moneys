package org.satellite.dev.progiple.moneys.command.released_subcommands;

import org.bukkit.command.CommandSender;
import org.satellite.dev.progiple.moneys.command.SubCommand;
import org.satellite.dev.progiple.moneys.configs.config.ConfigManager;
import org.satellite.dev.progiple.moneys.configs.data.DataManager;
import org.satellite.dev.progiple.moneys.Utils;

import java.util.List;

public class SetSubCommand implements SubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("moneys.admin") || sender.isOp()) {
            if (args.length >= 3) {
                DataManager.setBalance(args[2], Double.parseDouble(args[1]));
                DataManager.save();

                sender.sendMessage(ConfigManager.getMessage("edited").replace("{player}", args[2]));
            }
            else sender.sendMessage(ConfigManager.getMessage("noArgs"));
        }
        else sender.sendMessage(ConfigManager.getMessage("noPermission"));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Utils.getDefaultCompleter(args);
    }
}

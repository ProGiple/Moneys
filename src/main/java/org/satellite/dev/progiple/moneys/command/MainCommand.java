package org.satellite.dev.progiple.moneys.command;

import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.satellite.dev.progiple.moneys.command.released_subcommands.*;
import org.satellite.dev.progiple.moneys.configs.config.ConfigManager;

import java.util.HashMap;
import java.util.Map;

public class MainCommand implements CommandExecutor {
    @Getter
    private final static Map<String, SubCommand> subCommands = new HashMap<>();

    public MainCommand() {
        new ReloadSubCommand().register();
        new GiveSubCommand().register();
        new SetSubCommand().register();
        new TakeSubCommand().register();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            SubCommand subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                subCommand.execute(sender, args);
                return true;
            }
        }
        sender.sendMessage(ConfigManager.getMessage("unknownCommand"));
        return true;
    }
}

package org.satellite.dev.progiple.moneys.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(MainCommand.getSubCommands().keySet());
        }
        else if (args.length >= 2) {
            ISubCommand subCommand = MainCommand.getSubCommands().get(args[0].toLowerCase());
            if (subCommand != null) {
                return subCommand.tabComplete(sender, args);
            }
        }
        return List.of();
    }
}

package org.satellite.dev.progiple.moneys.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommand {
    String getName();
    void execute(CommandSender sender, String[] args);
    List<String> tabComplete(CommandSender sender, String[] args);

    default void register() {
        MainCommand.getSubCommands().put(this.getName().toLowerCase(), this);
    }
}

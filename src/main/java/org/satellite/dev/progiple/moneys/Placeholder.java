package org.satellite.dev.progiple.moneys;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.satellite.dev.progiple.moneys.configs.data.DataManager;

public class Placeholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "money";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ProGiple";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        double balance = DataManager.getBalance(player.getName());
        if (params.contains("int")) return String.valueOf((int) balance);
        return String.valueOf(balance);
    }
}

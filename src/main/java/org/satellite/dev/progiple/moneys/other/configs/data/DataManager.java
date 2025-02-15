package org.satellite.dev.progiple.moneys.other.configs.data;

import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;

@UtilityClass
public class DataManager {
    private final DataConfiguration dataConfig;
    static {
        dataConfig = new DataConfiguration();
    }

    public void reload() {
        dataConfig.reload();
    }

    public void save() {
        dataConfig.save();
    }

    public ConfigurationSection getSection() {
        return dataConfig.getSection();
    }

    public double getBalance(String nick) {
        return dataConfig.getBalance(nick);
    }

    public void setBalance(String nick, double newBalance) {
        dataConfig.setBalance(nick, newBalance);
    }

    public boolean hasPlayer(String nick) {
        return getSection().getKeys(false).contains(nick);
    }
}

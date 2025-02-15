package org.satellite.dev.progiple.moneys.configs.data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.satellite.dev.progiple.moneys.Moneys;

import java.io.File;
import java.io.IOException;

public class DataConfiguration {
    private final File file;
    private FileConfiguration config;
    public DataConfiguration() {
        this.file = new File(Moneys.getINSTANCE().getDataFolder(), "data.yml");
        this.reload();
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ConfigurationSection getSection() {
        return this.config.getConfigurationSection("players");
    }

    public double getBalance(String nick) {
        return this.config.getInt(String.format("players.%s", nick));
    }

    public void setBalance(String nick, double newBalance) {
        this.config.set(String.format("players.%s", nick), newBalance < 0 ? 0 : newBalance);
    }
}

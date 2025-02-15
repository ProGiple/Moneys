package org.satellite.dev.progiple.moneys.other.configs.config;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.satellite.dev.progiple.moneys.Moneys;
import org.satellite.dev.progiple.moneys.other.Utils;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    @Getter
    private final Map<String, String> messages = new HashMap<>();
    private FileConfiguration config;
    public Configuration() {
        this.config = Moneys.getINSTANCE().getConfig();
        this.loadMessages();
    }

    public void reload() {
        Moneys.getINSTANCE().reloadConfig();
        this.config = Moneys.getINSTANCE().getConfig();
        this.loadMessages();
    }

    public void loadMessages() {
        ConfigurationSection messageSection = this.config.getConfigurationSection("messages");
        if (messageSection == null) return;

        this.messages.clear();
        for (String key : messageSection.getKeys(false)) {
            this.messages.put(key, Utils.color(messageSection.getString(key)));
        }
    }

    public int getInt(String path) {
        return this.config.getInt(path);
    }
}

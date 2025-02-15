package org.satellite.dev.progiple.moneys.other.configs.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigManager {
    private final Configuration config;
    static {
        config = new Configuration();
    }

    public void reload() {
        config.reload();
    }

    public String getMessage(String id) {
        return config.getMessages().get(id);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }
}

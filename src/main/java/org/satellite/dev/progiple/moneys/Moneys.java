package org.satellite.dev.progiple.moneys;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.satellite.dev.progiple.moneys.command.CommandCompleter;
import org.satellite.dev.progiple.moneys.economy.MoneysEconomy;
import org.satellite.dev.progiple.moneys.handlers.PlayerJoinHandler;
import org.satellite.dev.progiple.moneys.handlers.PlayerQuitHandler;
import org.satellite.dev.progiple.moneys.command.MainCommand;

import java.io.File;

public final class Moneys extends JavaPlugin {
    @Getter private static Moneys INSTANCE;

    @Override
    public void onLoad() {
        Plugin vault = this.getServer().getPluginManager().getPlugin("Vault");
        if (vault != null) {
            RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager()
                    .getRegistration(Economy.class);
            if (economyProvider != null) {
                this.getServer().getServicesManager().unregister(economyProvider.getProvider());
            }
            this.getServer().getServicesManager()
                    .register(Economy.class, new MoneysEconomy(this), this, ServicePriority.Highest);
        }
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        if (!(new File(this.getDataFolder(), "data.yml").exists())) this.saveResource("data.yml", false);

        if (Bukkit.getServer().getPluginManager()
                .getPlugin("PlaceholderAPI") != null) new Placeholder().register();
        if (Bukkit.getServer().getPluginManager()
                .getPlugin("Vault") == null) {
            System.out.println("У вас не установлен Vault!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PluginCommand pluginCommand = this.getCommand("moneys");
        if (pluginCommand != null) {
            pluginCommand.setExecutor(new MainCommand());
            pluginCommand.setTabCompleter(new CommandCompleter());
        }

        this.reg(new PlayerJoinHandler(this));
        this.reg(new PlayerQuitHandler());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void reg(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }
}

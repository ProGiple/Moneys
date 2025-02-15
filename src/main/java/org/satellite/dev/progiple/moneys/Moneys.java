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
import org.satellite.dev.progiple.moneys.economy.MoneyEconomy;
import org.satellite.dev.progiple.moneys.listeners.OnJoin;
import org.satellite.dev.progiple.moneys.listeners.OnQuit;
import org.satellite.dev.progiple.moneys.other.Placeholder;

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
                    .register(Economy.class, new MoneyEconomy(this), this, ServicePriority.Highest);
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
            Command command = new Command();
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
        }

        this.reg(new OnJoin(this));
        this.reg(new OnQuit());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void reg(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
    }
}

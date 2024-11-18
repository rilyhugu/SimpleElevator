package com.github.rilyhugu.simpleelevator;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleElevator extends JavaPlugin {
    private static SimpleElevator instance;

    @Override
    public void onEnable() {
        FileConfiguration config = getInstance().getConfig();

        config.addDefault("searchDistance", 50);
        config.options().copyDefaults(true);

        getInstance().saveConfig();

        Bukkit.getPluginManager().registerEvents(new ElevatorListener(), this.getInstance());
    }

    public static SimpleElevator getInstance() {
        return instance;
    }
}

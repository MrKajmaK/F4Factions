package me.f4dev.f4factions;

import org.bukkit.plugin.java.JavaPlugin;

public final class F4factions extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("F4Factions has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("F4Factions has been disabled.");
    }
}

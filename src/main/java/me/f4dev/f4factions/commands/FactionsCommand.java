package me.f4dev.f4factions.commands;

import me.f4dev.f4factions.F4factions;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FactionsCommand implements CommandExecutor {
    F4factions plugin;

    public FactionsCommand(F4factions plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("factions").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return false;
    }
}
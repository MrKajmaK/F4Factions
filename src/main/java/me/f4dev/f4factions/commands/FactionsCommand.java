package me.f4dev.f4factions.commands;

import me.f4dev.f4factions.F4factions;
import me.f4dev.f4factions.factions.Faction;
import me.f4dev.f4factions.factions.FactionRoles;
import me.f4dev.f4factions.factions.FactionsManager;
import me.f4dev.f4factions.players.FactionsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class FactionsCommand implements CommandExecutor {
    F4factions plugin;

    public FactionsCommand(F4factions plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("factions").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length > 0) {
            switch(args[0]) {
                case "create":
                case "c":
                    if(sender instanceof Player) {
                        if(sender.hasPermission("f4.factions.create")) {
                            if(args.length == 2) {
                                String tag = args[1];
                                String leader = sender.getName();
                                FactionsPlayer player = plugin.playersManager.getPlayer(leader);

                                if(player.getFaction() == 0) {
                                    if(plugin.factionsManager.getFaction(tag) == null) {
                                        try {
                                            Faction faction = plugin.factionsManager.createFaction(tag, leader);

                                            if(faction != null) {
                                                player.setFaction(faction);
                                                player.setRole(FactionRoles.LEADER);
                                                plugin.playersManager.updatePlayer(leader, player);

                                                sender.sendMessage("CREATED FACTION WITH TAG " + tag);
                                            }
                                        } catch(SQLException e) {
                                            sender.sendMessage("SQL ERROR");
                                        }
                                    } else {
                                        sender.sendMessage("FACTION WITH TAG " + tag + " EXISTS");
                                    }
                                } else {
                                    sender.sendMessage("YOU ARE IN FACTION");
                                }
                            } else if(args.length == 3) {
                                if(sender.hasPermission("f4.factions.create.other")) {
                                    String tag = args[1];
                                    String leader = args[2];
                                    FactionsPlayer player = plugin.playersManager.getPlayer(leader);

                                    if(player != null) {
                                        if(player.getFaction() == 0) {
                                            if(plugin.factionsManager.getFaction(tag) == null) {
                                                try {
                                                    Faction faction = plugin.factionsManager.createFaction(tag, leader);

                                                    if(faction != null) {
                                                        player.setFaction(faction);
                                                        player.setRole(FactionRoles.LEADER);
                                                        plugin.playersManager.updatePlayer(leader, player);
                                                    }
                                                } catch(SQLException e) {
                                                    sender.sendMessage("SQL ERROR");
                                                }
                                            }
                                        } else {
                                            sender.sendMessage("PLAYER " + leader + " IS IN FACTION");
                                        }
                                    } else {
                                        sender.sendMessage("PLAYER " + leader + " DOESN'T EXISTS");
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage("NO PERMISSIONS");
                        }
                    } else {
                        if(sender.hasPermission("f4.factions.create.other")) {
                            if(args.length == 3) {
                                String tag = args[1];
                                String leader = args[2];
                                FactionsPlayer player = plugin.playersManager.getPlayer(leader);

                                if(player != null) {
                                    if(player.getFaction() == 0) {
                                        if(plugin.factionsManager.getFaction(tag) == null) {
                                            try {
                                                Faction faction = plugin.factionsManager.createFaction(tag, leader);

                                                if(faction != null) {
                                                    player.setFaction(faction);
                                                    player.setRole(FactionRoles.LEADER);
                                                    plugin.playersManager.updatePlayer(leader, player);
                                                }
                                            } catch(SQLException e) {
                                                sender.sendMessage("SQL ERROR");
                                            }
                                        }
                                    } else {
                                        sender.sendMessage("PLAYER " + leader + " IS IN FACTION");
                                    }
                                } else {
                                    sender.sendMessage("PLAYER " + leader + " DOESN'T EXISTS");
                                }
                            }
                        }
                    }
            }
        }
        return false;
    }
}
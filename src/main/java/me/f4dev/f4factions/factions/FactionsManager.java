package me.f4dev.f4factions.factions;

import me.f4dev.f4factions.F4factions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FactionsManager {
    private F4factions plugin;

    private HashMap<Integer, Faction> factions = new HashMap<>();
    private HashMap<String, Integer> factionsByTag = new HashMap<>();

    public FactionsManager(F4factions plugin) throws SQLException {
        this.plugin = plugin;

        ResultSet factionsSet = plugin.statement.executeQuery("SELECT * FROM factions");

        while(factionsSet.next()) {
            int id = factionsSet.getInt("id");
            String tag = factionsSet.getString("tag");
            String leader = factionsSet.getString("leader");

            Faction faction = new Faction(id, tag, leader);

            factions.put(id, faction);
            factionsByTag.put(tag, id);
        }
    }

    public Faction getFaction(int id) {
        return factions.get(id);
    }

    public Faction getFaction(String tag) {
        return factions.get(factionsByTag.get(tag));
    }

    public boolean addFaction(Faction faction) {
        int id = faction.getId();
        String tag = faction.getTag();

        if(!factions.containsKey(id)) {
            factions.put(id, faction);
            factionsByTag.put(tag, id);

            return true;
        } else {
            return false;
        }
    }

    public boolean updateFaction(int id, Faction faction) {
        if(factions.containsKey(id)) {
            factions.replace(id, faction);

            return true;
        } else {
            return false;
        }
    }

    public boolean updateFaction(String tag, Faction faction) {
        if(factionsByTag.containsKey(tag)) {
            factions.replace(factionsByTag.get(tag), faction);

            return true;
        } else {
            return false;
        }
    }
}

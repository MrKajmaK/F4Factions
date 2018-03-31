package me.f4dev.f4factions.players;

import me.f4dev.f4factions.F4factions;
import me.f4dev.f4factions.factions.Faction;
import me.f4dev.f4factions.factions.FactionRoles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayersManager {
    private F4factions plugin;

    private HashMap<Integer, FactionsPlayer> players = new HashMap<>();
    private HashMap<String, Integer> playersByNick = new HashMap<>();

    public PlayersManager(F4factions plugin) throws SQLException {
        this.plugin = plugin;

        ResultSet playersSet = plugin.statement.executeQuery("SELECT * FROM players");

        while(playersSet.next()) {
            int id = playersSet.getInt("id");
            String nickname = playersSet.getString("nickname");
            int faction = playersSet.getInt("faction");
            int roleInt = playersSet.getInt("role");
            FactionRoles role = FactionRoles.valueOf(roleInt);
            FactionsPlayer factionsPlayer;

            if(role != null) {
                factionsPlayer = new FactionsPlayer(id, nickname, faction, role);
            } else {
                factionsPlayer = new FactionsPlayer(id, nickname, 0, null);
            }

            players.put(id, factionsPlayer);
            playersByNick.put(nickname, id);
        }
    }

    public FactionsPlayer getPlayer(int id) {
        return players.get(id);
    }

    public FactionsPlayer getPlayer(String nickname) {
        return players.get(playersByNick.get(nickname));
    }

    public boolean addPlayer(FactionsPlayer factionsPlayer) {
        int id = factionsPlayer.getId();
        String nickname = factionsPlayer.getNickname();

        if(!players.containsKey(id)) {
            players.put(id, factionsPlayer);
            playersByNick.put(nickname, id);

            return true;
        } else {
            return false;
        }
    }

    public boolean updatePlayer(int id, FactionsPlayer factionsPlayer) {
        if(players.containsKey(id)) {
            players.replace(id, factionsPlayer);

            return true;
        } else {
            return false;
        }
    }

    public boolean updatePlayer(String nickname, FactionsPlayer factionsPlayer) {
        if(playersByNick.containsKey(nickname)) {
            players.replace(playersByNick.get(nickname), factionsPlayer);

            return true;
        } else {
            return false;
        }
    }
}

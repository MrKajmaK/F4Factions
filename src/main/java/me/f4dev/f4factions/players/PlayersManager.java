package me.f4dev.f4factions.players;

import me.f4dev.f4factions.F4factions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayersManager {
    private F4factions plugin;

    private HashMap<Integer, Player> players = new HashMap<>();
    private HashMap<String, Integer> playersByNick = new HashMap<>();

    public PlayersManager(F4factions plugin) throws SQLException {
        this.plugin = plugin;

        ResultSet playersSet = plugin.statement.executeQuery("SELECT * FROM players");

        while(playersSet.next()) {
            int id = playersSet.getInt("id");
            String nickname = playersSet.getString("nickname");
            int faction = playersSet.getInt("faction");
            String rank = playersSet.getString("rank");

            Player player = new Player(id, nickname, faction, rank);

            players.put(id, player);
            playersByNick.put(nickname, id);
        }
    }

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public Player getPlayer(String nickname) {
        return players.get(playersByNick.get(nickname));
    }

    public boolean addPlayer(Player player) {
        int id = player.getId();
        String nickname = player.getNickname();

        if(!players.containsKey(id)) {
            players.put(id, player);
            playersByNick.put(nickname, id);

            return true;
        } else {
            return false;
        }
    }

    public boolean updatePlayer(int id, Player player) {
        if(players.containsKey(id)) {
            players.replace(id, player);

            return true;
        } else {
            return false;
        }
    }

    public boolean updatePlayer(String nickname, Player player) {
        if(playersByNick.containsKey(nickname)) {
            players.replace(playersByNick.get(nickname), player);

            return true;
        } else {
            return false;
        }
    }
}

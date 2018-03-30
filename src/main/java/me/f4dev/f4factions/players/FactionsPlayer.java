package me.f4dev.f4factions.players;

public class FactionsPlayer {
    private int id, faction;
    private String nickname, rank;

    public FactionsPlayer(int id, String nickname, int faction, String rank) {
        this.id = id;
        this.nickname = nickname;
        this.faction = faction;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}

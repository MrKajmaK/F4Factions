package me.f4dev.f4factions.players;

import me.f4dev.f4factions.factions.Faction;
import me.f4dev.f4factions.factions.FactionRoles;

public class FactionsPlayer {
    private int id, faction;
    private String nickname;
    private FactionRoles role;

    public FactionsPlayer(int id, String nickname, int faction, FactionRoles role) {
        this.id = id;
        this.nickname = nickname;
        this.faction = faction;
        this.role = role;
    }

    public FactionsPlayer(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.faction = 0;
        this.role = null;
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

    public void setFaction(Faction faction) {
        setFaction(faction.getId());
    }

    public FactionRoles getRole() {
        return role;
    }

    public void setRole(FactionRoles role) {
        this.role = role;
    }
}

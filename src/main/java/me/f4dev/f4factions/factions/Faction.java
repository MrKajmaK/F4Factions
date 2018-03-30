package me.f4dev.f4factions.factions;

public class Faction {
    private int id;
    private String tag, leader;

    public Faction(int id, String tag, String leader) {
        this.id = id;
        this.tag = tag;
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}

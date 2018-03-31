package me.f4dev.f4factions.factions;

import java.util.HashMap;
import java.util.Map;

public enum FactionRoles {
    LEADER(2),
    OFFICER(1),
    MEMBER(0);

    int value;
    private static Map<Integer, FactionRoles> map = new HashMap<Integer, FactionRoles>();

    FactionRoles(int value) {
        this.value = value;
    }

    static {
        for(FactionRoles role : FactionRoles.values()) {
            map.put(role.value, role);
        }
    }

    public static FactionRoles valueOf(int value) {
        return map.getOrDefault(value, null);
    }
}

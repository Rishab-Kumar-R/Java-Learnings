package com.rishab.gameConsole.pirate;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Weapon {
    KNIFE(0, 10),
    AXE(0, 30),
    MACHETE(1, 40),
    PISTOL(1, 50);

    private final int minLevel;
    private final int hitPoints;

    Weapon(int minLevel, int hitPoints) {
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public static Weapon getWeaponByChar(char firstChar) {
        for (Weapon w : values()) {
            if (w.name().charAt(0) == firstChar) {
                return w;
            }
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponsByLevel(int level) {
        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.minLevel > level));
        return weapons;
    }
}

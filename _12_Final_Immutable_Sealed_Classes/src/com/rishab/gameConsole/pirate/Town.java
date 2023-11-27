package com.rishab.gameConsole.pirate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public record Town(String name, String islandName, int level,
                   List<Loot> loot, List<Feature> features, List<Combatant> opponents) {

    public Town {
        loot = randomReducer(new ArrayList<>(EnumSet.allOf(Loot.class)), level + 2);
        features = randomReducer(new ArrayList<>(EnumSet.allOf(Feature.class)), level + 3);
        opponents = new ArrayList<>();

        if (level == 0) {
            opponents.add(new Islander("Willy", Weapon.KNIFE));
        } else {
            opponents.add(new Islander("Willy", Weapon.MACHETE));
            opponents.add(new Soldier("Eddy", Weapon.PISTOL));
        }
    }

    public Town(String name, String islandName, int level) {
        this(name, islandName, level, null, null, null);
    }

    private <T> List<T> randomReducer(List<T> list, int size) {
        Collections.shuffle(list);
        return list.subList(0, size);
    }

    @Override
    public String toString() {
        return name + " on " + islandName + " (level " + level + ")";
    }

    public String information() {
        return "Town: " + this + "\n\tLoot: " + loot + "\n\tFeatures: " + features + "\n\tOpponents: " + opponents;
    }

    public List<Loot> loot() {
        return (loot == null) ? null : new ArrayList<>(loot);
    }

    public List<Feature> features() {
        return (features == null) ? null : new ArrayList<>(features);
    }

    public List<Combatant> opponents() {
        return (opponents == null) ? null : new ArrayList<>(opponents);
    }
}

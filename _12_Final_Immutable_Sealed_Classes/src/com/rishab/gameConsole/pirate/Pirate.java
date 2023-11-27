package com.rishab.gameConsole.pirate;

import java.util.*;

public final class Pirate extends Combatant {
    private List<Town> townsVisited = new LinkedList<Town>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate(String name) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();
    }

    boolean useWeapon() {
        int count = opponents.size();
        if (count > 0) {
            int opponentIndex = count - 1;
            if (count > 1) {
                opponentIndex = new Random().nextInt(count + 1);
            }
            Combatant combatant = opponents.get(opponentIndex);
            if (super.useWeapon(combatant)) {
                opponents.remove(opponentIndex);
            } else {
                return combatant.useWeapon(this);
            }
        }
        return false;
    }

    boolean visitTown() {
        List<Town> levelTowns = PirateGame.getTowns(value("level"));
        if (levelTowns == null) return true;

        Town town = levelTowns.get(value("townIndex"));
        if (town != null) {
            townsVisited.add(town);
            loot = town.loot();
            features = town.features();
            opponents = town.opponents();
            return false;
        }
        return true;
    }

    boolean hasExperiences() {
        return (features != null && !features.isEmpty());
    }

    boolean hasOpponents() {
        return (opponents != null && !opponents.isEmpty());
    }

    boolean findLoot() {
        if (!loot.isEmpty()) {
            Loot item = loot.remove(0);
            System.out.println("You found " + item);
            adjustValue("score", item.getWorth());
            System.out.println(name() + " has " + value("score") + " health points");
        }
        if (loot.isEmpty()) {
            return visitNextTown();
        }
        return false;
    }

    boolean experienceFeature() {
        if (!features.isEmpty()) {
            Feature item = features.remove(0);
            System.out.println("You experienced " + item);
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + " has " + value("health") + " health points");
        }
        return (value("health") <= 0);
    }

    public String information() {
        Town current = ((LinkedList<Town>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
        return "———>" + current + "<———\n" + super.information() +
            "\n\tTowns visited: " + Arrays.toString(simpleNames);
    }

    private boolean visitNextTown() {
        int townIndex = value("townIndex");
        List<Town> towns = PirateGame.getTowns(value("level"));

        if (towns == null) return true;

        if (townIndex >= (towns.size() - 1)) {
            System.out.println("You have visited all towns in this level");
            System.out.println("You received a bonus of 500 points. Let's move to the next level!!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("You have visited " + (townIndex + 1) + " towns in this level");
            System.out.println("You received a bonus of 50 points. Let's move to the next town!!");
            adjustValue("townIndex", 1);
            adjustValue("score", 50);
        }

        return visitTown();
    }
}

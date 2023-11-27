package com.rishab.gameConsole.pirate;

import com.rishab.gameConsole.game.Player;

import java.util.*;

public sealed abstract class Combatant implements Player permits Islander, Pirate, Soldier {
    private final String name;
    private final Map<String, Integer> gameData;
    private Weapon currentWeapon;

    public Combatant(String name) {
        this.name = name;
    }

    public Combatant(String name, Map<String, Integer> gameData) {
        this.name = name;
        if (gameData != null) {
            this.gameData.putAll(gameData);
        }
    }

    {
        gameData = new HashMap<>(Map.of(
            "health", 100,
            "score", 0
        ));
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    int value(String name) {
        return gameData.get(name);
    }

    protected void setValue(String name, int value) {
        gameData.put(name, value);
    }

    protected void adjustValue(String name, int adjustment) {
        gameData.compute(name, (k, v) -> v += adjustment);
    }

    protected void adjustHealth(int adjustment) {
        int health = value("health");
        health += adjustment;
        health = (health < 0) ? 0 : (Math.min(health, 100));
        setValue("health", health);
    }

    boolean useWeapon(Combatant opponent) {
        System.out.println("You are fighting " + opponent.name());
        System.out.println(name + " used the " + currentWeapon);

        if (new Random().nextBoolean()) {
            System.out.println("You hit " + opponent.name + " with the " + currentWeapon);
            opponent.adjustHealth(-currentWeapon.getHitPoints());
            System.out.printf("%s's health is now %d, %s's health is now %d%n",
                name, value("health"), opponent.name(), opponent.value("health"));
            adjustValue("score", 50);
        } else {
            System.out.println("You missed " + opponent.name + " with the " + currentWeapon);
        }
        return opponent.value("health") <= 0;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String information() {
        return name + " " + gameData;
    }
}

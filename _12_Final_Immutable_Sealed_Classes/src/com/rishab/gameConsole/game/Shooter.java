package com.rishab.gameConsole.game;

public record Shooter(String name) implements Player {
    boolean findPrize() {
        System.out.println("Price found!");
        return false;
    }

    boolean useWeapon(String weapon) {
        System.out.println("You used " + weapon);
        return false;
    }
}

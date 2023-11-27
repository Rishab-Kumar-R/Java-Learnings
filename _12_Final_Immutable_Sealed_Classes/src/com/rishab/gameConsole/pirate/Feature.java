package com.rishab.gameConsole.pirate;

public enum Feature {
    ALLIGATOR(-45),
    ALOE_VERA(5),
    JELLYFISH(-10),
    PINEAPPLE(10),
    SNAKE(-25),
    SPRING(25),
    SUN_POISONING(-15);

    private final int healthPoints;

    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}

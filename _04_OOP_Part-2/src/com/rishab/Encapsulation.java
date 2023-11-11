package com.rishab;

public class Encapsulation {
    public static void main(String[] args) {

        // Player player = new Player();
        // player.name = "Player 1";
        // player.health = 20;
        // player.weapon = "Sword";

        // int damage = 10;
        // player.loseHealth(damage);
        // player.health = 200; // This is not a good practice, we should not be able to change the health directly
        // player.loseHealth(11);

        EnhancedPlayer player = new EnhancedPlayer("Tim", 200, "Sword");
        System.out.println("Initial health is " + player.healthRemaining());

        System.out.println();

        Printer printer = new Printer(50, false);
        System.out.println("Initial page count = " + printer.getPagesPrinted());
        int pagesPrinted = printer.printPages(5);
        System.out.println("Pages printed was " + pagesPrinted + ", new total print count for printer = " + printer.getPagesPrinted());
        pagesPrinted = printer.printPages(10);
        System.out.println("Pages printed was " + pagesPrinted + ", new total print count for printer = " + printer.getPagesPrinted());
    }
}

class EnhancedPlayer {
    private String name;
    private int health;
    private String weapon;

    public EnhancedPlayer(String name) {
        this(name, 100, "Sword");
    }

    public EnhancedPlayer(String name, int health, String weapon) {
        this.name = name;
        this.health = (health <= 0) ? 1 : (Math.min(health, 100));
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Player knocked out, health = " + this.healthRemaining());
        } else {
            System.out.println("Remaining health = " + this.healthRemaining());
        }
    }

    public void restoreHealth(int health) {
        this.health += health;
        if (this.health > 100) {
            this.health = 100;
            System.out.println("Health restored to 100");
        } else {
            System.out.println("Health restored to " + this.health);
        }
    }

    public int healthRemaining() {
        return this.health;
    }
}

class Player {
    public String name;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("Player knocked out, health = " + this.healthRemaining());
        } else {
            System.out.println("Remaining health = " + this.healthRemaining());
        }
    }

    public void restoreHealth(int health) {
        this.health += health;
        if (this.health > 100) {
            this.health = 100;
            System.out.println("Health restored to 100");
        } else {
            System.out.println("Health restored to " + this.health);
        }
    }

    public int healthRemaining() {
        return this.health;
    }
}

// Challenge
class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        this.tonerLevel = (tonerLevel >= 0 && tonerLevel <= 100) ? tonerLevel : -1;
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int addToner(int tonerAmount) {
        int newTonerLevel = this.tonerLevel + tonerAmount;
        if (newTonerLevel > 100 || newTonerLevel < 0) {
            return -1;
        }
        this.tonerLevel += tonerAmount;
        return this.tonerLevel;
    }

    public int printPages(int pages) {
        int pagesToPrint = (this.duplex) ? (pages / 2) + (pages % 2) : pages;
        this.pagesPrinted += pagesToPrint;
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return this.pagesPrinted;
    }
}

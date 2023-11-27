package com.rishab.gameConsole;

import com.rishab.gameConsole.game.GameConsole;
import com.rishab.gameConsole.pirate.Pirate;
import com.rishab.gameConsole.pirate.PirateGame;
import com.rishab.gameConsole.pirate.Weapon;

public class Main {
    public static void main(String[] args) {

        // var console = new GameConsole<>(new ShooterGame("The Shooter Game"));
        // int playerIndex = console.addPlayer();
        // console.playGame(playerIndex);

        // Weapon weapon = Weapon.getWeaponByChar('P');
        // System.out.println("weapon = " + weapon + ", hitPoints = " +
        //     weapon.getHitPoints() + ", minLevel = " + weapon.getMinLevel());

        // var list = Weapon.getWeaponsByLevel(1);
        // list.forEach(System.out::println);

        // Pirate pirate = new Pirate("Jack");
        // System.out.println(pirate);

        // PirateGame.getTowns(0).forEach(t -> System.out.println(t.information()));
        // System.out.println("—".repeat(50));
        // PirateGame.getTowns(1).forEach(t -> System.out.println(t.information()));

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);

    }
}

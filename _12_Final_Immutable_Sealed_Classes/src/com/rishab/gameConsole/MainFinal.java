package com.rishab.gameConsole;

import com.rishab.gameConsole.game.Game;
import com.rishab.gameConsole.game.GameAction;
import com.rishab.gameConsole.game.GameConsole;
import com.rishab.gameConsole.game.Player;
import com.rishab.gameConsole.pirate.PirateGame;
import com.rishab.gameConsole.pirate.Weapon;

// class SpecialGameAction extends GameAction {} // Error: Cannot inherit from final 'GameAction'
// class SpecialWeapon extends Weapon {} // Error: Cannot inherit from final 'Weapon'

// class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>> {
//     public SpecialGameConsole(Game<? extends Player> game) {
//         super(game);
//     }
// }

public class MainFinal {
    public static void main(String[] args) {

        // SpecialGameConsole<PirateGame> game = new SpecialGameConsole<>(new PirateGame("The Pirate Game"));
        GameConsole<PirateGame> game = new GameConsole<>(new PirateGame("The Pirate Game"));

    }
}

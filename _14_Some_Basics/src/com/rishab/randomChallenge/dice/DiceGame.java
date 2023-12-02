package com.rishab.randomChallenge.dice;

import com.rishab.randomChallenge.game.Game;
import com.rishab.randomChallenge.game.GameAction;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiceGame extends Game<DicePlayer> {
    public DiceGame(String gameName) {
        super(gameName);
    }

    @Override
    public DicePlayer createNewPlayer(String name) {
        return new DicePlayer(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Map<Character, GameAction> map = new LinkedHashMap<>(Map.of(
            'R', new GameAction('R', "Roll Dice", this::rollDice)
        ));
        map.putAll(getStandardActions());
        return map;
    }

    private boolean rollDice(int playerIndex) {
        return getPlayer(playerIndex).rollDiceAndSelect();
    }
}

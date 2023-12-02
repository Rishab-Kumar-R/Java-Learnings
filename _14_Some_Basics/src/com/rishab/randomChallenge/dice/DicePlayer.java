package com.rishab.randomChallenge.dice;

import com.rishab.randomChallenge.game.GameConsole;
import com.rishab.randomChallenge.game.Player;

import java.util.*;

public class DicePlayer implements Player {
    private final String name;
    private final List<Integer> currentDice = new ArrayList<>();
    private final Map<ScoredItem, Integer> scoreCard = new EnumMap<>(ScoredItem.class);

    public DicePlayer(String name) {
        this.name = name;
        for (ScoredItem item : ScoredItem.values()) {
            scoreCard.put(item, null);
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "DicePlayer{" +
            "name='" + name + '\'' +
            ", currentDice=" + currentDice +
            ", scoreCard=" + scoreCard +
            '}';
    }

    public void rollDice() {
        int randomCount = 5 - currentDice.size();

        var newDice = new Random()
            .ints(randomCount, 1, 7)
            .sorted()
            .boxed()
            .toList();

        currentDice.addAll(newDice);

        System.out.println("Your new dice are: " + currentDice);
    }

    private boolean pickLosers() {
        String prompt = """
            Press enter to Score.
            Type "ALL" to re-roll all dice.
            List numbers (separated by spaces) to re-roll those dice.
            """;

        String userInput = GameConsole.getUserInput(prompt + "———> ");

        if (userInput.isBlank()) {
            return true;
        } try {
            removeDice(userInput.split(" "));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Invalid input. Try again.");
        }
        return false;
    }

    private void removeDice(String[] diceToRemove) {
        if (diceToRemove.length == 1 && diceToRemove[0].contains("ALL")) {
            currentDice.clear();
        } else {
            for (String dice : diceToRemove) {
                currentDice.remove(Integer.valueOf(dice));
            }
            System.out.println("Keeping dice: " + currentDice);
        }
    }

    public boolean rollDiceAndSelect() {
        do {
            rollDice();
        } while (!pickLosers());

        do {
            System.out.println("You must score one of the following items: ");
        } while (!scoreDice());

        currentDice.clear();
        return getItemList().isEmpty();
    }

    public List<String> getItemList() {
        return scoreCard.entrySet().stream()
            .filter(e -> e.getValue() == null)
            .map(e -> e.getKey().name())
            .toList();
    }

    private boolean scoreDice() {
        List<String> remainingItems = getItemList();
        String prompt = String.join("\n", remainingItems.toArray(new String[0]));
        String userInput = GameConsole.getUserInput(prompt + "\n———> ").toUpperCase();
        if (userInput.isBlank()) return false;

        if (!remainingItems.contains(userInput)) {
            System.out.println("Invalid input. Try again.");
            return false;
        }
        ScoredItem item = ScoredItem.valueOf(userInput);
        scoreCard.put(item, item.score(currentDice));
        return true;
    }
}

package com.rishab.gameConsole.game;

import java.util.Scanner;

public final class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();
        System.out.printf("Adding player %s to game %s%n", name, game.getGameName());
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean done = false;
        while (!done) {
            var actions = game.getGameActions(playerIndex);
            System.out.println("Choose an action: ");
            for (Character c : actions.keySet()) {
                String prompt = actions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.print("Choose an action: ");

            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = actions.get(nextMove);

            if (gameAction != null) {
                System.out.println("-".repeat(50));
                done = game.executeGameAction(playerIndex, gameAction);
                if (!done) {
                    System.out.println("-".repeat(50));
                }
            }
        }
    }
}

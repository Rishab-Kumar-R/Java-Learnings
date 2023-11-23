package com.rishab.collectionMethods.games;

import com.rishab.collectionMethods.games.poker.PokerGame;

public class GameController {
    public static void main(String[] args) {

        PokerGame fiveCardDraw = new PokerGame(8, 5);
        fiveCardDraw.startPlay();

    }
}

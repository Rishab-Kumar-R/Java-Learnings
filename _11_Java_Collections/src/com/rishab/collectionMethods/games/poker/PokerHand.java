package com.rishab.collectionMethods.games.poker;

import com.rishab.collectionMethods.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerHand {
    private List<Card> hand;
    private List<Card> keepers;
    private List<Card> discards;
    private Ranking ranking = Ranking.NONE;
    private int playerNo;

    public PokerHand(int playerNo, List<Card> hand) {
        hand.sort(Card.sortRankReversedSuit());
        this.playerNo = playerNo;
        this.hand = hand;
        keepers = new ArrayList<>(hand.size());
        discards = new ArrayList<>(hand.size());
    }

    @Override
    public String toString() {
        return "%d. %-16s Rank:%d %-40s Best:%-7s Worst:%-6s %s".formatted(
            playerNo, ranking, ranking.ordinal(), hand,
            Collections.max(hand, Comparator.comparing(Card::rank)),
            Collections.min(hand, Comparator.comparing(Card::rank)),
            (!discards.isEmpty()) ? "Discards: " + discards : "");
    }

    private void setRank(int faceCount) {
        switch (faceCount) {
            case 4 -> ranking = Ranking.FOUR_OF_A_KIND;
            case 3 -> {
                if (ranking == Ranking.NONE) ranking = Ranking.THREE_OF_A_KIND;
                else ranking = Ranking.FULL_HOUSE;
            }
            case 2 -> {
                if (ranking == Ranking.NONE) ranking = Ranking.ONE_PAIR;
                else if (ranking == Ranking.THREE_OF_A_KIND) ranking = Ranking.FULL_HOUSE;
                else ranking = Ranking.TWO_PAIR;
            }
        }
    }

    public void evalHand() {
        List<String> faceList = new ArrayList<>(hand.size());
        hand.forEach(card -> faceList.add(card.face()));

        List<String> duplicateFaceCards = new ArrayList<>();
        faceList.forEach(face -> {
            if (!duplicateFaceCards.contains(face) && Collections.frequency(faceList, face) > 1) {
                duplicateFaceCards.add(face);
            }
        });

        for (String duplicate : duplicateFaceCards) {
            int start = faceList.indexOf(duplicate);
            int last = faceList.lastIndexOf(duplicate);
            setRank(last - start + 1);
            List<Card> subList = hand.subList(start, last + 1);
            keepers.addAll(subList);
        }

        pickDiscards();
    }

    private void pickDiscards() {
        List<Card> temp = new ArrayList<>(hand);
        temp.removeAll(keepers);

        int rankedCards = keepers.size();
        Collections.reverse(temp);

        int index = 0;
        for (Card card : temp) {
            if (index++ < 3 && (rankedCards > 2 || card.rank() < 9)) discards.add(card);
            else keepers.add(card);
        }
    }
}

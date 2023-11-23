package com.rishab.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String str1 = "Hello"; // 69609650
        String str2 = "Hello"; // 69609650
        String str3 = String.join("l", "He", "lo"); // 69609650
        String str4 = "He".concat("llo"); // 69609650
        String str5 = "hello"; // 99162322

        List<String> hellos = Arrays.asList(str1, str2, str3, str4, str5);
        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));

        // HashSet is a set that uses a hash table for storage.
        Set<String> mySet = new HashSet<>(hellos);
        System.out.println("mySet = " + mySet); // [Hello, hello]
        System.out.println("# of elements in mySet = " + mySet.size()); // 2

        for (String setValue : mySet) {
            System.out.print(setValue + ": ");
            for (int i = 0; i < hellos.size(); i++) {
                if (setValue.equals(hellos.get(i))) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println();
        }

        for (String setValue : mySet) {
            System.out.println(setValue + ": " + setValue.hashCode()); // Hello: 69609650, hello: 99162322
        }

        System.out.println();
        PlayingCard aceOfHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingOfClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenOfSpades = new PlayingCard("Spades", "Queen");

        List<PlayingCard> cards = Arrays.asList(aceOfHearts, kingOfClubs, queenOfSpades);
        cards.forEach(c -> System.out.println(c + ": " + c.hashCode()));

        Set<PlayingCard> deck = new HashSet<>();
        for (PlayingCard card : cards) {
            if (!deck.add(card)) {
                System.out.println("Duplicate card found: " + card);
            }
        }
        System.out.println(deck);

    }
}

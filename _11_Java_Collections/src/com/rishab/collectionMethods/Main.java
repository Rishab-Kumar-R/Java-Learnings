package com.rishab.collectionMethods;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEARTS, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Ace of Hearts", 1);

        // Collections.fill() method is used to replace all the elements of a List with the specified element.
        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("Size of cards: " + cards.size());

        // Collections.nCopies() method is used to get an immutable List consisting of n copies of the specified object.
        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUBS, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        // Collections.addAll() method is used to add all the specified elements to the specified collection.
        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Card Collections with Aces added", 2);

        // Collections.copy() method is used to copy all the elements from one list into another.
        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Card Collections with Kings copied", 2);

        cards = List.copyOf(kingsOfClubs);
        Card.printDeck(cards, "List with Kings copied", 1);

        // Collections.shuffle() method is used to randomly permute the specified list using a default source of randomness.
        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck:", 4);

        // Collections.reverse() method is used to reverse the order of the elements in the specified list.
        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck of Cards:", 4);

        // Collections.sort() method is used to sort the elements of the specified list in ascending order.
        var sortingAlgorithm = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm); // the preferred way is to use List.sort() method
        Card.printDeck(deck, "Sorted Deck of Cards:", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Sorted Deck of Cards:", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4, 8));
        Card.printDeck(kings, "Kings:", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16, 20));
        Card.printDeck(tens, "Tens:", 1);

        // Collections.indexOfSubList() method is used to find the starting position of the first occurrence of the specified target list within the specified source list.
        int startingSubListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("Staring Index of Tens: " + startingSubListIndex);
        System.out.println("Contains Tens: " + deck.containsAll(tens));

        // Collections.disjoint() method is used to check if two collections are disjoint or not.
        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("Disjoint: " + disjoint);
        boolean disjoint2 = Collections.disjoint(kings, tens);
        System.out.println("Disjoint: " + disjoint2);

        deck.sort(sortingAlgorithm);
        // Collections.binarySearch() method is used to search the specified list for the specified object using the binary search algorithm.
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEARTS, 10);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("Found Index: " + foundIndex);
        System.out.println("Found Index: " + deck.indexOf(tenOfHearts));
        System.out.println(deck.get(foundIndex));

        // Collections.replaceAll() method is used to replace all occurrences of one specified value in a list with another.
        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUBS, 10);
        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32, 36), "Replaced Tens:", 1);
        Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
        Card.printDeck(deck.subList(32, 36), "Replaced Tens:", 1);
        if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of Hearts replaced with Tens of Clubs");
        } else {
            System.out.println("Tens of Hearts not found");
        }

        // Collections.frequency() method is used to find the frequency of the specified element in the specified collection.
        System.out.println("Ten of Clubs Cards = " + Collections.frequency(deck, tenOfClubs));

        // Collections.max() method is used to return the maximum element of the given collection, according to the natural ordering of its elements.
        System.out.println("Best Card: " + Collections.max(deck, sortingAlgorithm));
        // Collections.min() method is used to return the minimum element of the given collection, according to the natural ordering of its elements.
        System.out.println("Worst Card: " + Collections.min(deck, sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Sorted By Suit, Rank: ", 4);

        // Collections.rotate() method is used to rotate the elements in the specified list by the specified distance.
        List<Card> copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, 2);
        System.out.println("Un-rotated Deck: " + deck.subList(0, 13));
        System.out.println("Rotated Deck: " + copied);
        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, -2);
        System.out.println("Un-rotated Deck: " + deck.subList(0, 13));
        System.out.println("Rotated Deck: " + copied);

        // Collections.swap() method is used to swap the elements at the specified positions in the specified list.
        copied = new ArrayList<>(deck.subList(0, 13));
        for (int i = 0; i < copied.size() / 2; i++) {
            Collections.swap(copied, i, copied.size() - i - 1);
        }
        System.out.println("Manual Reversed Deck: " + copied);

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.reverse(copied);
        System.out.println("Reversed Deck: " + copied);

    }
}

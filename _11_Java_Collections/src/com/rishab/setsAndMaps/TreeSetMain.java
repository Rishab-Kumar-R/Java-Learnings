package com.rishab.setsAndMaps;

import java.util.*;

public class TreeSetMain {
    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        // NavigableSet<Contact> sorted = new TreeSet<>(phones); // ClassCastException because Contact does not implement Comparable
        Comparator<Contact> contactComparator = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(contactComparator);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

        System.out.println();
        // Strings have a natural ordering (lexicographical)
        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);

        System.out.println();
        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator()); // fullList.sort(contactComparator);
        System.out.println("\nFull List:");
        fullList.forEach(System.out::println);

        Contact min = Collections.min(fullSet, fullSet.comparator()); // returns the minimum element in the set
        Contact max = Collections.max(fullSet, fullSet.comparator()); // returns the maximum element in the set
        Contact first = fullSet.first(); // returns the first element in the set
        Contact last = fullSet.last(); // returns the last element in the set
        System.out.printf("%nmin = %s, first = %s %n", min, first);
        System.out.printf("max = %s, last = %s %n", max, last);

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("\nFirst Element = " + copiedSet.pollFirst()); // removes and returns the first element in the set
        System.out.println("Last Element = " + copiedSet.pollLast()); // removes and returns the last element in the set
        copiedSet.forEach(System.out::println);

        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");

        System.out.println();
        for (Contact c : List.of(daffy, daisy, last, snoopy)) {
            System.out.printf("ceiling(%s) = %s %n", c.getName(), fullSet.ceiling(c)); // returns the least element in the set >= the given element
            System.out.printf("higher(%s) = %s %n", c.getName(), fullSet.higher(c)); // returns the least element in the set > the given element
        }
        for (Contact c : List.of(daffy, daisy, first, archie)) {
            System.out.printf("floor(%s) = %s %n", c.getName(), fullSet.floor(c)); // returns the greatest element in the set <= the given element
            System.out.printf("lower(%s) = %s %n", c.getName(), fullSet.lower(c)); // returns the greatest element in the set < the given element
        }

        System.out.println();
        NavigableSet<Contact> descendingSet = fullSet.descendingSet(); // returns a reverse order view of the set
        descendingSet.forEach(System.out::println);

        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed " + lastContact);
        descendingSet.forEach(System.out::println);
        fullSet.forEach(System.out::println);

        System.out.println();
        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion, true); // returns a view of the portion of the set strictly less than the given element
        headSet.forEach(System.out::println);
        System.out.println("--------------------------------");
        var tailSet = fullSet.tailSet(marion, false); // returns a view of the portion of the set greater than or equal to the given element
        tailSet.forEach(System.out::println);

        System.out.println();
        Contact linus = new Contact("Linus Van Pelt");
        var subset = fullSet.subSet(linus, false, marion, true); // returns a view of the portion of the set between the given elements
        subset.forEach(System.out::println);

    }
}

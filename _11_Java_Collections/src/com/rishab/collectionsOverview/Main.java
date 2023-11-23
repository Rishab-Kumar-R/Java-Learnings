package com.rishab.collectionsOverview;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(List.of());

        // List<String> list = new ArrayList<>();
        // Collection<String> list = new ArrayList<>();
        // Collection<String> list = new TreeSet<>();
        Collection<String> list = new HashSet<>();
        String[] names = {"Adam", "Kevin", "Smith", "John"};

        list.addAll(Arrays.asList(names));
        System.out.println(list);
        list.add("Peter");
        list.addAll(Arrays.asList("Tom", "Sam", "Harry", "Howard"));
        System.out.println(list);
        System.out.println("Harry is in the list? " + list.contains("Harry"));

        list.removeIf(s -> s.charAt(0) == 'H');
        System.out.println(list);
        System.out.println("Harry is in the list? " + list.contains("Harry"));

        // list.sort(); // Cannot resolve method 'sort()' in 'Collection'

    }
}

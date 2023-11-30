package com.rishab.streamIntro;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add(c + String.valueOf(i));
                // System.out.println(c + String.valueOf(i));
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.print(bingoPool.get(i) + " ");
        }
        System.out.println("\n" + "—".repeat(50));

        // List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf('O') == 0) {
                String updated = s.charAt(0) + "—" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n" + "—".repeat(50));

        for (int i = 0; i < 15; i++) {
            System.out.print(bingoPool.get(i) + " ");
        }
        System.out.println("\n" + "—".repeat(50));

        var tempStream = bingoPool.stream()
            .limit(15)
            .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
            .map(s -> s.charAt(0) + "—" + s.substring(1))
            .sorted();
        // .forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s -> System.out.print(s + " "));
        System.out.println("\n" + "—".repeat(50));
        // tempStream.forEach(s -> System.out.print(s.toLowerCase() + " ")); // throws IllegalStateException as stream is already closed

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
            .sorted(Comparator.reverseOrder());
            // .forEach(System.out::println);

        // Static methods on Stream interface
        var secondStream = Stream.of("Six", "Five", "Four")
            .map(String::toUpperCase);
            // .forEach(System.out::println);

        Stream.concat(secondStream, firstStream)
            .map(s -> s.charAt(0) + " — " + s)
            .forEach(System.out::println);

        // The map interface doesn't implement the Collection interface, hence Map interface doesn't have a stream method either,
        // but we can get a stream of keys, values or entries from a map using the keySet, values and entrySet methods
        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

        System.out.println("—".repeat(50));
        myMap.entrySet()
            .stream()
            .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " to " + e.getValue()[e.getValue().length - 1])
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
            .limit(10)
            .forEach(s -> System.out.print(s + " "));

        System.out.println("\n" + "—".repeat(50));
        IntStream.iterate(1, n -> n + 1)
            .filter(Main::isPrime)
            .limit(20)
            .forEach(s -> System.out.print(s + " ")); // 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71

        System.out.println("\n" + "—".repeat(50));
        IntStream.iterate(1, n -> n + 1)
            .limit(100)
            .filter(Main::isPrime)
            .forEach(s -> System.out.print(s + " ")); // 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

        System.out.println("\n" + "—".repeat(50));
        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
            .filter(Main::isPrime)
            .forEach(s -> System.out.print(s + " ")); // 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

        System.out.println("\n" + "—".repeat(50));
        IntStream.rangeClosed(1, 100)
            .filter(Main::isPrime)
            .forEach(s -> System.out.print(s + " ")); // 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97

    }

    public static boolean isPrime(int number) {
        if (number <= 2) return number == 2;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }

        return true;
    }
}

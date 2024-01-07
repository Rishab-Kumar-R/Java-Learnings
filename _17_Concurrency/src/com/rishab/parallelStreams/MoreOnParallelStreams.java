package com.rishab.parallelStreams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
    private final static String[] firstNames = {"Abel", "Bella", "Charlie", "Dua", "Ed", "Fiona"};
    private final static String[] lastNames = {"Tesfaye", "Thorne", "Puth", "Lipa", "Sheeran", "Apple"};
    private final static Random random = new Random();

    public Person() {
        this(firstNames[random.nextInt(firstNames.length)],
            lastNames[random.nextInt(lastNames.length)],
            random.nextInt(18, 100));
    }

    @Override
    public String toString() {
        return String.format("%s, %s (%d)", lastName, firstName, age);
    }
}

public class MoreOnParallelStreams {
    public static void main(String[] args) {

        var persons = Stream.generate(Person::new)
            .limit(10)
            .sorted(Comparator.comparing(Person::lastName))
            .toArray();

        for (var person : persons) {
            System.out.println(person);
        }

        System.out.println("--------------------------------");
        Arrays.stream(persons)
            .limit(10)
            .parallel()
//            .sorted(Comparator.comparing(Person::lastName))
            .forEachOrdered(System.out::println);

        System.out.println("--------------------------------");
        int sum = IntStream.rangeClosed(1, 100)
            .parallel()
            .reduce(0, Integer::sum);
        System.out.println("The sum of the numbers is: " + sum);

        System.out.println("--------------------------------");
        String humptyDumpty = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall.
            All the king's horses and all the king's men
            Couldn't put Humpty together again.
            """;
        var words = new Scanner(humptyDumpty).tokens().toList();
        words.forEach(System.out::println);
        System.out.println("--------------------------------");

        var backTogetherAgain = words
            .parallelStream()
            .collect(Collectors.joining(" "));
        System.out.println(backTogetherAgain);

        System.out.println("--------------------------------");
        Map<String, Long> lastNameCounts = Stream
            .generate(Person::new)
            .limit(10000)
            .parallel()
            .collect(Collectors.groupingByConcurrent(Person::lastName, Collectors.counting()));
        lastNameCounts.entrySet().forEach(System.out::println);

        long total = 0;
        for (long count : lastNameCounts.values()) {
            total += count;
        }
        System.out.println("Total: " + total);
        System.out.println(lastNameCounts.getClass().getName());

        var lastCounts = Collections.synchronizedMap(new TreeMap<String, Long>());
        Stream.generate(Person::new)
            .limit(10000)
            .parallel()
            .forEach(person -> lastCounts.merge(person.lastName(), 1L, Long::sum));
        System.out.println(lastCounts);

        total = 0;
        for (long count : lastCounts.values()) {
            total += count;
        }
        System.out.println("Total: " + total);
        System.out.println(lastCounts.getClass().getName());

    }
}

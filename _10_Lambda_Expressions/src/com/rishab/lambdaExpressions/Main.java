package com.rishab.lambdaExpressions;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Main {
    // records are implicitly static when used as an inner class in the same way enums and interfaces
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return this.firstName + " " + this.lastName;
        }
    }

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>(Arrays.asList(
            new Main.Person("Sally", "Brown"),
            new Person("Charlie", "Brown"),
            new Person("Peppermint", "Patty"),
            new Person("Marcie", "Johnson"),
            new Person("Linus", "van Pelt")
        ));

        // Comparator using anonymous class
        var comparatorLastName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }
        };
        people.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        System.out.println(people);

        // @FunctionalInterface // we can add this annotation to ensure that the interface is functional and can be used as a lambda
        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        // can't be used as a lambda because it is not a functional interface
        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };
        people.sort(comparatorMixed);
        System.out.println(people);

        // a short demo on Consumer Interface
        List<String> list = new ArrayList<>(List.of(
            "alpha", "bravo", "charlie", "delta"
        ));

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
        // The forEach method takes a Consumer interface as an argument
        String prefix = "nato";
        list.forEach(s -> {
            char firstCharacter = s.toUpperCase().charAt(0);
            System.out.println(prefix + " " + s + " means — " + firstCharacter);
        });

        System.out.println();
        int addition = calculator((a, b) -> a + b, 5, 5);
        var division = calculator((a, b) -> a / b, 10.0, 2.5);
        var concat = calculator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Hello", "World");

        System.out.println();
        var coords = Arrays.asList(
            new double[]{47.6197, -122.33},
            new double[]{91.5131, -89.5858},
            new double[]{21.2891, -157.9172},
            new double[]{-0.5074, 166.9460},
            new double[]{-90.0, 0.0}
        );
        coords.forEach(s -> System.out.println(Arrays.toString(s)));

        BiConsumer<Double, Double> biConsumer = (lat, lon) -> System.out.printf("[lat: %.4f, lon: %.4f]%n", lat, lon);
        var firstPoint = coords.get(0);
        processPoint(firstPoint[0], firstPoint[1], biConsumer);

        System.out.println("-".repeat(30));
        coords.forEach(point -> processPoint(point[0], point[1], biConsumer));
        coords.forEach(point ->
            processPoint(point[0], point[1], (lat, lon) -> System.out.printf("[lat: %.4f, lon: %.4f]%n", lat, lon))
        );

        System.out.println();
        // Predicate Interface
        list.removeIf(l -> l.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        list.addAll(List.of("echo", "easy", "early", "eat"));
        list.forEach(s -> System.out.println(s));

        System.out.println("-".repeat(30));
        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(s -> System.out.println(s));

        System.out.println();
        // Function Interface
        // UnaryOperator Interface
        list.replaceAll(s -> s.charAt(0) + " — " + s.toUpperCase());
        list.forEach(s -> System.out.println(s));

        System.out.println();
        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.fill(emptyStrings, "empty"); // alternate to Arrays.fill() we can use Arrays.setAll()
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, i -> (i + 1) + ".");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, i -> (i + 1) + "."
                + switch (i) {
                case 0 -> "one";
                case 1 -> "two";
                case 2 -> "three";
                default -> "";
            }
        );
        System.out.println(Arrays.toString(emptyStrings));

        System.out.println();
        String[] names = {"Peter", "Paul", "Mary", "John", "Bob"};
        String[] randomNames = randomlySelectedValues(15, names, () -> new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(randomNames));

    }

    // BinaryOperator Interface
    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of operation is: " + result);
        return result;
    }

    // BiConsumer Interface
    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }

    // Supplier Interface
    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {
        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }
}
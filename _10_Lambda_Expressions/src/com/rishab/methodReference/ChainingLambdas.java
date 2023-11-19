package com.rishab.methodReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ChainingLambdas {
    public static void main(String[] args) {

        String name = "Ethan";
        Function<String, String> upperCase = String::toUpperCase;
        System.out.println(upperCase.apply(name)); // ETHAN

        Function<String, String> lastName = s -> s.concat(" Hunt");
        Function<String, String> upperCaseLastName = upperCase.andThen(lastName); // andThen() returns a composed function that first applies this function to its input, and then applies the after function to the result.
        System.out.println(upperCaseLastName.apply(name)); // ETHAN Hunt

        upperCaseLastName = upperCase.compose(lastName); // compose() returns a composed function that first applies the before function to its input, and then applies this function to the result.
        System.out.println(upperCaseLastName.apply(name)); // ETHAN HUNT

        Function<String, String[]> f0 = upperCase
            .andThen(s -> s.concat(" Hunt"))
            .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name))); // [ETHAN, Hunt]

        Function<String, String> f1 = upperCase
            .andThen(s -> s.concat(" Hunt"))
            .andThen(s -> s.split(" "))
            .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name)); // HUNT, ETHAN

        Function<String, Integer> f2 = upperCase
            .andThen(s -> s.concat(" Hunt"))
            .andThen(s -> s.split(" "))
            .andThen(s -> String.join(", ", s))
            .andThen(String::length);
        System.out.println(f2.apply(name)); // 11

        System.out.println();
        String[] names = {"Michael", "Dean", "James", "Chris"};
        Consumer<String> c0 = s -> System.out.print(s.charAt(0));
        Consumer<String> c1 = System.out::println;
        Arrays.asList(names).forEach(c0
            .andThen(s -> System.out.print(" — "))
            .andThen(c1));

        System.out.println();
        Predicate<String> p1 = s -> s.equals("ETHAN");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Ethan");
        Predicate<String> p3 = s -> s.startsWith("E");
        Predicate<String> p4 = s -> s.endsWith("a");

        Predicate<String> combine1 = p1.or(p2); // or() returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
        System.out.println("Combine1 = " + combine1.test(name));

        Predicate<String> combine2 = p3.and(p4); // and() returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
        System.out.println("Combine2 = " + combine2.test(name));

        Predicate<String> combine3 = p3.and(p4).negate(); // negate() returns a predicate that represents the logical negation of this predicate.
        System.out.println("Combine3 = " + combine3.test(name));

        System.out.println();
        record Person(String firstName, String lastName) {
        }

        List<Person> list = new ArrayList<>(Arrays.asList(
            new Person("Ethan", "Hunt"),
            new Person("John", "Smith"),
            new Person("David", "Hunt"),
            new Person("John", "Davies")
        ));
        list.sort((o1, o2) -> o1.lastName().compareTo(o2.lastName()));
        list.forEach(System.out::println);

        System.out.println("--------COMPARE BY LAST NAME--------");
        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);

        System.out.println("--------COMPARE BY LAST NAME THEN FIRST NAME--------");
        list.sort(Comparator.comparing(Person::lastName).thenComparing(Person::firstName));
        list.forEach(System.out::println);

        System.out.println("--------COMPARE BY LAST NAME THEN FIRST NAME REVERSED--------");
        list.sort(Comparator.comparing(Person::lastName).thenComparing(Person::firstName).reversed());
        list.forEach(System.out::println);

    }
}

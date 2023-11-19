package com.rishab.methodReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class MethodReferenceChallenge {

    private static final Random random = new Random();

    private record Person(String firstName) {
        public String lastName(String string) {
            return firstName + " " + string.substring(0, string.indexOf(" "));
        }
    }

    public static void main(String[] args) {

        String[] names = {"Michael", "Dean", "James", "Chris"};

        Person person = new Person("Harry");

        List<UnaryOperator<String>> list = new ArrayList<>(List.of(
            String::toUpperCase,
            s -> s += " " + getRandomChar('A', 'Z') + ".",
            s -> s += " " + reverse(s, 0, s.indexOf(" ")),
            MethodReferenceChallenge::reverse,
            String::new,
            // s -> new String("Yo!" + s),
            String::valueOf,
            person::lastName,
            (new Person("Taylor"))::lastName
        ));
        applyChanges(names, list);

    }

    private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions) {
        List<String> backedByArray = Arrays.asList(names);
        for (var function : stringFunctions) {
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));
        }
    }

    private static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int) endChar + 1);
    }

    private static String reverse(String string) {
        return reverse(string, 0, string.length());
    }

    private static String reverse(String string, int start, int end) {
        return new StringBuilder(string.substring(start, end)).reverse().toString();
    }
}

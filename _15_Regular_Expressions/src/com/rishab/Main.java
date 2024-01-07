package com.rishab;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");

        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using String.format method: " + helloWorld2);

        String helloWorld3 = Main.format("%s %s", "Hello", "World");
        System.out.println("Using custom format method: " + helloWorld3);

        String testString = "Anyone can Learn abc's, 123's and any regular expression";
        String replacement = "(-)";

        String[] patterns = {"[a-zA-Z]*$", "^[a-zA-Z]{3}", "[aA]ny\\b"};

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Replacing " + pattern + " with " + replacement + " : " + output);
        }

        String paragraph = """
            Double, double toil and trouble;
            Fire burn and caldron bubble.
            Fillet of a fenny snake,
            In the caldron boil and bake
            Eye of newt and toe of frog,
            Wool of bat and tongue of dog,
            Adder's fork and blind-worm's sting,
            Lizard's leg and howlet's wing,
            For a charm of powerful trouble,
            Like a hell-broth boil and bubble.
            """;

        String[] lines = paragraph.split("\\R"); // \\R is a line separator
        System.out.println("Number of lines: " + lines.length);
        String[] words = paragraph.split("\\s"); // \\s is a whitespace
        System.out.println("Number of words: " + words.length);
        System.out.println(paragraph.replaceAll("[a-zA-z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner(paragraph);
        System.out.println(scanner.delimiter());

        scanner.useDelimiter("\\R");

        // while(scanner.hasNext()) {
        //     String element = scanner.next();
        //     System.out.println(element);
        // }

        // scanner.tokens()
        //     .map(s -> s.replaceAll("\\p{Punct}", "")) // \\p{Punct} is a punctuation
        //     .flatMap(s -> Arrays.stream(s.split("\\s+")))
        //     .filter(s -> s.matches("[a-zA-Z]+ble"))
        //     .forEach(System.out::println);

        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));

        scanner.close();

    }

    private static String format(String regexp, String... args) {
        int index = 0;
        // .* means any character any number of times
        // and below regexp means any character any number of times followed by %s
        while (regexp.matches(".*%s.*")) {
            regexp = regexp.replaceFirst("%s", args[index++]);
        }

        return regexp;
    }
}

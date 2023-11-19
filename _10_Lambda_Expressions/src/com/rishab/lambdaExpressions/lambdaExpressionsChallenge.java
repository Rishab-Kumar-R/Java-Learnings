package com.rishab.lambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class lambdaExpressionsChallenge {

    private static Random random = new Random();

    public static void main(String[] args) {

        Consumer<String> printWords = new Consumer<String>() {
            @Override
            public void accept(String string) {
                String[] parts = string.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };
        Consumer<String> printWordsLambda = string -> {
            String[] parts = string.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        Consumer<String> printWordsForEach = string -> {
            String[] parts = string.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };
        Consumer<String> printWordsConcise = string -> Arrays.asList(string.split(" ")).forEach(s -> System.out.println(s));
        printWords.accept("Let's split this up into an array");
        printWordsLambda.accept("Let's split this up into an array");
        printWordsForEach.accept("Let's split this up into an array");
        printWordsConcise.accept("Let's split this up into an array");

        System.out.println();
        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(everySecondChar.apply("1234567890"));
        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));

        System.out.println();
        Supplier<String> iLoveJava = () -> "I love Java!";
        System.out.println(iLoveJava.get());

        System.out.println();
        String[] names = {"Quinn", "Alice", "Katie", "Tyler", "Noah", "Liam", "Bob"};
        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("---> Transforming array to uppercase");
        System.out.println(Arrays.toString(names));

        List<String> backedByAnArray = Arrays.asList(names);
        backedByAnArray.replaceAll(s -> s += " " + getRandomChar('A', 'Z') + ".");
        System.out.println("---> Add random char to each name");
        System.out.println(backedByAnArray);

        backedByAnArray.replaceAll(s -> s += " " + getReversedName(s.split(" ")[0]));
        System.out.println("---> Add reversed name to each name");
        Arrays.asList(names).forEach(s -> System.out.println(s));

        List<String> newList = new ArrayList<>(List.of(names));
        newList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));
        System.out.println("---> Remove names that are palindromes");
        newList.forEach(s -> System.out.println(s));
    }

    private static char getRandomChar(char startChar, char endChar) {
        return (char) random.nextInt((int) startChar, (int) endChar + 1);
    }

    private static String getReversedName(String firstName) {
        return new StringBuilder(firstName).reverse().toString();
    }

    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    public static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }
}

package com.rishab;

import java.util.List;

public class RegexChallenges {
    public static void main(String[] args) {

        String sentence = "Hello, World!";
        boolean matches = sentence.matches("Hello, World!");
        System.out.println(matches);

        // String sentence2 = "[A-Z].*\\."; // or
        String sentence2 = "[A-Z][a-z\\s]+[.]";
        for (String s : List.of("The bike is red.", "I am a new student.", "hello world.", "How are you?")) {
            System.out.println(s + " : " + s.matches(sentence2));
        }

        // String sentence3 = "[A-Z].+[.?!]"; // or
        String sentence3 = "^[A-Z][\\p{all}]+[.?!]$";
        for (String s : List.of("The bike is red, and has flat tires.", "Hello, to everyone: Welcome!", "How are you, John?")) {
            System.out.println(s + " : " + s.matches(sentence3));
        }

    }
}

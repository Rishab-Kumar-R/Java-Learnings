package com.rishab.readingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Challenge {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("article.txt"))) {
            // System.out.printf("%,d lines in file%n", br.lines().count());

            Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

            // System.out.printf("%,d words in file%n", br.lines()
            //     // .flatMap(pattern::splitAsStream) // same as below
            //     .flatMap(l -> Arrays.stream(l.split(pattern.toString())))
            //     .count());

            // System.out.printf("%,d words in file%n", br.lines()
            //     .mapToLong(l -> l.split(pattern.toString()).length)
            //     .sum());

            List<String> excluded = List.of("defined", "there", "words", "while", "other", "including", "often");

            var result = br.lines()
                .flatMap(pattern::splitAsStream)
                .map(w -> w.replaceAll("\\p{Punct}", ""))
                .filter(w -> w.length() > 4)
                .map(String::toLowerCase)
                .filter(w -> !excluded.contains(w))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            result.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("—".repeat(50));
        try {
            String input = Files.readString(Path.of("article.txt"));
            input = input.replaceAll("\\p{Punct}", "");

            Pattern pattern = Pattern.compile("\\w{5,}");
            Matcher matcher = pattern.matcher(input);
            Map<String, Long> result = new HashMap<>();
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                result.merge(word, 1L, (oldValue, newValue) -> oldValue += newValue);
            }

            var sortedEntries = new ArrayList<>(result.entrySet());
            sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
            for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
                var entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

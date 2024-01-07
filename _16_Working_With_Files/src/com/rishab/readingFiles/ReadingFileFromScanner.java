package com.rishab.readingFiles;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ReadingFileFromScanner {
    public static void main(String[] args) {

        // try (Scanner scanner = new Scanner(new File("fixedWidth.txt"))) {
        // try (Scanner scanner = new Scanner(Path.of("fixedWidth.txt"))) {
        // try (Scanner scanner = new Scanner(new FileReader("fixedWidth.txt"))) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("fixedWidth.txt")))) {
            // while (scanner.hasNextLine()) {
            //     System.out.println(scanner.nextLine());
            // }

            // System.out.println(scanner.delimiter());
            // scanner.useDelimiter("$");
            // scanner.tokens().forEach(System.out::println);

            // scanner.findAll("[A-Za-z]{10,}")
            //     .map(MatchResult::group)
            //     .distinct()
            //     .sorted()
            //     .forEach(System.out::println);

            var results = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                .skip(1)
                .map(matchResult -> matchResult.group(3).trim())
                .distinct()
                .sorted()
                .toArray(String[]::new);
            System.out.println(Arrays.toString(results));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

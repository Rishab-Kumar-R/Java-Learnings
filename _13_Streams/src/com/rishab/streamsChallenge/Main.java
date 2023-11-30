package com.rishab.streamsChallenge;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    static int counter = 0;

    public static void main(String[] args) {

        int seed = 1;
        var streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1)
            .map(i -> "B" + i);

        seed += 15;
        var streamI = Stream.iterate(seed, i -> i + 1)
            .limit(15)
            .map(i -> "I" + i);

        seed += 15;
        int nSeed = seed;
        String[] nLabels = new String[15];
        Arrays.setAll(nLabels, i -> "N" + (nSeed + i));
        var streamN = Arrays.stream(nLabels);

        seed += 15;
        var streamG = Stream.of("G46", "G47", "G48", "G49", "G50", "G51", "G52", "G53", "G54", "G55", "G56", "G57", "G58", "G59", "G60");

        seed += 15;
        int oSeed = seed;
        var streamO = Stream.generate(Main::getCounter)
            .limit(15)
            .map(i -> "O" + (oSeed + i));

        Stream.concat(streamB, Stream.concat(streamI, Stream.concat(streamN, Stream.concat(streamG, streamO))))
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        Stream.generate(() -> new Random().nextInt(oSeed, oSeed + 15))
            .distinct() // returns a stream with distinct elements
            .limit(15)
            .map(i -> "O" + i)
            .sorted()
            .forEach(System.out::println);

    }

    private static int getCounter() {
        return counter++;
    }
}

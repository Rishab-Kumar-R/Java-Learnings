package com.rishab.streamsIntermediate;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        String lineSeparator = "—".repeat(50);

        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
            .filter(Character::isAlphabetic)
            // .filter(i -> Character.toUpperCase(i) > 'E') // F G H I J K L M N O P Q R S T U V W X Y Z f g h i j k l m n o p q r s t u v w x y z
            // .skip(5) // F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
            // .dropWhile(i -> Character.toUpperCase(i) <= 'E') // F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z
            // .takeWhile(i -> i < 'a') // F G H I J K L M N O P Q R S T U V W X Y Z
            .map(Character::toUpperCase) // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
            .distinct() // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
            .forEach(i -> System.out.printf("%c ", i));

        System.out.println("\n" + lineSeparator);
        Random random = new Random();
        Stream.generate(() -> random.nextInt((int) 'A', (int) 'Z' + 1))
            .limit(50)
            .distinct()
            .sorted()
            .forEach(i -> System.out.printf("%c ", i));

        System.out.println("\n" + lineSeparator);
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
            .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
            // .mapToDouble(Seat::price)
            // .boxed()
            // .map("%.2f"::formatted)
            .skip(5)
            .limit(10)
            .peek(s -> System.out.println("———>" + s + "<———"))
            .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));

        stream.forEach(System.out::println);

    }
}

package com.rishab.mathRandom;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // int maxMinusFive = Integer.MAX_VALUE - 5;
        // for (int i = 0, id = maxMinusFive; i < 10; i++, id = Math.incrementExact(id)) {
        //     System.out.printf("Attempt %d: %d\n", i, id);
        // }

        System.out.println("The absolute value of -5 is: " + Math.abs(-5)); // 5

        // This will provide a negative value, because the absolute value of Integer.MIN_VALUE is greater than Integer.MAX_VALUE (overflow)
        System.out.println("The absolute value of Integer.MIN_VALUE is: " + Math.abs(Integer.MIN_VALUE)); // -2147483648

        // This will throw an ArithmeticException, because the absolute value of Integer.MIN_VALUE is greater than Integer.MAX_VALUE (overflow)
        // System.out.println("The absolute value of Integer.MIN_VALUE is: " + Math.absExact(Integer.MIN_VALUE)); // ArithmeticException
        System.out.println("The absolute value of Integer.MIN_VALUE is: " + Math.abs((long) Integer.MIN_VALUE)); // 2147483648

        System.out.println("Maximum of 10 and -10 is: " + Math.max(10, -10)); // 10
        System.out.println("Minimum of 10.0000002 and 10.001 is: " + Math.min(10.0000002, 10.001)); // 10.0000002

        System.out.println("Rounding 10.2 to nearest integer: " + Math.round(10.2)); // 10
        System.out.println("Rounding 10.5 to nearest integer: " + Math.round(10.5)); // 11
        System.out.println("Rounding 10.8 to nearest integer: " + Math.round(10.8)); // 11

        System.out.println("Flooring 10.8: " + Math.floor(10.8)); // 10.0
        System.out.println("Ceiling 10.2: " + Math.ceil(10.2)); // 11.0

        System.out.println("The square root of 25 is: " + Math.sqrt(25)); // 5.0
        System.out.println("The cube root of 27 is: " + Math.cbrt(27)); // 3.0

        System.out.println("The value of 2 raised to the power of 3 is: " + Math.pow(2, 3)); // 8.0
        System.out.println("The value of 10 raised to the power of 5 is: " + Math.pow(10, 5)); // 100000.0

        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26) + 65);
        }

        System.out.println("—".repeat(50));
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", random.nextInt(65, 91));
        }

        System.out.println("—".repeat(50));
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", random.nextInt((int) 'A', (int) 'Z' + 1));
        }

        System.out.println("—".repeat(50));
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d%n", random.nextInt(-10, 11));
        }

        System.out.println("—".repeat(50));
        random.ints()
            .limit(10)
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        random.ints(0, 10)
            .limit(10)
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        random.ints(10,0, 10)
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        random.ints(10)
            .forEach(System.out::println);

        long nanoTime = System.nanoTime();
        Random pseudoRandom  = new Random(nanoTime);
        System.out.println("—".repeat(50));
        pseudoRandom.ints(10,0, 10)
            .forEach(i -> System.out.print(i + " "));

        System.out.println();
        Random notReallyRandom  = new Random(nanoTime);
        System.out.println("—".repeat(50));
        notReallyRandom.ints(10,0, 10)
            .forEach(i -> System.out.print(i + " "));

    }
}

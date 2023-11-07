package com.rishab;

public class ForLoop {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Loop " + i + " hello!");
        }

        for (double rate = 2.0; rate <= 5.0; rate++) {
            double interestAmount = calculateInterest(10000.0, rate);
            System.out.println("10,000 at " + rate + "% interest = " + interestAmount);
        }

        for (double rate = 7.5; rate <= 10; rate += 0.25) {
            double interestAmount = calculateInterest(100.00, rate);
            if (interestAmount > 8.5) {
                break;
            }
            System.out.println("$100.00 at " + rate + "% interest = $" + interestAmount);
        }

        System.out.println();

        int counter = 0;
        for (int i = 10; counter < 3 && i <= 50; i++) {
            if (isPrime(i)) {
                System.out.println("Number " + i + " is a prime number");
                counter++;
            }
        }

        int count = 0;
        int sum = 0;
        for (int i = 1; count < 5 && i <= 1000; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                count++;
                sum += i;
                System.out.println("Found a match: " + i);
            }
        }
        System.out.println("The sum is: " + sum);

        System.out.println();

        // Challenge
        System.out.println("Sum of odd numbers between 1 and 100: " + sumOdd(1, 100));
        System.out.println("Sum of odd numbers between 1 and 1000: " + sumOdd(-1, 100));
        System.out.println("Sum of odd numbers between 1 and 5000: " + sumOdd(13, 13));
        System.out.println("Sum of odd numbers between 1 and 10000: " + sumOdd(100, -100));
        System.out.println("Sum of odd numbers between 1 and 10000: " + sumOdd(100, 1000));

    }

    private static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }

    private static boolean isPrime(int number) {
        if (number <= 2) {
            return (number == 2);
        }
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOdd(int number) {
        return (number > 0 && number % 2 != 0);
    }

    private static int sumOdd(int start, int end) {
        if (start > 0 && end >= start) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                if (isOdd(i)) {
                    sum += i;
                }
            }
            return sum;
        }
        return -1;
    }
}

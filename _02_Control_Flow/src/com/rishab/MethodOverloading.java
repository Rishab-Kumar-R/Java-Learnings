package com.rishab;

public class MethodOverloading {
    public static void main(String[] args) {

        int newScore = calculateScore("Alex", 500);
        System.out.println("New score is " + newScore);

        // int unnamedScore = calculateScore(75);
        // System.out.println("Unnamed score is " + unnamedScore);

        System.out.println("New score is " + calculateScore(75));

        calculateScore();

        System.out.println("5ft, 8in = " + convertToCentimeters(5, 8) + " cm");
        System.out.println("68in = " + convertToCentimeters(68) + " cm");

        System.out.println(getDurationString(-3945));
        System.out.println(getDurationString(-65, 45));
        System.out.println(getDurationString(65, 145));
        System.out.println(getDurationString(65, 45));
        System.out.println(getDurationString(3945));

        System.out.println();

        // Challenge
        System.out.println(area(5.0)); // 78.53975
        System.out.println(area(-1)); // -1
        System.out.println(area(5.0, 4.0)); // 20.0
        System.out.println(area(-1.0, 4.0)); // -1

        System.out.println();

        printYearsAndDays(525600); // 525600 min = 1 y and 0 d
        printYearsAndDays(1051200); // 1051200 min = 2 y and 0 d
        printYearsAndDays(561600); // 561600 min = 1 y and 25 d

        System.out.println();

        printEqual(1, 1, 1); // All numbers are equal
        printEqual(1, 1, 2); // Neither all are equal nor different
        printEqual(-1, -1, -1); // Invalid Value
        printEqual(1, 2, 3); // All numbers are different

        System.out.println();

        System.out.println(isCatPlaying(true, 10)); // false
        System.out.println(isCatPlaying(false, 36)); // false
        System.out.println(isCatPlaying(false, 35)); // true
    }

    private static int calculateScore(String name, int score) {
        System.out.println("Player " + name + " scored " + score + " points");
        return score * 1000;
    }

    private static int calculateScore(int score) {
        return calculateScore("Anonymous", score);
    }

    private static void calculateScore() {
        System.out.println("No player name, no player score");
    }

    private static double convertToCentimeters(int inches) {
        if (inches < 0) {
            return -1;
        } else {
            return inches * 2.54;
        }
    }

    private static double convertToCentimeters(int feet, int inches) {
        if (feet < 0 || inches < 0 || inches > 12) {
            return -1;
        } else {
            return convertToCentimeters((feet * 12) + inches);
        }
    }

    private static String getDurationString(int seconds) {
        if (seconds < 0) {
            return "Invalid data for seconds(" + seconds + "), must be a positive integer value";
        }
        return getDurationString(seconds / 60, seconds % 60);
    }

    private static String getDurationString(int minutes, int seconds) {
        if (minutes < 0) {
            return "Invalid data for minutes(" + minutes + "), must be a positive integer value";
        }
        if (seconds <= 0 || seconds >= 59) {
            return "Invalid data for seconds(" + seconds + "), must be a positive integer value between 0 and 59";
        }
        int hours = minutes / 60;
        minutes %= 60;
        return hours + "h " + minutes + "m " + seconds + "s";
    }

    private static double area(double radius) {
        if (radius < 0) {
            return -1;
        } else {
            return Math.PI * radius * radius;
        }
    }

    private static double area(double height, double width) {
        if (height < 0 || width < 0) {
            return -1;
        } else {
            return height * width;
        }
    }

    private static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println("Invalid value");
        } else {
            long years = minutes / (60 * 24 * 365);
            long days = (minutes % (60 * 24 * 365)) / (60 * 24);
            System.out.println(minutes + " min = " + years + " y and " + days + " d");
        }
    }

    private static void printEqual(int num1, int num2, int num3) {
        if (num1 < 0 || num2 < 0 || num3 < 0) {
            System.out.println("Invalid Value");
        } else if (num1 == num2 && num2 == num3) {
            System.out.println("All numbers are equal");
        } else if (num1 != num2 && num2 != num3 && num1 != num3) {
            System.out.println("All numbers are different");
        } else {
            System.out.println("Neither all are equal nor different");
        }
    }

    private static boolean isCatPlaying(boolean summer, int temperature) {
        int lowerLimit = 25;
        int upperLimit = summer ? 45 : 35;
        return temperature >= lowerLimit && temperature <= upperLimit;
    }
}

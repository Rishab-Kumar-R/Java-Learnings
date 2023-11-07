package com.rishab;

public class Methods {
    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int highScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was: " + highScore);
        System.out.println("Your next high score is: " + calculateScore(true, 10000, 8, 200));

        int highScorePosition = calculateHighScorePosition(200);
        displayHighScorePosition("Alex", highScorePosition);

        // Challenges
        checkNumber(0);
        checkNumber(-1);
        checkNumber(1);

        System.out.println();

        printConversion(1.5);
        printConversion(10.25);

        System.out.println();

        printMegaBytesAndKiloBytes(2500);
        printMegaBytesAndKiloBytes(-1024);

        System.out.println();

        System.out.println(shouldWakeUp(true, 1));
        System.out.println(shouldWakeUp(false, 2));
        System.out.println(shouldWakeUp(true, 8));
        System.out.println(shouldWakeUp(true, -1));

        System.out.println();

        System.out.println(isLeapYear(-1600));
        System.out.println(isLeapYear(1600));
        System.out.println(isLeapYear(2017));
        System.out.println(isLeapYear(2000));

        System.out.println();

        System.out.println(areEqualByThreeDecimalPlaces(-3.1756, -3.175));
        System.out.println(areEqualByThreeDecimalPlaces(3.175, 3.176));
        System.out.println(areEqualByThreeDecimalPlaces(3.0, 3.0));

        System.out.println();

        System.out.println(hasEqualSum(1, 1, 1));
        System.out.println(hasEqualSum(1, 1, 2));

        System.out.println();

        System.out.println(hasTeen(9, 99, 19));
        System.out.println(hasTeen(23, 15, 42));
        System.out.println(hasTeen(22, 23, 34));
    }

    /**
     * This method calculates the score of the player
     *
     * @param gameOver       boolean indicating whether the game is over.
     * @param score          the score of the player
     * @param levelCompleted the level the player completed
     * @param bonus          the bonus the player got
     */
    private static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {
        int finalScore = score;
        if (gameOver) {
            finalScore += (levelCompleted * bonus);
        }
        return finalScore;
    }

    /**
     * This method displays the high score position
     *
     * @param name     the name of the player
     * @param position the position of the player
     */
    private static void displayHighScorePosition(String name, int position) {
        System.out.println(name + " managed to get into position " + position + " on the high score table");
    }

    /**
     * This method calculates the high score position
     *
     * @param score the score of the player
     * @return the position of the player
     */
    private static int calculateHighScorePosition(int score) {
        int position = 4;
        if (score >= 1000) {
            position = 1;
        } else if (score >= 500) {
            position = 2;
        } else if (score >= 100) {
            position = 3;
        }
        return position;
    }

    /**
     * This method checks if a number is positive, negative or zero
     *
     * @param num the number to check
     */
    private static void checkNumber(int num) {
        if (num < 0) {
            System.out.println("Negative number");
        } else if (num > 0) {
            System.out.println("Positive number");
        } else {
            System.out.println("Zero");
        }
    }

    /**
     * This method converts kilometers per hour to miles per hour
     *
     * @param kilometersPerHour the speed in kilometers per hour
     * @return the speed in miles per hour
     */
    private static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            return -1;
        } else {
            return Math.round(kilometersPerHour / 1.609);
        }
    }

    /**
     * This method prints the conversion from kilometers per hour to miles per hour
     *
     * @param kilometersPerHour the speed in kilometers per hour
     */
    private static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid value");
        } else {
            long milesPerHour = toMilesPerHour(kilometersPerHour);
            System.out.println(kilometersPerHour + " km/h = " + milesPerHour + " mi/h");
        }
    }

    /**
     * This method prints the conversion from kilobytes to megabytes
     *
     * @param kiloBytes the size in kilobytes
     */
    private static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes < 0) {
            System.out.println("Invalid value");
        } else {
            int megaBytes = kiloBytes / 1024;
            int remainingKiloBytes = kiloBytes % 1024;
            if (remainingKiloBytes == 0) {
                System.out.println(kiloBytes + " KB = " + megaBytes + " MB");
            } else {
                System.out.println(kiloBytes + " KB = " + megaBytes + " MB and " + remainingKiloBytes + " KB");
            }
        }
    }

    /**
     * Determines if you should wake up based on a dog's barking and the hour of the day.
     *
     * @param barking   a boolean indicating whether the dog is barking or not.
     * @param hourOfDay an integer representing the current hour of the day (0-23).
     * @return true if you should wake up, false otherwise.
     * Returns false if the hourOfDay is outside the valid range (0-23).
     * Returns true if the dog is barking and the hourOfDay is before 8 or after 22.
     */
    private static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if (hourOfDay < 0 || hourOfDay > 23) {
            return false;
        } else {
            return barking && (hourOfDay < 8 || hourOfDay > 22);
        }
    }

    /**
     * Determines if a given year is a leap year.
     *
     * @param year An integer representing the year to be checked.
     * @return true if the year is a leap year, false otherwise.
     * Returns false if the year is not within the valid range (1-9999).
     * A leap year is determined based on the following rules:
     * - A year is a leap year if it is divisible by 4.
     * - However, a year is not a leap year if it is divisible by 100, unless it is also divisible by 400.
     */
    private static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        } else {
            return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        }
    }

    /**
     * Checks if two double numbers are equal up to three decimal places.
     *
     * @param num1 The first double number to compare.
     * @param num2 The second double number to compare.
     * @return true if the numbers are equal up to three decimal places, false otherwise.
     * The comparison is based on rounding both numbers to the nearest three decimal places.
     */
    private static boolean areEqualByThreeDecimalPlaces(double num1, double num2) {
        return (int) (num1 * 1000) == (int) (num2 * 1000);
    }

    /**
     * Checks if the sum of two integers equals a third integer.
     *
     * @param num1 The first integer to be added.
     * @param num2 The second integer to be added.
     * @param num3 The integer to compare the sum against.
     * @return true if the sum of num1 and num2 equals num3, false otherwise.
     */
    private static boolean hasEqualSum(int num1, int num2, int num3) {
        return num1 + num2 == num3;
    }

    /**
     * Checks if at least one of the given integers is in the "teen" range (between 13 and 19 inclusive).
     *
     * @param num1 The first integer to be checked.
     * @param num2 The second integer to be checked.
     * @param num3 The third integer to be checked.
     * @return true if at least one of the integers is in the "teen" range, false otherwise.
     */
    private static boolean hasTeen(int num1, int num2, int num3) {
        return isTeen(num1) || isTeen(num2) || isTeen(num3);
    }

    /**
     * Checks if a given integer is in the "teen" range (between 13 and 19 inclusive).
     *
     * @param num The integer to be checked.
     * @return true if the integer is in the "teen" range, false otherwise.
     */
    private static boolean isTeen(int num) {
        return num >= 13 && num <= 19;
    }
}

package com.rishab;

import java.util.Scanner;

public class ReadingInputs {
    public static void main(String[] args) {

        // String currentYear = "2023";
        // String yearOfBirth = "2000";

        // System.out.println("Age = " + (Integer.parseInt(currentYear) - Integer.parseInt(yearOfBirth)));

        // String usersAgeWithPartialYear = "22.5";
        // double ageWithPartialYear = Double.parseDouble(usersAgeWithPartialYear);
        // System.out.println("The user says he's " + ageWithPartialYear + " years old.");

        // int currentYear = 2023;
        // try {
        //     System.out.println(getInputFromConsole(currentYear));
        // } catch (NullPointerException e) {
        //     System.out.println(getInputFromScanner(currentYear));
        // }

        // Challenge
        Scanner scanner = new Scanner(System.in);
        int counter = 1;
        double sum = 0;

        do {
            System.out.print("Enter number #" + counter + ": ");
            String nextNumber = scanner.nextLine();
            try {
                double number = Double.parseDouble(nextNumber);
                counter++;
                sum += number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        } while (counter <= 5);

        System.out.println("The sum of the numbers is " + sum);

        // Challenge
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        int loopCounter = 0;

        while (true) {
            System.out.print("Enter a number, or any character to exit: ");
            String nextEntry = scanner.nextLine();
            try {
                double validNum = Double.parseDouble(nextEntry);
                if (loopCounter == 0 || validNum < min) {
                    min = validNum;
                }
                if (loopCounter == 0 || validNum > max) {
                    max = validNum;
                }
                loopCounter++;
            } catch (NumberFormatException e) {
                break;
            }
        }

        if (loopCounter > 0) {
            System.out.println("The minimum number is " + min);
            System.out.println("The maximum number is " + max);
        } else {
            System.out.println("No valid numbers were entered.");
        }

        scanner.close();

        System.out.println();

        inputThenPrintSumAndAverage();

        System.out.println();

        System.out.println("The number of buckets needed is " + getBucketCount(3.4, 2.1, 1.5, 2));
        System.out.println("The number of buckets needed is " + getBucketCount(3.4, 2.1, 1.5));
        System.out.println("The number of buckets needed is " + getBucketCount(3.4, 1.5));
    }

    private static String getInputFromConsole(int currentYear) {
        String name = System.console().readLine("Enter your name: ");
        String yearOfBirth = System.console().readLine("Enter your year of birth: ");

        int age = currentYear - Integer.parseInt(yearOfBirth);

        return "Hello " + name + ", you are " + age + " years old.";
    }

    private static String getInputFromScanner(int currentYear) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("What is your year of birth?");

        boolean validDOB = false;
        int age = 0;

        do {
            System.out.print("Enter your year of birth >= " + (currentYear - 100) + " and <= " + currentYear + ": ");
            try {
                age = checkData(currentYear, scanner.nextLine());
                validDOB = age >= 0;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year of birth. Please try again.");
            }
        } while (!validDOB);

        scanner.close(); // Close the scanner to avoid resource leak

        return "Hello " + name + ", you are " + age + " years old.";
    }

    private static int checkData(int currentYear, String dateOfBirth) {
        int dob = Integer.parseInt(dateOfBirth);
        int minimumYear = currentYear - 100;

        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }
        return currentYear - dob;
    }

    private static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int counter = 0;
        double average = 0;

        while (true) {
            System.out.print("Enter a number: ");
            boolean hasNextInt = scanner.hasNextInt();

            if (hasNextInt) {
                int number = scanner.nextInt();
                sum += number;
                counter++;
            } else {
                break;
            }
        }

        if (counter > 0) {
            average = Math.round((double) sum / counter);
        }
        System.out.println("SUM = " + sum + " AVG = " + average);
        scanner.close();
    }

    /**
     * Calculates the number of paint buckets Bob needs to buy for a wall painting job.
     *
     * @param width         The width of the wall.
     * @param height        The height of the wall.
     * @param areaPerBucket The area that can be covered by one paint bucket.
     * @param extraBuckets  The number of extra paint buckets Bob already has at home.
     * @return The number of paint buckets needed to cover the wall, considering the extra buckets Bob has.
     * Returns -1 if any of the input parameters are invalid (width, height, areaPerBucket <= 0, extraBuckets < 0).
     */
    private static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double totalArea = width * height;
        int bucketsNeeded = (int) Math.ceil(totalArea / areaPerBucket);
        return bucketsNeeded - extraBuckets;
    }

    /**
     * Calculates the number of paint buckets Bob needs to buy for a wall painting job based on wall dimensions and paint coverage.
     *
     * @param width         The width of the wall.
     * @param height        The height of the wall.
     * @param areaPerBucket The area that can be covered by one paint bucket.
     * @return The number of paint buckets needed to cover the wall. Returns -1 if any of the input parameters are invalid
     * (width, height, or areaPerBucket are less than or equal to 0).
     */
    private static int getBucketCount(double width, double height, double areaPerBucket) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        double totalArea = width * height;
        return (int) Math.ceil(totalArea / areaPerBucket);
    }

    /**
     * Calculates the number of paint buckets Bob needs to buy based on the total wall area and the area coverage per paint bucket.
     *
     * @param area          The total area of the wall to be painted.
     * @param areaPerBucket The area that can be covered by one paint bucket.
     * @return The number of paint buckets needed to cover the wall. Returns -1 if any of the input parameters are invalid
     * (area or areaPerBucket are less than or equal to 0).
     */
    private static int getBucketCount(double area, double areaPerBucket) {
        if (area <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        return (int) Math.ceil(area / areaPerBucket);
    }
}

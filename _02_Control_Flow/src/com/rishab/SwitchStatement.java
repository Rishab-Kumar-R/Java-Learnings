package com.rishab;

public class SwitchStatement {
    public static void main(String[] args) {

        int value = 3;
        // if (value == 1) {
        //     System.out.println("Value was 1");
        // } else if (value == 2) {
        //     System.out.println("Value was 2");
        // } else {
        //     System.out.println("Was not 1 or 2");
        // }

        // Switch is limited to byte, short, char, and int (and their wrapper classes), String, and enums
        switch (value) {
            case 1 -> System.out.println("Value was 1");
            case 2 -> System.out.println("Value was 2");
            case 3, 4, 5 -> {
                System.out.println("Was a 3, a 4, or a 5");
                System.out.println("Actually it was a " + value);
            }
            default -> System.out.println("Was not 1, 2, 3, 4, or 5");
        }

        String month = "XYZ";
        System.out.println(month + " is in the " + getQuarter(month));

        System.out.println();

        // Challenge
        char character = 'X';
        switch (character) {
            case 'A':
                System.out.println("A is able");
                break;
            case 'B':
                System.out.println("B is baker");
                break;
            case 'C':
                System.out.println("C is charlie");
                break;
            case 'D':
                System.out.println("D is dog");
                break;
            case 'E':
                System.out.println("E is easy");
                break;
            default:
                System.out.println("Letter '" + character + "' not found");
                break;
        }

        printDayOfTheWeek(0);
        printWeekDay(0);

    }

    private static String getQuarter(String month) {
        return switch (month) {
            case "JANUARY", "FEBRUARY", "MARCH" -> "FIRST QUARTER";
            case "APRIL", "MAY", "JUNE" -> {
                yield "SECOND QUARTER";
            }
            case "JULY", "AUGUST", "SEPTEMBER" -> "THIRD QUARTER";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "FOURTH QUARTER";
            default -> {
                String message = "Invalid month";
                yield message;
            }
        };
    }

    private static void printDayOfTheWeek(int day) {
        switch (day) {
            case 0 -> System.out.println("Sunday");
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            default -> System.out.println("Invalid day");
        }
    }

    private static void printWeekDay(int day) {
        if (day == 0) {
            System.out.println("Sunday");
        } else if (day == 1) {
            System.out.println("Monday");
        } else if (day == 2) {
            System.out.println("Tuesday");
        } else if (day == 3) {
            System.out.println("Wednesday");
        } else if (day == 4) {
            System.out.println("Thursday");
        } else if (day == 5) {
            System.out.println("Friday");
        } else if (day == 6) {
            System.out.println("Saturday");
        } else {
            System.out.println("Invalid day");
        }
    }
}

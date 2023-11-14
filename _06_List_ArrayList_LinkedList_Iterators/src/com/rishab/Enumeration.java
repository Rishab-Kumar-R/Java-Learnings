package com.rishab;

import java.util.Random;

enum DayOfTheWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
}

enum Topping {
    LETTUCE, TOMATO, ONION, PICKLE, CHEESE, BACON, MUSHROOM, KETCHUP, MUSTARD, MAYO;

    public double getPrice() {
        return switch (this) {
            case TOMATO, ONION -> 0.25;
            case CHEESE, BACON, MUSHROOM -> 0.75;
            case MUSTARD -> 0.5;
            default -> 0.0;
        };
    }
}

public class Enumeration {
    public static void main(String[] args) {

        DayOfTheWeek weekDay = DayOfTheWeek.MONDAY;
        System.out.println("Weekday: " + weekDay);

        for (int i = 0; i < 9; i++) {
            weekDay = getRandomDay();

            // if (weekDay == DayOfTheWeek.SATURDAY || weekDay == DayOfTheWeek.SUNDAY) {
            //     System.out.print("Weekend!, ");
            // } else {
            //     System.out.print("Weekday!, ");
            // }
            // System.out.printf("Name: %s, Ordinal: %d%n", weekDay.name(), weekDay.ordinal());

            switchDayOfWeek(weekDay);
        }

        System.out.println();

        for (Topping topping : Topping.values()) {
            System.out.printf("%s: $%.2f%n", topping.name(), topping.getPrice());
        }

    }

    public static void switchDayOfWeek(DayOfTheWeek weekDay) {
        int weekDayInteger = weekDay.ordinal() + 1;

        switch (weekDay) {
            case WEDNESDAY -> System.out.println("It's day " + weekDayInteger + " of the week!. It's Wednesday!");
            case SATURDAY -> System.out.println("It's day " + weekDayInteger + " of the week!. It's Saturday!");
            default ->
                System.out.println(weekDay.name().charAt(0) + weekDay.name().substring(1).toLowerCase() + " is day " + weekDayInteger + " of the week!");
        }
    }

    public static DayOfTheWeek getRandomDay() {
        int randomInteger = new Random().nextInt(7);
        var allDays = DayOfTheWeek.values();

        return allDays[randomInteger];
    }
}

package com.rishab.streamTerminal;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        var result = IntStream
            .iterate(0, i -> i <= 1000, i -> i + 10)
            .summaryStatistics();

        System.out.println("Result: " + result); // Result: IntSummaryStatistics{count=101, sum=50500, min=0, average=500.000000, max=1000}

        var leapYearData = IntStream
            .iterate(2000, i -> i <= 2030, i -> i + 1)
            .filter(i -> i % 4 == 0 && (i % 100 != 0 || i % 400 == 0))
            .peek(System.out::println)
            .summaryStatistics();

        System.out.println("Leap Year Data: " + leapYearData); // Leap Year Data: IntSummaryStatistics{count=8, sum=16056, min=2000, average=2007.000000, max=2038}

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char) ('A' + i / 10), i % 10 + 1));
        // Arrays.asList(seats).forEach(System.out::println);
        long reservationCount = Arrays.stream(seats)
            .filter(Seat::isReserved)
            .count(); // returns the number of elements that match the predicate
        System.out.println("Reservation Count: " + reservationCount);

        boolean hasBookings = Arrays.stream(seats)
            .anyMatch(Seat::isReserved); // returns true if any of the elements match the predicate
        System.out.println("Has Bookings: " + hasBookings);

        boolean fullyBooked = Arrays.stream(seats)
            .allMatch(Seat::isReserved); // returns true if all the elements match the predicate
        System.out.println("Fully Booked: " + fullyBooked);

        boolean noBookings = Arrays.stream(seats)
            .noneMatch(Seat::isReserved); // returns true if none of the elements match the predicate
        System.out.println("No Bookings: " + noBookings);

    }
}

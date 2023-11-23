package com.rishab.setsAndMaps;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

class Theatre {
    class Seat implements Comparable<Seat> {
        private String seatNumber;
        private boolean reserved;

        public Seat(char rowChar, int seatNumber) {
            this.seatNumber = "%c%03d".formatted(rowChar, seatNumber).toUpperCase();
        }

        @Override
        public String toString() {
            return seatNumber;
        }

        @Override
        public int compareTo(Seat o) {
            return seatNumber.compareTo(o.seatNumber);
        }
    }

    private String theatreName;
    private int seatsPerRow;
    private NavigableSet<Seat> seats;

    public Theatre(String theatreName, int rows, int totalSeats) {
        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats / rows;

        seats = new TreeSet<>();
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) (i / seatsPerRow + (int) 'A');
            int seatInRow = i % seatsPerRow + 1;
            seats.add(new Seat(rowChar, seatInRow));
        }
    }

    public void printSeatMap() {
        String separatorLine = "—".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map %n%1$s%n", separatorLine, theatreName);

        int index = 0;
        for (Seat s : seats) {
            System.out.printf("%-8s%s",
                s.seatNumber + ((s.reserved) ? "(●)" : ""),
                ((index++ + 1) % seatsPerRow == 0) ? "\n" : "");
        }

        System.out.println(separatorLine);
    }

    public String reserveSeat(char row, int seat) {
        Seat requestedSeat = new Seat(row, seat);
        Seat requested = seats.floor(requestedSeat);

        if (requested == null || !requested.seatNumber.equals(requestedSeat.seatNumber)) {
            System.out.print("Seat " + requestedSeat + " not found");
            System.out.printf(": Seat must be between %s and %s%n", seats.first(), seats.last());
        } else {
            if (requested.reserved) {
                System.out.println("Seat " + requestedSeat + " already reserved");
            } else {
                requested.reserved = true;
                return requested.seatNumber;
            }
        }
        return null;
    }

    private boolean validate(int count, char first, char last, int min, int max) {
        boolean result = (min > 0 || seatsPerRow >= count || (max - min + 1) >= count);
        result = result && seats.contains(new Seat(first, min));
        if (!result) {
            System.out.printf("Invalid seat range: %1$d seats between %2$c[%3$d-%4$d]-%5$c[%3$d-%4$d] Try again%n", count, first, min, max, last);
            System.out.printf("Seat must be between %s and %s%n", seats.first().seatNumber, seats.last().seatNumber);
        }
        return result;
    }

    public Set<Seat> reserveSeats(int count, char minRow, char maxRow, int minSeat, int maxSeat) {
        char lastValid = seats.last().seatNumber.charAt(0);
        maxRow = (maxRow > lastValid) ? lastValid : maxRow;

        if (!validate(count, minRow, maxRow, minSeat, maxSeat)) {
            return null;
        }

        NavigableSet<Seat> selectedSeats = null;

        for (char letter = minRow; letter <= maxRow; letter++) {
            NavigableSet<Seat> contiguousSeats = seats.subSet(
                new Seat(letter, minSeat), true,
                new Seat(letter, maxSeat), true);

            int index = 0;
            Seat first = null;

            for (Seat current : contiguousSeats) {
                if (current.reserved) {
                    index = 0;
                    continue;
                }
                first = (index == 0) ? current : first;
                if (++index == count) {
                    selectedSeats = contiguousSeats.subSet(first, true, current, true);
                    break;
                }
            }
            if (selectedSeats != null) {
                break;
            }
        }

        Set<Seat> reservedSeats = null;
        if (selectedSeats != null) {
            selectedSeats.forEach(s -> s.reserved = true);
            reservedSeats = new TreeSet<>(selectedSeats);
        }
        return reservedSeats;
    }
}

public class TreeSetChallenge {
    public static void main(String[] args) {

        int rows = 10;
        int totalSeats = 100;
        Theatre theatre = new Theatre("PVR", rows, totalSeats);
        theatre.printSeatMap();

        bookSeat(theatre, 'A', 1);
        bookSeat(theatre, 'A', 1);
        bookSeat(theatre, 'B', 5);
        bookSeat(theatre, 'B', 11);
        bookSeat(theatre, 'M', 1);

        bookSeats(theatre, 5, 'D', 3, 8);
        bookSeats(theatre, 6, 'B', 'C', 3, 10);
        bookSeats(theatre, 4, 'B', 1, 10);
        bookSeats(theatre, 7, 'B', 'C', 1, 10);
        bookSeats(theatre, 1, 'B', 'C', 1, 10);
        bookSeats(theatre, 4, 'M', 'Z', 1, 10);
        bookSeats(theatre, 10, 'A', 'E', 1, 10);

    }

    private static void bookSeat(Theatre theatre, char row, int seatNumber) {
        String seat = theatre.reserveSeat(row, seatNumber);
        if (seat != null) {
            System.out.println("Seat " + seat + " booked");
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry, unable to book " + row + seatNumber + " seat");
        }
    }

    private static void bookSeats(Theatre theatre, int tickets, char minRow, int minSeat, int maxSeat) {
        bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat);
    }

    private static void bookSeats(Theatre theatre, int tickets, char minRow, char maxRow, int minSeat, int maxSeat) {
        var seats = theatre.reserveSeats(tickets, minRow, maxRow, minSeat, maxSeat);
        if (seats != null) {
            System.out.println("Seats " + seats + " booked");
            theatre.printSeatMap();
        } else {
            System.out.println("Sorry, unable to book " + tickets + " seats");
        }
    }
}

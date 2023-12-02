package com.rishab.dateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println("Today's date is: " + today);

        LocalDate someDate = LocalDate.of(2020, 1, 1);
        System.out.println("Some date is: " + someDate); // Some date is: 2020-01-01

        LocalDate yesterday = today.minusDays(1);
        System.out.println("Yesterday's date was: " + yesterday);

        LocalDate tomorrow = today.plusDays(1);
        System.out.println("Tomorrow's date is: " + tomorrow);

        LocalDate parsedDate = LocalDate.parse("2020-05-05");
        System.out.println("Parsed date is: " + parsedDate); // Parsed date is: 2020-05-05

        LocalDate mayFifth = LocalDate.of(2023, Month.MAY, 5);
        System.out.println("May fifth is: " + mayFifth); // May fifth is: 2023-05-05

        LocalDate day125 = LocalDate.ofYearDay(2023, 125);
        System.out.println("Day 125 is: " + day125); // Day 125 is: 2023-05-05

        System.out.println("May fifth belongs to the Year: " + mayFifth.getYear()); // May fifth belongs to the Year: 2023
        System.out.println("May fifth belongs to the Month: " + mayFifth.getMonth()); // May fifth belongs to the Month: MAY
        System.out.println("May fifth belongs to the Month of: " + mayFifth.getMonthValue()); // May fifth belongs to the Month of: 5
        System.out.println("May fifth belongs to the Day of the month: " + mayFifth.getDayOfMonth()); // May fifth belongs to the Day of the month: 5
        System.out.println("May fifth belongs to the Day of the week: " + mayFifth.getDayOfWeek()); // May fifth belongs to the Day of the week: FRIDAY
        System.out.println("May fifth belongs to the Day of the year: " + mayFifth.getDayOfYear()); // May fifth belongs to the Day of the year: 125

        System.out.println("The year of May fifth in ChronoField: " + mayFifth.get(ChronoField.YEAR)); // The year of May fifth in ChronoField: 2023
        System.out.println("The month of May fifth in ChronoField: " + mayFifth.get(ChronoField.MONTH_OF_YEAR)); // The month of May fifth in ChronoField: 5
        System.out.println("The day of the month of May fifth in ChronoField: " + mayFifth.get(ChronoField.DAY_OF_MONTH)); // The day of the month of May fifth in ChronoField: 5
        System.out.println("The day of the year of May fifth in ChronoField: " + mayFifth.get(ChronoField.DAY_OF_YEAR)); // The day of the year of May fifth in ChronoField: 125

        System.out.println("May fifth with year set to 2024: " + mayFifth.withYear(2024)); // May fifth with year set to 2024: 2024-05-05
        System.out.println("May fifth with month set to January: " + mayFifth.withMonth(1)); // May fifth with month set to January: 2023-01-05
        System.out.println("May fifth with day of the month set to 1: " + mayFifth.withDayOfMonth(1)); // May fifth with day of the month set to 1: 2023-05-01
        System.out.println("May fifth with day of the year set to 126: " + mayFifth.withDayOfYear(126)); // May fifth with day of the year set to 126: 2023-05-06
        System.out.println(mayFifth.with(ChronoField.DAY_OF_YEAR, 126)); // 2023-05-06

        System.out.println("May fifth > today: " + mayFifth.isAfter(today)); // May fifth > today: true
        System.out.println("May fifth < today: " + mayFifth.isBefore(today)); // May fifth < today: false
        System.out.println("Comparing May fifth and today: " + mayFifth.compareTo(today));
        System.out.println("Comparing today and May fifth: " + today.compareTo(mayFifth));

        System.out.println("today == now? " + today.compareTo(LocalDate.now())); // today == now? true
        System.out.println("today == now? " + today.equals(LocalDate.now())); // today == now? true

        System.out.println("today is leap year? " + today.isLeapYear()); // today is leap year? false
        System.out.println("someDate is a leap year? " + someDate.isLeapYear()); // someDate is a leap year? true
        System.out.println("May fifth + 1 year is a leap year? " + mayFifth.plusYears(1).isLeapYear()); // May fifth + 1 year is a leap year? false

        System.out.println("—".repeat(50));
        mayFifth.datesUntil(mayFifth.plusDays(9)).forEach(System.out::println);

        System.out.println("—".repeat(50));
        mayFifth.datesUntil(mayFifth.plusYears(1), Period.ofDays(7)).forEach(System.out::println);

        System.out.println("—".repeat(50));
        LocalTime time = LocalTime.now();
        System.out.println("Current time is: " + time);

        LocalTime sixThirtyPM = LocalTime.of(18, 30);
        System.out.println("Six thirty PM is: " + sixThirtyPM); // Six thirty PM is: 18:30

        LocalTime sixThirtyPMWithSeconds = LocalTime.of(18, 30, 30);
        System.out.println("Six thirty PM with seconds is: " + sixThirtyPMWithSeconds); // Six thirty PM with seconds is: 18:30:30

        LocalTime sixThirtyAM = LocalTime.parse("06:30");
        LocalTime sixThirtyAMWithNanoSeconds = LocalTime.parse("06:30:30.123");
        System.out.println("Six thirty AM is: " + sixThirtyAM); // Six thirty AM is: 06:30
        System.out.println("Six thirty AM with nano seconds is: " + sixThirtyAMWithNanoSeconds); // Six thirty AM with nano seconds is: 06:30:30.123
        System.out.println("Is it really six thirty AM? " + sixThirtyAM.get(ChronoField.AMPM_OF_DAY)); // Is it really six thirty AM? 0 (0 means AM)
        System.out.println("Is it really six thirty PM? " + sixThirtyPM.get(ChronoField.AMPM_OF_DAY)); // Is it really six thirty PM? 1 (1 means PM)

        System.out.println("The hour of six thirty PM is: " + sixThirtyPM.getHour()); // The hour of six thirty AM is: 18
        System.out.println("The hour of day of six thirty PM is: " + sixThirtyPM.get(ChronoField.HOUR_OF_DAY)); // The hour of day of six thirty AM is: 18
        System.out.println("Adding day to six thirty PM: " + sixThirtyPM.plusHours(24)); // Adding day to six thirty AM: 18:30
        System.out.println("Adding day to six thirty PM: " + sixThirtyPM.plus(24, ChronoUnit.HOURS)); // Adding day to six thirty AM: 18:30

        System.out.println("The range of six thirty PM in hours is: " + sixThirtyPM.range(ChronoField.HOUR_OF_DAY)); // The range of six thirty AM is: 0 - 23
        System.out.println("The range of six thirty PM in minutes is: " + sixThirtyPM.range(ChronoField.MINUTE_OF_HOUR)); // The range of six thirty AM is: 0 - 59
        System.out.println("The range of six thirty PM in minutes is: " + sixThirtyPM.range(ChronoField.MINUTE_OF_DAY)); // The range of six thirty AM is: 0 - 1439
        System.out.println("The range of six thirty PM in seconds is: "  + sixThirtyPM.range(ChronoField.SECOND_OF_MINUTE)); // The range of six thirty AM is: 0 - 59
        System.out.println("The range of six thirty PM in seconds is: " + sixThirtyPM.range(ChronoField.SECOND_OF_DAY)); // The range of six thirty AM is: 0 - 86399

        System.out.println("—".repeat(50));
        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println("Today and now is: " + todayAndNow); // Today and now is: 2021-05-05T12:00:00.000000

        LocalDateTime noonOfMayFifth = LocalDateTime.of(2023, 5, 5, 12, 0);
        System.out.printf("%tD %tr %n", noonOfMayFifth, noonOfMayFifth); // 05/05/23 12:00:00 PM
        System.out.printf("%1$tF %1$tT %n", noonOfMayFifth); // 2023-05-05 12:00:00

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE)); // 2021-W18-3

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("May fifth in full format is: " + noonOfMayFifth.format(formatter)); // May fifth in full format is: Friday, May 5, 2023
        System.out.println("May fifth in medium format is: " + noonOfMayFifth.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))); // May fifth in medium format is: May 5, 2023

        LocalDateTime noonOfMaySixth = noonOfMayFifth.plusHours(24);
        System.out.println("Noon of May sixth is: " + noonOfMaySixth); // Noon of May sixth is: 2023-05-06T12:00
        System.out.println("May sixth in medium format is: " + noonOfMaySixth.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))); // May sixth in medium format is: May 6, 2023, 12:00:00 PM
        System.out.println("May sixth is after May fifth? " + noonOfMaySixth.isAfter(noonOfMayFifth)); // May sixth is after May fifth? true

    }
}

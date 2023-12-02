package com.rishab.locale;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.format.DateTimeFormatter.*;

public class DateTimeAndLocaleChallenge {

    private record Employee(String name, Locale locale, ZoneId zone) {
        public Employee(String name, String locale, String zone) {
            this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
        }

        public Employee(String name, Locale locale, String zone) {
            this(name, locale, ZoneId.of(zone));
        }

        String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf) {
            return "%s [%s] : %s".formatted(name, zone, zdt.format(dtf.localizedBy(locale)));
        }
    }

    public static void main(String[] args) {

        Employee sam = new Employee("Sam", Locale.US, "America/New_York");
        Employee greg = new Employee("Greg", "fr-FR", "Europe/Paris");

        ZoneRules samRules = sam.zone.getRules();
        ZoneRules gregRules = greg.zone.getRules();
        System.out.println(sam + " : " + samRules);
        System.out.println(greg + " : " + gregRules);

        ZonedDateTime samNow = ZonedDateTime.now(sam.zone);
        ZonedDateTime gregNow = ZonedDateTime.of(samNow.toLocalDateTime(), greg.zone);
        long hoursBetween = Duration.between(samNow, gregNow).toHours();
        long minutesBetween = Duration.between(samNow, gregNow).toMinutesPart();

        System.out.println("Greg is " + Math.abs(hoursBetween) + " hours and " +
            Math.abs(minutesBetween) + " minutes " + (hoursBetween < 0 ? "ahead" : "behind") + " Sam");

        System.out.println("Greg in day light saving time: " +
            gregRules.isDaylightSavings(gregNow.toInstant()) + " " +
            gregRules.getDaylightSavings(gregNow.toInstant()) + ": " +
            gregNow.format(ofPattern("zzzz z")));

        System.out.println("Sam in day light saving time: " +
            samRules.isDaylightSavings(samNow.toInstant()) + " " +
            samRules.getDaylightSavings(samNow.toInstant()) + ": " +
            samNow.format(ofPattern("zzzz z")));

        int days = 10;
        Map<LocalDate, List<ZonedDateTime>> map = scheduleMeeting(sam, greg, days);
        DateTimeFormatter dtf = ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);

        for (LocalDate ld: map.keySet()) {
            System.out.println(ld.format(ofLocalizedDate(FormatStyle.FULL)));
            for (ZonedDateTime zdt : map.get(ld)) {
                System.out.println("\t" + sam.getDateInfo(zdt, dtf) + " <———> " +
                    greg.getDateInfo(zdt.withZoneSameInstant(greg.zone()), dtf));
            }
        }

    }

    private static Map<LocalDate, List<ZonedDateTime>> scheduleMeeting(Employee first, Employee second, int days) {
        Predicate<ZonedDateTime> rules = zdt -> zdt.getDayOfWeek() != DayOfWeek.SATURDAY &&
            zdt.getDayOfWeek() != DayOfWeek.SUNDAY &&
            zdt.getHour() >= 9 && zdt.getHour() <= 17;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days + 1))
            .map(date -> date.atStartOfDay(first.zone()))
            .flatMap(date -> IntStream.range(0, 24).mapToObj(date::withHour))
            .filter(rules)
            .map(zdt -> zdt.withZoneSameInstant(second.zone()))
            .filter(rules)
            .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate, TreeMap::new, Collectors.toList()));
    }
}

package com.rishab.moreOnTime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {

        System.setProperty("user.timezone", "America/Los_Angeles");
        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of Time Zones: " + ZoneId.getAvailableZoneIds().size());
        ZoneId.getAvailableZoneIds().stream()
            .filter(s -> s.startsWith("Asia"))
            .sorted()
            .map(ZoneId::of)
            .forEach(z -> System.out.println(z.getId() + ": " + z.getRules()));

        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternativeIds = TimeZone.getAvailableIDs();
        Set<String> oldWay = new HashSet<>(Set.of(alternativeIds));

        oldWay.removeAll(jdk8Zones);
        System.out.println("JDK 8 time zones are: " + jdk8Zones);

        ZoneId bet = ZoneId.of("BET", ZoneId.SHORT_IDS);
        System.out.println(bet);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        Instant instant = Instant.now();
        System.out.println(instant);

        System.out.println("—".repeat(50));
        for (ZoneId z : List.of(
            ZoneId.of("Asia/Calcutta"),
            ZoneId.of("Europe/Paris"),
            ZoneId.of("America/New_York"))) {
            DateTimeFormatter zoneFormatter = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + instant.atZone(z).format(zoneFormatter));
            System.out.println("\t" + z.getRules().getDaylightSavings(instant));
            System.out.println("\t" + z.getRules().isDaylightSavings(instant));
        }

        System.out.println("—".repeat(50));
        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("The date of birth is: " + dob.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime zdt = ZonedDateTime.ofInstant(dobInstant, ZoneId.of("Asia/Tokyo"));
        System.out.println("The date of birth is: " + zdt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime zdt2 = zdt.withZoneSameInstant(ZoneId.of("Asia/Calcutta"));
        System.out.println("The date of birth is: " + zdt2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        ZonedDateTime firstOfMonth = ZonedDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.printf("The first day of next month = %tD %n", firstOfMonth);

        System.out.println("—".repeat(50));
        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        System.out.println("Time past since EPOCH: " + timePast);

        Duration timeSince = Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        System.out.println("Time since EPOCH: " + timeSince);

        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4).plusDays(4).plusHours(7).plusMinutes(14).plusSeconds(37);
        System.out.println("The date of birth of 2nd child is: " + dob2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        Period timePast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        System.out.println("Time past since EPOCH: " + timePast2);

        Duration timeSince2 = Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        System.out.println("Time since EPOCH: " + timeSince2);

        for (ChronoUnit unit : ChronoUnit.values()) {
            if (unit.isSupportedBy(LocalDate.EPOCH)) {
                long val = unit.between(LocalDate.EPOCH, dob2.toLocalDate());
                System.out.println("Time past since EPOCH in " + unit + ": " + val);
            } else {
                System.out.println("Time past since EPOCH in " + unit + ": Not supported");
            }
        }

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (unit.isSupportedBy(ldt)) {
                long val = unit.between(ldt, dob2);
                System.out.println("Time past since EPOCH in " + unit + ": " + val);
            } else {
                System.out.println("Time past since EPOCH in " + unit + ": Not supported");
            }
        }

    }
}

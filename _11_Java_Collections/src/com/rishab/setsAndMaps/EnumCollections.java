package com.rishab.setsAndMaps;

import java.util.*;

public class EnumCollections {

    enum WeekDay {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}

    public static void main(String[] args) {

        // EnumSet
        List<WeekDay> employee1WorkDays = new ArrayList<>(List.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));
        var employee1DaysSet = EnumSet.copyOf(employee1WorkDays);
        System.out.println(employee1DaysSet.getClass().getSimpleName()); // RegularEnumSet
        employee1DaysSet.forEach(System.out::println); // MONDAY TUESDAY THURSDAY FRIDAY

        System.out.println();
        var allDaysSet = EnumSet.allOf(WeekDay.class);
        allDaysSet.forEach(System.out::println); // MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY

        System.out.println();
        Set<WeekDay> newPersonDaysSet = EnumSet.complementOf(employee1DaysSet);
        newPersonDaysSet.forEach(System.out::println); // WEDNESDAY SATURDAY SUNDAY

        System.out.println();
        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(employee1DaysSet);
        anotherWay.forEach(System.out::println); // WEDNESDAY SATURDAY SUNDAY

        System.out.println();
        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        businessDays.forEach(System.out::println); // MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY

        System.out.println();
        // EnumMap
        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);
        employeeMap.put(WeekDay.FRIDAY, new String[]{"Security", "Testing"});
        employeeMap.put(WeekDay.MONDAY, new String[]{"Development", "Research"});
        employeeMap.forEach((k, v) -> System.out.println(k + " -> " + Arrays.toString(v)));

    }
}

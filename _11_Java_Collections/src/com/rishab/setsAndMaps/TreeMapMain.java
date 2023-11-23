package com.rishab.setsAndMaps;

import java.time.LocalDate;
import java.util.*;

record Course(String courseId, String courseName, String courseSubject) {
}

record Purchase(String courseId, int studentsId, double price, int year, int dayOfYear) {
    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(year, dayOfYear);
    }
}

class Student {
    public static int lastId = 1;
    private String name;
    private int id;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        this.id = lastId++;
    }

    public Student(String name, Course course) {
        this(name, new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i -> courseList.get(i).courseName());
        return "[%d]: %s".formatted(id, String.join(", ", courseNames));
    }
}

public class TreeMapMain {

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course python = new Course("PY101", "Python 101", "Python");
        Course cpp = new Course("CPP101", "C++ 101", "C++");

        addPurchase("Peter", python, 99.99);
        addPurchase("Taylor", python, 129.99);
        addPurchase("Kathy", cpp, 149.99);
        addPurchase("William,", python, 99.99);
        addPurchase("Peter", cpp, 109.99);

        addPurchase("Linda", cpp, 199.99);
        addPurchase("Bob", python, 59.99);
        addPurchase("Ioana", cpp, 149.99);
        addPurchase("George", python, 99.99);
        addPurchase("Thomas", cpp, 119.99);

        purchases.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println();
        students.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println();
        NavigableMap<LocalDate, List<Purchase>> dateListNavigableMap = new TreeMap<>();
        for (Purchase p : purchases.values()) {
            dateListNavigableMap.compute(p.purchaseDate(), (k, v) -> {
                List<Purchase> list = (v == null) ? new ArrayList<>() : v;
                list.add(p);
                return list;
            });
        }
        dateListNavigableMap.forEach((k, v) -> System.out.println(k + " : " + v));

        int currentYear = LocalDate.now().getYear();
        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = dateListNavigableMap.headMap(week1);
        Map<LocalDate, List<Purchase>> week2Purchases = dateListNavigableMap.tailMap(week1);

        // System.out.println();
        // week1Purchases.forEach((k, v) -> System.out.println(k + " : " + v));
        // System.out.println();
        // week2Purchases.forEach((k, v) -> System.out.println(k + " : " + v));

        displayStats(1, week1Purchases);
        displayStats(2, week2Purchases);

        System.out.println();
        LocalDate lastDate = dateListNavigableMap.lastKey();
        var previousEntry = dateListNavigableMap.lastEntry();

        while (previousEntry != null) {
            List<Purchase> lastDaysData = previousEntry.getValue();
            System.out.println(lastDate + " purchases: " + lastDaysData.size());

            LocalDate prevDate = dateListNavigableMap.lowerKey(lastDate);
            previousEntry = dateListNavigableMap.lowerEntry(lastDate);
            lastDate = prevDate;
        }

        System.out.println();
        var reversed = dateListNavigableMap.descendingMap();
        LocalDate firstDate = reversed.firstKey();
        // var nextEntry = reversed.firstEntry();
        var nextEntry = reversed.pollFirstEntry();

        while(nextEntry != null) {
            List<Purchase> lastDaysData = nextEntry.getValue();
            System.out.println(firstDate + " purchases : " + lastDaysData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
            // nextEntry = reversed.higherEntry(firstDate);
            nextEntry = reversed.pollFirstEntry();
            firstDate = nextDate;
        }
        System.out.println();
        dateListNavigableMap.forEach((k, v) -> System.out.println(k + " : " + v));

    }

    public static void addPurchase(String name, Course course, double price) {
        Student student = students.get(name);
        if (student == null) {
            student = new Student(name, course);
            students.put(name, student);
        } else {
            student.addCourse(course);
        }

        int day = new Random().nextInt(1, 15);
        String key = course.courseId() + "—" + student.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), student.getId(), price, year, day);

        purchases.put(key, purchase);
    }

    private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {
        System.out.println("—".repeat(50));
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((k, v) -> {
            System.out.println(k + " : " + v);

            for (Purchase p : v) {
                weeklyCounts.merge(p.courseId(), 1, (prev, curr) -> prev + curr);
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }
}

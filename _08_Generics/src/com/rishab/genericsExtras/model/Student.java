package com.rishab.genericsExtras.model;

import com.rishab.genericsExtras.util.QueryItem;

import java.util.Comparator;
import java.util.Random;

public class Student implements QueryItem, Comparable<Student> {

    private static int LAST_ID = 10000;
    private int studentId;
    private String name;
    private String course;
    private int yearStarted;

    protected static Random random = new Random();
    private static String[] firstNames = {"John", "Jane", "Jack", "Jill", "James", "Jenny", "Joe", "Jade", "Jasper", "Jasmine"};
    private static String[] courses = {"Computer Science", "Mathematics", "Physics", "Chemistry", "Biology", "Geology", "Astronomy", "Engineering", "Medicine", "Law"};

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        this.name = firstNames[random.nextInt(10)] + " " + (char) lastNameIndex;
        this.course = courses[random.nextInt(10)];
        this.yearStarted = random.nextInt(2018, 2023);
        this.studentId = LAST_ID++;
    }

    @Override
    public String toString() {
        return "%d %-20s %-20s %-20s".formatted(this.studentId, this.name, this.course, this.yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> this.name.equalsIgnoreCase(value);
            case "COURSE" -> this.course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> this.yearStarted == Integer.parseInt(value);
            default -> false;
        };
    }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(studentId).compareTo(o.studentId);
    }
}

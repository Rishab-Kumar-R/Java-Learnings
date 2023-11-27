package com.rishab.unmodifiableCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        String lineSeparator = "—".repeat(50);

        List<Student> students = new ArrayList<>(List.of(bob, bill));
        List<Student> studentsFirstCopy = new ArrayList<>(students); // shallow copy
        List<Student> studentSecondCopy = List.copyOf(students);
        List<Student> studentsThirdCopy = Collections.unmodifiableList(students);

        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
        // studentSecondCopy.add(new Student("Bonnie", new StringBuilder())); // throws UnsupportedOperationException as List.copyOf() returns an unmodifiable list
        // studentSecondCopy.set(0, new Student("Bonnie", new StringBuilder())); // throws UnsupportedOperationException
        // studentSecondCopy.sort(Comparator.comparing(Student::getName)); // throws UnsupportedOperationException
        studentsFirstCopy.sort(Comparator.comparing(Student::getName)); // works fine
        // studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder())); // throws UnsupportedOperationException

        students.add(new Student("Bonnie", new StringBuilder()));

        bobsNotes.append("Bob waste time on the internet");

        StringBuilder bonnieNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonnieNotes.append("Bonnie spends his time playing games on his laptop");

        students.forEach(System.out::println);
        System.out.println(lineSeparator);

        studentsFirstCopy.forEach(System.out::println);
        System.out.println(lineSeparator);

        studentSecondCopy.forEach(System.out::println);
        System.out.println(lineSeparator);

        studentsThirdCopy.forEach(System.out::println);
        System.out.println(lineSeparator);

    }
}

package com.rishab.streamingStudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {
    public static void main(String[] args) {

        Course java = new Course("CSE110", "Java Programming");
        Course python = new Course("CSE120", "Python Programming");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(java, python))
            .limit(1000)
            .collect(Collectors.toList());

        Optional<Student> optionalStudent = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty: " + optionalStudent.isEmpty() + ", Present: " + optionalStudent.isPresent());
        System.out.println(optionalStudent);
        // System.out.println(optionalStudent.get());
        // optionalStudent.ifPresent(System.out::println); // prints nothing as optionalStudent is empty
        optionalStudent.ifPresentOrElse(System.out::println, () -> System.out.println("No student found"));

        Optional<Student> optionalStudent2 = getStudent(students, "first");
        System.out.println("Empty: " + optionalStudent2.isEmpty() + ", Present: " + optionalStudent2.isPresent());
        System.out.println(optionalStudent2);
        optionalStudent2.ifPresent(System.out::println);

        // Student firstStudent = optionalStudent2.orElse(getDummyStudent(java)); // getDummyStudent() is called even though optionalStudent2 is not empty
        Student firstStudent = optionalStudent2.orElseGet(() -> getDummyStudent(java)); // getDummyStudent() is not called as optionalStudent2 is not empty
        long id = firstStudent.getStudentId();
        System.out.println("First student id: " + id);

        List<String> countries = students.stream()
            .map(Student::getCountryCode)
            .distinct()
            .toList();

        // these may look like stream pipelines, but they are not, as they are not terminal operations
        Optional.of(countries)
            .map(l -> String.join(", ", l))
            .filter(l -> l.contains("UK"))
            .ifPresentOrElse(System.out::println, () -> System.out.println("No UK students found"));

    }

    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.isEmpty()) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        } else {
            return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
        }
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("--- getDummyStudent ---");
        return new Student("USA", 2020, 18, "M", true, courses);
    }
}

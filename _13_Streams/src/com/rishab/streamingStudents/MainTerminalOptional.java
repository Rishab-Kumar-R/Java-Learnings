package com.rishab.streamingStudents;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MainTerminalOptional {
    public static void main(String[] args) {

        Course java = new Course("CSE110", "Java Programming");
        Course python = new Course("CSE120", "Python Programming");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(java, python))
            .limit(1000)
            .toList();

        int minAge = 16;
        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .findAny()
            .ifPresentOrElse(
                s -> System.out.printf("Found student %d from %s is younger than %d\n",
                    s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("No student younger than " + minAge + " found"));

        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .findFirst()
            .ifPresentOrElse(
                s -> System.out.printf("Found student %d from %s is younger than %d\n",
                    s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("No student younger than " + minAge + " found"));

        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .min(Comparator.comparing(Student::getAge)) // replaces sorted(Comparator) and findFirst()
            .ifPresentOrElse(
                s -> System.out.printf("Found student %d from %s is younger than %d\n",
                    s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("No student younger than " + minAge + " found"));

        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .max(Comparator.comparing(Student::getAge))
            .ifPresentOrElse(
                s -> System.out.printf("Found student %d from %s is younger than %d\n",
                    s.getStudentId(), s.getCountryCode(), s.getAge()),
                () -> System.out.println("No student younger than " + minAge + " found"));

        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .mapToInt(Student::getAge)
            .average()
            .ifPresentOrElse(
                avg -> System.out.printf("Average age of students younger than %d is %.2f\n",
                    minAge, avg),
                () -> System.out.println("No student younger than " + minAge + " found"));

        students.stream()
            .filter(s -> s.getAge() <= minAge)
            .map(Student::getCountryCode)
            .distinct()
            .reduce((a, b) -> String.join(", ", a, b))
            .ifPresentOrElse(System.out::println, () -> System.out.println("None found"));

        students.stream()
            .map(Student::getCountryCode)
            .distinct()
            .map(l -> String.join(", ", l))
            .filter(l -> l.contains("RUS"))
            .findAny()
            .ifPresentOrElse(System.out::println, () -> System.out.println("No RUS students found"));

    }
}

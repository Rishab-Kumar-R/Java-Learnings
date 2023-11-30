package com.rishab.streamingStudents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Course java = new Course("CSE110", "Java Programming");
        Course python = new Course("CSE120", "Python Programming");

        // Student harry = new Student("USA", 2020, 18, "M", true, java, python);
        // System.out.println(harry);

        // harry.watchLecture("CSE110", 10, 5, 2021);
        // harry.watchLecture("CSE120", 7, 7, 2021);
        // System.out.println(harry);

        // Stream.generate(() -> Student.getRandomStudent(java, python))
        //     .limit(10)
        //     .forEach(System.out::println);

        Student[] students = new Student[1000];
        Arrays.setAll(students, i -> Student.getRandomStudent(java, python));

        var maleStudents = Arrays.stream(students)
            .filter(s -> s.getGender().equals("M"));

        System.out.println("# of male students: " + maleStudents.count());

        for (String gender : List.of("M", "F", "O")) {
            var student = Arrays.stream(students)
                .filter(s -> s.getGender().equals(gender));
            System.out.println("# of " + gender + " students: " + student.count());
        }

        List<Predicate<Student>> list = List.of(
            s -> s.getAge() < 20,
            (Student s) -> s.getAge() >= 20 && s.getAge() < 25
        );

        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            var student = Arrays.stream(students).filter(list.get(i));
            long count = student.count();
            total += count;
            System.out.printf("# of students (%s) = %d%n", i == 0 ? "under 20" : "20 to 25", count);
        }
        System.out.println("# of students >= 25 = " + (students.length - total));

        var ageStream = Arrays.stream(students)
            .mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for age enrolled: " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(students)
            .mapToInt(Student::getAge);
        System.out.println("Stats for current age: " + currentAgeStream.summaryStatistics());

        Arrays.stream(students)
            .map(Student::getCountryCode)
            .distinct()
            .sorted()
            .forEach(s -> System.out.print(s + " "));

        System.out.println();
        boolean longTerm = Arrays.stream(students)
            .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 5) && s.getMonthsSinceLastActivity() < 6);
        System.out.println("Long term students: " + longTerm);

        long longTermCount = Arrays.stream(students)
            .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 5) && s.getMonthsSinceLastActivity() < 6)
            .count();
        System.out.println("Long term students: " + longTermCount);

        // Arrays.stream(students)
        //     .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 5) && s.getMonthsSinceLastActivity() < 6)
        //     .filter(s -> !s.hasProgrammingExperience())
        //     .limit(5)
        //     .toList()
        //     .forEach(System.out::println);

        var longTimeLearners = Arrays.stream(students)
            .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 5) && s.getMonthsSinceLastActivity() < 6)
            .filter(s -> !s.hasProgrammingExperience())
            .limit(5)
            .toList(); // returns a List<Student> object, this is immutable
            // .toArray(Student[]::new); // returns an array of Student objects

        var learners = Arrays.stream(students)
            .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 5) && s.getMonthsSinceLastActivity() < 6)
            .filter(s -> !s.hasProgrammingExperience())
            .limit(5)
            .collect(Collectors.toList()); // returns a List<Student> object, this is mutable

        Collections.shuffle(learners);
        // Collections.shuffle(longTimeLearners); // UnsupportedOperationException, because it is immutable

    }
}

package com.rishab.streamingStudents;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class MainMapping {
    public static void main(String[] args) {

        Course python = new Course("CSE120", "Python Programming", 50);
        Course java = new Course("CSE110", "Java Programming", 100);
        Course javaGames = new Course("CSE118", "Creating Games in Java");

        List<Student> students = IntStream.rangeClosed(1, 5000)
            .mapToObj(i -> Student.getRandomStudent(java, python))
            .toList();

        var mappedStudents = students.stream()
            .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.println(k + ": " + v.size()));

        System.out.println("—".repeat(50));
        int minAge = 25;
        var youngerSet = students.stream()
            .collect(groupingBy(Student::getCountryCode,
                filtering(s -> s.getAge() <= minAge, toList())));
        youngerSet.forEach((k, v) -> System.out.println(k + ": " + v.size()));

        System.out.println("—".repeat(50));
        var experienced = students.stream()
            .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced students: " + experienced.get(true).size());

        System.out.println("—".repeat(50));
        var expCount = students.stream()
            .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced students: " + expCount.get(true));

        System.out.println("—".repeat(50));
        var experiencedAndActive = students.stream()
            .collect(partitioningBy(
                s -> s.hasProgrammingExperience() && s.getMonthsSinceLastActivity() == 0, counting()));
        System.out.println("Experienced and active students: " + experiencedAndActive.get(true));

        System.out.println("—".repeat(50));
        var multiLevel = students.stream()
            .collect(groupingBy(Student::getCountryCode,
                groupingBy(Student::getGender)));
        multiLevel.forEach((k1, v1) -> {
            System.out.println(k1 + ": " + v1.size());
            v1.forEach((k2, v2) -> System.out.println("  " + k2 + ": " + v2.size()));
        });

        System.out.println("—".repeat(50));
        long studentBodyCount = 0;
        for (var list : experienced.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("Student body count: " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
            .mapToInt(l -> l.size())
            .sum();
        System.out.println("Student body count: " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
            .map(l -> l.stream()
                .filter(s -> s.getMonthsSinceLastActivity() <= 1)
                .count()
            )
            .mapToLong(l -> l)
            .sum();
        System.out.println("Active student body count: " + studentBodyCount);

        // how complex the above operation is, hence the need for the flatMap() method
        long count = experienced.values().stream()
            .flatMap(l -> l.stream())
            .filter(s -> s.getMonthsSinceLastActivity() <= 1)
            .count();
        System.out.println("Active student body count: " + count);

        count = multiLevel.values().stream()
            .flatMap(map -> map.values().stream()
                .flatMap(list -> list.stream())
            )
            .filter(s -> s.getMonthsSinceLastActivity() <= 1)
            .count();
        System.out.println("Active student body count: " + count);

        count = multiLevel.values().stream()
            .flatMap(map -> map.values().stream())
            .flatMap(list -> list.stream())
            .filter(s -> s.getMonthsSinceLastActivity() <= 1)
            .count();
        System.out.println("Active student body count: " + count);

    }
}

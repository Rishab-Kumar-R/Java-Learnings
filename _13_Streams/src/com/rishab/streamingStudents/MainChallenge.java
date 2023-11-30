package com.rishab.streamingStudents;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainChallenge {
    public static void main(String[] args) {

        Course python = new Course("CSE120", "Python Programming", 50);
        Course java = new Course("CSE110", "Java Programming", 100);
        Course javaGames = new Course("CSE118", "Creating Games in Java");

        List<Student> students = IntStream.rangeClosed(1, 5000)
            .mapToObj(i -> Student.getRandomStudent(java, python))
            .toList();

        double totalPercent = students.stream()
            .mapToDouble(s -> s.getPercentComplete("CSE110"))
            .reduce(0, Double::sum);
        // .sum(); // same as above

        double averagePercent = totalPercent / students.size();
        System.out.printf("Average percent complete for CSE110: %.2f%%%n", averagePercent);

        int topPercent = (int) (1.25 * averagePercent);
        System.out.printf("Best Percent Complete for CSE110: %d%%%n", topPercent);

        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);
        List<Student> hardWorkers = students.stream()
            .filter(s -> s.getMonthsSinceLastActivity("CSE110") == 0)
            .filter(s -> s.getPercentComplete("CSE110") >= topPercent)
            .sorted(longTermStudent)
            .limit(10)
            .toList();

        hardWorkers.forEach(s -> {
            s.addCourse(javaGames);
            System.out.print(s.getStudentId() + " ");
        });
        System.out.println();

        Comparator<Student> uniqueSorted = longTermStudent.thenComparing(Student::getStudentId);
        students.stream()
            .filter(s -> s.getMonthsSinceLastActivity("CSE110") == 0)
            .filter(s -> s.getPercentComplete("CSE110") >= topPercent)
            .sorted(longTermStudent)
            .limit(10)
            // toList()
            // .collect(Collectors.toList())
            // .collect(Collectors.toSet())
            .collect(() -> new TreeSet<>(uniqueSorted), TreeSet::add, TreeSet::addAll)
            .forEach(s -> {
                s.addCourse(javaGames);
                System.out.print(s.getStudentId() + " ");
            });

    }
}

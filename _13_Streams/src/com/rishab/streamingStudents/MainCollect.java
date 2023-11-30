package com.rishab.streamingStudents;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.TreeSet;

public class MainCollect {
    public static void main(String[] args) {

        Course java = new Course("CSE110", "Java Programming");
        Course python = new Course("CSE120", "Python Programming");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(java, python))
            .limit(1000)
            .toList();

        Set<Student> indianStudents = students.stream()
            .filter(s -> s.getCountryCode().equals("IND"))
            .collect(Collectors.toSet()); // returns a Set<Student> instead of a Stream<Student>
        System.out.println("# of Indian students: " + indianStudents.size());

        Set<Student> underTwenty = students.stream()
            .filter(s -> s.getAgeEnrolled() < 20)
            .collect(Collectors.toSet());
        System.out.println("# of students under 20: " + underTwenty.size());

        Set<Student> youngIndianStudents = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngIndianStudents.addAll(indianStudents);
        youngIndianStudents.retainAll(underTwenty);
        youngIndianStudents.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();

        Set<Student> youngIndianStudents2 = students.stream()
            .filter(s -> s.getCountryCode().equals("IND"))
            .filter(s -> s.getAgeEnrolled() < 20)
            .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
        youngIndianStudents2.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();

        String countryList = students.stream()
            .map(Student::getCountryCode)
            .distinct()
            .sorted()
            .reduce("", (s1, s2) -> s1 + " " + s2);
        System.out.println("Countries: " + countryList);

    }
}

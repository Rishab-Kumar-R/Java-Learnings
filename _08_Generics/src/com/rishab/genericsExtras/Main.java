package com.rishab.genericsExtras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.rishab.genericsExtras.model.StudentWithPercent;
import com.rishab.genericsExtras.model.Student;
import com.rishab.genericsExtras.model.StudentWithPercentComparator;
import com.rishab.genericsExtras.util.QueryItem;
import com.rishab.genericsExtras.util.QueryList;

record Employee(String name) implements QueryItem {
    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        // printList(students);
        printMostList(students);

        List<StudentWithPercent> studentWithPercents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            studentWithPercents.add(new StudentWithPercent());
        }
        // printList(studentWithPercents);
        printMostList(studentWithPercents);

        System.out.println();

        testList(new ArrayList<>(List.of("Able", "Baker", "Charlie", "Delta", "Echo")));
        testList(new ArrayList<>(List.of(1, 2, 3, 4, 5)));

        var queryList = new QueryList<>(studentWithPercents);
        var matches = queryList.getMatches("Course", "Computer Science");
        printMostList(matches);

        System.out.println();
        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        printMostList(students2021);

        // QueryList<Employee> employeeQueryList = new QueryList<Employee>();

        System.out.println();

        QueryList<StudentWithPercent> studentWithPercentQueryList = new QueryList<>();
        for (int i = 0; i < 20; i++) {
            studentWithPercentQueryList.add(new StudentWithPercent());
        }
        System.out.println("StudentWithPercentQueryList in ordered form:");
        studentWithPercentQueryList.sort(Comparator.naturalOrder());
        printingList(studentWithPercentQueryList);

        System.out.println("Matches:");
        var listMatches = studentWithPercentQueryList
            .getMatches("PercentComplete", "50")
            .getMatches("Course", "Engineering");
        matches.sort(new StudentWithPercentComparator());
        printingList(listMatches);

        System.out.println("Ordered:");
        matches.sort(null);
        printingList(matches);

    }

    public static void printingList(List<?> students) {
        for (var student : students) {
            System.out.println(student);
        }
    }

    // the '?' is called a wildcard
    public static void printMostList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student);
        }
    }

    // Type Erasure - the compiler removes the generic type information and replaces it with the Object type
    public static void testList(List<?> list) {
        for (var element : list) {
            if (element instanceof String) {
                System.out.println("String: " + ((String) element).toUpperCase());
            } else if (element instanceof Integer) {
                System.out.println("Integer: " + ((Integer) element).floatValue());
            }
        }
    }

    // public static void testList(List<String> list) {
    //     for (var element : list) {
    //         System.out.println("String: " + element.toUpperCase());
    //     }
    // }

    // public static void testList(List<Integer> list) {
    //     for (var element : list) {
    //         System.out.println("Integer: " + element.floatValue());
    //     }
    // }

    public static <T extends Student> void printList(List<T> students) {
        for (var student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
        }
    }
}

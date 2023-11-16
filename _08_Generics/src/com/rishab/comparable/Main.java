package com.rishab.comparable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

        for (Integer i : others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo() returns %d\n", five, (val == 0 ? "==" : (val < 0 ? "<" : ">")), i, val);
        }

        String banana = "banana";
        String[] fruit = {"apple", "banana", "pear", "BANANA"};

        for (String s : fruit) {
            int val = banana.compareTo(s);
            System.out.printf("%s %s %s: compareTo() returns %d\n", banana, (val == 0 ? "==" : (val < 0 ? "<" : ">")), s, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

        System.out.println("A: " + (int) 'A' + ", a: " + (int) 'a');
        System.out.println("B: " + (int) 'B' + ", b: " + (int) 'b');

        Student chris = new Student("Chris");
        Student[] students = {new Student("Liz"), new Student("Sue"), chris, new Student("Bob")};
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));
        // System.out.println("Result = " + chris.compareTo("Mike")); // ClassCastException
        System.out.println("Result = " + chris.compareTo(new Student("CHRIS")));

        Comparator<Student> gpaComparator = new StudentGPAComparator();
        Arrays.sort(students, gpaComparator.reversed());
        System.out.println(Arrays.toString(students));

        System.out.println();

        Employee[] employees = {
            new Employee("George", 35),
            new Employee("Ringo", 24),
            new Employee("Paul", 22),
            new Employee("John", 28)
        };
        Arrays.sort(employees);
        System.out.println(Arrays.toString(employees));

        Comparator<Employee> ageComparator = new EmployeeAgeComparator();
        Arrays.sort(employees, ageComparator);
        System.out.println(Arrays.toString(employees));

    }
}

class Student implements Comparable<Student> {
    private static int LAST_ID = 1000;
    private static Random random = new Random();

    protected String name;
    private int id;
    protected double gpa;

    public Student(String name) {
        this.name = name;
        this.id = LAST_ID++;
        this.gpa = random.nextDouble(1.0, 4.0);
    }

    @Override
    public String toString() {
        return "%s (%d): %.2f".formatted(name, id, gpa);
    }

    // @Override
    // public int compareTo(Student o) {
    //     return this.name.compareTo(o.name);
    // }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
    }
}

class StudentGPAComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return (o1.gpa + o1.name).compareTo(o2.gpa + o2.name);
    }
}

class Employee implements Comparable<Employee> {
    private String name;
    protected int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age + "]";
    }
}

class EmployeeAgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.age - o2.age;
    }
}

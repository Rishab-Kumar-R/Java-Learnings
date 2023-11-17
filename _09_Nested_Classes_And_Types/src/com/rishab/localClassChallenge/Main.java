package com.rishab.localClassChallenge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Employee employee1 = new Employee("Peter", "Parker", "01/01/2001");
        Employee employee2 = new Employee("Tony", "Stark", "12/07/2000");
        Employee employee3 = new Employee("Bruce", "Banner", "19/12/2014");
        Employee employee4 = new Employee("Steve", "Rogers", "04/07/2011");
        Employee employee5 = new Employee("Natasha", "Romanoff", "04/07/2011");

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5));

        printOrderedList(employeeList, "name");
        System.out.println();
        printOrderedList(employeeList, "year");

    }

    public static void printOrderedList(List<Employee> employeeList, String sortField) {
        int currentYear = LocalDate.now().getYear();

        // local class
        class MyEmployee {
            final Employee containedEmployee;
            final int yearsWorked;
            final String fullName;

            public MyEmployee(Employee containedEmployee) {
                this.containedEmployee = containedEmployee;
                this.yearsWorked = currentYear - Integer.parseInt(containedEmployee.hireDate().split("/")[2]);
                this.fullName = String.join(" ", containedEmployee.firstName(), containedEmployee.lastName());
            }

            @Override
            public String toString() {
                return "%s has been working for %d years".formatted(fullName, yearsWorked);
            }
        }

        List<MyEmployee> myEmployeeList = new ArrayList<>();
        for (Employee employee : employeeList) {
            myEmployeeList.add(new MyEmployee(employee));
        }

        var comparator = new Comparator<MyEmployee>() {
            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {
                if (sortField.equalsIgnoreCase("name")) {
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };
        myEmployeeList.sort(comparator.reversed());

        for (MyEmployee myEmployee : myEmployeeList) {
            System.out.println(myEmployee);
        }
    }
}

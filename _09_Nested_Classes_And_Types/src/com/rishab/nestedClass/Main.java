package com.rishab.nestedClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee(1001, "Ash", 2010),
            new Employee(1007, "Garry", 2021),
            new Employee(1045, "Ethan", 2015),
            new Employee(1135, "Derek", 2022),
            new Employee(1003, "Bella", 2017)
        ));

        // employeeList.sort(new EmployeeComparator<>()); // from src/com/rishab/staticNestedClass/EmployeeComparator.java
        employeeList.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        System.out.println("Store Employees:");
        List<StoreEmployee> storeEmployeeList = new ArrayList<>(List.of(
            new StoreEmployee(1005, "Cathy", 2018, "Toronto"),
            new StoreEmployee(1002, "Finn", 2011, "Richmond Hill"),
            new StoreEmployee(1151, "Olivia", 2020, "Downtown"),
            new StoreEmployee(1077, "Hannah", 2012, "Richmond Hill"),
            new StoreEmployee(1009, "Dylan", 2018, "Toronto")
        ));

        var comparator = new StoreEmployee().new StoreEmployeeComparator<>();
        storeEmployeeList.sort(comparator);
        for (StoreEmployee storeEmployee : storeEmployeeList) {
            System.out.println(storeEmployee);
        }

        System.out.println("Employee with Pig Latin Names:");
        addPigLatinName(storeEmployeeList);

    }

    // Local class
    public static void addPigLatinName(List<? extends StoreEmployee> list) {
        String lastName = "Smith";
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private final String pigLatinName;
            private final Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() + " Pig Latin Name: " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return this.pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());
        for (var employee : list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin, employee));
        }

        newList.sort(null); // null means use the natural order
        for (var employee : newList) {
            System.out.println(employee.originalInstance.getName() + " " + employee.pigLatinName);
        }
    }
}

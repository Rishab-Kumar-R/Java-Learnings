package com.rishab.nestedClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunMethods {
    public static void main(String[] args) {

        List<StoreEmployee> storeEmployeeList = new ArrayList<>(List.of(
            new StoreEmployee(1005, "Cathy", 2018, "Toronto"),
            new StoreEmployee(1002, "Finn", 2011, "Richmond Hill"),
            new StoreEmployee(1151, "Olivia", 2020, "Downtown"),
            new StoreEmployee(1077, "Hannah", 2012, "Richmond Hill"),
            new StoreEmployee(1009, "Dylan", 2018, "Toronto")
        ));

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreEmployeeComparator<StoreEmployee>();

        // using local class
        class NameSort<T> implements Comparator<StoreEmployee> {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }

        var c3 = new NameSort<StoreEmployee>();

        // using anonymous class
        var c4 = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        sortIt(storeEmployeeList, c0); // com.rishab.nestedClass.EmployeeComparator@1b6d3586 (top-level class)
        sortIt(storeEmployeeList, c1); // com.rishab.nestedClass.Employee$EmployeeComparator@4554617c (static inner class)
        sortIt(storeEmployeeList, c2); // com.rishab.nestedClass.StoreEmployee$StoreEmployeeComparator@74a14482 (inner class)
        sortIt(storeEmployeeList, c3); // com.rishab.nestedClass.RunMethods$1NameSort@1540e19d (local class)
        sortIt(storeEmployeeList, c4); // com.rishab.nestedClass.RunMethods$2@677327b6 (anonymous class)
        // or
        sortIt(storeEmployeeList, (o1, o2) -> o1.getName().compareTo(o2.getName())); // com.rishab.nestedClass.RunMethods$$Lambda$1/0x0000000800060840@7c30a502 (lambda)

    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {
        System.out.println("Sorting with comparator: " + comparator.toString());
        list.sort(comparator);
        for (var employee : list) {
            System.out.println(employee);
        }
    }
}

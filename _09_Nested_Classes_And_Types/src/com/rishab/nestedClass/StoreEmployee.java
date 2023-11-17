package com.rishab.nestedClass;

import java.util.Comparator;

public class StoreEmployee extends Employee {
    private String store;

    public StoreEmployee() {
    }

    public StoreEmployee(int employeeId, String name, int yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s %-8s".formatted(super.toString(), store);
    }

    // This is a non-static nested class or inner class
    public class StoreEmployeeComparator<T extends StoreEmployee> implements Comparator<StoreEmployee> {

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            if (result == 0) { // if the store is same, sort by yearStarted
                return new Employee.EmployeeComparator<>("yearStarted").compare(o1, o2);
            }
            return result;
        }
    }
}

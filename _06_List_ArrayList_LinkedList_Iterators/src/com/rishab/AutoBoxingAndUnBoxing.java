package com.rishab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoBoxingAndUnBoxing {
    public static void main(String[] args) {

        Integer boxedInt = Integer.valueOf(56); // preferred but unnecessary
        Integer deprecatedBoxing = new Integer(56); // deprecated since JDK 9
        int unboxedInt = boxedInt.intValue(); // preferred but unnecessary

        // AutoBoxing and UnBoxing
        Integer autoBoxedInt = 56;
        int autoUnboxedInt = autoBoxedInt;
        System.out.println(autoBoxedInt.getClass().getName()); // java.lang.Integer
        System.out.println(autoUnboxedInt); // 56

        Double resultBoxed = getLiteralDoublePrimitive();
        double resultUnboxed = getDoubleObject();

        Integer[] wrapperArray = new Integer[5];
        wrapperArray[0] = 50; // AutoBoxing
        System.out.println("Wrapper Array: " + Arrays.toString(wrapperArray));
        System.out.println(wrapperArray[0].getClass().getName()); // java.lang.Integer

        Character[] characterArray = {'a', 'b', 'c'};
        System.out.println("Character Array: " + Arrays.toString(characterArray));
        System.out.println(characterArray[0].getClass().getName()); // java.lang.Character

        System.out.println();

        var list = List.of(1, 2, 3, 4, 5);
        System.out.println("List: " + list);

        System.out.println();

        Customer bob = new Customer("Bob", 1000.0);
        System.out.println(bob);

        Bank bank = new Bank("Bank of America");
        bank.addNewCustomer("David", 1000.0);
        System.out.println(bank);

        bank.addTransaction("David", 500.55);
        bank.addTransaction("David", -200.14);
        bank.printStatement("David");

        bank.addNewCustomer("Bob", 250.0);
        bank.addTransaction("Bob", 415.55);
        bank.printStatement("Bob");

    }

    private static ArrayList<Integer> getList(Integer... varargs) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : varargs) {
            list.add(i);
        }
        return list;
    }

    private static int returnAnInt(Integer i) {
        return i;
    }

    private static Integer returnAnInteger(int i) {
        return i;
    }

    private static Double getDoubleObject() {
        return Double.valueOf(56.0);
    }

    private static double getLiteralDoublePrimitive() {
        return 56.0;
    }
}

// Challenge
record Customer(String name, ArrayList<Double> transactions) {
    public Customer(String name, double initialDeposit) {
        this(name, new ArrayList<>(500));
        this.transactions.add(initialDeposit);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "name='" + name + '\'' +
            ", transactions=" + transactions +
            '}';
    }
}

class Bank {
    private String name;
    private ArrayList<Customer> customer = new ArrayList<>(5000);

    public Bank(String name) {
        this.name = name;
    }

    private Customer getCustomer(String customerName) {
        for (Customer customer : this.customer) {
            if (customer.name().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        System.out.println("Customer " + customerName + " not found");
        return null;
    }

    public void addNewCustomer(String customerName, double initialDeposit) {
        if (getCustomer(customerName) == null) {
            this.customer.add(new Customer(customerName, initialDeposit));
            System.out.println("Customer " + customerName + " added successfully");
        } else {
            System.out.println("Customer " + customerName + " already exists");
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
            "name='" + name + '\'' +
            ", customer=" + customer +
            '}';
    }

    public void addTransaction(String name, double transactionAmount) {
        Customer customer = getCustomer(name);
        if (customer != null) {
            customer.transactions().add(transactionAmount);
        }
    }

    public void printStatement(String customerName) {
        Customer customer = getCustomer(customerName);
        if (customer == null) {
            return;
        }
        System.out.println("-".repeat(50));
        System.out.println("Statement for " + customerName);
        for (double d : customer.transactions()) {
            System.out.printf("$%10.2f (%s)%n", d, d > 0 ? "deposit" : "withdrawal");
        }
    }
}

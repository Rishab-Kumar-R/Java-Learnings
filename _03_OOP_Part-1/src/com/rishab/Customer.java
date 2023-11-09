package com.rishab;

public class Customer {
    private String name;
    private double creditLimit;
    private String email;

    public Customer() {
        this("Default name", "Default email");
    }

    public Customer(String name, String email) {
        this(name, 1000.00, email);
    }

    public Customer(String name, double creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    public String getEmail() {
        return this.email;
    }
}

package com.rishab.constructorsProject;

public record Person(String name, String dob) {
    // Canonical constructor
    // public Person(String name, String dob) {
    //     this.name = name;
    //     this.dob = dob.replace("-", "/");
    //     // this.dob = dob.trim(); // we can't assign to dob again
    // }

    public Person(Person person) {
        this(person.name, person.dob);
    }

    // compact constructor
    public Person {
        if (dob == null) throw new IllegalArgumentException("dob cannot be null");
        dob = dob.replace("-", "/");
    }

    // Instance initializer is not allowed in records
    // {
    //     this.dob = "01/01/1950";
    // }
}

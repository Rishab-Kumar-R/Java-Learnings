package com.rishab.copyingClasses;

import java.util.Arrays;

record Person(String name, String dob, Person[] kids) {
    // copy constructor
    public Person(Person person) {
        this(person.name, person.dob, person.kids == null ? null : Arrays.copyOf(person.kids, person.kids.length));
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", kids=" + Arrays.toString(kids) +
            '}';
    }
}

public class Main {
    public static void main(String[] args) {

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963",
            new Person[]{
                new Person("Jack Jr", "01/01/1981", null),
                new Person("Jill Jr", "02/02/1982", null)
            });
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965",
            new Person[]{
                new Person("Jill Jr1", "01/01/1981", null),
                new Person("Jill Jr2", "02/02/1982", null)
            });

        Person[] persons = {joe, jim, jack, jane, jill};
        // Creating a shallow copy of persons' array
        // making a deep copy over here is not a good idea, because the Person record has two fields of type String
        // Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        // Creating a deep copy of persons' array
        Person[] personsCopy = persons.clone(); // this performs a shallow copy
        // Person[] personsCopy = new Person[persons.length];
        // Arrays.setAll(personsCopy, i -> new Person(persons[i])); // using lambda

        // for (int i = 0; i < persons.length; i++) {
            // Person person = persons[i];
            // Person[] kids = person.kids();
            // personsCopy[i] = new Person(persons[i]); // copy constructor
        // }

        // As it's a shallow copy, the Person objects are not copied. However, the references are copied
        // so the Person objects are shared between persons and personsCopy
        // so, if we change the Person object in persons, it will be reflected in personsCopy and vice versa
        var jillKids = personsCopy[4].kids();
        jillKids[1] = new Person("Jane Jr", "03/03/1983", null);

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("persons[" + i + "] and personsCopy[" + i + "] are pointing to the same object: " + persons[i]);
            } else {
                System.out.println("persons[" + i + "] and personsCopy[" + i + "] are pointing to the different object: " + persons[i]);
            }
        }

        System.out.println("5th person in persons: " + persons[4]);
        System.out.println("5th person in personsCopy: " + personsCopy[4]);

    }
}

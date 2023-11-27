package com.rishab.constructorsProject;

import com.rishab.external.Child;

public class Main {
    public static void main(String[] args) {

        Parent parent = new Parent("Alex", "01/01/1990", 4);
        Child child = new Child();

        System.out.println("Parent object:" + parent);
        System.out.println("Child object:" + child);

        System.out.println("--------------------");
        Person person = new Person("George", "01-01-1950");
        System.out.println(person);

        System.out.println("--------------------");
        Person personCopy = new Person(person);
        System.out.println(personCopy);

        System.out.println("--------------------");
        Generation g = Generation.BABY_BOOMER; // when an enum is loaded, all its values are instantiated
        // Generation generation = new Generation(); // enum type cannot be instantiated

    }
}

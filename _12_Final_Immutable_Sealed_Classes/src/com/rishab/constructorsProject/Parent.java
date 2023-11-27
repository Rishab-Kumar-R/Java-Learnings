package com.rishab.constructorsProject;

public class Parent {
    static {
        System.out.println("Parent static initializer block");
    }

    private final String name;
    private final String dob;
    protected final int siblings;

    // instance initializer block
    {
        // name = "Alex";
        // dob = "01/01/1990";
        System.out.println("Parent instance initializer block");
    }

    // public Parent() {
    //     System.out.println("No-arg Parent constructor");
    // }

    public Parent(String name, String dob, int siblings) {
         this.name = name;
         this.dob = dob;
         this.siblings = siblings;
        System.out.println("Parent constructor");
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "[name='" + name + "', dob='" + dob + "']";
    }
}

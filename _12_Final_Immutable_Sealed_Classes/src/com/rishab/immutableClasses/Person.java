package com.rishab.immutableClasses;

import java.util.Arrays;

public class Person {
    private String name;
    private String dob;
    private Person[] kids;

    public Person(String name, String dob) {
        this(name, dob, null);
    }

    public Person(String name, String dob, Person[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public String getDob() {
        return dob;
    }

    // public void setDob(String dob) {
    //     this.dob = dob;
    // }

    public Person[] getKids() {
        return kids;
    }

    public void setKids(Person[] kids) {
        this.kids = kids;
    }

    @Override
    public String toString() {
        String kidString = "N/A";

        if (this.kids != null) {
            String[] names = new String[this.kids.length];
            Arrays.setAll(names, i -> names[i] = this.kids[i] == null ? "" : this.kids[i].name);
            kidString = String.join(", ", names);
        }
        return this.name + ", dob = " + this.dob + ", kids = " + kidString;
    }
}

package com.rishab.immutableClasses;

public class Main {
    public static void main(String[] args) {

        // Person mike = new Person();
        // mike.setName("Mike");
        // Person john = new Person();
        // john.setName("John");
        // Person mary = new Person();
        // mary.setName("Mary");
        // Person harry = new Person();
        // harry.setName("Harry");
        // harry.setDob("02/10/1980");
        // harry.setKids(new Person[]{mike, john, mary});
        // System.out.println(harry);

        // harry.setName("Harry Potter");
        // harry.setKids(new Person[]{new Person(), new Person()});
        // System.out.println(harry);

        Person mike = new Person("Mike", "25/12/2018");
        Person john = new Person("John", "19/07/2016");
        Person mary = new Person("Mary", "05/03/2019");

        Person[] harryKids = new Person[]{mike, john, mary};
        Person harry = new Person("Harry", "02/10/1980", harryKids);

        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

        harry.setKids(new Person[]{new Person("Aaron", "01/01/2010")});
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Aaron

        Person[] kids = harry.getKids();
        kids[0] = john;
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = John

        kids = null;
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = John

        harry.setKids(kids);
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = N/A

    }
}

package com.rishab.immutableClasses;

public class MainRecord {
    public static void main(String[] args) {

        PersonRecord mike = new PersonRecord("Mike", "25/12/2018");
        PersonRecord john = new PersonRecord("John", "19/07/2016");
        PersonRecord mary = new PersonRecord("Mary", "05/03/2019");

        PersonRecord[] harryKids = new PersonRecord[]{mike, john, mary};
        PersonRecord harry = new PersonRecord("Harry", "02/10/1980", harryKids);

        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

        PersonRecord harryCopy = new PersonRecord("Harry", "02/10/1980");
        System.out.println(harryCopy); // Harry, dob = 02/10/1980, kids = , , , , , , , , , , , , , , , , , , ,

        PersonRecord[] kids = harryCopy.kids();
        kids[0] = john;
        kids[1] = new PersonRecord("Aaron", "01/01/2010");
        System.out.println(harryCopy); // Harry, dob = 02/10/1980, kids = John, Aaron, , , , , , , , , , , , , , , , , ,

        // but if we create a copy kids in PersonRecord, then
        // System.out.println(harryCopy); // Harry, dob = 02/10/1980, kids = , , , , , , , , , , , , , , , , , , ,

        // Unlike in Main.java where we could set the kids to null, we cannot do that here.
        // harryCopy.kids(null); // Error: cannot assign a value to final variable kids
        // Hence, at some level, PersonRecord is immutable. But it's not creating a defensive copy of the kids array.

        harryKids[0] = new PersonRecord("Yoda", "01/12/2020");
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Yoda, John, Mary
    }
}

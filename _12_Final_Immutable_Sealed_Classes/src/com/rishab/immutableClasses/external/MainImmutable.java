package com.rishab.immutableClasses.external;

import com.rishab.immutableClasses.PersonImmutable;
import com.rishab.immutableClasses.external.domain.LivingPerson;
import com.rishab.immutableClasses.hacker.PersonOfInterest;

public class MainImmutable {
    public static void main(String[] args) {

        PersonImmutable mike = new PersonImmutable("Mike", "25/12/2018");
        PersonImmutable john = new PersonImmutable("John", "19/07/2016");
        PersonImmutable mary = new PersonImmutable("Mary", "05/03/2019");

        PersonImmutable[] harryKids = new PersonImmutable[]{mike, john, mary};
        PersonImmutable harry = new PersonImmutable("Harry", "02/10/1980", harryKids);

        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

        PersonImmutable[] kids = harry.getKids();
        kids[0] = john;
        kids[1] = new PersonImmutable("Aaron", "01/01/2010");
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

        harryKids[0] = new PersonImmutable("Yoda", "01/12/2020");
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

        LivingPerson harryLiving = new LivingPerson(harry.getName(), harry.getKids());
        System.out.println(harryLiving); // Harry, dob = null, kids = Mike, John, Mary, , , , , , ,

        LivingPerson bob = new LivingPerson("Bob", null);
        harryLiving.addKid(bob);
        System.out.println(harryLiving); // Harry, dob = null, kids = Mike, John, Mary, Bob, , , , , ,

        PersonOfInterest harryClone = new PersonOfInterest(harry);
        System.out.println(harryClone); // Harry, dob = 02/10/1980, kids = Mike, John, Mary
        kids = harryClone.getKids();
        kids[1] = mary;
        System.out.println(harryClone); // Harry, dob = 02/10/1980, kids = Mike, Mary, Mary
        System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, Mary, Mary

        // but if we make a constructor chain to PersonImmutable, then we can't do this
        // System.out.println(harryClone); // Harry, dob = 02/10/1980, kids = Mike, John, Mary
        // System.out.println(harry); // Harry, dob = 02/10/1980, kids = Mike, John, Mary

    }
}

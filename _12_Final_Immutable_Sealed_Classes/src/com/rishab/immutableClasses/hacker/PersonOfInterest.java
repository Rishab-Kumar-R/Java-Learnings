package com.rishab.immutableClasses.hacker;

import com.rishab.immutableClasses.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {
    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }
}

package com.rishab.immutableClasses;

import java.util.Arrays;

public class PersonImmutable {
    private final String name;
    private final String dob;
    protected final PersonImmutable[] kids;

    public PersonImmutable(String name, String dob) {
        this(name, dob, null);
    }

    public PersonImmutable(String name, String dob, PersonImmutable[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids == null ? null : Arrays.copyOf(kids, kids.length);
    }

    // Copy constructor, hence a subclass can extend this class and override this constructor.
    protected PersonImmutable(PersonImmutable person) {
        this(person.getName(), person.getDob(), person.getKids());
    }

    // Returning a defensive copy of an immutable type is not necessary.
    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    // Returning a defensive copy of a mutable type(like an array).
    public final PersonImmutable[] getKids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }

    @Override
    public String toString() {
        String kidString = "N/A";

        if (this.kids != null) {
            String[] names = new String[this.kids.length];
            Arrays.setAll(names, i -> names[i] = this.kids[i] == null ? "" : this.kids[i].name);
            kidString = String.join(", ", names);
        }
        return this.name + ", dob = " + getDob() + ", kids = " + kidString;
    }
}

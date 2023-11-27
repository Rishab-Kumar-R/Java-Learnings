package com.rishab.immutableClasses;

import java.util.Arrays;

public record PersonRecord(String name, String dob, PersonRecord[] kids) {
    public PersonRecord(String name, String dob) {
        this(name, dob, new PersonRecord[20]);
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

    @Override
    public PersonRecord[] kids() {
        return kids == null ? null : Arrays.copyOf(kids, kids.length);
    }
}

package com.rishab.setsAndMaps;

import java.util.Set;
import java.util.HashSet;

public class Contact {
    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<String> phoneNumbers = new HashSet<>();

    public Contact(String name) {
        this(name, null);
    }

    public Contact(String name, String email) {
        this(name, email, 0);
    }

    public Contact(String name, long phoneNumber) {
        this(name, null, phoneNumber);
    }

    public Contact(String name, String email, long phoneNumber) {
        this.name = name;
        if (email != null) {
            this.emails.add(email);
        }
        if (phoneNumber > 0) {
            String phoneNumberString = String.valueOf(phoneNumber);
            phoneNumberString = "(%s) %s-%s".formatted(
                phoneNumberString.substring(0, 3),
                phoneNumberString.substring(3, 6),
                phoneNumberString.substring(6)
            );
            this.phoneNumbers.add(phoneNumberString);
        }
    }

    public String getName() {
        return name;
    }

    public String getNameLastFirst() {
        return name.substring(name.indexOf(" ") + 1) + ", " + name.substring(0, name.indexOf(" "));
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phoneNumbers);
    }

    public Contact mergeContactData(Contact contact) {
        Contact newContact = new Contact(this.name);

        newContact.emails = new HashSet<>(this.emails);
        newContact.phoneNumbers = new HashSet<>(this.phoneNumbers);

        newContact.emails.addAll(contact.emails);
        newContact.phoneNumbers.addAll(contact.phoneNumbers);

        return newContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        return 33 * getName().hashCode();
    }

    public void addEmail(String companyName) {
        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted(
            name.charAt(0),
            names[names.length - 1],
            companyName.replaceAll(" ", "").toLowerCase()
        );
        if (!emails.add(email)) {
            System.out.println("Email already exists");
        } else {
            System.out.println(name + " added email: " + email);
        }
    }

    public void replaceEmailIfExist(String oldEmail, String newEmail) {
        if (emails.contains(oldEmail)) {
            emails.remove(oldEmail);
            emails.add(newEmail);
        }
    }
}

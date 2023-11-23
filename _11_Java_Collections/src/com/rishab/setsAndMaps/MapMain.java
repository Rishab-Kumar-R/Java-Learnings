package com.rishab.setsAndMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        String lineSeparator = "—".repeat(50);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println(lineSeparator);

        Map<String, Contact> contactMap = new HashMap<>();
        for (Contact contact : fullList) {
            contactMap.put(contact.getName(), contact);
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println(lineSeparator);
        System.out.println(contactMap.get("Charlie Brown"));
        System.out.println(contactMap.get("Chuck Brown"));

        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contactMap.getOrDefault("Chuck Brown", defaultContact));

        System.out.println(lineSeparator);
        contactMap.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contactMap.put(contact.getName(), contact);
            if (duplicate != null) {
                // System.out.println("duplicate = " + duplicate);
                // System.out.println("current value = " + contact);
                contactMap.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        contactMap.clear();
        for (Contact contact : fullList) {
            contactMap.putIfAbsent(contact.getName(), contact);
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        contactMap.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contactMap.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contactMap.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        contactMap.clear();
        fullList.forEach(contact -> contactMap.merge(contact.getName(), contact, Contact::mergeContactData));
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contactMap.compute(contactName, (k, v) -> new Contact(k)); // this will create new contact objects
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contactMap.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        for (String contactName : new String[]{"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contactMap.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun Place");
                return v;
            });
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        contactMap.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExist("DDuck@funplace.com", newEmail);
            return v;
        });
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");
        Contact replacedContact = contactMap.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy " + updatedDaisy);
        boolean success = contactMap.replace("Daisy Duck", daisy, updatedDaisy);
        if (success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Didn't match both key: %s and value: %s %n".formatted("Daisy Duck", replacedContact));
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println(lineSeparator);
        success = contactMap.remove("Daisy Duck", daisy);
        if (success) {
            System.out.println("Successfully removed element");
        } else {
            System.out.printf("Didn't match on both key: %s and value: %s %n".formatted("Daisy Duck", daisy));
        }
        contactMap.forEach((k, v) -> System.out.println("key = " + k + ", value = " + v));

    }
}

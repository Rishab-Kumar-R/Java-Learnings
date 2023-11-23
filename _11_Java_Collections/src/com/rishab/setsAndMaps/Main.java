package com.rishab.setsAndMaps;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone Data", phones);
        printData("Email Data", emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExist("RHood@sherwoodforest.com", "RHood@sherwoodforest.org");
        System.out.println(robinHood);

        // Set operations
        // Union (A ∪ B)
        Set<Contact> unionOfEmailAndPhone = new HashSet<>();
        unionOfEmailAndPhone.addAll(emailContacts);
        unionOfEmailAndPhone.addAll(phoneContacts);
        printData("Email ∪ Phone", unionOfEmailAndPhone);

        // Intersection (A ∩ B)
        Set<Contact> intersectionOfEmailAndPhone = new HashSet<>(emailContacts);
        intersectionOfEmailAndPhone.retainAll(phoneContacts);
        printData("Email ∩ Phone", intersectionOfEmailAndPhone);

        Set<Contact> intersectionOfPhoneAndEmail = new HashSet<>(phoneContacts);
        intersectionOfPhoneAndEmail.retainAll(emailContacts);
        printData("Phone ∩ Email", intersectionOfPhoneAndEmail);

        // Difference (A - B = A ∩ B')
        Set<Contact> emailMinusPhone = new HashSet<>(emailContacts);
        emailMinusPhone.removeAll(phoneContacts);
        printData("Email - Phone", emailMinusPhone);

        Set<Contact> phoneMinusEmail = new HashSet<>(phoneContacts);
        phoneMinusEmail.removeAll(emailContacts);
        printData("Phone - Email", phoneMinusEmail);

        // Symmetric Difference (A Δ B = (A ∪ B) - (A ∩ B)) || (A Δ B = (A - B) ∪ (B - A))
        Set<Contact> symmetricDifference = new HashSet<>(emailMinusPhone);
        symmetricDifference.addAll(phoneMinusEmail);
        printData("Email Δ Phone", symmetricDifference);

        Set<Contact> symmetricDifference2 = new HashSet<>(unionOfEmailAndPhone);
        symmetricDifference2.removeAll(intersectionOfEmailAndPhone);
        printData("Email Δ Phone", symmetricDifference2);

        System.out.println();
        Set<Integer> set1 = new HashSet<>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(new Integer[]{4, 5, 6, 7, 8}));

        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);

        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference: " + difference);

        Set<Integer> difference2 = new HashSet<>(set1);
        difference2.removeAll(set2);
        System.out.println("Symmetric Difference: " + difference2);

    }

    public static void printData(String header, Collection<Contact> contacts) {
        System.out.println("-".repeat(50));
        System.out.println(header);
        System.out.println("-".repeat(50));
        contacts.forEach(System.out::println);
    }
}

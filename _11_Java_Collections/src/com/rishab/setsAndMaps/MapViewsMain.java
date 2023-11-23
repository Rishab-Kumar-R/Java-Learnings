package com.rishab.setsAndMaps;

import java.util.*;

public class MapViewsMain {
    public static void main(String[] args) {

        String lineSeparator = "—".repeat(50);

        Map<String, Contact> contactMap = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contactMap.put(c.getName(), c));
        ContactData.getData("email").forEach(e -> contactMap.put(e.getName(), e));

        Set<String> keyViews = contactMap.keySet();
        System.out.println(keyViews);

        Set<String> copyOfKeyViews = new TreeSet<>(keyViews);
        System.out.println(copyOfKeyViews);

        if (contactMap.containsKey("Linus Van Pelt")) {
            System.out.println("Linus Van Pelt is in the map");
        }

        System.out.println(lineSeparator);
        keyViews.remove("Daffy Duck");
        System.out.println(keyViews);
        contactMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println(lineSeparator);
        copyOfKeyViews.remove("Linus Van Pelt");
        System.out.println(copyOfKeyViews);
        contactMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println(lineSeparator);
        keyViews.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));
        System.out.println(keyViews);
        contactMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println(lineSeparator);
        keyViews.clear();
        System.out.println(contactMap);

        ContactData.getData("email").forEach(e -> contactMap.put(e.getName(), e));
        ContactData.getData("phone").forEach(c -> contactMap.put(c.getName(), c));
        System.out.println(keyViews);

        System.out.println(lineSeparator);
        var values = contactMap.values();
        values.forEach(System.out::println);
        values.retainAll(ContactData.getData("email"));
        System.out.println(keyViews);
        contactMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println(lineSeparator);
        List<Contact> list = new ArrayList<>(contactMap.values());
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + " -> " + c));

        System.out.println(lineSeparator);
        Contact first = list.getFirst();
        contactMap.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keyViews.forEach(System.out::println);

        System.out.println(lineSeparator);
        Set<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contactMap.keySet().size()) {
            System.out.println("There are duplicates in the map");
        }

        System.out.println(lineSeparator);
        Set<Map.Entry<String, Contact>> entrySet = contactMap.entrySet();
        for (var entry : entrySet) {
            System.out.println(entrySet.getClass().getName());
            if (!entry.getKey().equals(entry.getValue().getName())) {
                System.out.println(entrySet.getClass().getName());
                System.out.println("Key and value are not equal for " + entry.getKey() + " -> " + entry.getValue());
            }
        }

    }
}

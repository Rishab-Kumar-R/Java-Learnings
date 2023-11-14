package com.rishab;

import java.util.*;

record GroceryItem(String name, String type, int count) {
    public GroceryItem(String name) {
        this(name, "DAIRY", 1);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) x %d", name.toUpperCase(), type, count);
    }
}

public class ArrayListDemo {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("Milk");
        groceryArray[1] = new GroceryItem("Apples", "PRODUCE", 6);
        // groceryArray[2] = "5 Oranges"; // This will throw an error
        groceryArray[2] = new GroceryItem("Bread", "BAKERY", 2);
        System.out.println(Arrays.toString(groceryArray));

        ArrayList objectList = new ArrayList();
        objectList.add(new GroceryItem("Butter"));
        objectList.add("Yogurt"); // If type is not specified, it will be Object, in that case we can add anything
        System.out.println(objectList);

        ArrayList<GroceryItem> groceryList = new ArrayList<>();
        groceryList.add(new GroceryItem("Lettuce", "PRODUCE", 1));
        // groceryList.add("Tomatoes"); // This will throw an error
        groceryList.add(new GroceryItem("Tomatoes", "PRODUCE", 3));
        groceryList.add(new GroceryItem("Bananas", "PRODUCE", 5));
        groceryList.add(0, new GroceryItem("Eggs", "DAIRY", 12));
        groceryList.set(1, new GroceryItem("Oranges", "PRODUCE", 5));
        groceryList.remove(2);
        System.out.println(groceryList);

        System.out.println();

        String[] items = {"Milk", "Bread", "Butter", "Eggs", "Yogurt"};

        List<String> list = List.of(items);
        System.out.println("List: " + list);
        // System.out.println(list.getClass().getName()); // class java.util.ImmutableCollections$ListN
        // list.add("Cheese"); // This will throw an error

        ArrayList<String> groceries = new ArrayList<>(list);
        // System.out.println(groceries.getClass().getName()); // class java.util.ArrayList
        groceries.add("Cheese");
        System.out.println("Groceries: " + groceries);

        ArrayList<String> anotherList = new ArrayList<>(List.of("Pasta", "Rice", "Beans"));
        System.out.println("Another List: " + anotherList);
        groceries.addAll(anotherList);
        System.out.println("Groceries: " + groceries);

        System.out.println("Groceries third item: " + groceries.get(2));
        System.out.println("Groceries contains Milk: " + groceries.contains("Milk"));
        System.out.println("Index of Yogurt: " + groceries.indexOf("Yogurt"));
        groceries.add("Yogurt");
        System.out.println("Last index of Yogurt: " + groceries.lastIndexOf("Yogurt"));
        System.out.println("Groceries size: " + groceries.size());
        System.out.println("Groceries is empty: " + groceries.isEmpty());
        System.out.println("Groceries: " + groceries);

        groceries.remove(1);
        groceries.remove("Pasta");
        System.out.println("Groceries: " + groceries);
        groceries.removeAll(List.of("Yogurt", "Rice"));
        System.out.println("Groceries: " + groceries);
        groceries.removeIf(item -> item.startsWith("B"));
        System.out.println("Groceries: " + groceries);

        groceries.addAll(0, List.of("Lettuce", "Honey", "Nuts", "Ketchup"));
        System.out.println("Groceries: " + groceries);

        groceries.retainAll(List.of("Lettuce", "Ketchup", "Milk"));
        System.out.println("Groceries: " + groceries);
        groceries.clear();
        System.out.println("Groceries: " + groceries);
        System.out.println("Groceries is empty: " + groceries.isEmpty());

        groceries.addAll(List.of("Einstein Bagels", "Onions", "Peanut Butter", "Jelly"));
        groceries.addAll(Arrays.asList("Eggs", "Pizza", "Bananas", "Ham", "Donuts"));
        System.out.println("Groceries: " + groceries);
        groceries.sort(Comparator.naturalOrder());
        System.out.println("Sorted Groceries: " + groceries);
        groceries.sort(Comparator.reverseOrder());
        System.out.println("Reverse Sorted Groceries: " + groceries);

        var groceriesArray = groceries.toArray(new String[groceries.size()]);
        System.out.println("Groceries Array: " + Arrays.toString(groceriesArray));

        System.out.println();

        String[] originalArray = new String[]{"First", "Second", "Third"};
        var originalList = Arrays.asList(originalArray);

        originalList.set(0, "one");
        System.out.println("Original List: " + originalList); // [one, Second, Third]
        System.out.println("Original Array: " + Arrays.toString(originalArray)); // [one, Second, Third]

        originalList.sort(Comparator.naturalOrder());
        System.out.println("Sorted Original List: " + originalList);
        System.out.println("Sorted Original Array: " + Arrays.toString(originalArray)); // [Second, Third, one]

        // originalList.remove(0); // This will throw an error at runtime
        // originalList.add("Fourth"); // This will throw an error at runtime
        System.out.println("Original List: " + originalList);

        List<String> newList = Arrays.asList("Sunday", "Monday", "Tuesday");
        System.out.println("New List: " + newList);

        System.out.println();

        // Challenge
        boolean flag = true;
        ArrayList<String> groceriesList = new ArrayList<>();

        while (flag) {
            printActions();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> addItems(groceriesList);
                case 2 -> removeItems(groceriesList);
                default -> flag = false;
            }
            groceriesList.sort(Comparator.naturalOrder());
            System.out.println("Groceries List: " + groceriesList);
        }

    }

    private static void addItems(ArrayList<String> groceriesList) {
        System.out.print("Enter item(s) to add (comma delimited list): ");
        String[] items = scanner.nextLine().split(",");
        // groceriesList.addAll(List.of(items));
        for (String item : items) {
            String trimmedItem = item.trim();
            if (!groceriesList.contains(trimmedItem)) {
                groceriesList.add(trimmedItem);
            }
        }
    }

    private static void removeItems(ArrayList<String> groceriesList) {
        System.out.print("Enter item(s) to remove (comma delimited list): ");
        String[] items = scanner.nextLine().split(",");
        for (String item : items) {
            String trimmedItem = item.trim();
            groceriesList.remove(trimmedItem);

        }
    }

    private static void printActions() {
        String textBlock = """
            Available actions:
            0 - to quit
            1 - to add item(s) to the list (comma delimited list)
            2 - to remove item(s) from the list (comma delimited list)
            Enter a number for which actions you want to perform:""";
        System.out.print(textBlock + " ");
    }
}

class Contact {
    private final String name;
    private final String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public static Contact createContact(String name, String phoneNumber) {
        return new Contact(name, phoneNumber);
    }
}

class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts = new ArrayList<>();

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            System.out.println("Contact already exists");
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact);
        if (position < 0) {
            System.out.println("Contact not found");
            return false;
        }
        myContacts.set(position, newContact);
        return true;
    }

    public boolean removeContact(Contact contact) {
        int position = findContact(contact);
        if (position < 0) {
            System.out.println("Contact not found");
            return false;
        }
        myContacts.remove(position);
        return true;
    }

    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            System.out.println((i + 1) + ". " + myContacts.get(i).getName() + " -> " + myContacts.get(i).getPhoneNumber());
        }
    }
}

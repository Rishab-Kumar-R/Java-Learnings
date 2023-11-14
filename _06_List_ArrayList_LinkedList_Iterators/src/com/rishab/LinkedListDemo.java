package com.rishab;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Scanner;

public class LinkedListDemo {
    public static void main(String[] args) {

        // LinkedList<String> placesToVisit = new LinkedList<>();
        var placesToVisit = new LinkedList<String>();

        placesToVisit.add("Sydney");
        placesToVisit.add(0, "Melbourne");
        placesToVisit.add("Brisbane");
        System.out.println("Places to visit: " + placesToVisit);

        addMoreElements(placesToVisit);
        System.out.println("Places to visit after adding more elements: " + placesToVisit);

        // removeElements(placesToVisit);
        // System.out.println("Places to visit after removing elements: " + placesToVisit);

        // gettingElements(placesToVisit);

        // printItinerary(placesToVisit);
        // printItinerary2(placesToVisit);
        // printItinerary3(placesToVisit);

        // testIterator(placesToVisit);
        testListIterator(placesToVisit);

        System.out.println();

        LinkedList<Place> places = new LinkedList<>();
        Place adelaide = new Place("Adelaide", 1374);
        addPlace(places, adelaide);
        addPlace(places, new Place("adelaide", 1374));
        addPlace(places, new Place("Brisbane", 917));
        addPlace(places, new Place("Perth", 3923));
        addPlace(places, new Place("Alice Springs", 2771));
        addPlace(places, new Place("Darwin", 3972));
        addPlace(places, new Place("Melbourne", 877));
        places.addFirst(new Place("Sydney", 0));
        System.out.println(places);

        var iterator = places.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;

        printMenu();
        while (!quit) {
            if (!iterator.hasPrevious()) {
                System.out.println("We are at the start of the list. Originating: " + iterator.next());
                goingForward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("We are at the end of the list. Destination: " + iterator.previous());
                goingForward = false;
            }
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase().substring(0, 1);
            switch (choice) {
                case "F":
                    System.out.println("Going forward");
                    // Reverse the direction if we are at the end of the list
                    if (!goingForward) {
                        goingForward = true;
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Next place: " + iterator.next());
                    }
                    break;
                case "B":
                    System.out.println("Going backward");
                    // Reverse the direction if we are at the start of the list
                    if (goingForward) {
                        goingForward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Previous place: " + iterator.previous());
                    }
                    break;
                case "L":
                    System.out.println("List of places: " + places);
                    break;
                case "M":
                    printMenu();
                    break;
                default:
                    quit = true;
                    break;
            }
        }
        scanner.close();

    }

    private static void printMenu() {
        System.out.println("""
            Available actions (select option):
            (F)orward
            (B)ackward
            (L)ist Places
            (M)enu
            (Q)uit""");
    }

    private static void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Place " + place + " already exists in the list");
            return;
        }
        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Place " + p + " already exists in the list");
                return;
            }
        }
        int matchedIndex = 0;
        for (var listPlace : list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    private static void addMoreElements(LinkedList<String> list) {
        list.addFirst("Perth");
        list.add("Adelaide");
        list.addLast("Darwin");

        // Queue methods
        list.offer("Alice Springs");
        list.offerFirst("Ottawa");
        list.offerLast("Toronto");

        // Stack methods
        list.push("Vancouver");
    }

    private static void removeElements(LinkedList<String> list) {
        list.remove(4);
        list.remove("Adelaide");

        System.out.println("List in removeElements method: " + list);
        String element1 = list.remove(); // Removes the first element
        System.out.println("Removed element: " + element1);
        String element2 = list.removeFirst(); // Same as remove()
        System.out.println("Removed first element: " + element2);
        String element3 = list.removeLast(); // Removes the last element
        System.out.println("Removed last element: " + element3);

        // Queue methods
        String element4 = list.poll(); // Removes the first element
        System.out.println("Polled element: " + element4);
        String element5 = list.pollFirst(); // Same as poll()
        System.out.println("Polled first element: " + element5);
        String element6 = list.pollLast(); // Removes the last element
        System.out.println("Polled last element: " + element6);

        list.push("Halifax");
        list.push("Jasper");
        list.push("Quebec City");
        System.out.println("List after pushing elements: " + list);

        // Stack methods
        String element7 = list.pop(); // Removes the first element
        System.out.println("Popped element: " + element7);
    }

    private static void gettingElements(LinkedList<String> list) {
        System.out.println("Retrieved element: " + list.get(4)); // Returns the element at the specified index
        System.out.println("Retrieved first element: " + list.getFirst()); // Returns the first element
        System.out.println("Retrieved last element: " + list.getLast()); // Returns the last element
        System.out.println("Brisbane is at index: " + list.indexOf("Brisbane")); // Returns the first index of the element
        list.addLast("Melbourne");
        System.out.println("Last index of Melbourne: " + list.lastIndexOf("Melbourne")); // Returns the last index of the element

        // Queue methods
        System.out.println("Element at the head of the queue: " + list.element()); // Returns first element

        // Stack methods
        System.out.println("Element at the top of the stack peek(): " + list.peek()); // Returns first element
        System.out.println("Element at the top of the stack peekFirst(): " + list.peekFirst()); // Returns first element
        System.out.println("Element at the top of the stack peekLast(): " + list.peekLast()); // Returns last element
    }

    // Using for loop
    public static void printItinerary(LinkedList<String> list) {
        System.out.println("Trip starts from: " + list.getFirst());
        for (int i = 1; i < list.size(); i++) {
            System.out.println("--> From " + list.get(i - 1) + " to " + list.get(i));
        }
        System.out.println("Trip ends at: " + list.getLast());
    }

    // Using for-each loop
    public static void printItinerary2(LinkedList<String> list) {
        System.out.println("Trip starts from: " + list.getFirst());
        String previousTown = list.getFirst();
        for (String town : list) {
            System.out.println("--> From " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at: " + list.getLast());
    }

    // Using ListIterator
    public static void printItinerary3(LinkedList<String> list) {
        System.out.println("Trip starts from: " + list.getFirst());
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            var town = iterator.next();
            System.out.println("--> From " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at: " + list.getLast());
    }

    private static void testIterator(LinkedList<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase("brisbane")) {
                iterator.remove();
                // list.remove(); // Throws ConcurrentModificationException meaning that the list has been modified
            }
            // System.out.println(iterator.next());
        }
        System.out.println("List after removing Brisbane in testIterator method: " + list);
    }

    private static void testListIterator(LinkedList<String> list) {
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase("sydney")) {
                iterator.add("Canberra");
            }
        }
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println("List after iterating in testListIterator method: " + list);

        var iterator2 = list.listIterator(3);
        System.out.println("The next element from index 3: " + iterator2.next());
        System.out.println("The previous element from index 3: " + iterator2.previous());
    }
}

// Challenge:
record Place(String name, int distance) {
    @Override
    public String toString() {
        return String.format("%s (%d km)", name, distance);
    }
}

class Song {
    private final String title;
    private final double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Song{" +
            "title='" + title + '\'' +
            ", duration=" + duration +
            '}';
    }
}

class Album {
    private final String name;
    private final String artist;
    private final ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) {
            songs.add(new Song(title, duration));
            return true;
        }
        return false;
    }

    private Song findSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playlist) {
        int index = trackNumber - 1;
        if (index >= 0 && index < songs.size()) {
            playlist.add(songs.get(index));
            return true;
        }
        return false;
    }

    public boolean addToPlaylist(String title, LinkedList<Song> playlist) {
        Song song = findSong(title);
        if (song != null) {
            playlist.add(song);
            return true;
        }
        return false;
    }
}

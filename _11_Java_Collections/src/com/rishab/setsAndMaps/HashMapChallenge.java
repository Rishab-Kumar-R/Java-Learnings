package com.rishab.setsAndMaps;

import java.util.*;

class AdventureGame {
    private static final String GAME_LOCATIONS = """
        road,at the end of the road, W: hill, E:well house,S:valley,N:forest
        hill,on top of hill with a view in all directions,N:forest, E:road
        well house,inside a well house for a small spring,W:road,N:lake,S:stream
        valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
        forest,at the edge of a thick dark forest,S:road,E:lake
        lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
        stream,near a stream with a rocky bed,W:valley, N:well house
        """;

    private enum Compass {
        E, N, S, W;

        private static final String[] directions = {"EAST", "NORTH", "SOUTH", "WEST"};

        public String getString() {
            return directions[this.ordinal()];
        }
    }

    private record Location(String description, Map<Compass, String> nextPlaces) {
    }

    private String lastPlace;
    private Map<String, Location> adventureMap = new HashMap<>();

    public AdventureGame() {
        this(null);
    }

    public AdventureGame(String customLocations) {
        loadLocations(GAME_LOCATIONS);

        if (customLocations != null) {
            loadLocations(customLocations);
        }
    }

    private void loadLocations(String data) {
        for (String s : data.split("\\R")) {
            String[] parts = s.split(",", 3);
            Arrays.asList(parts).replaceAll(String::trim);

            Map<Compass, String> nextPlaces = loadDirections(parts[2]);
            Location location = new Location(parts[1], nextPlaces);
            adventureMap.put(parts[0], location);
        }
        // adventureMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    private Map<Compass, String> loadDirections(String nextPlaces) {
        Map<Compass, String> directions = new HashMap<>();
        List<String> nextSteps = Arrays.asList(nextPlaces.split(","));

        nextSteps.replaceAll(String::trim);

        for (String nextPlace : nextSteps) {
            String[] parts = nextPlace.split(":");
            Compass compass = Compass.valueOf(parts[0].trim());
            String destination = parts[1].trim();
            directions.put(compass, destination);
        }

        return directions;
    }

    private void visit(Location location) {
        System.out.println("*** You are at " + location.description + " ***");
        System.out.println("\tFrom here you can go to: ");
        location.nextPlaces.forEach((k, v) -> System.out.printf("\t\t• A %s to %s (%s) %n", v, k.getString(), k));
        System.out.print("Select your compass (EAST{E}, NORTH{N}, SOUTH{S}, WEST{W} or QUIT{Q}) >> ");
    }

    public void move(String direction) {
        var nextPlaces = adventureMap.get(this.lastPlace).nextPlaces;
        String nextPlace = null;
        if ("ENSW".contains(direction)) {
            nextPlace = nextPlaces.get(Compass.valueOf(direction));
            if (nextPlace != null) {
                play(nextPlace);
            }
        } else {
            System.out.println("Invalid direction");
        }
    }

    public void play(String location) {
        if (adventureMap.containsKey(location)) {
            Location next = adventureMap.get(location);
            lastPlace = location;
            visit(next);
        } else {
            System.out.println(location + " is not a valid location");
        }
    }
}

public class HashMapChallenge {
    public static void main(String[] args) {

        String myLocations = """
            lake,at the edge of a lake,W:forest,S:well house,E:ocean,N:cave
            ocean,on the shore enjoying the view,W:lake
            cave,at the entrance of a mysterious cave,E:ocean,W:forest,S:lake
            """;

        AdventureGame game = new AdventureGame(myLocations);
        game.play("lake");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String direction = scanner.nextLine().toUpperCase().substring(0, 1);
            if (direction.equals("Q")) {
                System.out.println("Game Over");
                break;
            } else {
                game.move(direction);
            }
        }

    }
}

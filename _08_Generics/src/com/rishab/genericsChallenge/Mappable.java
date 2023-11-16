package com.rishab.genericsChallenge;

import java.util.Arrays;

public interface Mappable {
    void render();

    static double[] stringToLatLon(String location) {
        var splits = location.split(",");
        double lat = Double.parseDouble(splits[0]);
        double lon = Double.parseDouble(splits[1]);
        return new double[]{lat, lon};
    }
}

// as this class is abstract, it does not need to implement the render() method
abstract class Point implements Mappable {
    private double[] location = new double[2];

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    @Override
    public void render() {
        System.out.println("Rendering " + this + " at POINT (" + location() + ")");
    }

    private String location() {
        return Arrays.toString(this.location);
    }
}

abstract class Line implements Mappable {
    private double[][] locations;

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for (var location : locations) {
            this.locations[index++] = Mappable.stringToLatLon(location);
        }
    }

    private String locations() {
        return Arrays.deepToString(this.locations);
    }

    @Override
    public void render() {
        System.out.println("Rendering " + this + " at LINE (" + locations() + ")");
    }
}

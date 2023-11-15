package com.rishab;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

enum FlightStages implements Trackable {
    GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override
    public void track() {
        if (this != GROUNDED) {
            System.out.println("Monitoring " + this.name() + " stage...");
        }
    }

    public FlightStages getNextStage() {
        FlightStages[] allStages = FlightStages.values();
        return allStages[(this.ordinal() + 1) % allStages.length];
    }
}

record DragonFly(String name, String type) implements FlightEnabled {
    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

class Satellite implements OrbitEarth {
    FlightStages stage = FlightStages.GROUNDED;

    @Override
    public void achieveOrbit() {
        transition("Achieving orbit...");
    }

    @Override
    public void takeOff() {
        transition("Taking off...");
    }

    @Override
    public void land() {
        transition("Landing...");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("Data collection...");
    }

    public void transition(String description) {
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }
}

interface OrbitEarth extends FlightEnabled {
    void achieveOrbit();

    // static methods in interfaces are implicitly public
    private static void log(String description) {
        var today = new Date();
        System.out.println(today + ": " + description);
    }

    private void logStage(FlightStages stage, String description) {
        description = description + " " + stage.name();
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage);
        logStage(stage, "Transitioning to" + nextStage.name());
        return nextStage;
    }
}

interface FlightEnabled {
    // Interface variables are implicitly public, static and final
    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    // Interface methods are implicitly public and abstract
    public abstract void takeOff();

    abstract void land();

    void fly();

    // adding this method to the interface will break all the classes that implement this interface
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = stage.getNextStage();
        System.out.println(getClass().getSimpleName() + " is transitioning from " + stage.name() + " to " + nextStage.name());
        return nextStage;
    }
}

interface Trackable {
    void track();
}

abstract class Animals {
    public abstract void move();
}

class Bird extends Animals implements FlightEnabled, Trackable {
    @Override
    public void move() {
        System.out.println("Bird is flying...");
    }

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off...");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing...");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying...");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + " is being tracked...");
    }
}

class Plane implements FlightEnabled, Trackable {
    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off...");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing...");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying...");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + " is being tracked...");
    }

    @Override
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " is transitioning to " + stage.name());
        return FlightEnabled.super.transition(stage);
    }
}

class Truck implements Trackable {
    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + " is being tracked...");
    }
}

public class InterfacesDemo {
    public static void main(String[] args) {

        Bird bird = new Bird();
        Animals animals = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

        animals.move();
        // flier.move(); // flier can only access methods from FlightEnabled
        // tracked.move(); // tracked can only access methods from Trackable

        // inFlight(flier);
        // inFlight(new Plane());

        Trackable truck = new Truck();
        truck.track();

        double kilometersTravelled = 100;
        double milesTravelled = kilometersTravelled * FlightEnabled.KM_TO_MILES;
        System.out.printf("%.2f km = %.2f miles\n", kilometersTravelled, milesTravelled);

        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        // Why this is better than ArrayList<FlightEnabled> fliers = new ArrayList<>();
        List<FlightEnabled> betterFliers = new ArrayList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFliers(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFliers(betterFliers);
        landFliers(betterFliers);

    }

    // Always use the most generic type possible, here we use List instead of ArrayList
    // This way we can pass in any type of List, not just ArrayList
    private static void triggerFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.takeOff();
        }
    }

    private static void flyFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.fly();
        }
    }

    private static void landFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.land();
        }
    }
}

package com.rishab;

public class Casting {
    public static void main(String[] args) {

        Movie movie = Movie.getMovie("S", "Interstellar");
        movie.watchMovie();

        // Adventure adventure = Movie.getMovie("A", "Jumanji"); // Error: incompatible types: Movie cannot be converted to Adventure
        Adventure adventure = (Adventure) Movie.getMovie("A", "Jumanji");
        adventure.watchMovie();

        Object comedy = Movie.getMovie("C", "Anchorman");
        // comedy.watchMovie(); // Error: cannot find symbol
        // comedy.watchComedy(); // Error: cannot find symbol
        ((Movie) comedy).watchMovie();
        ((Comedy) comedy).watchComedyMovie();

        var missionImpossible = Movie.getMovie("a", "Mission Impossible - Ghost Protocol");
        missionImpossible.watchMovie();

        var superBad = new Comedy("Superbad");
        superBad.watchComedyMovie();

        // Testing the runtime type using instanceof operator
        Object unknownObject = Movie.getMovie("c", "Get Him to the Greek");
        if (unknownObject.getClass().getSimpleName().equals("Comedy")) {
            ((Comedy) unknownObject).watchComedyMovie();
        } else if (unknownObject instanceof Adventure) {
            ((Adventure) unknownObject).watchAdventureMovie();
        } else if (unknownObject instanceof ScienceFiction syfy) {
            syfy.watchScienceFictionMovie();
        } else {
            ((Movie) unknownObject).watchMovie();
        }

        System.out.println();

        Car car = new Car("2021 Ford Mustang GT Fastback");
        runRace(car);

        Car ferrari = new GasPoweredCar("2021 Ferrari 812 GTS", 5.0, 12);
        runRace(ferrari);

        Car tesla = new ElectricCar("2021 Tesla Model S", 500.0, 100);
        runRace(tesla);

        Car ferrariHybrid = new HybridCar("2022 Ferrari SF90 Stradale", 5.0, 7, 8);
        runRace(ferrariHybrid);

    }

    public static void runRace(Car car) {
        car.startEngine();
        car.drive();
    }
}

// Challenge on Polymorphism
class Car {
    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println("Car -> startEngine()");
    }

    public void drive() {
        System.out.println("Car -> drive(), type = " + this.getClass().getSimpleName() + ", description = " + this.description);
        this.runEngine();
    }

    protected void runEngine() {
        System.out.println("Car -> runEngine()");
    }
}

class GasPoweredCar extends Car {
    private double averageKilometerPerLitre;
    private int cylinders = 6;

    public GasPoweredCar(String description) {
        super(description);
    }

    public GasPoweredCar(String description, double averageKilometerPerLitre, int cylinders) {
        super(description);
        this.averageKilometerPerLitre = averageKilometerPerLitre;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("Gas -> All %d cylinders fired up!%n", this.cylinders);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Gas -> Usage exceeds %.2f km/litre%n", this.averageKilometerPerLitre);
    }
}

class ElectricCar extends Car {
    private double averageKilometerPerCharge;
    private int batterySize;

    public ElectricCar(String description) {
        super(description);
    }

    public ElectricCar(String description, double averageKilometerPerCharge, int batterySize) {
        super(description);
        this.averageKilometerPerCharge = averageKilometerPerCharge;
        this.batterySize = batterySize;
    }

    @Override
    public void startEngine() {
        System.out.printf("Electric -> Battery size = %d kWh%n", this.batterySize);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Electric -> Usage exceeds %.2f km/charge%n", this.averageKilometerPerCharge);
    }
}

class HybridCar extends Car {
    private double averageKilometerPerLitre;
    private int batterySize;
    private int cylinders;

    public HybridCar(String description) {
        super(description);
    }

    public HybridCar(String description, double averageKilometerPerLitre, int batterySize, int cylinders) {
        super(description);
        this.averageKilometerPerLitre = averageKilometerPerLitre;
        this.batterySize = batterySize;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("Hybrid -> Battery size = %d kWh, %d cylinders fired up!%n", this.batterySize, this.cylinders);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Hybrid -> Usage exceeds %.2f km/litre%n", this.averageKilometerPerLitre);
    }
}

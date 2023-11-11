package com.rishab;

public class Composition {
    public static void main(String[] args) {

        ComputerCase computerCase = new ComputerCase("NZXT H510", "NZXT", "Corsair 750W");
        Monitor monitor = new Monitor("VG248QE", "ASUS", 24, "1920x1080");
        MotherBoard motherBoard = new MotherBoard("Z390", "ASUS", 4, 2, "UEFI");
        PersonalComputer personalComputer = new PersonalComputer("ROG Strix", "ASUS", monitor, motherBoard, computerCase);

        // personalComputer.getComputerCase().pressPowerButton();
        // personalComputer.getMotherBoard().loadProgram("Windows 11");
        // personalComputer.getMonitor().drawPixelAt(10, 20, "red");

        personalComputer.powerUp();

        System.out.println();

        SmartKitchen smartKitchen = new SmartKitchen();
        smartKitchen.setKitchenState(true, false, true);
        smartKitchen.doKitchenWork();

        System.out.println();

        Wall wall1 = new Wall("West");
        Wall wall2 = new Wall("East");
        Wall wall3 = new Wall("North");
        Wall wall4 = new Wall("South");
        Ceiling ceiling = new Ceiling(12, "White");
        Bed bed = new Bed("Modern", 4, 3, 2, 1);
        Lamp lamp = new Lamp("Classic", false, 75);
        Bedroom bedroom = new Bedroom("Alex Room", wall1, wall2, wall3, wall4, ceiling, bed, lamp);

        bedroom.makeBed();
        bedroom.getLamp().turnOn();
    }
}

// **NOTE** Composition is a "has-a" relationship, whereas Inheritance is a "is-a" relationship

class Product {
    private String model;
    private String manufacturer;
    private int width;
    private int height;
    private int depth;

    public Product(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
}

class Monitor extends Product {
    private int size;
    private String resolution;

    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public Monitor(String model, String manufacturer, int size, String resolution) {
        super(model, manufacturer);
        this.size = size;
        this.resolution = resolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.printf("Drawing pixel at %d, %d in color %s%n", x, y, color);
    }
}

class MotherBoard extends Product {
    private int ramSlots;
    private int cardSlots;
    private String bios;

    public MotherBoard(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public MotherBoard(String model, String manufacturer, int ramSlots, int cardSlots, String bios) {
        super(model, manufacturer);
        this.ramSlots = ramSlots;
        this.cardSlots = cardSlots;
        this.bios = bios;
    }

    public void loadProgram(String programName) {
        System.out.println("Program " + programName + " is now loading...");
    }
}

class ComputerCase extends Product {
    private String powerSupply;

    public ComputerCase(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public ComputerCase(String model, String manufacturer, String powerSupply) {
        super(model, manufacturer);
        this.powerSupply = powerSupply;
    }

    public void pressPowerButton() {
        System.out.println("Power button pressed...");
    }
}

// Composition
class PersonalComputer extends Product {
    private Monitor monitor;
    private MotherBoard motherBoard;
    private ComputerCase computerCase;

    public PersonalComputer(String model, String manufacturer, Monitor monitor, MotherBoard motherBoard, ComputerCase computerCase) {
        super(model, manufacturer);
        this.monitor = monitor;
        this.motherBoard = motherBoard;
        this.computerCase = computerCase;
    }

    // public ComputerCase getComputerCase() {
    //     return computerCase;
    // }

    // public Monitor getMonitor() {
    //     return monitor;
    // }

    // public MotherBoard getMotherBoard() {
    //     return motherBoard;
    // }

    private void drawLogo() {
        monitor.drawPixelAt(10, 10, "yellow");
    }

    private void loadBIOS() {
        motherBoard.loadProgram("Windows 11");
    }

    public void powerUp() {
        computerCase.pressPowerButton();
        loadBIOS();
        drawLogo();
    }
}

// Challenge
class SmartKitchen {
    private CoffeeMaker coffeeMaker;
    private Refrigerator refrigerator;
    private Dishwasher dishWasher;

    public SmartKitchen() {
        coffeeMaker = new CoffeeMaker();
        refrigerator = new Refrigerator();
        dishWasher = new Dishwasher();
    }

    public CoffeeMaker getCoffeeMaker() {
        return coffeeMaker;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public Dishwasher getDishWasher() {
        return dishWasher;
    }

    public void setKitchenState(boolean coffeeFlag, boolean fridgeFlag, boolean dishesFlag) {
        coffeeMaker.setHasWorkToDo(coffeeFlag);
        refrigerator.setHasWorkToDo(fridgeFlag);
        dishWasher.setHasWorkToDo(dishesFlag);
    }

    public void doKitchenWork() {
        coffeeMaker.brewCoffee();
        refrigerator.orderFood();
        dishWasher.doDishes();
    }
}

class CoffeeMaker {
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void brewCoffee() {
        if (hasWorkToDo) {
            System.out.println("Brewing coffee...");
            hasWorkToDo = false;
        } else {
            System.out.println("No work to do in the coffee maker...");
        }
    }
}

class Refrigerator {
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void orderFood() {
        if (hasWorkToDo) {
            System.out.println("Ordering food...");
            hasWorkToDo = false;
        } else {
            System.out.println("No work to do in the refrigerator...");
        }
    }
}

class Dishwasher {
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void doDishes() {
        if (hasWorkToDo) {
            System.out.println("Doing dishes...");
            hasWorkToDo = false;
        } else {
            System.out.println("No work to do in the dishwasher...");
        }
    }
}

class Bedroom {
    private String name;
    private Wall wall1;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    private Ceiling ceiling;
    private Bed bed;
    private Lamp lamp;

    public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp) {
        this.name = name;
        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.ceiling = ceiling;
        this.bed = bed;
        this.lamp = lamp;
    }

    public Lamp getLamp() {
        return this.lamp;
    }

    public void makeBed() {
        System.out.println("Bedroom -> Making bed...");
        bed.make();
    }
}

class Wall {
    private String direction;

    public Wall(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }
}

class Ceiling {
    private int height;
    private String color;

    public Ceiling(int height, String color) {
        this.height = height;
        this.color = color;
    }

    public int getHeight() {
        return this.height;
    }

    public String getColor() {
        return this.color;
    }
}

class Bed {
    private String style;
    private int pillows;
    private int height;
    private int sheets;
    private int quilt;

    public Bed(String style, int pillows, int height, int sheets, int quilt) {
        this.style = style;
        this.pillows = pillows;
        this.height = height;
        this.sheets = sheets;
        this.quilt = quilt;
    }

    public String getStyle() {
        return style;
    }

    public int getPillows() {
        return pillows;
    }

    public int getHeight() {
        return height;
    }

    public int getSheets() {
        return sheets;
    }

    public int getQuilt() {
        return quilt;
    }

    public void make() {
        System.out.println("Bed -> Making...");
    }
}

class Lamp {
    private String style;
    private boolean battery;
    private int globRating;

    public Lamp(String style, boolean battery, int globRating) {
        this.style = style;
        this.battery = battery;
        this.globRating = globRating;
    }

    public String getStyle() {
        return style;
    }

    public boolean isBattery() {
        return battery;
    }

    public int getGlobRating() {
        return globRating;
    }

    public void turnOn() {
        System.out.println("Lamp -> Turning on...");
    }
}

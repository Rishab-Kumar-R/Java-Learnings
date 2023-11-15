package com.rishab;

import java.util.ArrayList;
import java.util.Arrays;

public class AbstractClass {
    public static void main(String[] args) {

        // Animal animal = new Animal("animal", "big", 100); // This won't work because Animal is an abstract class
        Dog wolf = new Dog("Wolf", "big", 100);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(wolf);
        animals.add(new Dog("Pug", "small", 10));
        animals.add(new Dog("German Shepherd", "big", 50));
        animals.add(new Fish("Shark", "big", 100));
        animals.add(new Fish("Goldfish", "small", 1));

        animals.add(new Horse("Clydesdale", "large", 1000));

        for (Animal animal : animals) {
            doAnimalStuff(animal);
            if (animal instanceof Mammal currentMammal) {
                currentMammal.shedHair();
            }
        }

    }

    private static void doAnimalStuff(Animal animal) {
        animal.makeNoise();
        animal.move("slow");
    }
}

abstract class Animal {
    protected String type;
    private String size;
    private double weight;

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    // Remember that abstract methods don't have a body and can't be private
    public abstract void move(String speed);

    public abstract void makeNoise();

    // Abstract classes can have non-abstract methods aka concrete methods
    // final methods can't be overridden, hence you can't implement this method in a subclass
    public final String getExplicitType() {
        return getClass().getSimpleName() + " (" + this.type + ")";
    }
}

class Dog extends Mammal {
    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if (speed.equalsIgnoreCase("slow")) {
            System.out.println(this.getExplicitType() + " is walking...");
        } else {
            System.out.println(this.getExplicitType() + " is running...");
        }
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " is shedding hair like crazy...");
    }

    @Override
    public void makeNoise() {
        if (this.type.equalsIgnoreCase("Wolf")) {
            System.out.print("Howling...");
        } else {
            System.out.print("Barking...");
        }
    }
}

class Fish extends Animal {
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if (speed.equalsIgnoreCase("slow")) {
            System.out.println(this.getExplicitType() + " is swimming...");
        } else {
            System.out.println(this.getExplicitType() + " is swimming fast...");
        }
    }

    @Override
    public void makeNoise() {
        if (this.type.equalsIgnoreCase("Shark")) {
            System.out.print("Silence...");
        } else {
            System.out.print("Bloop...");
        }
    }
}

// You can also have abstract classes that extend other abstract classes and this provides a lot of flexibility
// It can implement all the parent's abstract methods or none of them or some of them
abstract class Mammal extends Animal {
    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.print(this.getExplicitType() + " ");
        System.out.println(speed.equalsIgnoreCase("slow") ? " is walking..." : " is running...");
    }

    public abstract void shedHair();
}

class Horse extends Mammal {
    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {
        System.out.println(getExplicitType() + " is neighing...");
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " is shedding hair...");
    }
}

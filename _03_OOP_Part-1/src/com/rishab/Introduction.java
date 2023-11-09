package com.rishab;

public class Introduction {
    public static void main(String[] args) {

        Car car = new Car();
        car.describeCar();

        System.out.println("Getting make: " + car.getMake());
        System.out.println("Getting model: " + car.getModel());

        car.setMake("Porsche");
        car.setModel("911");
        car.setColor("Silver");
        car.setConvertible(false);
        car.describeCar();

        car.setMake("Maserati"); // Unsupported
        car.describeCar();

        Car gClass = new Car();
        gClass.setMake("Mercedes");
        gClass.setModel("G Class");
        gClass.setColor("Black");
        gClass.setDoors(5);
        gClass.setConvertible(false);
        gClass.describeCar();

        System.out.println();

        // Challenge
        // It's so tedious to set each field individually. Let's create a constructor to do it for us.
        // Account account = new Account();
        // account.setNumber("12345");
        // account.setBalance(1000.00);
        // account.setCustomerName("Eric");
        // account.setCustomerEmail("youremail@eric.com");
        // account.setCustomerPhone("(123) 456-7890");
        // account.withdraw(100.0);
        // account.deposit(250.0);
        // account.withdraw(50.0);
        // account.withdraw(45.55);

        Account account = new Account("12345", 1000.00, "Eric", "youremail@eric.com", "(123) 456-7890");
        System.out.println("Account number: " + account.getNumber());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Customer name: " + account.getCustomerName());
        System.out.println("Customer email: " + account.getCustomerEmail());
        System.out.println("Customer phone: " + account.getCustomerPhone());

        // Example of constructor chaining
        // Account alexAccount = new Account("Alex", "myemail@alex.com", "(087) 123-4567");
        // System.out.println("Account number: " + alexAccount.getNumber());
        // System.out.println("Balance: " + alexAccount.getBalance());

        System.out.println();

        Customer customer = new Customer("Tim", 1000, "tim@gmail.com");
        System.out.println("Name: " + customer.getName());
        System.out.println("Credit limit: " + customer.getCreditLimit());
        System.out.println("Email: " + customer.getEmail());

        Customer secondCustomer = new Customer();
        System.out.println("Name: " + secondCustomer.getName());
        System.out.println("Credit limit: " + secondCustomer.getCreditLimit());
        System.out.println("Email: " + secondCustomer.getEmail());

        Customer thirdCustomer = new Customer("David", "david@gmail.com");
        System.out.println("Name: " + thirdCustomer.getName());
        System.out.println("Credit limit: " + thirdCustomer.getCreditLimit());
        System.out.println("Email: " + thirdCustomer.getEmail());

        // System.out.println();

        // SimpleCalculator calculator = new SimpleCalculator();
        // calculator.setFirstNumber(5.0);
        // calculator.setSecondNumber(4);
        // System.out.println("add = " + calculator.getAdditionResult());
        // System.out.println("subtract = " + calculator.getSubtractionResult());
        // calculator.setFirstNumber(5.25);
        // calculator.setSecondNumber(0);
        // System.out.println("multiply = " + calculator.getMultiplicationResult());
        // System.out.println("divide = " + calculator.getDivisionResult());

        // System.out.println();

        // Person person = new Person();
        // person.setFirstName("");
        // person.setLastName("");
        // person.setAge(10);
        // System.out.println("fullName = " + person.getFullName());
        // System.out.println("teen = " + person.isTeen());
        // person.setFirstName("John");
        // person.setAge(18);
        // System.out.println("fullName = " + person.getFullName());
        // System.out.println("teen = " + person.isTeen());
        // person.setLastName("Smith");
        // System.out.println("fullName = " + person.getFullName());

        System.out.println();

        Wall wall = new Wall(5, 4);
        System.out.println("area = " + wall.getArea());
        wall.setHeight(-1.5);
        System.out.println("width = " + wall.getWidth());
        System.out.println("height = " + wall.getHeight());
        System.out.println("area = " + wall.getArea());

        System.out.println();

        Point first = new Point(6, 5);
        Point second = new Point(3, 1);
        System.out.println("distance(0,0) = " + first.distance());
        System.out.println("distance(second) = " + first.distance(second));
        System.out.println("distance(2,2) = " + first.distance(2, 2));
        Point point = new Point();
        System.out.println("distance() = " + point.distance());

        System.out.println();

        Carpet carpet = new Carpet(3.5);
        Floor floor = new Floor(2.75, 4.0);
        CarpetCostCalculator calculator = new CarpetCostCalculator(floor, carpet);
        System.out.println("total = " + calculator.getTotalCost());
        carpet = new Carpet(1.5);
        floor = new Floor(5.4, 4.5);
        calculator = new CarpetCostCalculator(floor, carpet);
        System.out.println("total = " + calculator.getTotalCost());

        System.out.println();

        ComplexNumber one = new ComplexNumber(1.0, 1.0);
        ComplexNumber number = new ComplexNumber(2.5, -1.5);
        one.add(1, 1);
        System.out.println("one.real = " + one.getReal());
        System.out.println("one.imaginary = " + one.getImaginary());
        one.subtract(number);
        System.out.println("one.real = " + one.getReal());
        System.out.println("one.imaginary = " + one.getImaginary());
        number.subtract(one);
        System.out.println("number.real = " + number.getReal());
        System.out.println("number.imaginary = " + number.getImaginary());

    }
}

// Challenge on Constructors
class Wall {
    private double width;
    private double height;

    public Wall() {
        this(0, 0);
    }

    public Wall(double width, double height) {
        this.width = (width < 0) ? 0 : width;
        this.height = (height < 0) ? 0 : height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = (width < 0) ? 0 : width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = (height < 0) ? 0 : height;
    }

    public double getArea() {
        return this.width * this.height;
    }
}

class Point {
    private int x;
    private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double distance(Point point) {
        return Math.sqrt((point.getX() - this.x) * (point.getX() - this.x) + (point.getY() - this.y) * (point.getY() - this.y));
    }

    public double distance(int x, int y) {
        return Math.sqrt((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y));
    }
}

class Floor {
    private double width;
    private double length;

    public Floor(double width, double length) {
        this.width = (width < 0) ? 0 : width;
        this.length = (length < 0) ? 0 : length;
    }

    public double getArea() {
        return this.width * this.length;
    }
}

class Carpet {
    private double cost;

    public Carpet(double cost) {
        this.cost = (cost < 0) ? 0 : cost;
    }

    public double getCost() {
        return this.cost;
    }
}

class CarpetCostCalculator {
    private Floor floor;
    private Carpet carpet;

    public CarpetCostCalculator(Floor floor, Carpet carpet) {
        this.floor = floor;
        this.carpet = carpet;
    }

    public double getTotalCost() {
        return this.floor.getArea() * this.carpet.getCost();
    }
}

class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public void  add(double real, double imaginary) {
        this.real += real;
        this.imaginary += imaginary;
    }

    public void add(ComplexNumber complexNumber) {
        this.real += complexNumber.getReal();
        this.imaginary = complexNumber.getImaginary();
    }

    public void subtract(double real, double imaginary) {
        this.real -= real;
        this.imaginary -= imaginary;
    }

    public  void subtract(ComplexNumber complexNumber) {
        this.real -= complexNumber.getReal();
        this.imaginary -= complexNumber.getImaginary();
    }
}

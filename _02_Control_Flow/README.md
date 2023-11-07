# Control flow the program

## Table of contents

- [if-else statement](./src/com/rishab/IfElseStatements.java)
- [Methods or functions](./src/com/rishab/Methods.java)
- [Method Overloading](./src/com/rishab/MethodOverloading.java)
- [Switch Statement](./src/com/rishab/SwitchStatement.java)
- [For Loop](./src/com/rishab/ForLoop.java)
- [While Loop](./src/com/rishab/WhileLoop.java)
- [Local Variables and Scope](./src/com/rishab/Scoping.java)
- [Reading User Input](./src/com/rishab/ReadingInputs.java)

## if statement

If statement is used to check a condition and execute a block of code if the condition is true.

```java
if (condition) {
    // code block
}
```

## if-else statement

If-else statement is used to check a condition and execute a block of code if the condition is true and another block of code if the condition is false.

```java
if (condition) {
    // code block
} else {
    // code block
}
```

## if-else if-else statement

If-else if-else statement is used to check a condition and execute a block of code if the condition is true and another block of code if the condition is false. If the first condition is false, it will check the second condition and so on.

```java
if (condition) {
    // code block
} else if (condition) {
    // code block
} else {
    // code block
}
```

---

## Methods or functions

Methods or functions are used to perform a specific task. It is a block of code that is executed when it is called.

```java
static void myMethod() {
    System.out.println("This is my method");
}

static void myMethodWithParameter(String name) {
    System.out.println("Hello " + name);
}

static int myMethodWithReturn(int x, int y) {
    return x + y;
}

public static void main(String[] args) {
    myMethod();
    myMethodWithParameter("Alex");
    int sum = myMethodWithReturn(5, 10);
    System.out.println(sum);
}
```

---

## Method Overloading

Method overloading is a feature that allows a class to have more than one method having the same name, if their argument lists are different.
It's a way to implement polymorphism in Java and it is also known as Compile time polymorphism.

```java
static int plusMethod(int x, int y) {
    return x + y;
}

static double plusMethod(double x, double y) {
    return x + y;
}

public static void main(String[] args) {
    int myNum1 = plusMethod(8, 5);
    double myNum2 = plusMethod(4.3, 6.26);
    System.out.println("int: " + myNum1);
    System.out.println("double: " + myNum2);
}
```

---

## Switch Statement

Switch statement is used to perform different actions based on different conditions.

```java
int day = 4;

switch (day) {
    case 1 -> System.out.println("Monday");
    case 2 -> System.out.println("Tuesday");
    case 3 -> System.out.println("Wednesday");
    case 4 -> System.out.println("Thursday");
    case 5 -> System.out.println("Friday");
    case 6 -> System.out.println("Saturday");
    case 7 -> System.out.println("Sunday");
    default -> System.out.println("Looking forward to the Weekend");
}
```

---

## For Loop

For loop is used to execute a block of code a certain number of times.

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

---

## While Loop

While loop is used to execute a block of code as long as a specified condition is true.

```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
```

---

## Do-While Loop

Do-while loop is used to execute a block of code as long as a specified condition is true. The difference between do-while and while is that do-while evaluates its expression at the bottom of the loop instead of the top. Therefore, the statements within the do block are always executed at least once.

```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 5);
```

---

## Break Statement

Break statement is used to terminate a loop or a switch statement.

```java
for (int i = 0; i < 10; i++) {
    if (i == 4) {
        break;
    }
    System.out.println(i);
}
```

---

## Continue Statement

Continue statement is used to skip the current iteration of a loop and continue with the next iteration.

```java
for (int i = 0; i < 10; i++) {
    if (i == 4) {
        continue;
    }
    System.out.println(i);
}
```

---

## Local Variables and Scope

Local variables are declared in methods, constructors, or blocks. Local variables are created when the method, constructor or block is entered and the variable will be destroyed once it exits the method, constructor, or block.

```java
public static void main(String[] args) {
    int x = 5;
    System.out.println(x);
}
```

Here, x is a local variable. This variable is accessible only within the main method.

**Scope** refers to the lifetime and accessibility of a variable. The scope of a local variable exists only within the block in which the variable is declared.

```java
public static void main(String[] args) {
    int x = 5;
    System.out.println(x);
    {
        int y = 10;
        System.out.println(y);
    }
    System.out.println(y); // Error: y is not defined
}
```

Here, y is a local variable. This variable is accessible only within the block in which it is declared.

---

## Intro to OOP

Object-oriented programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data and code: data in the form of fields (often known as attributes or properties), and code, in the form of procedures (often known as methods).

### Four Pillars of OOP

- **Encapsulation**: Encapsulation is the mechanism of hiding of data implementation by restricting access to public methods. Instance variables are kept private and accessor methods are made public to achieve this.

- **Abstraction**: Abstraction means using simple things to represent complexity. We all know how to turn the TV on, but we don’t need to know how it works in order to enjoy it. In Java, abstraction means simple things like objects, classes, and variables represent more complex underlying code and data. This is important because it lets avoid repeating the same work multiple times.

- **Inheritance**: Inheritance is a mechanism in which one class acquires the property of another class. For example, a child inherits the traits of his/her parents. With inheritance, we can reuse the fields and methods of the existing class.

- **Polymorphism**: Polymorphism means to process objects differently based on their data type. In other words it means, one method with multiple implementation, for a certain class of action. It can be implemented by abstract class or interface in Java.

### Class

A class is a blueprint for creating objects. A class contains fields (variables) and methods to describe the behavior of an object.

```java
public class Car {
    String color;
    int numberOfDoors;

    void drive() {
        System.out.println("Car is moving");
    }
}
```

### Object

An object is an instance of a class. When a class is defined, no memory is allocated but when it is instantiated (i.e. an object is created) memory is allocated.

```java
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.color = "Red";
        myCar.numberOfDoors = 4;
        myCar.drive();
    }
}
```

### static keyword

The static keyword is used to create variables that will exist independently of any instances created for the class. Only one copy of the static variable exists regardless of the number of instances of the class.

```java
public class Car {
    static int numberOfCars;

    Car() {
        numberOfCars++;
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar1 = new Car();
        Car myCar2 = new Car();
        System.out.println(Car.numberOfCars);
    }
}
```

---

## Reading User Input

| Technique              | Description                                                                                                                                                                                                        |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| System.in              | Like System.out, the System class has a field called in. The System.in field is an InputStream object that is typically connected to keyboard input of console programs.                                           |
| System.console()       | The System class also has a method called console() that returns a Console object. The Console class is a separate class defined in the java.io package. The Console class is used to read input from the console. |
| Command Line Arguments | This is the most common way to read input in Java. The command line arguments are stored in the args[] string array passed to main() method.                                                                       |
| Scanner                | The Scanner class is a class in java.util, which allows the user to read values of various types.                                                                                                                  |

### System.in

```java
try {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String name = reader.readLine();
    System.out.println(name);
} catch (IOException e) {
    e.printStackTrace();
}
```

### System.console()

```java
String name = System.console().readLine();
System.out.println(name);
```

### Scanner

```java
Scanner scanner = new Scanner(System.in);
String name = scanner.nextLine();
System.out.println(name);
```

### Command Line Arguments

```java
String name = args[0];
System.out.println(name);
```

---

## Exception Handling

An exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions.

Some of the exceptions are:

- ArithmeticException
- ArrayIndexOutOfBoundsException
- ClassNotFoundException
- FileNotFoundException
- IOException
- InterruptedException
- NoSuchFieldException
- NoSuchMethodException
- NullPointerException
- NumberFormatException
- RuntimeException
- StringIndexOutOfBoundsException

```java
try {
    int[] myNumbers = {1, 2, 3};
    System.out.println(myNumbers[10]);
} catch (Exception e) {
    System.out.println("Something went wrong.");
}
```

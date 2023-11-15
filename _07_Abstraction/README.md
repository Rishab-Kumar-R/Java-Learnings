# Abstraction in Java

Abstraction is a process of hiding the implementation details and showing only functionality to the user. Another way, it shows only essential things to the user and hides the internal details, for example, sending SMS where you type the text and send the message. You don't know the internal processing about the message delivery.

- In Java, abstraction is achieved by interfaces and abstract classes. We can achieve 100% abstraction using interfaces.
- Abstract class and interface both can have abstract methods which are not implemented in the class, but abstract class can have defined methods also.
- An abstract class can have instance variables but in the interface, we can have only static and final variables.
- An abstract class can have a constructor but an interface can't have.

## Table of Contents

- [Abstract Classes](./src/com/rishab/AbstractClass.java)
- [Interfaces](./src/com/rishab/InterfacesDemo.java)

## Abstract Class in Java

- A class which is declared with the abstract keyword is known as an abstract class in Java. It can have abstract and non-abstract methods (method with the body).
- Before learning the Java abstract class, let's understand the abstraction in Java first.

### Points to Remember

- An abstract class must be declared with an abstract keyword.
- An abstract class can't be instantiated.
- An abstract class can have constructors and static methods also.
- It can have final methods which will force the subclass not to change the body of the method.

### Method Modifier and Description

| Modifier and Type | Method and Description                                                                                                       |
| ----------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| abstract          | It is used to declare the abstract method, a method that is declared without an implementation.                              |
| static            | It is used to declare a static method, a method that is declared as a member of a class but not instantiated.                |
| final             | It is used to declare a final method, a method that cannot be overridden in a subclass.                                      |
| default           | It is used to declare a default method, a method that is declared inside an interface and provides a default implementation. |
| native            | It is used to declare a native method, a method that is implemented in a platform-dependent code.                            |
| synchronized      | It is used to declare a synchronized method, which can be used only in the context of a synchronized block.                  |

### Example of an abstract class that has an abstract method

```java
abstract class Bike {
  abstract void run();
}

class Honda4 extends Bike {
    void run() {
        System.out.println("running safely");
    }
    public static void main(String[] args) {
        Bike obj = new Honda4();
        obj.run();
    }
}
```

### Example of an abstract class that has an abstract method and non-abstract method

```java
abstract class Bike{
    abstract void run();
    void changeGear() {
        System.out.println("gear changed");
    }
}

class Honda4 extends Bike {
    void run() {
        System.out.println("running safely");
    }
    public static void main(String[] args) {
        Bike obj = new Honda4();
        obj.run();
        obj.changeGear();
    }
}
```

## Abstract Class vs Interface

| Abstract Class                                                                | Interface                                                                                               |
| ----------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| An abstract class can have abstract and non-abstract methods.                 | An interface can have only abstract methods. Since Java 8, it can have default and static methods also. |
| An abstract class doesn't support multiple inheritance.                       | An interface supports multiple inheritance.                                                             |
| An abstract class can have final, non-final, static and non-static variables. | An interface has only static and final variables.                                                       |
| An abstract class can provide the implementation of interface.                | An interface can't provide the implementation of abstract class.                                        |
| An abstract class can be extended using keyword "extends".                    | An interface can be implemented using keyword "implements".                                             |
| A Java abstract class can have class members like private, protected, etc.    | Members of a Java interface are public by default.                                                      |

---

## Interface in Java

- An interface in Java is a blueprint of a class. It has static constants and abstract methods only.
- The interface in Java is a mechanism to achieve abstraction. There can be only abstract methods in the Java interface, not method body. It is used to achieve abstraction and multiple inheritance in Java.
- In other words, you can say that interfaces can have abstract methods and variables. It cannot have a method body.

### Why use Java interface?

- It is used to achieve abstraction.
- By interface, we can support the functionality of multiple inheritance.
- It can be used to achieve loose coupling.

### Example of Java Interface

```java
interface Drawable {
    void draw();
}

class Rectangle implements Drawable {
    public void draw() {
        System.out.println("drawing rectangle");
    }
}

class Circle implements Drawable {
    public void draw() {
        System.out.println("drawing circle");
    }
}

class TestInterface1 {
    public static void main(String[] args) {
        Drawable d = new Circle();
        d.draw(); // drawing circle
    }
}
```

## Extending Interfaces

- An interface can extend another interface, similarly to the way that a class can extend another class. The extends keyword is used to extend an interface, and the child interface inherits the methods of the parent interface.
- _Remember_ : An interface doesn't implement another interface, it extends.

```java
interface PrintAble {
    void print();
}

interface ShowAble extends PrintAble {
    void show();
}

class TestInterface4 implements ShowAble {
    public void print() {
        System.out.println("Hello");
    }
    public void show() {
        System.out.println("Welcome");
    }
    public static void main(String[] args) {
        TestInterface4 obj = new TestInterface4();
        obj.print();
        obj.show();
    }
}
```

## Abstracted Types—Coding to an Interface

- Both abstract classes and interfaces can be used to declare abstracted types.
- Reference types can be used in code, as variable types, method return types, and so on.
- When we use an abstracted reference type, this is referred to as coding to an interface.
- This means the code doesn't use specific implementation types, but more general interface types.
- This is a good practice because it makes the code more flexible and reusable.
- It also allows for substitution of some other class or object, that still implements the same interface, without changing the code.
- Coding to an interface scales well, to support new subtypes, and helps when refactoring code.
- The downside, though, is that alterations to the interface may wreak havoc, on the client code.
- Imagine that you have a large code base that uses a particular interface, and you decide to add an extra abstract method, to support some new functionality.
- As soon as you do this, all the client code will break, because it doesn't implement the new method.
- The code isn't backward compatible anymore with the new version of the interface.
- But Java has made several changes to the Interface type over time, to try to address the last problem.

## Changes to Interfaces since Java 8

- Before Java 8, interfaces could only contain public abstract methods and constants.
- JDK 8 introduced the concept of default methods and public static methods, and JDK 9 added private methods, both static and instance.
- All of these new methods types (on an interface) are concrete methods, which means they have a body.

### The Interface Extension Method—the default method (as of JDK 8)

- An extension method is identified by the modifier default, so it's more commonly known as the default method.
- It's a concrete method, which means it has a body, and it's used to add a new method to an interface, without breaking the existing implementations.
- The default method is also known as the defender method or virtual extension method.
- It's a lot like a method on a superclass, a subclass can override that, but it's not inherited.

```java
interface Vehicle {
    default void print() {
        System.out.println("I am a vehicle!");
    }
}

class Car implements Vehicle {
    @Override
    public void print() {
        System.out.println("I am a car!");
    }
}

class TestInterfaceDefault {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}
```

### public static methods on an interface (as of JDK 8)

- Another enhancement that Java included in JDK 8 is the ability to add public static methods to an interface.
- These methods are not inherited by the implementing classes, but they can be invoked using the interface name, as if they were static methods on a class.
- Static methods don't need to specify a public access modifier, because they're implicitly public.

```java
interface Vehicle {
    static void blowHorn() {
        System.out.println("Blowing horn!!!");
    }
}

class TestInterfaceStatic {
    public static void main(String[] args) {
        Vehicle.blowHorn();
    }
}
```

### private methods on an interface (as of JDK 9)

- JDK 9 introduced the ability to add private methods to an interface, both static and instance.
- This enhancement primarily addresses the problem of re-use of the code. within concrete methods on an interface.
- A private static method can be accessed by either a public static method, a default method or a private instance method.
- A private instance method is used to support default methods, and other private methods.

```java
interface Vehicle {
    default void print() {
        printVehicle();
    }
    private void printVehicle() {
        System.out.println("I am a vehicle!");
    }
}

class Car implements Vehicle {
    @Override
    public void print() {
        printVehicle();
    }
    private void printVehicle() {
        System.out.println("I am a car!");
    }
}

class TestInterfacePrivate {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}
```

---

## Constants in Java

- Constants in Java is a value that cannot be changed during the execution of the program.
- _Constant variables_ is a final variable of primitive data type or type String that is initialized with a constant expression (its value is constant during the execution of the program).
- Constants in Java are usually named in all uppercase letters. If the name is composed of two words, they are separated by an underscore (\_).
- Constants in Java are declared using the keyword final.
- A static constant is a constant that belongs to the class and not to the objects of the class. It is declared using the keywords static final.
- E.g. `INTEGER.MAX_VALUE`, and `INTEGER.MIN_VALUE` are constants in Java.

```java
class Constants {
    public static final double PI = 3.14159;
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int GET_MAX() {
        return MAX;
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(Constants.PI);
        System.out.println(Constants.MIN);
        System.out.println(Constants.MAX);
        System.out.println(Constants.GET_MAX());
    }
}
```

---

## Interfaces vs Abstract Classes

| Interfaces                                                                                                    | Abstract Classes                                                                            |
| ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| An interface can extend another Java interface only, an interface cannot extend an abstract class or a class. | An abstract class can extend another Java class and implement multiple Java interfaces.     |
| An interface can have only abstract methods.                                                                  | An abstract class can have abstract and non-abstract methods.                               |
| An interface cannot have a constructor.                                                                       | An abstract class can have a constructor and it is invoked when a class is instantiated.    |
| An interface cannot have instance variables.                                                                  | An abstract class can have instance variables and non-abstract methods.                     |
| An interface cannot have a method body.                                                                       | An abstract class can have a method body.                                                   |
| An interface cannot declare variables other than public static final variables.                               | An abstract class can declare variables and constants with any access modifier.             |
| An interface is absolutely abstract and cannot be instantiated.                                               | An abstract class also cannot be instantiated, but can be invoked if a main() exists.       |
| An interface can extend multiple interfaces.                                                                  | An abstract class can extend only one class or one abstract class at a time.                |
| An interface is used to achieve abstraction.                                                                  | An abstract class is used to achieve abstraction and for sharing common functionality.      |
| An interface is used to achieve loose coupling.                                                               | An abstract class is used to achieve code reusability and for sharing common functionality. |

### When to use an interface and when to use an abstract class?

**Use an abstract class:**

- When you want to share code among several closely related classes.
- When you want to have non-static or non-final variables in your class.
- When you want to have a constructor in your class.
- When you want to declare non-static or non-final methods. This enables you to define required methods, plus default methods and constants.
- When you want to implement abstract methods with default behavior and you think that the behavior may change in the future.
- _Remember_ : If you think that you have a good design reason to provide default behavior for an interface, go ahead. But if you do so, be extremely careful about future changes to that interface.

**Use an interface:**

- When you want to model a has-a relationship.
- When you want to specify the behavior of a particular data type, but not concerned about who implements its behavior.
- When you want to take advantage of multiple inheritance of type.

### Interfaces are used in many of Java's core packages

- The Java Collections Framework, for example, uses interfaces to specify the functionality of generic data structures. The List interface, for example, specifies that a List must have methods for adding, removing, and getting elements, and for determining the size of the list.
- Interfaces are also the basis for many of the features like Streams, Lambda Expressions, and the Java Module System, that were added to Java in JDK 8 and JDK 9.
- Another example is the JDBC API, which uses interfaces to specify the functionality of database drivers. The DriverManager class, for example, uses the Driver interface to interact with database drivers. The Driver interface specifies methods for connecting to a database, and for determining if a driver can be used to connect to a particular database. The Driver interface is implemented by database vendors, to provide drivers for their databases.

# Composition, Polymorphism, and Encapsulation

## Table of Contents

- [Composition](./src/com/rishab/Composition.java)
- [Encapsulation](./src/com/rishab/Encapsulation.java)
- [Polymorphism](./src/com/rishab/Polymorphism.java)
- [Casting](./src/com/rishab/Casting.java)
- [Packages](./src/com/rishab/PackageDemo.java)

## Composition

Composition is a way to combine simple objects or data types into more complex ones. It is somewhat similar to inheritance, but it is more flexible. In composition, the complex object is made up of simple objects or data types. The complex object does not inherit the simple object, but it is composed of them. The simple objects are called components of the complex object. The complex object is called the composite object.

```java

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine(300, 8, 5);
        Car car = new Car("Ford", "Mustang", 2019, engine);

        System.out.println(car.getMake());
        System.out.println(car.getModel());
        System.out.println(car.getYear());
        System.out.println(car.getEngine().getHorsePower());
        System.out.println(car.getEngine().getCylinders());
        System.out.println(car.getEngine().getLiters());
    }
}

class Engine {
    private int horsePower;
    private int cylinders;
    private int liters;

    public Engine(int horsePower, int cylinders, int liters) {
        this.horsePower = horsePower;
        this.cylinders = cylinders;
        this.liters = liters;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getCylinders() {
        return cylinders;
    }

    public int getLiters() {
        return liters;
    }
}

class Car {
    private String make;
    private String model;
    private int year;
    private Engine engine;

    public Car(String make, String model, int year, Engine engine) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Engine getEngine() {
        return engine;
    }
}
```

### Composition vs Inheritance

| Composition                                                                          | Inheritance                                                                           |
| ------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------- |
| Composition is a way to combine simple objects or data types into more complex ones. | Inheritance is a way to create new classes from existing classes.                     |
| In composition, the complex object is made up of simple objects or data types.       | In inheritance, the new class is a subclass of the existing class.                    |
| The complex object does not inherit the simple object, but it is composed of them.   | The new class inherits the properties and methods of the existing class.              |
| The simple objects are called components of the complex object.                      | The existing class is called the superclass and the new class is called the subclass. |
| The complex object is called the composite object.                                   | The subclass can override the methods of the superclass.                              |
| Composition is more flexible than inheritance.                                       | Inheritance is less flexible than composition.                                        |
| Composition is used to represent `has-a` relationships.                              | Inheritance is used to represent `is-a` relationships.                                |
| Composition provide functional reuse outside of the class hierarchy.                 | Inheritance provide functional reuse within the class hierarchy.                      |

---

## Encapsulation

Encapsulation is a mechanism of wrapping the data (variables) and code acting on the data (methods) together as a single unit. In encapsulation, the variables of a class will be hidden from other classes, and can be accessed only through the methods of their current class. Therefore, it is also known as data hiding.

To achieve encapsulation in Java:

- Declare the variables of a class as private.
- Provide public setter and getter methods to modify and view the variables values.

### Why Encapsulation / hiding data?

- To make interface simpler, hiding unnecessary details from the user.
- To protect the integrity of data on an object, we may hide or restrict access to certain properties or components of an object.
- To decouple the components of an object, we may make one component dependent on the other, and then hide the dependency.
- Allowing direct access to data on an object, can potentially bypass checks, and additional processing, your class has place to manage the data.
- Omitting a constructor, that would accept initialization data, may mean the calling code is responsible for setting up this data, on a new object.

---

## Polymorphism

Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object. Any Java object that can pass more than one IS-A test is considered to be polymorphic. In Java, all Java objects are polymorphic since any object will pass the IS-A test for their own type and for the class Object. It is important to know that the only possible way to access an object is through a reference variable. A reference variable can be of only one type. Once declared, the type of a reference variable cannot be changed.

### Polymorphism in Java

There are two types of polymorphism in Java:

- Compile-time polymorphism (static binding)
- Runtime polymorphism (dynamic binding)

### Compile-time polymorphism

Compile-time polymorphism is also known as static polymorphism. This type of polymorphism is achieved by method overloading or operator overloading. In Java, we can overload methods, constructors, and operators.

```java
class Adder {
    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }
}

class TestOverloading1 {
    public static void main(String[] args) {
        System.out.println(Adder.add(11, 11));
        System.out.println(Adder.add(11, 11, 11));
    }
}
```

### Runtime polymorphism

Runtime polymorphism or dynamic method dispatch is a process in which a call to an overridden method is resolved at runtime rather than at compile-time. In this process, an overridden method is called through the reference variable of a superclass. The determination of the method to be called is based on the object being referred to by the reference variable.

```java
class Animal {
    void eat() {
        System.out.println("eating...");
    }
}

class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("eating bread...");
    }
}

class TestPolymorphism {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.eat();
    }
}
```

---

## Casting with classes

Casting in Java is a way to explicitly convert a variable from one data type to university. When dealing with classes and objects, casting is often used to convert between different class types, especially when there is an inheritance relationship between the classes.

### Upcasting

Upcasting is the typecasting of a child object to a parent object. Upcasting is always allowed in Java because it is done implicitly. Upcasting is also known as widening or type promotion.

### Downcasting

Downcasting is the typecasting of a parent object to a child object. Downcasting is not allowed in Java because it is not done implicitly. Downcasting is also known as narrowing or type conversion.

```java
class Animal {
    public void eat() {
        System.out.println("Animal is eating.");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println("Dog is barking.");
    }
}

class Cat extends Animal {
    public void meow() {
        System.out.println("Cat is meowing.");
    }
}

public class CastingExample {
    public static void main(String[] args) {
        // Upcasting (implicit casting)
        Dog myDog = new Dog();
        Animal myAnimal = myDog; // Dog is implicitly cast to Animal
        myAnimal.eat(); // Accessing the eat() method from Animal

        // Downcasting (explicit casting)
        Animal newAnimal = new Dog();
        Dog newDog = (Dog) newAnimal; // Animal is explicitly cast to Dog
        newDog.bark(); // Accessing the bark() method from Dog

        // Error if you try to downcast to a non-related class
        // Cat newCat = (Cat) newAnimal; // This will result in a ClassCastException

        // instanceof keyword can be used to check the type before casting
        if (newAnimal instanceof Dog) {
            Dog anotherDog = (Dog) newAnimal;
            anotherDog.bark();
        } else {
            System.out.println("The animal is not a Dog.");
        }
    }
}
```

---

## `var` keyword

The `var` keyword was introduced in Java 10. It is a reserved type name and can be used as the type of a local variable in a method. The `var` keyword instructs the compiler to infer the type of the variable from the expression on the right-hand side of the initialization statement. The `var` keyword can only be used for local variables inside a method. It cannot be used for member variables, method parameters, return types, or catch parameters.

```java
public class VarExample {
    public static void main(String[] args) {
        var name = "John Doe";
        var age = 30;
        var salary = 35000.0;
        var isEmployed = true;

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Employed: " + isEmployed);
    }
}
```

- It can't be used for class fields, method parameters, return types, or catch parameters.
- It can't be used with lambda expressions or method references.
- It can't be used without an assignment initializer, because the compiler needs to infer the type from the right-hand side of the assignment.
- It can't be assigned `null` because the compiler needs to infer the type.

### RunTime vs CompileTime Typing

- **Compile-time typing** is when the compiler knows the type of a variable at compile time. This is the case with variables that are declared with a type name, such as `int`, `double`, `String`, etc.
- **Run-time typing** is when the compiler does not know the type of a variable at compile time. This is the case with variables that are declared with the `var` keyword.

---

## Organizing Classes, Packages, and Import Statements

### Packages

A package is a group of related classes and interfaces. Packages are used for:

- Preventing naming conflicts. For example, there can be two classes with the same name `Employee` in two different packages. But there can't be two classes with the same name `Employee` in the same package.
- Making searching/locating and usage of classes, interfaces, enumerations, and annotations easier.
- Providing controlled access: protected and default have package level access control. A protected member is accessible by classes in the same package and its subclasses. A default member (without any access specifier) is accessible by classes in the same package only.

### Package declaration

The package statement should be the first line in the source file. There can be only one package statement in each source file, and it applies to all types in the file. If a package statement is not used then the class, interfaces, enumerations, and annotation types will be placed in the default package.

```java
package com.anything.example;

public class MyClass {
    // ...
}
```

### Package naming conventions

Package names are written in all lower case to avoid conflict with the names of classes or interfaces. Companies use their reversed Internet domain name to begin their package names—for example, `com.example.mypackage` for a package named `mypackage` created by a programmer at example.com.

### Import statements

The import statement is used to make classes and interfaces available and accessible to the current source code. The import statement is optional. If it is not used then the classes present in the `java.lang` package will be available to the current source file by default.

```java
import java.util.ArrayList;
import java.util.List;

public class MyClass {
    // ...
}
```

### Importing all classes in a package

The `*` is known as the wildcard in Java. It is used to import all classes in a package, except the classes in the subpackages of the specified package.

```java
import java.util.*;
```

### Static import

The static import statement is used to import static members from a class. Static members include fields and methods that are declared as static. If you only want to use a static member of a class, you can import the class using the static import statement. Then you don't need to use the class name to access the static member.

```java
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class MyClass {
    public static void main(String[] args) {
        System.out.println(PI);
        System.out.println(sqrt(4));
    }
}
```

### Classpath

- The classpath is a parameter in the Java Virtual Machine or the Java compiler that specifies the location of user-defined classes and packages. The parameter may be set either on the command-line, or through an environment variable. The classpath is a list of the directories that the Java compiler or Java Virtual Machine searches for both .class files and resource files at runtime.

- The classpath environment variable is used by the Java Virtual Machine to locate user-defined classes. The classpath environment variable is set using the `CLASSPATH` environment variable. The classpath environment variable is used by all Java applications and applets.

- The classpath command-line option is used by the Java compiler and Java Virtual Machine to locate user-defined classes. The classpath command-line option is set using the `-cp` or `-classpath` command-line option. The classpath command-line option is used by only one Java application or applet.

- The classpath is searched in the following order:

  - Bootstrap classes of your JVM
  - Extension classes of your JVM
  - User-defined classes and packages

- The classpath can contain wildcards. The asterisk `*` wildcard character represents zero or more characters. The question mark `?` wildcard character represents exactly one character.

```java
java -cp /home/user/myapp/lib/*:/home/user/myapp/classes com.myapp.Main
```

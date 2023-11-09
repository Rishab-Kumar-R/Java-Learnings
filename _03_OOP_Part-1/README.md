# Object-Oriented Programming (OOP) - Part 1

Object-oriented programming (OOP) is a programming paradigm that relies on the concept of classes and objects. It is used to structure a software program into simple, reusable pieces of code blueprints (usually called classes), which are used to create individual instances of objects. There are many object-oriented programming languages including JavaScript, C++, Java, and Python.

## Table of Contents

- [Introduction to Classes and Objects](./src/com/rishab/Introduction.java)
- [Getters and Setters](./src/com/rishab/Car.java)
- [Constructors](./src/com/rishab/Account.java)
- [The POJO Class](./src/com/rishab/POJODemo.java)
- [Inheritance](./src/com/rishab/Inheritance.java)
- [java.lang.Object](./src/com/rishab/ObjectClass.java)
- [String Formatting](./src/com/rishab/StringFormatting.java)
- [String Builder](./src/com/rishab/StringBuilderClass.java)

## Introduction to Classes and Objects

Software objects are often used to model real-world objects you find in everyday life. This approach to programming is called object-oriented programming (OOP).
An object has two characteristics:

- attributes
- behavior

Class is a template for an object and an object is an instance of a class. When the individual objects are created, they inherit all the variables and methods from the class.

- A class member can be a field, method, property, or event.
- A class can have static and non-static members. Static members are shared by all instances of the class and non-static members are specific to an instance of the class.
- If a field is static, there is only one copy of the field, no matter how many instances of the class are created. Static variables are called class variables.
- If a field is non-static, each instance of the class has its own copy of the field. Non-static variables are also called instance variables.
- Classes can be organized into logical groupings, which are called packages. We declare a package name in the class using the package keyword. If we don't declare a package name, the class, its subclasses, and its subclasses' subclasses are put into an unnamed package (called the default package). The package name is the same as the directory (folder) name where the .java files are stored.
- A class can be declared with the modifier public, which means that the class is visible to all classes everywhere. If a class has no modifier (the default, also known as package-private), it is visible only within its own package.

```java
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Alex";
        person1.age = 24;

        Person person2 = new Person();
        person2.name = "Aaron";
        person2.age = 21;

        System.out.println(person1.name);
        System.out.println(person1.age);
    }
}

class Person {
    // Instance variables (data or "state")
    String name;
    int age;

    // Classes can contain

    // 1. Data
    // 2. Subroutines (methods)
}
```

---

## Access Modifiers

- public: The class is accessible by any other class.
- protected: The class is accessible by classes in the same package and subclasses in the other packages.
- no modifier: The class is accessible by classes in the same package.

---

## Encapsulation

Encapsulation is one of the fundamental concepts in object-oriented programming (OOP). It describes the idea of bundling data and methods that work on that data within one unit, e.g., a class in Java.
This concept is also often used to hide the internal representation, or state, of an object from the outside. This is called information hiding.

---

## Getters and Setters

Getters and setters are used to protect your data, particularly when creating classes. For each variable, the getter method returns its value while the setter method sets the value.

---

## Constructors

A constructor in Java is a special method that is used to initialize objects. The constructor is called when an object of a class is created. It can be used to set initial values for object attributes.

If a class contains no constructor declarations, then a default constructor with no formal parameters and no throws clause is implicitly declared.

```java
class Car {
    int modelYear;
    String modelName;

    public Car(int year, String name) {
        modelYear = year;
        modelName = name;
    }

    public static void main(String[] args) {
        Car myCar = new Car(1969, "Mustang");
        System.out.println(myCar.modelYear + " " + myCar.modelName);
    }
}
```

**Constructor Overloading:** Multiple constructors can be created by overloading them. The only rule is that each constructor must have a unique signature similar to method overloading.

```java
class Car {
    int modelYear;
    String modelName;

    public Car() {
        modelYear = 1969;
        modelName = "Mustang";
    }

    public Car(int year, String name) {
        modelYear = year;
        modelName = name;
    }

    public static void main(String[] args) {
        Car myCar = new Car();
        System.out.println(myCar.modelYear + " " + myCar.modelName);

        Car myCar2 = new Car(1999, "Audi");
        System.out.println(myCar2.modelYear + " " + myCar2.modelName);
    }
}
```

**Constructor Chaining:** A constructor can call another constructor of the same class. It is useful in the case of multiple constructors.

```java
class Car {
    int modelYear;
    String modelName;

    public Car() {
        this(1969, "Mustang");
    }

    public Car(int year, String name) {
        modelYear = year;
        modelName = name;
    }

    public static void main(String[] args) {
        Car myCar = new Car();
        System.out.println(myCar.modelYear + " " + myCar.modelName);
    }
}
```

---

## References vs Objects vs Instances vs Classes

- **Reference:** A reference is a variable that refers to an object.
- **Object:** An object is an instance of a class.
- **Instance:** An instance means the same thing as an object.
- **Class:** A class is a blueprint from which individual objects are created.

---

## Static variables vs Instance variables and methods

- **Static variables:** A static variable is common to all the instances (or objects) of the class because it is a class-level variable. In other words, you can say that only a single copy of static variable is created and shared among all the instances of the class. Memory allocation for such variables only happens once when the class is loaded in the memory.

- **Instance variables:** Instance variables are non-static variables and are declared in a class outside any method, constructor or block. As instance variables are declared in a class, these variables are created when an object of the class is created and destroyed when the object is destroyed. Unlike local variables, we may use access specifiers for instance variables. If we do not specify any access specifier then the default access specifier will be used.

```java
class Student {
    private int rollNo; // instance variable
    private String name; // instance variable
    private static String college = "ITS"; // static variable

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    void display() {
        System.out.println(rollNo + " " + name + " " + college);
    }

    public static void main(String args[]) {
        Student s1 = new Student(111, "Karan");
        Student s2 = new Student(222, "Aryan");

        s1.display();
        s2.display();
    }
}
```

- **Static methods:** A static method belongs to the class rather than object of a class. A static method can be invoked without the need for creating an instance of a class. static method can access static data member and can change the value of it.

- **Instance methods:** Instance methods are methods which require an object of its class to be created before it can be called. To invoke a instance method, we have to create an Object of the class in within which it defined.

```java
class Student {
    private int rollNo;
    private String name;
    private static String college = "ITS";

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    // static method to change the value of static variable
    static void change() {
        college = "BBDIT";
    }

    // instance method to display the values
    void display() {
        System.out.println(rollNo + " " + name + " " + college);
    }

    public static void main(String args[]) {
        Student.change(); // calling change method

        Student s1 = new Student(111, "Karan");
        Student s2 = new Student(222, "Aryan");
        Student s3 = new Student(333, "Sonoo");

        s1.display();
        s2.display();
        s3.display();
    }
}
```

---

## The POJO Class

POJO stands for Plain Old Java Object. It is an ordinary Java object, not bound by any special restriction other than those forced by the Java Language Specification and not requiring any classpath. POJOs are used for increasing the readability and re-usability of a program.

- It's a class that generally only has instance fields.
- It has getters/setters for accessing these fields. It does not have any behavior of its own.
- Many database frameworks (like Hibernate) require POJOs to read or write from the database, files or streams.
- A POJO also called Java Bean is a Java class that follows some specific conventions mentioned below:
  - All properties in a POJO class must be private.
  - Getters and setters must be public.
  - A no-argument constructor must be present in the class.
  - It must implement Serializable interface.
- A POJO is sometimes called an Entity class, because it represents an entity from the database table.
- Data Transfer Object (DTO) is a design pattern which is used to transfer data between software application subsystems. DTOs are often used in conjunction with data access objects to retrieve data from a database.
- There are many generation tools available that will turn a data model into generated POJOs or JavaBeans. The code generation in Intellij IDEA is done by using the shortcut `Alt + Insert` and selecting the option `Constructor` or `Getter and Setter`.

---

## Annotations

Annotations are used to provide metadata for Java code. Annotations do not change action of compiled program. Annotations help to associate metadata (information) to the program elements i.e. instance variables, constructors, methods, classes, etc. Annotations are introduced in Java 5.0 and are available to us from JDK 1.5 onwards.

- **@Override:** The @Override annotation is used when we override a method in sub class. If the method is not present in the super class, then the compiler will throw an error.
- **@Deprecated:** The @Deprecated annotation is used to inform the compiler to generate a warning whenever a program uses a method, class, or field with the @Deprecated annotation. It is good to document the reason using the @Deprecated annotation.
- **@SuppressWarnings:** The @SuppressWarnings annotation is used to suppress warnings issued by the compiler. It is used to suppress unnecessary warnings that might be generated in the code.
- **@SafeVarargs:** The @SafeVarargs annotation is used to suppress warnings generated by Java compiler when variable arguments are used with generics. It is used to ensure that the code is type safe.
- **@FunctionalInterface:** The @FunctionalInterface annotation is used to ensure that the functional interface can’t have more than one abstract method. In case more than one abstract methods are present, the compiler flags an ‘Unexpected @FunctionalInterface annotation’ message. However, it is not mandatory to use this annotation.
- **@Native:** The @Native annotation is used to indicate that a method is implemented in native code using JNI (Java Native Interface).
- **@Target:** The @Target annotation is used to specify the Java elements where annotations can be used. The Java elements can be package, class, constructors, methods, fields, local variables, parameters, etc.
- **@Retention:** The @Retention annotation is used to specify how long annotations with the annotated type are to be retained. It takes RetentionPolicy argument whose SOURCE, CLASS or RUNTIME type.
- **@Documented:** The @Documented annotation is used to indicate that whenever the specified annotation is used those elements should be documented using the Javadoc tool.
- **@Inherited:** The @Inherited annotation is used to indicate that the annotation type can be inherited from the super class.
- **@Repeatable:** The @Repeatable annotation is used to indicate that the marked annotation can be applied more than once to the same declaration or type use.

---

## Overridden Methods

When different classes have methods with the same name and parameters, it is known as method overriding. It is used to achieve runtime polymorphism. In this process, an object is allowed to behave differently at runtime.

```java
class Animal {
    public void move() {
        System.out.println("Animals can move");
    }
}

class Dog extends Animal {
    @Override
    public void move() {
        System.out.println("Dogs can walk and run");
    }
}

public class TestDog {
    public static void main(String args[]) {
        Animal a = new Animal(); // Animal reference and object
        Animal b = new Dog(); // Animal reference but Dog object

        a.move(); // runs the method in Animal class
        b.move(); // runs the method in Dog class
    }
}
```

---

## The Record Type

A record is a data type that stores a fixed number of fields in a fixed order. The field names are not part of a record's definition; they are simply used to access the fields. For example, a record might contain an integer followed by a floating-point number. A record's values are ordinarily written sequentially into memory (typically starting at some fixed memory address), making it straightforward to index into the record and to calculate the address of an arbitrary field (after casting the record to a pointer).

- It's purpose is to replace the boilerplate of the POJO class, but to be more restrictive.
- Java calls them _plain data carriers_.
- A record is a special class that contains data, that are not meant to be altered. In other words, it seeks to achive immutaibility, for the data in its members.
- It contains only the most fundamental methods, such as constructors and accessors.

```java
public record Person(String name, int age) {
    // The constructor is implicit
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
```

---

## Inheritance

Inheritance is a mechanism in which one class acquires the property of another class. For example, a child inherits the traits of his/her parents. With inheritance, we can reuse the fields and methods of the existing class. Hence, inheritance facilitates Reusability and is an important concept of OOPs.

```java
class Animal {
    void eat() {
        System.out.println("eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("barking...");
    }
}

public class TestInheritance {
    public static void main(String args[]) {
        Dog d = new Dog();
        d.bark();
        d.eat();
    }
}
```

**Types of Inheritance:**

- **Single Inheritance:** When a class inherits only a single class, it is called single inheritance. We saw an example above.
- **Multilevel Inheritance:** When a class inherits another class which is already inheriting another class, it is known as multilevel inheritance. In the example given below, Dog class inherits the Animal class which is again inherited by the BabyDog class.

**Note:** A class can extend only one class. Multiple inheritance is not allowed in Java.

**super Keyword:** The super keyword in Java is a reference variable that is used to refer parent class objects. The keyword “super” came into the picture with the concept of Inheritance. It is majorly used in the following contexts:

- Use of super with variables: This scenario occurs when a derived class and base class has same data members. In order to access the data members of base class, super keyword is used. It is also used to refer immediate parent class instance variable.
- Use of super with methods: When we want to call parent class methods in child class and both classes have methods with same name, then we use super keyword to resolve ambiguity.
- Use of super with constructors: super() can be used to invoke immediate parent class constructor.

---

## What is `java.lang.Object`?

The Object class is the parent class of all the classes in java by default. In other words, it is the topmost class of java. The Object class is beneficial if you want to refer any object whose type you don't know. Notice that parent class reference variable can refer the child class object, know as upcasting.

Every class in Java is directly or indirectly derived from the Object class. If a Class does not extend any other class then it is direct child class of Object and if extends other class then it is an indirectly derived. Therefore, all Java classes are subclasses of the Object class.

**Methods of Object class:**

| Method Name | Description                                                                 |
| ----------- | --------------------------------------------------------------------------- |
| clone()     | Creates and returns a copy of this object.                                  |
| equals()    | Indicates whether some other object is "equal to" this one.                 |
| finalize()  | Called by the garbage collector on an object when garbage collection        |
| getClass()  | Returns the runtime class of an object.                                     |
| hashCode()  | Returns a hash code value for the object.                                   |
| toString()  | Returns a string representation of the object.                              |
| wait()      | Causes the current thread to wait until another thread invokes the notify() |
| notify()    | Wakes up a single thread that is waiting on this object's monitor.          |

---

## `this` vs `super` keywords

| this keyword                                                                        | super keyword                                                                   |
| ----------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| this keyword is used to refer to the current object inside a method or constructor. | super keyword is used to refer to the immediate parent class instance variable. |
| this keyword is commonly used with constructor and setter method.                   | super keyword is commonly used with method overriding.                          |
| this() is used to call the default constructor of the same class.                   | super() is used to call the default constructor of the parent class.            |
| this() must be the first statement in the constructor.                              | super() must be the first statement in the constructor.                         |
| this() is used in constructor chaining.                                             | super() is used in constructor chaining and method overriding.                  |

---

## Method Overloading vs Method Overriding

| Method Overloading                                                                                               | Method Overriding                                                                             |
| ---------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| Method overloading is a technique of defining multiple methods with the same name but with different parameters. | Method overriding is a technique by which child class redefines the superclass method.        |
| Method overloading is performed within class.                                                                    | Method overriding occurs in two classes that have IS-A (inheritance) relationship.            |
| In case of method overloading, **parameter must be different**.                                                  | In case of method overriding, **parameter must be same**.                                     |
| Method overloading is the example of **compile time polymorphism**.                                              | Method overriding is the example of **run time polymorphism** or **dynamic method dispatch**. |
| In java, method overloading can't be performed by changing return type of the method only.                       | In java, method overriding can't be performed by changing return type of the method.          |
| May have different return types and different access modifiers.                                                  | Must have same return type and must **NOT** have more restrictive access modifier.            |

---

## String

- String is a sequence of characters, for e.g. “Hello” is a string of 5 characters.
- In java, string is an immutable object which means it is constant and can cannot be changed once it has been created.
- The java.lang.String class is used to create a string object.
- Java String class provides a lot of methods to perform operations on strings such as compare(), concat(), equals(), split(), length(), replace(), compareTo(), intern(), substring() etc.

```java
String s1 = "Java"; // creating string by Java string literal
char ch[] = { 's', 't', 'r', 'i', 'n', 'g', 's' };
String s2 = new String(ch); // converting char array to string
String s3 = new String("example"); // creating Java string by new keyword
```

**String methods** are split into these basic categories:

- _String Inspection Methods:_ These methods inspect the string to find out something about it, such as the length of the string or whether the string contains a certain character.
- _String Comparison Methods:_ These methods compare one string to another string to determine whether the two strings are identical or whether one comes before or after the other.
- _String Manipulation Methods:_ These methods perform various tasks that are useful when working with strings, such as extracting substrings, searching for substrings, converting the case of characters in the string, and replacing substrings.

### String Inspection Methods

| Method Name              | Description                                                                                   |
| ------------------------ | --------------------------------------------------------------------------------------------- |
| length()                 | Returns the length of this string.                                                            |
| isEmpty()                | Returns true if, and only if, length() is 0.                                                  |
| charAt()                 | Returns the char value at the specified index.                                                |
| indexOf(), lastIndexOf() | Returns the index within this string of the first/last occurrence of the specified character. |
| isBlank()                | Returns true if the string is empty or contains only white space codepoints.                  |

### String Comparison Methods

| Method Name             | Description                                                            |
| ----------------------- | ---------------------------------------------------------------------- |
| equals()                | Compares this string to the specified object.                          |
| contentEquals()         | Compares this string to the specified CharSequence.                    |
| equalsIgnoreCase()      | Compares this String to another String, ignoring case considerations.  |
| contains()              | Returns true if and only if this string contains the specified         |
| endsWith() startsWith() | Tests if this string ends/starts with the specified suffix/prefix.     |
| regionMatches()         | Tests if two string regions are equal.                                 |
| compareTo()             | Compares two strings lexicographically.                                |
| matches()               | Tells whether or not this string matches the given regular expression. |

### String Manipulation Methods

| Method Name                           | Description                                                                                                  |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| concat()                              | Concatenates the specified string to the end of this string.                                                 |
| replace() replaceAll() replaceFirst() | Replaces each substring of this string that matches the given regular expression with the given replacement. |
| join()                                | Returns a new String composed of copies of the CharSequence elements joined together with a copy of the      |
| repeat()                              | Returns a string whose value is the concatenation of this string repeated count times.                       |
| substring() subSequence()             | Returns a new string that is a substring of this string.                                                     |

### String vs StringBuffer vs StringBuilder

| String                                                                                                               | StringBuffer                                                             | StringBuilder                                                             |
| -------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------ | ------------------------------------------------------------------------- |
| String is immutable.                                                                                                 | StringBuffer is mutable.                                                 | StringBuilder is mutable.                                                 |
| String class overrides the equals() method of Object class.                                                          | StringBuffer class doesn't override the equals() method of Object class. | StringBuilder class doesn't override the equals() method of Object class. |
| String is slow and consumes more memory when you concat too many strings because every time it creates new instance. | StringBuffer is fast and consumes less memory when you cancat strings.   | StringBuilder is fast and consumes less memory when you cancat strings.   |
| String class is thread safe and immutable.                                                                           | StringBuffer class is thread safe and mutable.                           | StringBuilder class is not thread safe and mutable.                       |

---

## String Builder

- StringBuilder class is used to create mutable (modifiable) string.
- The Java StringBuilder class is same as StringBuffer class except that it is non-synchronized. It is available since JDK 1.5.
- It is recommended to use StringBuilder class if thread safety is not required because it is faster than StringBuffer.
- Commonly used Constructors of StringBuilder class:
  - StringBuilder(): creates an empty string builder with initial capacity of 16.
  - StringBuilder(String str): creates a string builder with specified string.
  - StringBuilder(int capacity): creates an empty string builder with specified capacity as length.
  - StringBuilder(CharSequence seq): creates an empty string builder with specified CharSequence.

```java
StringBuilder sb = new StringBuilder("Hello ");
sb.append("Java"); // now original string is changed
System.out.println(sb); // prints Hello Java
```

**Methods of StringBuilder class:**

| Method Name             | Description                                                                              |
| ----------------------- | ---------------------------------------------------------------------------------------- |
| append()                | Appends the string representation of the argument to the end of this sequence.           |
| insert()                | Inserts the string representation of the argument into this sequence.                    |
| replace()               | Replaces the characters in a substring of this sequence with characters in the specified |
| delete() deleteCharAt() | Removes the characters in a substring of this sequence.                                  |
| reverse()               | Causes this character sequence to be replaced by the reverse of the sequence.            |
| capacity()              | Returns the current capacity.                                                            |
| setLength()             | Sets the length of the character sequence.                                               |

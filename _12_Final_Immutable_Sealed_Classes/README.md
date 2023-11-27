# Final, Immutable classes, Constructors, Initializers, Sealed Classes

- Objects have state, which is the data stored in instance fields. Objects also have behavior, which is the methods that
  can be invoked on them. Classes define the state and behavior of an object. And, objects are instances of a class.
- State can be changed after an object is created, either intentionally or unintentionally. For example, a method might
  change the value of a field, or a bug in a constructor might allow another class to change the field. This can lead to
  unexpected results in your program.
- When state remains constant throughout the lifetime of an object, and code is prevented from accidentally changing
  that state, the object is considered immutable.
- Immutable object is an object whose state cannot be changed after it is created (immutable objects are also
  thread-safe). Immutable objects are particularly useful in concurrent applications. Since they cannot change state,
  they cannot be corrupted by thread interference or observed in an inconsistent state.
- Mutable objects are objects whose state can be changed after it is created. Mutable objects are not thread-safe.
  Mutable objects are useful if you need to update the state of an object, such as a collection of items, over time.

## Table of Contents

- [Final Modifier](./src/com/rishab/finalExplored/Main.java)
- [Why state changes are not good](./src/com/rishab/finalExplored/MainMailer.java)
- [Immutable Classes](./src/com/rishab/immutableClasses/Main.java)
- [Copy Classes](./src/com/rishab/copyingClasses/Main.java)
- [Unmodifiable Collections](./src/com/rishab/unmodifiableCollections/Main.java)
- [Constructors](./src/com/rishab/constructorsProject/Main.java)
- [Final Classes](./src/com/rishab/gameConsole/game/GameConsole.java)
- [Sealed Classes](./src/com/rishab/gameConsole/sealed/SpecialAbstractClass.java)

**_Advantages of immutable objects:_**

- Immutable object isn't subject to unwanted, unplanned and unintended modification, known as side effects.
- Immutable class is inherently thread-safe, because no thread at all can change it once it is created.
- This allows us to use more efficient collections and operations, which don't have to manage synchronization of access
  to this object.

**_Disadvantages of immutable objects:_**

- Immutable object can't be changed, so every change to an object results in creating a new object.
- If you're constantly needing to alter text values, it's more efficient to use a mutable object like StringBuilder
  instance, rather than creating a new String instance each time you need to alter the text.

**_Classes must be designed to produce immutable objects:_**

- It's important to understand the POJOs (Plain Old Java Objects) and Java Beans, which many code generation tools
  create, are not by design, immutable, and therefore this architecture may not be preferred for creating immutable
  objects.
- One of the most useful tools in our arsenal for creating immutable objects is the final keyword. When applied to a
  class, the final keyword prevents the class from being subclassed. When applied to a method, the final keyword
  prevents the method from being overridden in a subclass. When applied to a field, the final keyword prevents the field
  from being changed after it's initialized.

## The `final` modifier

When we make use of the final modifier, we prevent any further modifications to that component.

- **final methods:** A final method cannot be overridden by any subclasses.
- **final fields:** A final field cannot be changed after it's initialized, and it must include an initializer statement
  where it's declared.
- **final static fields:** A final static field cannot be changed after it's initialized, and it must include an
  initializer statement where it's declared.
- **final classes:** A final class cannot be subclassed.
- **final variables:** A final variable can only be initialized once, either via an initializer or an assignment
  statement. It does not need to be initialized at the point of declaration: this is called a "blank final" variable. A
  blank final instance variable of a class must be definitely assigned at the end of every constructor of the class in
  which it is declared; similarly, a blank final static variable must be definitely assigned in a static initializer of
  the class in which it is declared; otherwise, a compile-time error occurs in both cases.
- **final parameters:** A final parameter cannot be changed inside the method.

### Hiding vs Overriding

- **Hiding:** When a class defines a static method with the same signature as a static method in a superclass, the
  method in the class hides the one in the superclass.
- **Overriding:** When a class defines a method with the same signature as a method in a superclass, the method in the
  class overrides the one in the superclass.

### Strategies for Declaring a Class, to produce Immutable Objects

- Make all fields private and final.
- Don't provide any methods that modify fields. (Don't provide any setter methods)
- Create defensive copies of your fields in the getter methods.

```java
public class MyClass {
    private Date myDate;

    public MyClass(Date myDate) {
        this.date = new Date(myDate.getTime()); // Defensive copy of the field on the constructor
    }

    public Date getMyDate() {
        return new Date(myDate.getTime()); // Defensive copy of the field on the getter
    }
}
```

- Use a constructor or factory method to set data, making copies of mutable objects.
- Mark the class final, or make the constructor private and construct instances in factory methods.

### Defensive Copies as input

- Defensive copies are also important when you're accepting mutable objects as input to a method.
- If you don't make a defensive copy of the mutable object, the caller can change the state of the object after it's
  passed to your method.
- This can lead to unexpected results in your program.
- Making a copy sounds expensive, but it's not as expensive as you might think.

### What's a copy? Shallow vs Deep Copy

- A shallow copy of an object copies the object itself, but not the internal objects.

```java
class Address {
    String city;
    String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }
}

class Person implements Cloneable {
    String name;
    int age;
    Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", address=" + address.city + ", " + address.state + '}';
    }
}

public class ShallowCopyExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person originalPerson = new Person("John", 25, new Address("CityA", "StateA"));

        // Create a shallow copy
        Person clonedPerson = (Person) originalPerson.clone();

        // Modify the address in the original person
        originalPerson.address.city = "CityB";
        originalPerson.address.state = "StateB";

        // Print original and cloned persons
        System.out.println("Original Person: " + originalPerson); // Original Person: Person{name='John', age=25, address=CityB, StateB}
        System.out.println("Cloned Person: " + clonedPerson); // Cloned Person: Person{name='John', age=25, address=CityB, StateB}
    }
}
```

- A deep copy of an object copies the object and all the internal objects it refers to.

```java
import java.util.ArrayList;
import java.util.List;

class Address {
    String city;
    String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }
}

class Person implements Cloneable {
    String name;
    int age;
    Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Perform a deep copy by cloning the Address object as well
        Person clonedPerson = (Person) super.clone();
        clonedPerson.address = new Address(this.address.city, this.address.state);
        return clonedPerson;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", address=" + address.city + ", " + address.state + '}';
    }
}

public class DeepCopyExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person originalPerson = new Person("John", 25, new Address("CityA", "StateA"));

        // Create a deep copy
        Person clonedPerson = (Person) originalPerson.clone();

        // Modify the address in the original person
        originalPerson.address.city = "CityB";
        originalPerson.address.state = "StateB";

        // Print original and cloned persons
        System.out.println("Original Person: " + originalPerson); // Original Person: Person{name='John', age=25, address=CityB, StateB}
        System.out.println("Cloned Person: " + clonedPerson); // Cloned Person: Person{name='John', age=25, address=CityA, StateA}
    }
}
```

### Immutable, Unmodifiable Collections and Views

- An unmodifiable collection is a collection that cannot be modified after it is created.
- A view is a collection backed by another collection, such as a map or a list, and changes to the backing collection
  are reflected in the view.
- The Collections class provides methods for creating unmodifiable collections and views of collections.
- The unmodifiable collections and views are read-only, and they throw an `UnsupportedOperationException` if you try to
  modify them.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableCollectionsExample {
    public static void main(String[] args) {
        List<String> mutableList = new ArrayList<>();
        mutableList.add("one");
        mutableList.add("two");
        mutableList.add("three");

        // Create an unmodifiable list
        List<String> unmodifiableList = Collections.unmodifiableList(mutableList);

        // Try to modify the unmodifiable list
        unmodifiableList.add("four"); // Throws UnsupportedOperationException
    }
}
```

|          | Unmodifiable Copy of Collections                                  | Unmodifiable View of Collections            |
| -------- | ----------------------------------------------------------------- | ------------------------------------------- |
| **List** | `List.copyOf(list)`                                               | `Collections.unmodifiableList(list)`        |
|          | `List.of("one", "two", "three")`                                  |                                             |
| **Set**  | `Set.copyOf(set)`                                                 | `Collections.unmodifiableSet(set)`          |
|          | `Set.of("one", "two", "three")`                                   | `Collections.unmodifiableNavigableSet(set)` |
|          |                                                                   | `Collections.unmodifiableSortedSet(set)`    |
| **Map**  | `Map.copyOf(map)`                                                 | `Collections.unmodifiableMap(map)`          |
|          | `Map.entry(key, value)`                                           | `Collections.unmodifiableNavigableMap(map)` |
|          | `Map.of(key1, value1, key2, value2)`                              | `Collections.unmodifiableSortedMap(map)`    |
|          | `Map.ofEntries(Map.entry(key1, value1), Map.entry(key2, value2))` |                                             |

---

## Constructors and Initializers

**_Constructors:_**

- Constructors are used to initialize the state of an object.
- Constructors are invoked when an object is created using the new keyword.
- Constructors are not methods, and they don't have a return type, can have access modifiers, but the most common access
  modifier is public.

```java
public class MyClass {
    public MyClass() {
        // Constructor code goes here
    }
}
```

- **Default Constructors:** If you don't provide any constructors, the compiler will provide a default constructor with
  no parameters.

```java
public class MyClass {
    // Compiler will provide a default constructor
}
```

- **Parameterized Constructors:** If you provide one or more constructors, the compiler will not provide a default
  constructor.

```java
public class MyClass {
    public MyClass(String name) {
        // Constructor code goes here
    }
}
```

- **Chaining Constructors:** You can chain constructors together using this keyword.

```java
class MyClass {
    private String name;
    private int age;

    public MyClass(String name) {
        this(name, 0); // Call the constructor with two parameters
    }

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

- **Record Constructors:** Records have a special constructor that takes all the fields as parameters.

  - The Canonical, or Long constructor is the implicitly generated constructor. We can explicitly declare our one.

    ```java
    public record Person(String name, int age) {
        // Canonical constructor
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    ```

  - The Custom constructor is just an overloaded constructor. It must explicitly call the Canonical constructor as the
    first statement.

    ```java
    public record Person(String name, int age) {
        // Custom constructor
        public Person(String name) {
            this(name, 0);
        }
    }
    ```

  - The Compact, or Short constructor is a special kind of constructor, used only on records. It's a succinct way of
    explicitly declaring a Canonical constructor.

    ```java
    public record Person(String name, int age) {
        // Compact constructor
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
    }
    ```

- **Enum Constructors:** Enum constructors are always private, and they can't be invoked directly. They are invoked when
  an enum constant is created.

```java
public enum MyEnum {
    ONE(1),
    TWO(2),
    THREE(3);

    private final int value;

    MyEnum(int value) {
        this.value = value;
    }
}
```

**_Initializers:_**

- Initializers are used to initialize the state of an object.
- Initializers are invoked before constructors.
- Initializers are not methods, and they don't have a return type, can't have access modifiers, but they can have other
  modifiers.

```java
public class MyClass {
    {
        // Initializer code goes here
    }
}
```

- **Static Initializers:**

  - Static initializers are used to initialize the state of a class.
  - Static initializers are invoked when the class is loaded.
  - Static initializers are not methods, and they don't have a return type, can't have access modifiers, but they can
    have other modifiers.

```java
public class MyClass {
    static {
        // Static initializer code goes here
    }
}
```

### [The Java Class File Disassembler (javap)](https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javap.html)

- The Java Class File Disassembler (javap) is a command-line tool that disassembles one or more class files.
- The javap command prints its output to the console.

```bash
# Disassemble a class file
javap -p out/production/12_Final_Immutable_Sealed_Classes/com/rishab/constructorsProject/MyClass.class
```

---

## Final Classes

- A final class cannot be subclassed, extended, abstract.
- A final class can be instantiated, have final, non-final, static and non-static fields.
- Enums and Records are implicitly final classes.

  | Operations                 | final class | abstract class | private constructors only | protected constructors only                                    |
  | -------------------------- | ----------- | -------------- | ------------------------- | -------------------------------------------------------------- |
  | Instantiate a new instance | Yes         | No             | No                        | Yes, but only from subclasses, and classes in the same package |
  | A subclass can be created  | No          | Yes            | No                        | Yes                                                            |

---

## Sealed Classes

- A sealed class is a class or interface that restricts which other classes or interfaces may extend or implement it.
- Sealed classes, like enums, capture alternative values, but with the benefit that the number of alternatives is open rather than fixed.
- Sealed classes are declared with the sealed modifier.
- Using the permits clause, you can specify which classes or interfaces are permitted to extend or implement the sealed class or interface.
- The permitted classes or interfaces must be in the same module as the sealed class or interface.

```java
public sealed class SealedClass permits SubClass1, SubClass2, SubClass3 {
    // Class code goes here
}
```

# Nested Classes And Types

Nested classes are classes that are defined within other classes. They are also called inner classes. They are used to logically group classes that are only used in one place, to increase the use of encapsulation, and to create more readable and maintainable code. They can be divided into four categories: static nested classes, non-static nested classes (inner classes), local classes, and anonymous classes.

| Class Type                                | Description                                                                                                                                                |
| ----------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Static Nested Classes                     | A static nested class is a static class defined at the member level. It can be accessed without instantiating the outer class, using other static members. |
| Non-Static Nested Classes (Inner Classes) | An inner class is a non-static class defined at the member level. It can be accessed only through an instance of the outer class.                          |
| Local Classes                             | A local class is a class defined within a block of code, typically a method body.                                                                          |
| Anonymous Classes                         | An anonymous class is a special case of a local class that does not have a name.                                                                           |

## Table of Contents

- [Static Nested Class](./src/com/rishab/nestedClass/Employee.java)
- [Non-Static Nested Class (Inner Class)](./src/com/rishab/nestedClass/StoreEmployee.java)
- [Local Class](./src/com/rishab/nestedClass/Main.java)
- [Anonymous Class](./src/com/rishab/nestedClass/RunMethods.java)

## Static Nested Classes

- The static nested class is a class enclosed in the structure of another class. It is a static member of the outer class, and it can be accessed without instantiating the outer class.
- This class has the advantage of being able to access the private members of the outer class.
- The static nested class can only access the static members of the outer class. If you want to access the non-static members, you need to create an object of the outer class in the static nested class.

- The following example demonstrates how to use the static nested class:

```java
public class StaticNestedClass {
    private static String name = "Alex";
    private static int age = 20;

    static class NestedClass {
        public void accessOuterClass() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        }
    }

    public static void main(String[] args) {
        NestedClass nestedObject = new NestedClass();
        nestedObject.accessOuterClass();
    }
}
```

- The output of the above code will be:

```shell
Name: Alex
Age: 20
```

---

## Non-Static Nested Classes (Inner Classes)

- An inner class is a class defined at the member level of another class. It can be declared as public, private, protected, or package private.
- The inner class can access all members of the outer class, including private members.
- The inner class can be accessed only through an instance of the outer class. To instantiate an inner class, you must first instantiate the outer class. Then, create the inner object within the outer object with this syntax:

- The following example demonstrates how to use the inner class:

```java
public class InnerClass {
    private String name = "Alex";

    class NestedClass {
        public void accessOuterClass() {
            System.out.println("Name: " + name);
        }
    }

    public static void main(String[] args) {
        InnerClass outerObject = new InnerClass();
        InnerClass.NestedClass innerObject = outerObject.new NestedClass();
        innerObject.accessOuterClass();
    }
}
```

- The output of the above code will be:

```shell
Name: Alex
```

---

## Local Classes

- A local class is a class defined within a block of code, typically a method body. It can be declared as public, private, protected, or package private.
- The local class can access all members of the enclosing block, including local variables. However, the local variables of the enclosing block must be declared as final or effectively final.
- The local class can be accessed only within the block where it is defined. To instantiate a local class, you must first instantiate the outer class. Then, create the local object within the outer object with this syntax:

- The following example demonstrates how to use the local class:

```java
public class LocalClass {
    private String name = "Alex";

    public void printName() {
        class NestedClass {
            public void accessOuterClass() {
                System.out.println("Name: " + name);
            }
        }
        NestedClass nestedObject = new NestedClass();
        nestedObject.accessOuterClass();
    }

    public static void main(String[] args) {
        LocalClass outerObject = new LocalClass();
        outerObject.printName();
    }
}
```

- The output of the above code will be:

```shell
Name: Alex
```

---

## Anonymous Classes

- An anonymous class is a special case of a local class that does not have a name. It can be declared as public, private, protected, or package private.
- The anonymous class can access all members of the enclosing block, including local variables. However, the local variables of the enclosing block must be declared as final or effectively final.
- The anonymous class can be accessed only within the block where it is defined. To instantiate an anonymous class, you must first instantiate the outer class. Then, create the anonymous object within the outer object with this syntax:

- The following example demonstrates how to use the anonymous class:

```java
public class AnonymousClass {
    private String name = "Alex";

    public void printName() {
        Object obj = new Object() {
            public void accessOuterClass() {
                System.out.println("Name: " + name);
            }
        };
        ((AnonymousClass) obj).printName();
    }

    public static void main(String[] args) {
        AnonymousClass outerObject = new AnonymousClass();
        outerObject.printName();
    }
}
```

- The output of the above code will be:

```shell
Name: Alex
```

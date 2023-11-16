# Generics in Java

## Table of Contents

- [Generics Introduction](./src/com/rishab/generics/Main.java)
- [Comparable & Comparator](./src/com/rishab/comparable/Main.java)

Generics allow us to create classes, interfaces, and methods that take types as parameters. These parameters are called type parameters, and we use angle brackets to specify the type parameter. For example, we can create a generic class called `Box` that can hold any type of object. We can then create a `Box` object that holds an `Integer` object, or a `Box` object that holds a `String` object, etc.

```java
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(10);
        System.out.println(integerBox.get());

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello World");
        System.out.println(stringBox.get());
    }
}
```

---

## Generic Type Parameters

A generic type is a generic class or interface that is parameterized over types. We use angle brackets (`<>`) to specify the type parameter. For example, `Box<T>` is a generic type, where `T` is the type parameter.

A few letters are reserved for special use cases. These are:

- `E` - Element (used extensively by the Java Collections Framework)
- `K` - Key (used extensively when working with maps)
- `N` - Number
- `T` - Type
- `V` - Value (used extensively when working with maps)
- `S`, `U`, `V` etc. - 2nd, 3rd, 4th types

---

## Generic Methods

Generic methods are methods that introduce their own type parameters. This is similar to declaring a generic type, but the type parameter's scope is limited to the method where it is declared. Static and non-static generic methods are allowed, as well as generic class constructors.

```java
public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};

        System.out.println("intArray contains:");
        printArray(intArray);

        System.out.println("\ndoubleArray contains:");
        printArray(doubleArray);
    }

    public static <T> void printArray(T[] inputArray) {
        for (T element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}
```

---

## Bounded Type Parameters

We can also limit the types that can be passed as type arguments to a generic class. For example, we can limit the type argument to be a subclass of a particular class. This is called bounded type parameters.

```java
class Box<T extends Number> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(10);
        System.out.println(integerBox.get());

        Box<Double> doubleBox = new Box<>();
        doubleBox.set(10.0);
        System.out.println(doubleBox.get());

        // Box<String> stringBox = new Box<>(); // Compile-time error
    }
}
```

---

## Interfaces used for sorting

### Comparable

The `Comparable` interface defines the `compareTo()` method, which is used to compare two objects of the same type. The `compareTo()` method returns an `int` value that indicates if the result of the comparison is positive, negative, or equal. The `compareTo()` method is used by sorting methods, such as `Collections.sort()` and `Arrays.sort()`.

```java
public interface Comparable<T> {
    public int compareTo(T o);
}
```

```java
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Employee o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rahul", 22));
        employees.add(new Employee("Rohit", 20));
        employees.add(new Employee("Raj", 19));

        Collections.sort(employees);

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
```

- If `this` is less than `o`, the `compareTo()` method returns a negative integer. (this < o) => -1
- If `this` is greater than `o`, the `compareTo()` method returns a positive integer. (this > o) => 1
- If `this` is equal to `o`, the `compareTo()` method returns 0. (this == o) => 0

### Comparator

The `Comparator` interface defines the `compare()` method, which is used to compare two objects of different types. The `compare()` method returns an `int` value that indicates if the result of the comparison is positive, negative, or equal. The `compare()` method is used by sorting methods, such as `Collections.sort()` and `Arrays.sort()`.

```java
public interface Comparator<T> {
    public int compare(T o1, T o2);
}
```

```java
public class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class EmployeeAgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getAge() - o2.getAge();
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rahul", 22));
        employees.add(new Employee("Rohit", 20));
        employees.add(new Employee("Raj", 19));

        Collections.sort(employees, new EmployeeAgeComparator());

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
```

- If `o1` is less than `o2`, the `compare()` method returns a negative integer. (o1 < o2) => -1
- If `o1` is greater than `o2`, the `compare()` method returns a positive integer. (o1 > o2) => 1
- If `o1` is equal to `o2`, the `compare()` method returns 0. (o1 == o2) => 0

### Comparable vs Comparator

| Comparable                                                                                                                      | Comparator                                                                                                                        |
| ------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| `compareTo()` method is defined in the same class whose objects are being sorted.                                               | `compare()` method is defined in a separate class.                                                                                |
| `compareTo()` method is used to provide the default sorting behavior for the objects.                                           | `compare()` method is used to provide the alternate sorting behavior for the objects.                                             |
| Compares `this` with the specified object for order.                                                                            | Compares its two arguments for order.                                                                                             |
| Returns a negative integer, zero, or a positive integer as `this` is less than, equal to, or greater than the specified object. | Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second. |
| `compareTo()` method is used by sorting methods, such as `Collections.sort()` and `Arrays.sort()`.                              | `compare()` method is used by sorting methods, such as `Collections.sort()` and `Arrays.sort()`.                                  |

---

## Wildcards

A wildcard is a special type of parameter that can be used in a type argument declaration. There are three types of wildcards:

- `?` - Unbounded Wildcard
- `? extends Type` - Upper Bounded Wildcard
- `? super Type` - Lower Bounded Wildcard

### Unbounded Wildcard

The unbounded wildcard type is specified using the wildcard character (`?`). This wildcard can be used as the type of a parameter, field, or local variable and sometimes as a return type. It represents an unknown type.

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        printList(integerList);

        List<String> stringList = Arrays.asList("Hello", "World");
        printList(stringList);
    }

    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }
}
```

### Upper Bounded Wildcard

The upper bounded wildcard type is specified using the wildcard character (`?`), followed by the extends keyword, followed by its upper bound. This wildcard can be used as the type of a parameter, field, or local variable and sometimes as a return type. It represents an unknown type that is a subtype of the specified type.

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        System.out.println(sum(integerList));

        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println(sum(doubleList));
    }

    public static double sum(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }
}
```

### Lower Bounded Wildcard

The lower bounded wildcard type is specified using the wildcard character (`?`), followed by the super keyword, followed by its lower bound. This wildcard can be used as the type of a parameter, field, or local variable and sometimes as a return type. It represents an unknown type that is a supertype of the specified type.

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        addNumbers(integerList);
        System.out.println(integerList);

        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println(numberList);
    }

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }
}
```

---

## Type Parameter, Type Argument, and Wildcard

- A type parameter is a placeholder for a type that is specified when a generic type is instantiated.

```java
public class Box<T> {
    // ...
}
```

- A type argument is an actual type that is specified when a generic type is instantiated.

```java
Box<Integer> integerBox = new Box<>();
```

- A wildcard is a special type of parameter that can be used in a type argument declaration.

```java
public static void printList(List<?> list) {
    // ...
}
```

---

## Type Erasure

- Generics were introduced in Java 5.0 to provide compile-time type checking and removing the risk of `ClassCastException` at runtime.
- Generics are implemented by using type erasure. Type erasure is a process in which compiler replaces a generic type with a concrete type or `Object` type.
- Type erasure is a technique used by compilers to implement generic types when it is not possible to determine the type of the generic type at runtime.

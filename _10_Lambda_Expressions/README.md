# Lambda Expressions, Functional Interfaces & Method References

## Table of Contents

- [Lambda Expressions](./src/com/rishab/lambdaExpressions/Main.java)
- [Method References](./src/com/rishab/methodReference/Main.java)

## Lambda Expressions

- Lambda expressions are a new and important feature included in Java SE 8. They provide a clear and concise way to represent one method interface using an expression. Lambda expressions also improve the Collection libraries, making it easier to iterate through, filter, and extract data from a Collection. In addition, new concurrency features improve performance in multicore environments.
- Lambda expressions basically express instances of functional interfaces (An interface with a single abstract method is called functional interface. An example is java.lang.Runnable). lambda expressions implement the only abstract function and therefore implement functional interfaces.
- Lambda expressions are added in Java 8 and provide below functionalities.

  - Enable to treat functionality as a method argument, or code as data.
  - A function that can be created without belonging to any class.
  - A lambda expression can be passed around as if it was an object and executed on demand.

- Lambda expressions are used primarily to define inline implementation of a functional interface, i.e., an interface with a single method only. In the example below, we've used various types of lambda expressions to define the operation method of MathOperation interface. Then we have defined the implementation of sayMessage of GreetingService.
- For a lambda expression, **the method is inferred by the compiler**. So, the lambda expression should be compatible with the method signature of the functional interface. The compiler checks the lambda expression type and the type of the method in the functional interface. If they are compatible, then the lambda expression is treated as an instance of the functional interface.

```java
public class LambdaExample {
    public static void main(String[] args) {
        // Traditional approach without lambda expression
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World! (Traditional)");
            }
        };

        // Using lambda expression
        Runnable lambdaRunnable = () -> System.out.println("Hello, World! (Lambda)");

        // Execute the run method of each Runnable
        traditionalRunnable.run();
        lambdaRunnable.run();
    }
}
```

- **Lambda Expressions Syntax**

  - A lambda expression is characterized by the following syntax.
  - A lambda expression can have zero or more parameters.
  - The type of the parameters can be explicitly declared, or it can be inferred from the context.
  - Multiple parameters are enclosed in mandatory parentheses and separated by commas. Empty parentheses are used to represent an empty set of parameters.
  - When there is a single parameter, if its type is inferred, it is not mandatory to use parentheses. e.g. `a -> return a * a`.
  - The body of the lambda expressions can contain zero or more statements.
  - If the body of lambda expression has a single statement, curly brackets are not mandatory, and the return type of the anonymous function is the same as that of the body expression. When there is more than one statement in the body than these must be enclosed in curly brackets (a code block) and the return type of the anonymous function is the same as the type of the value returned within the code block, or void if nothing is returned.
  - Lambda expressions can throw a checked exception only if it is declared in the functional interface. In other words, you can throw a checked exception in lambda expression if the abstract method of interface declares that exception.

---

## Functional Interfaces (SAM—Single Abstract Method)

- A functional interface is an interface that contains only one abstract method. They can have only one functionality to exhibit. From Java 8 onwards, lambda expressions can be used to represent the instance of a functional interface. A functional interface can have any number of default methods. Runnable, ActionListener, Comparable are some of the examples of functional interfaces.
- Before Java 8, we had to create anonymous inner class objects or implement these interfaces. But since Java 8, we can use lambda expressions to concisely implement these interfaces using the concept of functional interfaces. An important point to note is that lambda expressions can only be used to implement functional interfaces.
- **Functional Interface Rules**

  - A functional interface has only one abstract method, but it can have multiple default methods.
  - A functional interface can extend another interface only when it does not have any abstract method.
  - A functional interface can extend a java.lang.Object class without any issue.

```java
@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);
}

@FunctionalInterface
public interface GreetingService {
    void sayMessage(String message);
}

public class LambdaExample {
    public static void main(String[] args) {
        // Traditional approach without lambda expression
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World! (Traditional)");
            }
        };

        // Using lambda expression
        Runnable lambdaRunnable = () -> System.out.println("Hello, World! (Lambda)");

        // Execute the run method of each Runnable
        traditionalRunnable.run();
        lambdaRunnable.run();

        // Traditional approach without lambda expression
        MathOperation traditionalAddition = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };

        // Using lambda expression
        MathOperation lambdaAddition = (int a, int b) -> a + b;

        // Execute the operation method of each MathOperation
        System.out.println(traditionalAddition.operation(1, 2));
        System.out.println(lambdaAddition.operation(1, 2));

        // Traditional approach without lambda expression
        GreetingService traditionalGreetingService = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println(message);
            }
        };

        // Using lambda expression
        GreetingService lambdaGreetingService = message -> System.out.println(message);

        // Execute the sayMessage method of each GreetingService
        traditionalGreetingService.sayMessage("Hello, World! (Traditional)");
        lambdaGreetingService.sayMessage("Hello, World! (Lambda)");
    }
}
```

## In-built Functional Interfaces in Java

1. **Consumer Interface:** The java.util.function.Consumer Interface is a functional interface that represents an operation that accepts a single input argument and returns no result. It is a part of the java.util.function package. Since Consumer is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
   @FunctionalInterface
   public interface Consumer<T> {
       void accept(T t);
   }
   ```

2. **BinaryOperator Interface:** The java.util.function.BinaryOperator Interface is a functional interface that represents an operation which takes two operands of the same type, producing a result of the same type as the operands. It is a part of the java.util.function package. Since BinaryOperator is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
   @FunctionalInterface
   public interface BinaryOperator<T> extends BiFunction<T,T,T> {
       T apply(T t1, T t2);
   }
   ```

3. **Predicate Interface:** The java.util.function.Predicate Interface is a functional interface that represents an operation that takes a single argument and returns a boolean value. It is a part of the java.util.function package. Since Predicate is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }
   ```

4. **Function Interface:** The java.util.function.Function Interface is a functional interface that represents an operation that takes a single argument and returns a single value. It is a part of the java.util.function package. Since Function is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }
   ```

5. **Supplier Interface:** The java.util.function.Supplier Interface is a functional interface that represents an operation that takes no argument and returns a result. It is a part of the java.util.function package. Since Supplier is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
   ```

6. **UnaryOperator Interface:** The java.util.function.UnaryOperator Interface is a functional interface that represents an operation that takes a single argument and returns a single value. It is a part of the java.util.function package. Since UnaryOperator is a functional interface, it can be used as the assignment target for a lambda expression or method reference.

   ```java
    @FunctionalInterface
    public interface UnaryOperator<T> extends Function<T,T> {
        T apply(T t);
    }
   ```

   _NOTE:_ There are over 40 functional interfaces in Java 8. You can find the complete list [here](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html). But the below 4 are the most commonly used ones.

   | Interface Category | Functional Interface | Method Signature    | Purpose                                               | Example             |
   | ------------------ | -------------------- | ------------------- | ----------------------------------------------------- | ------------------- |
   | Consumer           | `Consumer<T>`        | `void accept(T t)`  | Accepts a single input argument and returns no result | forEach(), accept() |
   | Predicate          | `Predicate<T>`       | `boolean test(T t)` | Accepts a single input argument and returns a boolean | removeIf(), test()  |
   | Function           | `Function<T, R>`     | `R apply(T t)`      | Accepts a single input argument and returns a result  | map(), apply()      |
   | Supplier           | `Supplier<T>`        | `T get()`           | Does not accept any argument and returns a result     | get(), getAsInt()   |

### The two most common Consumer Interface are `Consumer<T>` and `BiConsumer<T, U>`

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```

### The two most common Predicate Interface are `Predicate<T>` and `BiPredicate<T, U>`

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```

### The two most common Function Interface are `Function<T, R>` and `BiFunction<T, U, R>`

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```

### The two most common Supplier Interface are `Supplier<T>` and `BooleanSupplier`

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

@FunctionalInterface
public interface BooleanSupplier {
    boolean getAsBoolean();
}
```

---

## Method References

- Method references help to point to methods by their names. A method reference is described using `::` (double colon) symbol. A method reference can be used to point the following types of methods −
  - Static methods
  - Instance methods
  - Constructors using new operator (TreeSet::new)
- Method references are compact, easy-to-read lambda expressions for methods that already have a name. They are often much shorter than lambda expressions. As with lambda expressions, the Java compiler automatically infers the target type/context in which the method reference is evaluated. Consider the following example.

- **Method Reference Example**

  - The following example shows how to refer a static method.

  ```java
  public class LambdaExample {
      public static void main(String[] args) {
          // Using method reference
          Runnable methodReferenceRunnable = LambdaExample::printHelloWorld;

          // Execute the run method of each Runnable
          methodReferenceRunnable.run();
      }

      public static void printHelloWorld() {
          System.out.println("Hello, World! (Method Reference)");
      }
  }
  ```

  - The following example shows how to refer an instance method.

  ```java
  public class LambdaExample {
      public static void main(String[] args) {
          // Using method reference
          Consumer<String> methodReferenceConsumer = System.out::println;

          // Execute the accept method of each Consumer
          methodReferenceConsumer.accept("Hello, World! (Method Reference)");
      }
  }
  ```

  - The following example shows how to refer a constructor.

  ```java
  public class LambdaExample {
      public static void main(String[] args) {
          // Using method reference
          Supplier<TreeSet<String>> methodReferenceSupplier = TreeSet::new;

          // Execute the get method of each Supplier
          TreeSet<String> treeSet = methodReferenceSupplier.get();
          treeSet.add("Hello, World! (Method Reference)");
          System.out.println(treeSet);
      }
  }
  ```

### Convenience Methods on functional interfaces in java.util.function package

- The java.util.function package contains many builtin functional interfaces in Java 8. Some of the most commonly used functional interfaces are Predicate, Consumer, Function, and Supplier. These interfaces already have some convenience methods defined in them. These convenience methods are nothing but the default methods defined in the interfaces. These default methods are nothing but the convenience methods that can be used to perform some common operations.

  | Interface Category | Convenience method example                                   | Purpose                                                                                                                  |
  | ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------ |
  | Function           | `function1.andThen(function2)`                               | Returns a composed function that first applies the function1 to its input, and then applies the function2 to the result. |
  | Function           | `function1.compose(function2)`                               | Returns a composed function that first applies the function2 to its input, and then applies the function1 to the result. |
  | Consumer           | `consumer1.andThen(consumer2)`                               | Returns a composed Consumer that performs, in sequence, this operation followed by the operation.                        |
  | Predicate          | `predicate1.and(predicate2)`                                 | Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.               |
  | Predicate          | `predicate1.or(predicate2)`                                  | Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.                |
  | Predicate          | `predicate1.negate()`                                        | Returns a predicate that represents the logical negation of this predicate.                                              |
  | Comparator         | (static) `comparator.comparing()`                            | Returns a comparator that compares its input arguments according to the order induced by the specified comparator.       |
  | Comparator         | (static) `comparator.naturalOrder()`                         | Returns a comparator that compares Comparable objects in natural order.                                                  |
  | Comparator         | (static) `comparator.reverseOrder()`                         | Returns a comparator that imposes the reverse of the natural ordering.                                                   |
  | Comparator         | (default) `comparator.thenComparing()` Comparator other      | Returns a lexicographic-order comparator with another comparator.                                                        |
  | Comparator         | (default) `comparator.thenComparing()` Function keyExtractor | Returns a lexicographic-order comparator with another comparator.                                                        |
  | Comparator         | (default) `comparator.reversed()`                            | Returns a comparator that imposes the reverse ordering of this comparator.                                               |

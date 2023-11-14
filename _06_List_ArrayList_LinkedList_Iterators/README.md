# List, ArrayList, LinkedList, & Iterators

Java includes an entire library for java containers, they are called collections. Collections are used to store, retrieve, manipulate, and communicate aggregate data. The Java Collections Framework is a set of classes and interfaces that implement commonly reusable collection data structures. The framework consists of:

- **Interfaces:** abstract data types representing collections.
- **Implementations:** concrete implementations of the interfaces.
- **Algorithms:** methods that perform useful computations, such as searching and sorting, on objects that implement collection interfaces.

The Java Collections Framework provides the following benefits:

- **Reduces programming effort:** by providing useful data structures and algorithms so you don't have to write them yourself.
- **Increases program speed and quality:** by providing high-performance, high-quality implementations of useful data structures and algorithms.
- **Allows interoperability among unrelated APIs:** by establishing a common language to pass collections back and forth.

## Table of Contents

- [ArrayList](./src/com/rishab/ArrayListDemo.java)
- [LinkedList](./src/com/rishab/LinkedListDemo.java)
- [AutoBoxing and UnBoxing](./src/com/rishab/AutoBoxingAndUnBoxing.java)
- [Enumeration](./src/com/rishab/Enumeration.java)

## List

A list is an ordered collection of elements that allows duplicate entries. The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.

_NOTE:_ Lists are immutable, meaning that they cannot be changed once created. To change a list, you must create a new list with the desired changes.

The `List` interface is found in the `java.util` package and inherits the `Collection` interface. It is a generic interface that has the following declaration:

```java
public interface List<E> extends Collection<E>
```

---

## ArrayList

The `ArrayList` class is a resizable array, which can be found in the `java.util` package. It inherits the `AbstractList` class and implements the `List` interface. The `ArrayList` class is equivalent to the `Vector` class, but it is not synchronized.

The `ArrayList` class can contain duplicate elements. It maintains the insertion order of the elements. It is the best approach to use `ArrayList` in case of retrieval operations and `LinkedList` in case of insertion and deletion operations.

| Feature                                          | Array                                          | ArrayList                                                                                                       |
| :----------------------------------------------- | :--------------------------------------------- | :-------------------------------------------------------------------------------------------------------------- |
| **primitive type supported**                     | Yes                                            | No                                                                                                              |
| **index based**                                  | Yes                                            | Yes                                                                                                             |
| **ordered by index**                             | Yes                                            | Yes                                                                                                             |
| **allows duplicates**                            | Yes                                            | Yes                                                                                                             |
| **null allowed**                                 | Yes                                            | Yes                                                                                                             |
| **resizeable**                                   | No                                             | Yes                                                                                                             |
| **mutable**                                      | No                                             | Yes                                                                                                             |
| **inherits from java.util.Object**               | No                                             | Yes                                                                                                             |
| **implements java.util.List**                    | No                                             | Yes                                                                                                             |
| **Fixed size**                                   | Yes                                            | No                                                                                                              |
| **Instantiating**                                | `String[] arr = new String[10];`               | `ArrayList<String> arr = new ArrayList<String>();`                                                              |
| **Accessing elements**                           | `arr[0]`                                       | `arr.get(0)`                                                                                                    |
| **Printing elements**                            | `System.out.println(Arrays.toString(arr))`     | `System.out.println(arr)`                                                                                       |
| **Printing elements for multidimensional array** | `System.out.println(Arrays.deepToString(arr))` | `System.out.println(arr)`                                                                                       |
| **Finding Element**                              | `Arrays.binarySearch(arr, "element")`          | `arr.indexOf("element")`, `arr.lastIndexOf("element")`, `arr.contains("element")`, `arr.containsAll("element")` |
| **Sorting**                                      | `Arrays.sort(arr)`                             | `arr.sort(Comparator.naturalOrder())`, `arr.sort(Comparator.reverseOrder())`                                    |
| **Removing element**                             | `arr[0] = null`                                | `arr.remove(0)`, `arr.remove("element")`, `arr.removeAll("element")`                                            |

### Creating Special Kinds of Lists

| Using Arrays.asList()                                | Using List.of()                                      |
| :--------------------------------------------------- | :--------------------------------------------------- |
| Returned List is NOT resizable, but is mutable       | Returned List is IMMUTABLE                           |
| `List<String> list = Arrays.asList("a", "b", "c");`  | `var list = List.of("a", "b", "c");`                 |
| `String[] days = new String[]{"Mon", "Tue", "Wed"};` | `String[] days = new String[]{"Mon", "Tue", "Wed"};` |
| `List<String> list = Arrays.asList(days);`           | `List<String> list = List.of(days);`                 |

---

## LinkedList

The `LinkedList` class is a doubly linked list implementation of the `List` and `Deque` interfaces. The `LinkedList` class can contain duplicate elements. It maintains the insertion order and is not synchronized. In `LinkedList`, the manipulation is fast because no shifting is required.

- The LinkedList is a linear data structure in which the elements are not stored in contiguous locations and every element is a separate object with a data part and address part. The elements are linked using pointers and addresses. Each element is known as a node. Due to the dynamicity and ease of insertions and deletions, they are preferred over the arrays. It also has few disadvantages like the nodes cannot be accessed directly instead we need to start from the head and follow through the link to reach to a node we wish to access.
- In Java, LinkedList can be represented as a class and a Node as a separate class. The LinkedList class contains a reference of Node class type.
- The LinkedList class extends AbstractSequentialList and implements the List and Deque interfaces.

```java
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable
```

### LinkedList vs ArrayList

| Feature          | LinkedList                                                                                                                            | ArrayList                                                                                                                            |
| :--------------- | :------------------------------------------------------------------------------------------------------------------------------------ | :----------------------------------------------------------------------------------------------------------------------------------- |
| Storage Overhead | LinkedList requires extra space because it stores the reference of the next and previous elements.                                    | ArrayList doesn't require extra space.                                                                                               |
| Random access    | LinkedList can be traversed in reverse direction only by going through the next pointer.                                              | ArrayList can be randomly accessed using the index.                                                                                  |
| Performance      | Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list, so no bit shifting is required in memory. | ArrayList is slow because it internally uses an array. If any element is removed from the array, all the bits are shifted in memory. |
| Memory           | LinkedList implementation requires more memory than ArrayList because it stores both the data and address.                            | ArrayList implementation requires less memory because it stores only data.                                                           |
| Speed            | LinkedList is slow access and fast insertion and deletion.                                                                            | ArrayList is fast access and slow insertion and deletion.                                                                            |
| Usage            | LinkedList is used for implementing stacks and queues.                                                                                | ArrayList is used for implementing ArrayList and Vectors.                                                                            |

### LinkedList and ArrayList Operations - Big O notation

_NOTE_: The Big O notation is used to describe the performance or complexity of an algorithm. Big O specifically describes the worst-case scenario, and can be used to describe the execution time required or the space used (e.g. in memory or on disk) by an algorithm.

| Operation                 | LinkedList | ArrayList |
| :------------------------ | :--------- | :-------- |
| add()                     | O(1)       | O(1)      |
| add(int index, E element) | O(n)       | O(n)      |
| contains(E element)       | O(n)       | O(n)      |
| get(int index)            | O(n)       | O(1)      |
| indexOf(E element)        | O(n)       | O(n)      |
| remove(int index)         | O(n)       | O(n)      |
| remove(E element)         | O(n)       | O(n)      |
| set(int index, E element) | O(n)       | O(1)      |

---

## Iterators

An iterator is an object that enables a programmer to traverse a container, particularly lists. Various types of iterators are often provided via a container's interface. Though the interface and semantics of the iterator may vary from one container to another, there are a set of common patterns for traversing containers. The two most common are the iterator and the enumerator.

### Iterator

So far we have mainly used `for` loops to iterate over a collection. The `for` loop is a control structure that allows us to repeat certain operations by incrementing and evaluating a loop counter. Using a `for` loop is the most common way to iterate over a collection.

```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```

The `Iterator` interface provides methods to iterate over any `Collection`. By using `Iterator` we can perform both read and remove operations. The `Iterator` interface is found in the `java.util` package and contains three methods:

- `public boolean hasNext()`: Returns true if the iteration has more elements.
- `public Object next()`: Returns the next element in the iteration.
- `public void remove()`: Removes the last element returned by the iterator. It is less used.

```java
Iterator<String> itr = list.iterator();
while (itr.hasNext()) {
    System.out.println(itr.next());
}
```

### ListIterator

The `ListIterator` interface extends the `Iterator` interface. It is used to iterate over the elements in a list in either direction. It is different from the `Iterator` interface in the following ways:

- `ListIterator` can traverse the list in both directions (forward and backward) while `Iterator` can traverse the list only in forward direction.
- `ListIterator` can modify the list while traversing it. `Iterator` can't modify the collection while traversing the list.

The `ListIterator` interface is found in the `java.util` package and contains the following methods:

- `public boolean hasNext()`: Returns true if this list iterator has more elements when traversing the list in the forward direction.
- `public Object next()`: Returns the next element in the list.
- `public boolean hasPrevious()`: Returns true if this list iterator has more elements when traversing the list in the reverse direction.
- `public Object previous()`: Returns the previous element in the list.
- `public int nextIndex()`: Returns the index of the element that would be returned by a subsequent call to next().
- `public int previousIndex()`: Returns the index of the element that would be returned by a subsequent call to previous().
- `public void remove()`: Removes from the list the last element that was returned by next() or previous() (optional operation).
- `public void set(Object e)`: Replaces the last element returned by next() or previous() with the specified element (optional operation).
- `public void add(Object e)`: Inserts the specified element into the list (optional operation).

```java
ListIterator<String> listIterator = list.listIterator();
while (listIterator.hasNext()) {
    System.out.println(listIterator.next());
}
```

**NOTE:** It's really important to understand that the iterator's current position is always between two elements - or, in other words, the iterator is always pointing at an element. When you call `next()` or `previous()`, the iterator moves to the next or previous element. When you call `add()`, the element is inserted before the iterator's current position. When you call `remove()`, the element that was returned by the last call to `next()` or `previous()` is removed.

---

## AutoBoxing and UnBoxing

### What is Boxing?

- A primitive is boxed, or wrapped, in a containing class, whose main data is a primitive value.
- Each primitive data type has a wrapper class in Java. The wrapper class for primitive data type `int` is `Integer`.
- Each wrapper class has a static overloaded factory method `valueOf` that takes a primitive data type as an argument and returns an object of the wrapper class.

```java
Integer i = Integer.valueOf(42);
```

- Another way to box a primitive is to pass it to the constructor of the wrapper class. (DEPRECATED)

```java
Integer i = new Integer(42);
```

### What is auto-boxing?

- Auto-boxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes.
- For example, converting an `int` to an `Integer`, a `double` to a `Double`, and so on.

### What is unboxing?

- Unboxing is the reverse process of auto-boxing. It is the automatic conversion of wrapper class to the primitive type.
- For example, converting an `Integer` to an `int`, a `Double` to a `double`, and so on.

```java
Integer boxedInteger = 15;

int unboxedInt = boxedInteger.intValue(); // explicit unboxing (or) manual unboxing

int unboxedInt = boxedInteger; // implicit unboxing (or) auto unboxing
```

---

## Enumerations

An enumeration is a list of named constants. An enumeration type (sometimes called an enumeration or enum) provides an efficient way to define a set of named integral constants that may be assigned to a variable.

```java
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

### Enumerations vs Classes

- An enum can, just like a class, have attributes and methods. The only difference is that enum constants are public, static and final (unchangeable - cannot be overridden).
- An enum cannot be used to create objects, and it cannot extend other classes (but it can implement interfaces).
- Why And When To Use Enums?
  - Use enums when you have values that you know aren't going to change, like month days, days, colors, deck of cards, etc.
  - Use enums when you have a set of constants that are logically related to each other, such as the compass directions (North, South, East and West).
- Enums are used when we know all possible values at compile time, such as choices on a menu, rounding modes, command line flags, etc. It is not necessary that the set of constants in an enum type stay fixed for all time. In Java (from 1.5), enums are represented using enum data type. Java enums are more powerful than C/C++ enums. In Java, we can also add variables, methods and constructors to it. The main objective of enum is to define our own data types(Enumerated Data Types).

### Enumerations vs Interfaces

- An enum can, just like an interface, have abstract methods. The body of the enum constants (the part that says `RED(255, 0, 0)`) must always implement these methods. If you don't, compile time error will occur. However, it is not necessary for an enum constant to implement all the abstract methods if the enum class has a default implementation (as in this example).
- An enum cannot implement interfaces, but it can extend other classes (except for `java.lang.Enum`, because it is final).

```java
enum Color {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255);

    private int r;
    private int g;
    private int b;

    private Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String toString() {
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Color.RED);
        System.out.println(Color.GREEN);
        System.out.println(Color.BLUE);
    }
}
```

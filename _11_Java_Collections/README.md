# [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/index.html)

A collection — sometimes called a container — is simply an object that groups multiple elements into a single unit. Collections are used to store, retrieve, manipulate, and communicate aggregate data. Typically, they represent data items that form a natural group, such as a poker hand (a collection of cards), a mail folder (a collection of letters), or a telephone directory (a mapping of names to phone numbers).

The Java platform doesn't provide any direct implementations of this interface but provides implementations of more specific sub-interfaces, such as Set and List. This interface is typically used to pass collections around and manipulate them where maximum generality is desired.

## Table of Contents

- [Collections Overview](./src/com/rishab/collectionsOverview/Main.java)
- [Collections Methods](./src/com/rishab/collectionMethods/Main.java)
- [Hashing](./src/com/rishab/hashing/Main.java)
- [Sets and HashSets](./src/com/rishab/setsAndMaps/Main.java)
- [TreeSet](./src/com/rishab/setsAndMaps/TreeSetMain.java)
- [Maps & HashMaps](./src/com/rishab/setsAndMaps/MapMain.java)
- [Map Views](./src/com/rishab/setsAndMaps/MapViewsMain.java)
- [TreeMap](./src/com/rishab/setsAndMaps/TreeMapMain.java)
- [Enum Collections](./src/com/rishab/setsAndMaps/EnumCollections.java)

## Collection Interface

The Collection interface is the foundation upon which the collection framework is built. It declares the core methods that all collections will have. It is one of the root interfaces of the Java Collection API.

### Methods of Collection Interface

| Method                                          | Description                                                                                   |
| ----------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `boolean add(E e)`                              | Ensures that this collection contains the specified element (optional operation).             |
| `boolean addAll(Collection<? extends E> c)`     | Adds all of the elements in the specified collection to this collection (optional operation). |
| `void clear()`                                  | Removes all of the elements from this collection (optional operation).                        |
| `boolean contains(Object o)`                    | Returns true if this collection contains the specified element.                               |
| `boolean containsAll(Collection<?> c)`          | Returns true if this collection contains all of the elements in the specified collection.     |
| `Iterator<E> iterator()`                        | Returns an iterator over the elements in this collection.                                     |
| `boolean remove(Object o)`                      | Removes a single instance of the specified element from this collection, if it is present.    |
| `boolean removeAll(Collection<?> c)`            | Removes all of this collection's elements that are also contained in the specified collection |
| `boolean removeIf(Predicate<? super E> filter)` | Removes all of the elements of this collection that satisfy the given predicate.              |
| `boolean retainAll(Collection<?> c)`            | Retains only the elements in this collection that are contained in the specified collection.  |

## List Interface

The List interface provides a way to store the ordered collection. It is a child interface of Collection.

### Methods of List Interface

| Method                                                 | Description                                                                                                                       |
| ------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------- |
| `void add(int index, E element)`                       | Inserts the specified element at the specified position in this list (optional operation).                                        |
| `boolean addAll(int index, Collection<? extends E> c)` | Inserts all of the elements in the specified collection into this list at the specified position (optional operation).            |
| `E get(int index)`                                     | Returns the element at the specified position in this list.                                                                       |
| `int indexOf(Object o)`                                | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| `int lastIndexOf(Object o)`                            | Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.  |
| `ListIterator<E> listIterator()`                       | Returns a list iterator over the elements in this list (in proper sequence).                                                      |
| `ListIterator<E> listIterator(int index)`              | Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.      |
| `E remove(int index)`                                  | Removes the element at the specified position in this list (optional operation).                                                  |
| `E set(int index, E element)`                          | Replaces the element at the specified position in this list with the specified element (optional operation).                      |
| `List<E> subList(int fromIndex, int toIndex)`          | Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.                    |
| `void sort(Comparator<? super E> c)`                   | Sorts this list according to the order induced by the specified Comparator.                                                       |
| `static <E> List<E> of()`                              | Returns an unmodifiable list containing zero elements.                                                                            |

---

## Interfaces Hierarchy

<div style="text-align: center;" display="flex">
    <img src="https://upload.wikimedia.org/wikipedia/commons/a/ab/Java.util.Collection_hierarchy.svg" style="background-color: white;" width="700" alt="collection-hierarchy"/>
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Java.util.Map_hierarchy.svg/1280px-Java.util.Map_hierarchy.svg.png" style="background-color: white;" width="700" alt="map-hierarchy"/>
</div>

---

## Collection Class

- The Collection class is a utility class that consists of static methods that operate on or return collections. It inherits Object class. It is defined in java.util package.
- The important points about Java Collections class are:

  - Java Collection class supports the polymorphic algorithms that operate on collections.
  - Java Collection class throws a NullPointerException if the collections or class objects provided to them are null.
  - Java Collection class is fail-fast, if the collection is modified after the creation of iterator, it throws ConcurrentModificationException.

- The various methods of Collection class are:

| Method                                                           | Description                                                                               |
| ---------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `Collections.fill(Collection<? super T> c, T val)`               | Replaces all of the elements of the specified collection with the specified element.      |
| `Collections.nCopies(int n, T o)`                                | Returns an immutable list consisting of n copies of the specified object.                 |
| `Collections.addAll(Collection<? super T> c, T... elements)`     | Adds all of the specified elements to the specified collection.                           |
| `Collections.copy(List<? super T> dest, List<? extends T> src)`  | Copies all of the elements from one list into another.                                    |
| `Collections.shuffle(List<?> list)`                              | Randomly permutes the specified list using a default source of randomness.                |
| `Collections.sort(List<T> list)`                                 | Sorts the specified list into ascending order, according to the natural ordering.         |
| `Collections.reverse(List<?> list)`                              | Reverses the order of the elements in the specified list.                                 |
| `Collections.indexOfSubList(List<?> source, List<?> target)`     | Returns the starting position of the first occurrence of the specified target list.       |
| `Collections.lastIndexOfSubList(List<?> source, List<?> target)` | Returns the starting position of the last occurrence of the specified target list.        |
| `Collections.disjoint(Collection<?> c1, Collection<?> c2)`       | Returns true if the two specified collections have no elements in common.                 |
| `Collections.binarySearch(List<? extends Comparable<? super T>>` | Searches the specified list for the specified object using the binary search algorithm.   |
| `Collections.replaceAll(List<T> list, T oldVal, T newVal)`       | Replaces all occurrences of one specified value in a list with another.                   |
| `Collections.frequency(Collection<?> c, Object o)`               | Returns the number of elements in the specified collection equal to the specified object. |
| `Collections.min(Collection<? extends T> coll)`                  | Returns the minimum element of the given collection, according to the natural ordering.   |
| `Collections.max(Collection<? extends T> coll)`                  | Returns the maximum element of the given collection, according to the natural ordering.   |
| `Collections.rotate(List<?> list, int distance)`                 | Rotates the elements in the specified list by the specified distance.                     |
| `Collections.swap(List<?> list, int i, int j)`                   | Swaps the elements at the specified positions in the specified list.                      |

---

## Importance of Hashcode

- Since sets are collections of unique elements, the set implementation needs to know whether two objects are equal or not. This is done by calling the equals() method on the objects. If the equals() method returns true, the set implementation assumes that the two objects are equal and removes the duplicate object from the set. Adding an element is always incurring the cost of first checking for a match.
- If the set is large, this becomes a costly operation, O(n) linear time. A mechanism to reduce this cost is introduced by something called hashing.
- The idea is to assign a unique code to each object and store them in a bucket. When an object is added to the set, the set implementation first checks if the hash code of the object is already present in the bucket. If it is, then the set implementation calls the equals() method to check if the object is really a duplicate. If the hash code is not present, then the set implementation assumes that the object is unique and adds it to the bucket. This reduces the cost of checking for duplicates to O(1) constant time.
- The hash code is an integer value generated by a hashing algorithm. The hash code is generated based on the contents of the object. If the contents of the object are changed, then the hash code also changes. The hash code is not guaranteed to be unique. Two different objects can have the same hash code. However, the hash code of two equal objects is always the same.

  | Testing of equality                                        | Hashcode                                        |
  | ---------------------------------------------------------- | ----------------------------------------------- |
  | `public boolean equals(Object obj)`                        | `public int hashCode()`                         |
  | This method is used to test whether two objects are equal. | This method returns the hash code of an object. |

---

## Hashing

- Hashing is a technique used to uniquely identify a specific object from a group of similar objects. Some examples of how hashing is used in our lives include:

  - In universities, each student is assigned a unique roll number that can be used to retrieve information about them.
  - In libraries, each book is assigned a unique number that can be used to determine information about the book, such as its exact position in the library or the people who have borrowed it.
  - In airports, each passenger is assigned a unique boarding pass that can be used to identify them.

- In all of these examples, the object is assigned a unique identifier that can be used to retrieve information about the object. In Java, hashing is used to assign a unique identifier to each object that is created using the new keyword. This unique identifier is called a hashcode. The hashcode is used to retrieve information about the object from a collection.

---

## Sets and HashSets

- A set is a collection that cannot contain duplicate elements. It models the mathematical set abstraction. The two most common implementations of the Set interface are HashSet and TreeSet. The HashSet class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls. TreeSet, which implements the Set interface, provides guaranteed log(n) time cost for the basic operations (add, remove and contains).
- The set can be useful because the operations on them are rapid. The HashSet class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee that the order will remain constant over time. This class permits the null element.
- Sets are used to store unique elements. The HashSet class implements the Set interface. It represents an unordered collection of objects in which duplicate values cannot be stored. It is used to store unordered sets of elements. It is a generic class declared as:

  ```java
  public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable
  ```

- Set interface defines the basic methods like `add`, `remove`, `clear`, to maintain the items in the set. But there is no way to retrieve an item from a set. We can check if something exists, using `contains`, and you can iterate over all the elements in the set.

- HashSet is the best performing implementation of the Set interface. It stores its elements in a hash table. This allows the elements to be accessed by their hash code. The HashSet class offers constant time performance for the basic operations (add, remove, contains and size), assuming the hash function disperses the elements properly among the buckets. Iterating over this set requires time proportional to the sum of the HashSet instance's size (the number of elements) plus the "capacity" of the backing HashMap instance (the number of buckets). Thus, it's crucial not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
- HashSet doesn't maintain any order of elements. The elements are placed on the basis of their hashcode. HashSet permits the null elements. HashSet is the best approach for search operations. The initial default capacity of HashSet is 16, and the load factor is 0.75.
- The HashSet class is non-synchronized. It represents the implementation of a hash table, and it is not synchronized. It is not-thread safe. In the case of multithreaded environment, we must explicitly synchronize the HashSet. The fail-fast behavior of the HashSet is the same as the ArrayList. It is achieved by the iterators of the HashSet. It throws ConcurrentModificationException when one thread tries to modify the collection while another thread is iterating over it. However, it is not guaranteed that this behavior will be shown every time.
- HashSet internally uses HashMap to store its elements. The add() method of HashSet internally calls put() method on the backing HashMap instance with an element as key and constant Object named PRESENT as value. The PRESENT object is a test double object which is used to save memory. The following code snippet shows the add() method implementation of HashSet.

  ```java
  public boolean add(E e) {
      return map.put(e, PRESENT)==null;
  }
  ```

### Set Operations

- The Set interface provides methods for performing basic operations such as union, intersection, and subset. The following table lists some methods of the Set interface.

### Symmetric Operations

- The ability to evaluate sets and perform operations on them is one of the most important aspects of the Set interface. The Set interface provides methods for performing basic operations such as union, intersection, and subset.
- The **Union** of two sets is the set of elements which are in either of the sets. The union of two sets A and B is denoted by A ∪ B. The union of two sets can be performed using the `addAll()` method. The following code snippet shows how to perform the union of two sets.

  ```java
  Set<Integer> set1 = new HashSet<>();
  set1.addAll(Arrays.asList(new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));

  Set<Integer> set2 = new HashSet<>();
  set2.addAll(Arrays.asList(new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));

  Set<Integer> union = new HashSet<>(set1);
  union.addAll(set2);
  System.out.println("Union of the two Set");
  System.out.println(union);
  ```

- The **Intersection** of two sets is the set of elements which are common to both the sets. The intersection of two sets A and B is denoted by A ∩ B. The intersection of two sets can be performed using the `retainAll()` method. The following code snippet shows how to perform the intersection of two sets.

  ```java
  Set<Integer> set1 = new HashSet<>();
  set1.addAll(Arrays.asList(new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));

  Set<Integer> set2 = new HashSet<>();
  set2.addAll(Arrays.asList(new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));

  Set<Integer> intersection = new HashSet<>(set1);
  intersection.retainAll(set2);
  System.out.println("Intersection of the two Set");
  System.out.println(intersection);
  ```

### Asymmetric Operations

- The Set interface also provides methods for performing asymmetric operations such as subset, proper subset, and disjoint. The following table lists some methods of the Set interface.

- The **Difference** of two sets is the set of elements which are in the first set but not in the second set. The difference of two sets A and B is denoted by A - B. The difference of two sets can be performed using the `removeAll()` method. The following code snippet shows how to perform the difference of two sets.

  ```java
  Set<Integer> set1 = new HashSet<>();
  set1.addAll(Arrays.asList(new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));

  Set<Integer> set2 = new HashSet<>();
  set2.addAll(Arrays.asList(new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));

  Set<Integer> difference = new HashSet<>(set1);
  difference.removeAll(set2);
  System.out.println("Difference of the two Set");
  System.out.println(difference);
  ```

- The **Symmetric Difference** of two sets is the set of elements which are in either of the sets and not in their intersection. The symmetric difference of two sets A and B is denoted by A Δ B. The symmetric difference of two sets can be performed using the `removeAll()` method. The following code snippet shows how to perform the symmetric difference of two sets.

  ```java
  Set<Integer> set1 = new HashSet<>();
  set1.addAll(Arrays.asList(new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));

  Set<Integer> set2 = new HashSet<>();
  set2.addAll(Arrays.asList(new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));

  Set<Integer> difference = new HashSet<>(set1);
  difference.removeAll(set2);
  System.out.println("Difference of the two Set");
  System.out.println(difference);
  ```

---

## Ordered Sets

Ordered sets are sets that maintain their elements in a specific order. The elements are ordered using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used. The following code snippet shows how to create an ordered set. Here let's consider `LinkedHashSet` and `TreeSet`.

### LinkedHashSet

- The `LinkedHashSet` class is a subclass of `HashSet` that maintains a linked list of the entries in the set, in the order in which they were inserted. This allows insertion-order iteration over the set. That is, when cycling through a LinkedHashSet using an iterator, the elements will be returned in the order in which they were inserted. The following code snippet shows how to create a LinkedHashSet.

  ```java
  Set<String> linkedHashSet = new LinkedHashSet<>();
  ```

- Like HashSet, it provides constant-time `O(1)` performance for the basic operations (add, contains and size), assuming the hash function disperses the elements properly among the buckets. Performance is likely to be just slightly below that of HashSet, due to the added expense of maintaining the linked list, with one exception: Iteration over a LinkedHashSet requires time proportional to the size of the set, regardless of its capacity. Iteration over a HashSet is likely to be more expensive, requiring time proportional to its capacity.

### TreeSet

- The `TreeSet` class is a `NavigableSet` implementation based on a `TreeMap`. The elements are ordered using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used. The following code snippet shows how to create a TreeSet.

  ```java
  Set<String> treeSet = new TreeSet<>();
  ```

- The TreeSet class guarantees that the elements will be sorted. The set is sorted using the natural ordering of its elements, or by a Comparator provided at set creation time, depending on which constructor is used. This implementation provides guaranteed `O(log(n))` time cost for the basic operations (add, remove and contains). Java's internal implementation uses a balanced binary tree data structure called a Red-Black tree.
- Elements which implements `Comparable`(said to have a natural order sort, like Strings and numbers) can be elements of a TreeSet. If the elements don't implement Comparable, you must pass a `Comparator` to the constructor.

### Opreations on TreeSet

- The TreeSet class provides methods for performing basic operations.

- The **first()** method returns the first (lowest) element currently in this set.

  ```java
  System.out.println("First element of the set: " + treeSet.first()); // First element of the set: 0
  ```

- The **last()** method returns the last (highest) element currently in this set.

  ```java
  System.out.println("Last element of the set: " + treeSet.last()); // Last element of the set: 9
  ```

- The **pollFirst()** method retrieves and removes the first (lowest) element, or returns null if this set is empty.

  ```java
  System.out.println("First element of the set: " + treeSet.pollFirst()); // First element of the set: 0
  ```

- The **pollLast()** method retrieves and removes the last (highest) element, or returns null if this set is empty.

  ```java
  System.out.println("Last element of the set: " + treeSet.pollLast()); // Last element of the set: 9
  ```

- The **ceiling()** method returns the least element in this set greater than or equal to the given element, or null if there is no such element.

  ```java
  System.out.println("Ceiling value for 5: " + treeSet.ceiling(5)); // Ceiling value for 5: 5
  ```

- The **higher()** method returns the least element in this set strictly greater than the given element, or null if there is no such element.

  ```java
  System.out.println("Higher value for 5: " + treeSet.higher(5)); // Higher value for 5: 6
  ```

- The **floor()** method returns the greatest element in this set less than or equal to the given element, or null if there is no such element.

  ```java
  System.out.println("Floor value for 5: " + treeSet.floor(5)); // Floor value for 5: 5
  ```

- The **lower()** method returns the greatest element in this set strictly less than the given element, or null if there is no such element.

  ```java
  System.out.println("Lower value for 5: " + treeSet.lower(5)); // Lower value for 5: 4
  ```

- The **descendingSet()** method returns a reverse order view of the elements contained in this set. The descending set is backed by this set, so changes to the set are reflected in the descending set, and vice-versa. If either set is modified while an iteration over either set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.

  ```java
  System.out.println("Reverse order view of the set: " + treeSet.descendingSet()); // Reverse order view of the set: [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
  ```

- The **headSet()** method returns a view of the portion of this set whose elements are strictly less than toElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. HeadSet is exclusive by default. We can also pass the boolean value to the headSet() method. If the boolean value is true, it returns the subset including the toElement.

  ```java
  System.out.println("Head set of the set: " + treeSet.headSet(5)); // Head set of the set: [0, 1, 2, 3, 4]
  System.out.println("Head set of the set: " + treeSet.headSet(5, true)); // Head set of the set: [0, 1, 2, 3, 4, 5]
  ```

- The **tailSet()** method returns a view of the portion of this set whose elements are greater than or equal to fromElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. TailSet is inclusive by default. We can also pass the boolean value to the tailSet() method. If the boolean value is false, it returns the subset excluding the fromElement.

  ```java
  System.out.println("Tail set of the set: " + treeSet.tailSet(5)); // Tail set of the set: [5, 6, 7, 8, 9]
  System.out.println("Tail set of the set: " + treeSet.tailSet(5, false)); // Tail set of the set: [6, 7, 8, 9]
  ```

- The **subSet()** method returns a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive. (If fromElement and toElement are equal, the returned set is empty.) The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. We can also pass the boolean value to the subSet() method. If the boolean value is true, it returns the subset including the fromElement, otherwise returns the subset excluding the fromElement.

  ```java
  System.out.println("Sub set of the set: " + treeSet.subSet(3, 5)); // Sub set of the set: [3, 4]
  System.out.println("Sub set of the set: " + treeSet.subSet(3, false, 5, true)); // Sub set of the set: [4, 5]
  ```

### Why would you use a TreeSet?

- TreeSet is a sorted set implementation. It stores its elements in a Red-Black tree data structure. This allows elements to be retrieved quickly and they are always sorted in the natural order of the elements. The TreeSet class offers guaranteed `log(n)` time cost for the basic operations (add, remove and contains).
- If the number of elements is not large, or we want a colleaction that's sorted, and continuously kept sorted as we add or remove elements, then TreeSet is a good alternative to ArrayList.

---

## Maps Interface

- Even though maps are not technically a collection, they are still considered a part of the Java Collections Framework.
- A map is an object that maps keys to values. A map cannot contain duplicate keys: Each key can map to at most one value. It models the mathematical function abstraction. The two most common implementations of the Map interface are HashMap and TreeMap. The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls. TreeMap, which implements the Map interface, provides guaranteed `log(n)` time cost for the containsKey, get, put and remove operations.
- The Map interface provides three collection views, which allow a map's contents to be viewed as a set of keys, collection of values, or set of key-value mappings. The order of a map is defined as the order in which the iterators on the map's collection views return their elements. Some map implementations, like the TreeMap class, make specific guarantees as to their order; others, like the HashMap class, do not.
- The Map interface includes methods for basic operations (such as put, get, remove, containsKey, containsValue, size, and empty), bulk operations (such as putAll and clear), and collection views (such as keySet, entrySet, and values).

```java
public interface Map<K, V> // K - key, V - value
```

### Map characteristics

- A map cannot contain duplicate keys.
- Each key can map to at most one value.
- The order of a map is defined as the order in which the iterators on the map's collection views return their elements.

### HashMap

- The HashMap class is a subclass of AbstractMap that implements the Map interface. It stores the data in (Key, Value) pairs. To access a value in a HashMap, we must know its key. HashMap is known as HashMap because it uses a technique called Hashing. Hashing is a technique of converting a large String to small String that represents the same String. A shorter value helps in indexing and faster searches. HashSet also uses HashMap internally.
- HashMap is a part of Java's collection since Java 1.2. This class makes no guarantees as to the order of the map. It is similar to the Hashtable class except that it is unsynchronized and permits nulls(null values and null key). It is not an ordered collection which means it does not return the keys and values in the same order in which they have been inserted into the HashMap. It does not sort the stored keys and Values. HashMap is the implementation of Map, but it doesn't maintain any order.
- The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls. This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time. This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
- An instance of HashMap has two parameters that affect its performance: initial capacity and load factor. The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created. The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.
- As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs. Higher values decrease the space overhead but increase the lookup cost (reflected in most of the operations of the HashMap class, including get and put). The expected number of entries in the map and its load factor should be taken into account when setting its initial capacity, so as to minimize the number of rehash operations. If the initial capacity is greater than the maximum number of entries divided by the load factor, no rehash operations will ever occur.

```java
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable
```

```java
Map<String, Integer> map = new HashMap<>();
```

### Operations on Map

- The HashMap class provides methods for performing basic operations such as put, get, remove, containsKey, containsValue, size, and empty.

- The **put()** method is used to insert an entry in this map.

  ```java
  map.put(key, value);
  ```

- The **putIfAbsent()** method is used to insert an entry in this map only if the specified key is not already mapped to a value.

  ```java
  map.putIfAbsent(key, value);
  ```

- The **get()** method is used to return the value mapped by the specified key.

  ```java
  map.get(key);
  ```

- The **getOrDefault()** method is used to return the value mapped by the specified key. If the key is not found, it returns the default value.

  ```java
  map.getOrDefault(key, defaultValue);
  ```

- The **merge()** method is used to combine multiple mapped values for a key into a single value.

  ```java
  map.merge(key, value, BiFunction);
  ```

- The **compute()** method is used to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).

  ```java
  map.compute(key, BiFunction);
  ```

- The **computeIfAbsent()** method is used to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).

  ```java
  map.computeIfAbsent(key, Function);
  ```

- The **computeIfPresent()** method is used to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).

  ```java
  map.computeIfPresent(key, BiFunction);
  ```

- The **replaceAll()** method is used to replace all the entries of the specified map with the specified value.

  ```java
  map.replaceAll(BiFunction);
  ```

- The **replace()** method is used to replace the specified value for a specified key.

  ```java
  map.replace(key, value);
  ```

- The **replace()** method is used to replace the old value with specified new value for a specified key if and only if it is currently mapped to some value.

  ```java
  map.replace(key, oldValue, newValue);
  ```

- The **remove()** method is used to remove the entry for the specified key only if it is currently mapped to some value.

  ```java
  map.remove(key);
  ```

- The **remove()** method is used to remove the entry for the specified key only if it is currently mapped to a given value.

  ```java
  map.remove(key, value);
  ```

### The Map.Entry Interface

- The Map.Entry interface enables you to work with a map entry.

  ```java
  public interface Map<K, V> {
      public interface Entry<K, V> {
          K getKey();
          V getValue();
          V setValue(V value);
          boolean equals(Object o);
          int hashCode();
      }
  }
  ```

- The `entrySet()` method of the Map interface returns a collection-view of the map, whose elements are of this class. The only way to obtain a reference to a map entry is from the iterator of this collection-view. These Map.Entry objects are valid only for the duration of the iteration; more formally, the behavior of a map entry is undefined if the backing map has been modified after the entry was returned by the iterator, except through the setValue operation on the map entry.

  ```java
  Map<String, Integer> map = new HashMap<>();
  map.put("A", 1);
  map.put("B", 2);
  map.put("C", 3);
  map.put("D", 4);
  map.put("E", 5);

  Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
  for (Map.Entry<String, Integer> entry : entrySet) {
      System.out.print(entry.getKey() + " : " + entry.getValue() + ", ");
  }
  ```

  ```bash
  // Output
  A : 1, B : 2, C : 3, D : 4, E : 5,
  ```

- The `keySet()` method of the Map interface returns a set-view of the keys contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, and clear operations. It does not support the add or addAll operations.

  ```java
  Map<String, Integer> map = new HashMap<>();
  map.put("A", 1);
  map.put("B", 2);
  map.put("C", 3);
  map.put("D", 4);
  map.put("E", 5);

  Set<String> keySet = map.keySet();
  for (String key : keySet) {
      System.out.print(key + ", ");
  }
  ```

  ```bash
  // Output
  A, B, C, D, E,
  ```

- The `values()` method of the Map interface returns a collection-view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. If the map is modified while an iteration over the collection is in progress (except through the iterator's own remove operation), the results of the iteration are undefined. The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.

  ```java
  Map<String, Integer> map = new HashMap<>();
  map.put("A", 1);
  map.put("B", 2);
  map.put("C", 3);
  map.put("D", 4);
  map.put("E", 5);

  Collection<Integer> values = map.values();
  for (Integer value : values) {
      System.out.print(value + ", ");
  }
  ```

  ```bash
  // Output
  1, 2, 3, 4, 5,
  ```

### Map Views methods

- The Map interface provides methods for performing bulk operations on map entries and for performing various queries on maps.

- The **containsKey()** method is used to check whether a particular key is mapped into the map or not.

  ```java
  map.containsKey(key);
  ```

- The **containsValue()** method is used to check whether a particular value is mapped into the map or not.

  ```java
  map.containsValue(value);
  ```

- The **remove()** method is used to remove the mapping for the specified key in the map.

  ```java
  map.remove(key);
  ```

### LinkedHashMap

- The LinkedHashMap class is a subclass of HashMap that maintains a linked list of the entries in the map, in the order in which they were inserted. This allows insertion-order iteration over the map. That is, when cycling through a LinkedHashMap using an iterator, the elements will be returned in the order in which they were inserted. The following code snippet shows how to create a LinkedHashMap.

  ```java
  Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
  ```

- The LinkedHashMap class is a Hash table and linked list implementation of the Map interface, with predictable iteration order. It inherits HashMap class and implements the Map interface. The important points about Java LinkedHashMap class are:

  - A LinkedHashMap contains values based on the key.
  - It contains only unique elements.
  - It may have one null key and multiple null values.
  - It is same as HashMap instead maintains insertion order.
  - The LinkedHashMap class is non-synchronized.
  - It is not-thread safe. If multiple threads try to access it simultaneously, it must be synchronized externally.
  - It is less efficient than HashMap.
  - The LinkedHashMap class can be used as a base class for a custom map implementation.

- The LinkedHashMap class provides all of the optional Map operations, and permits null elements. Like HashMap, it provides constant-time performance for the basic operations (add, contains and remove), assuming the hash function disperses elements properly among the buckets. Performance is likely to be just slightly below that of HashMap, due to the added expense of maintaining the linked list, with one exception: Iteration over the collection-views of a LinkedHashMap requires time proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more expensive, requiring time proportional to its capacity.

  ```java
  public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V>
  ```

### TreeMap

- The TreeMap class is a Red-Black tree based implementation of the Map interface. This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations. Algorithms are adaptations of those in Cormen, Leiserson, and Rivest's Introduction to Algorithms.
- The TreeMap class is a member of Java Collections Framework. It extends AbstractMap class and implements NavigableMap interface. It contains only unique elements. It cannot have null key but can have multiple null values. It is same as HashMap instead maintains ascending order(Sorted using the natural order of its key). The important points about Java TreeMap class are:

  - A TreeMap contains values based on the key. It implements the NavigableMap interface and extends AbstractMap class.
  - It contains only unique elements.
  - It cannot have null key but can have multiple null values.
  - It is same as HashMap instead maintains ascending order(Sorted using the natural order of its key).
  - The TreeMap class is non-synchronized.
  - It is not-thread safe. If multiple threads try to access it simultaneously, it must be synchronized externally.
  - It is less efficient than HashMap.
  - The TreeMap class can be used as a base class for a custom map implementation.

- The TreeMap class provides all of the optional Map operations, and permits null elements. Like the HashMap class, it provides constant-time performance for the basic operations (add, contains and remove), assuming the hash function disperses elements properly among the buckets. Performance is likely to be just slightly below that of the HashMap class, due to the added expense of maintaining the tree, with one exception: Iteration over the collection-views of a TreeMap requires time proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more expensive, requiring time proportional to its capacity.

  ```java
  public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, Serializable
  ```

  ```java
  Map<String, Integer> treeMap = new TreeMap<>();
  ```

### Operations on TreeMap

- The TreeMap class provides methods for performing basic operations such as put, get, remove, containsKey, containsValue, size, and empty.

- The **ceilingEntry()** method is used to return a key-value mapping associated with the least key greater than or equal to the given key, or null if there is no such key.

  ```java
  treeMap.ceilingEntry(key);
  ```

- The **floorEntry()** method is used to return a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.

  ```java
  treeMap.floorEntry(key);
  ```

- The **higherEntry()** method is used to return a key-value mapping associated with the least key strictly greater than the given key, or null if there is no such key.

  ```java
  treeMap.higherEntry(key);
  ```

- The **lowerEntry()** method is used to return a key-value mapping associated with the greatest key strictly less than the given key, or null if there is no such key.

  ```java
  treeMap.lowerEntry(key);
  ```

- The **firstEntry()** method is used to return a key-value mapping associated with the least key in this map, or null if the map is empty.

  ```java
  treeMap.firstEntry();
  ```

- The **lastEntry()** method is used to return a key-value mapping associated with the greatest key in this map, or null if the map is empty.

  ```java
  treeMap.lastEntry();
  ```

- The **firstKey()** method is used to return the first (lowest) key currently in this map.

  ```java
  treeMap.firstKey();
  ```

- The **lastKey()** method is used to return the last (highest) key currently in this map.

  ```java
  treeMap.lastKey();
  ```

- The **pollFirstEntry()** method is used to remove and return a key-value mapping associated with the least key in this map, or null if the map is empty.

  ```java
  treeMap.pollFirstEntry();
  ```

- The **pollLastEntry()** method is used to remove and return a key-value mapping associated with the greatest key in this map, or null if the map is empty.

  ```java
  treeMap.pollLastEntry();
  ```

### TreeMap View collections

| View collection method                                                                                       | Description                                                                                                                                                                                                                                                                                              |
| ------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `entrySet()`, `keySet()`, `values()`                                                                         | Provides views of mappings, keys, and values respectively. These are views of the map, so changes to the map are reflected in the collection, and vice-versa.                                                                                                                                            |
| `descendingKeySet()`, `descendingKeyMap()`                                                                   | Provides reversed order key set or map, reversed by the key values.                                                                                                                                                                                                                                      |
| `headMap(K key)`, `headMap(K key, boolean inclusive)`, `tailMap(K key)`, `tailMap(K key, boolean inclusive)` | Provides a view of either the first or last parts of the map, divided by the specified key. The **head** map is by default **EXCLUSIVE** of all the elements higher or equal to the specified key. The **tail** map is by default **INCLUSIVE** of all the elements lower or equal to the specified key. |
| `subMap(K fromKey, K toKey)`, `subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)`       | Provides a view of the map that is a subset of the map, divided by the specified keys. The **from** map is by default **EXCLUSIVE** of the element equal to the specified key. The **to** map is by default **INCLUSIVE** of the element equal to the specified key.                                     |

---

## EnumSet and EnumMap

### EnumSet

- The EnumSet class is a specialized Set implementation for use with enum types. All of the elements in an enum set must come from a single enum type that is specified, explicitly or implicitly, when the set is created. Enum sets are represented internally as bit vectors. This representation is extremely compact and efficient. The space and time performance of this class should be good enough to allow its use as a high-quality, typesafe alternative to traditional int-based "bit flags." Even bulk operations (such as containsAll and retainAll) should run very quickly if their argument is also an enum set.
- The EnumSet class is a member of the Java Collections Framework and implements the Set interface. It extends AbstractSet class and implements the Set interface. The important points about Java EnumSet class are:

  - Java EnumSet class is the specialized Set implementation for use with enum types.
  - Java EnumSet class internally implements the Set interface.
  - Java EnumSet class is a member of the Java Collections Framework.
  - Java EnumSet class is not synchronized.
  - Java EnumSet class is not-thread safe. If multiple threads access an enum set concurrently, and at least one of the threads modifies the set, it must be synchronized externally.
  - Java EnumSet class has one constructor and it is private. We can create an EnumSet by using the static factory method of(). We can also create a copy of an existing EnumSet by using the copyOf() method.

- The EnumSet class provides methods for performing basic operations such as add, remove, contains, size, or empty. The following code snippet shows how to create an EnumSet.

  ```java
  public class EnumSet<E extends Enum<E>> extends AbstractSet<E> implements Cloneable, Serializable
  ```

  ```java
  public enum Days {
      SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
  }

  EnumSet<Days> set = EnumSet.of(Days.TUESDAY, Days.WEDNESDAY);
  ```

### EnumMap

- The EnumMap class is a specialized Map implementation for use with enum type keys. All of the keys in an enum map must come from a single enum type that is specified, explicitly or implicitly, when the map is created. Enum maps are represented internally as arrays. This representation is extremely compact and efficient. The space and time performance of this class should be good enough to allow its use as a high-quality, typesafe alternative to traditional int-based "enum indexes."
- The EnumMap class is a member of the Java Collections Framework and implements the Map interface. It extends AbstractMap class and implements the Map interface. The important points about Java EnumMap class are:

  - Java EnumMap class is the specialized Map implementation for use with enum keys.
  - Java EnumMap class internally implements the Map interface.
  - Java EnumMap class is a member of the Java Collections Framework.
  - Java EnumMap class is not synchronized.
  - Java EnumMap class is not-thread safe. If multiple threads access an enum map concurrently, and at least one of the threads modifies the map, it must be synchronized externally.
  - Java EnumMap class has one constructor and it is private. We can create an EnumMap by using the static factory method of(). We can also create a copy of an existing EnumMap by using the copyOf() method.

- The EnumMap class provides methods for performing basic operations such as put, get, remove, containsKey, containsValue, size, and empty. The following code snippet shows how to create an EnumMap.

  ```java
  public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements Serializable, Cloneable
  ```

  ```java
  public enum Days {
      SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
  }

  EnumMap<Days, String> map = new EnumMap<>(Days.class);
  map.put(Days.MONDAY, "1");
  map.put(Days.TUESDAY, "2");
  map.put(Days.WEDNESDAY, "3");
  map.put(Days.THURSDAY, "4");
  map.put(Days.FRIDAY, "5");
  map.put(Days.SATURDAY, "6");
  map.put(Days.SUNDAY, "7");
  ```

# [Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

- A stream is a sequence of data elements made available over time. It is particularly useful for tasks that may benefit from being asynchronous, including tasks such as I/O processing or reading from a file, and as such is important for programming in the reactive style.

## Table of Contents

- [Introduction to Streams](./src/com/rishab/streamIntro/Main.java)
- [Intermediate Operations](./src/com/rishab/streamsIntermediate/Main.java)
- [Terminal Operations](./src/com/rishab/streamTerminal/Main.java)
- [Collectors](./src/com/rishab/streamingStudents/MainCollect.java)
- [Optional](./src/com/rishab/streamingStudents/MainOptional.java)
- [Terminal Operations that returns Optional](./src/com/rishab/streamingStudents/MainTerminalOptional.java)
- [Stream to Maps](./src/com/rishab/streamingStudents/MainMapping.java)

In Java 8, the Stream interface lets you process data in a declarative way. For example, consider the following code that finds the sum of all calories in a list of dishes:

```java
  class Dish {
      private final String name;
      private final boolean vegetarian;
      private final int calories;
      private final Type type;
      // constructors, getters

      public Dish(String name, boolean vegetarian, int calories, Type type) {
          this.name = name;
          this.vegetarian = vegetarian;
          this.calories = calories;
          this.type = type;
      }

      public int getCalories() { return calories; }

      public enum Type { MEAT, FISH, OTHER }
  }

  public class Streams {
      public static void main(String[] args) {
          List<Dish> menu = Arrays.asList(
              new Dish("pork", false, 800, Dish.Type.MEAT),
              new Dish("beef", false, 700, Dish.Type.MEAT),
              new Dish("chicken", false, 400, Dish.Type.MEAT),
              new Dish("french fries", true, 530, Dish.Type.OTHER),
              new Dish("rice", true, 350, Dish.Type.OTHER),
              new Dish("season fruit", true, 120, Dish.Type.OTHER),
              new Dish("pizza", true, 550, Dish.Type.OTHER),
              new Dish("prawns", false, 400, Dish.Type.FISH),
              new Dish("salmon", false, 450, Dish.Type.FISH)
          );

          int calories = menu.stream()
              .map(Dish::getCalories)
              .reduce(0, Integer::sum);
      }
  }
```

- Stream is different from a collection in that it doesn’t store any data. It carries values from a source through a pipeline of computational operations.
- A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel); followed by zero or more intermediate operations such as `Stream.filter` or `Stream.map`; and a terminal operation such as `Stream.forEach` or `Stream.reduce`.
- Streams are **lazy**; computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.
- Collections and streams, while related, have different goals. Collections are primarily concerned with the efficient management of, and access to, their elements. By contrast, streams do not provide a means to directly access or manipulate their elements, and are instead concerned with declarative describing their source and the computational operations which will be performed in aggregate on that source.
- A stream pipeline can be viewed as a query on the stream source. Unless the source was explicitly designed for concurrent modification (such as a ConcurrentHashMap), unpredictable or erroneous behavior may result from modifying the stream source while it is being queried.
- Most stream operations accept parameters that describe user-specified behavior, such as the lambda expression `m -> m.getWeight()`. To preserve correct behavior, these behavioral parameters:
  - must be non-interfering (they do not modify the stream source); and
  - in most cases must be stateless (their result should not depend on any state that might change during execution of the stream pipeline).

### Why use streams?

- First, they make the code to process data uniform, concise and repeatable, in ways that feel similar to a database's query language.
- Second, when working with large collections, streams can offer a significant performance benefit. The stream library automatically partitions the data and runs many of the filter/map/reduce operations in parallel, on all the cores of your multicore processor. This means that you don't have to write any multithreaded code to benefit from multicore architectures.

_Note:_ We can't reuse a stream. Once it's been used, we have to create a new one. Once we invoke a terminal operation on a stream, it is closed.

---

## Structure of the Stream Pipeline

- A stream pipeline consists of a source, zero or more intermediate operations, and a terminal operation. Stream pipelines ends in a **terminal operation**, which produces a result (such as `forEach` or `reduce`) or side effect (such as `forEach` or `peek`) and consumes the stream. A stream pipeline can be executed sequentially or in parallel.

```java
List<String> names = menu.stream()
    .filter(d -> d.getCalories() > 300)
    .map(Dish::getName)
    .limit(3)
    .collect(toList());
```

---

## Stream Methods

### Creating Streams

| Method                                                               | Finite | Infinite | Description                                                                                                                                                                                            |
| -------------------------------------------------------------------- | ------ | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `Collection.stream()`                                                | Yes    | No       | Returns a sequential stream considering collection as its source.                                                                                                                                      |
| `Arrays.stream(T[] t)`                                               | Yes    | No       | Returns a sequential stream with the specified array as its source.                                                                                                                                    |
| `Stream.of(T... t)`                                                  | Yes    | No       | Returns a sequential ordered stream whose elements are the specified values.                                                                                                                           |
| `Stream.iterate(T seed, UnaryOperator<T> f)`                         | No     | Yes      | Returns an infinite sequential ordered Stream produced by iterative application of a function `f` to an initial element `seed`, producing a Stream consisting of `seed`, `f(seed)`, `f(f(seed))`, etc. |
| `Stream.iterate(T seed, Predicate<? super T> p, UnaryOperator<T> f)` | Yes    |          | Returns, if this stream is ordered, a stream consisting of the elements of this stream that match the given predicate.                                                                                 |
| `Stream.generate(Supplier<T> s)`                                     | No     | Yes      | Returns an infinite sequential unordered stream where each element is generated by the provided `Supplier`.                                                                                            |
| `IntStream.range(int startInclusive, int endExclusive)`              | Yes    | No       | Returns a sequential ordered `IntStream` from `startInclusive` (inclusive) to `endExclusive` (exclusive) by an incremental step of `1`.                                                                |
| `IntStream.rangeClosed(int startInclusive, int endInclusive)`        | Yes    | No       | Returns a sequential ordered `IntStream` from `startInclusive` (inclusive) to `endInclusive` (inclusive) by an incremental step of `1`.                                                                |

---

## Stream operations

- Stream operations are divided into intermediate and terminal operations, and are combined to form stream pipelines. A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel); followed by zero or more intermediate operations such as `Stream.filter` or `Stream.map`; and a terminal operation such as `Stream.forEach` or `Stream.reduce`.

### Intermediate operations

- Intermediate operations return a new stream. They are always lazy; executing an intermediate operation such as `filter()` does not perform any filtering, but instead creates a new stream that, when traversed, contains the elements of the initial stream that match the given predicate. Traversal of the pipeline source does not begin until the terminal operation of the pipeline is executed.

  | Intermediate operation                         | Returns     | Description                                                                                                                                                                    |
  | ---------------------------------------------- | ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
  | `distinct()`                                   | `Stream<T>` | Returns a stream consisting of the distinct elements (according to `Object.equals(Object)`) of this stream.                                                                    |
  | `filter(Predicate<? super T> predicate)`       | `Stream<T>` | Returns a stream consisting of the elements of this stream that match the given predicate.                                                                                     |
  | `takeWhile(Predicate<? super T> predicate)`    | `Stream<T>` | Returns, if this stream is ordered, a stream consisting of the longest prefix of elements taken from this stream that match the given predicate.                               |
  | `dropWhile(Predicate<? super T> predicate)`    | `Stream<T>` | Returns, if this stream is ordered, a stream consisting of the remaining elements of this stream after dropping the longest prefix of elements that match the given predicate. |
  | `limit(long maxSize)`                          | `Stream<T>` | Returns a stream consisting of the elements of this stream, truncated to be no longer than `maxSize` in length.                                                                |
  | `map(Function<? super T, ? extends R> mapper)` | `Stream<R>` | Returns a stream consisting of the results of applying the given function to the elements of this stream.                                                                      |
  | `peek(Consumer<? super T> action)`             | `Stream<T>` | Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.    |
  | `skip(long n)`                                 | `Stream<T>` | Returns a stream consisting of the remaining elements of this stream after discarding the first `n` elements of the stream.                                                    |
  | `sorted()`                                     | `Stream<T>` | Returns a stream consisting of the elements of this stream, sorted according to natural order.                                                                                 |
  | `sorted(Comparator<? super T> comparator)`     | `Stream<T>` | Returns a stream consisting of the elements of this stream, sorted according to the provided `Comparator`.                                                                     |

  | Special Primitive Stream | Mapping from reference type to primitive          | Mapping from primitive type to reference type               |
  | ------------------------ | ------------------------------------------------- | ----------------------------------------------------------- |
  | `IntStream`              | `mapToInt(ToInFunction<? super T> mapper)`        | `mapToObj(IntFunction<? extends T> mapper)` or `boxed()`    |
  | `LongStream`             | `mapToLong(ToLongFunction<? super T> mapper)`     | `mapToObj(LongFunction<? extends T> mapper)` or `boxed()`   |
  | `DoubleStream`           | `mapToDouble(ToDoubleFunction<? super T> mapper)` | `mapToObj(DoubleFunction<? extends T> mapper)` or `boxed()` |

_Note:_ The Java API designers designed the Stream API to ley you process data in a declarative way, much like a structured query language (SQL) for Java. This is a very different approach from the imperative style you use when programming with collections.

### Terminal operations

- Terminal operations, such as `Stream.forEach` or `IntStream.sum`, may traverse the stream to produce a result or a side effect. After the terminal operation is performed, the stream pipeline is considered consumed, and can no longer be used; if you need to traverse the same data source again, you must return to the data source to get a new stream. In almost all cases, terminal operations are eager, completing their traversal of the data source and processing of the pipeline before returning. Only the terminal operations `iterator()` and `spliterator()` are not; these are provided as an "escape hatch" to enable arbitrary client-controlled pipeline traversals in the event that the existing operations are not sufficient to the task.

  | Matching and searching | Transformations and Type reductions | Statistical (Numeric) Reductions | Processing streams |
  | ---------------------- | ----------------------------------- | -------------------------------- | ------------------ |
  | allMatch               | collect                             | average²                         | forEach            |
  | anyMatch               | reduce                              | count                            | forEachOrdered     |
  | findAny¹               | toArray                             | max¹                             |                    |
  | findFirst¹             | toList                              | min¹                             |                    |
  | noneMatch              |                                     | sum²                             |                    |
  |                        |                                     | summaryStatistics²               |                    |

  | Terminal operation                          | Returns                | Description                                                                                                                          |
  | ------------------------------------------- | ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
  | `count()`                                   | `long`                 | Returns the count of elements in this stream.                                                                                        |
  | `max(Comparator<? super T> comparator)`     | `Optional<T>`          | Returns the maximum element of this stream according to the provided `Comparator`.                                                   |
  | `min(Comparator<? super T> comparator)`     | `Optional<T>`          | Returns the minimum element of this stream according to the provided `Comparator`.                                                   |
  | `average()`                                 | `OptionalDouble`       | Returns an `OptionalDouble` describing the arithmetic mean of elements of this stream, or an empty optional if this stream is empty. |
  | `sum()`                                     | `int`                  | Returns the sum of elements in this stream.                                                                                          |
  | `summaryStatistics()`                       | `IntSummaryStatistics` | Returns a `IntSummaryStatistics` describing various summary data about the elements of this stream.                                  |
  | `allMatch(Predicate<? super T> predicate)`  | `boolean`              | Returns whether all elements of this stream match the provided predicate.                                                            |
  | `anyMatch(Predicate<? super T> predicate)`  | `boolean`              | Returns whether any elements of this stream match the provided predicate.                                                            |
  | `noneMatch(Predicate<? super T> predicate)` | `boolean`              | Returns whether no elements of this stream match the provided predicate.                                                             |

  - **Reduction operations** combine the content of a stream in some way to return a single value. The most common one is `reduce`, but there are also `count`, `min`, `max`, `sum`, and `average`.

    | Terminal Operation                                                                               | Return Type   | Description                                                                                |
    | ------------------------------------------------------------------------------------------------ | ------------- | ------------------------------------------------------------------------------------------ |
    | `collect(Collector<? super T, A, R> collector)`                                                  | `R`           | Performs a mutable reduction operation on the elements of this stream using a `Collector`. |
    | `collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)` | `R`           | Performs a mutable reduction operation on the elements of this stream.                     |
    | `reduce(BinaryOperator<T> accumulator)`                                                          | `Optional<T>` | Performs a reduction on the elements of this stream, using the provided identity value.    |
    | `reduce(T identity, BinaryOperator<T> accumulator)`                                              | `T`           | Performs a reduction on the elements of this stream, using an associative accumulation.    |
    | `reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)`        | `U`           | Performs a reduction on the elements of this stream, using the provided identity,          |
    | `toArray()`                                                                                      | `Object[]`    | Returns an array containing the elements of this stream.                                   |
    | `toArray(IntFunction<A[]> generator)`                                                            | `A[]`         | Returns an array containing the elements of this stream, using the provided generator.     |
    | `toList()`                                                                                       | `List<T>`     | Returns a list containing the elements of this stream.                                     |

- Return an Optional instance. Optional is a container class to represent the existence or absence of a value. It's a simple container for a value which may be null or non-null. Think of a method which may return a non-null result but sometimes return nothing. Instead of returning null, you return an Optional in Java 8.

- Reduction operations is a special type of terminal operation. They reduce the stream to a single summary element by repeated application of a combining operation, such as `Stream.reduce` or `IntStream.sum`.
- The result can be a primitive type, like a `int` or a `double`, int the case of count operations, or an object, like a `String` or a `List`.

---

## Collectors

- A collector is a special type of reduction called a mutable reduction. It is a function that takes elements from a stream and puts them into some mutable data structure, such as a `Collection`, `Map`, or `String`. The `collect` method is a special type of reduction called a mutable reduction. It is a function that takes elements from a stream and puts them into some mutable data structure, such as a `Collection`, `Map`, or `String`. The `collect` method takes a `Collector` parameter that specifies the way the reduction is to be performed. Collectors are used to combine the stream elements into a single value, e.g., a collection or a primitive. Collectors can be used to return a list or a string.

  | Collector factory method                                                                                                                                   | Description                                                                                                                                                                   |
  | ---------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
  | `toList()`                                                                                                                                                 | Returns a `Collector` that accumulates the input elements into a new `List`.                                                                                                  |
  | `toSet()`                                                                                                                                                  | Returns a `Collector` that accumulates the input elements into a new `Set`.                                                                                                   |
  | `toCollection(Supplier<C> collectionFactory)`                                                                                                              | Returns a `Collector` that accumulates the input elements into a new `Collection`, in encounter order.                                                                        |
  | `toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)`                                                          | Returns a `Collector` that accumulates elements into a `Map` whose keys and values are the result of applying the provided mapping functions to the input elements.           |
  | `toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction)`                         | Returns a `Collector` that accumulates elements into a `Map` whose keys and values are the result of applying the provided mapping functions to the input elements.           |
  | `toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapFactory)` | Returns a `Collector` that accumulates elements into a `Map` whose keys and values are the result of applying the provided mapping functions to the input elements.           |
  | `toConcurrentMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper)`                                                | Returns a `Collector` that accumulates elements into a `ConcurrentMap` whose keys and values are the result of applying the provided mapping functions to the input elements. |

- The `Collectors` class provides implementations of the most common collectors. The `Collectors` class provides static factory methods for all the collectors. For example, to collect the dishes in a menu into a list, you can write:

  ```java
  List<Dish> dishes = menu.stream().collect(toList());
  ```

---

## Optional class

- The `Optional` is a generic class is a container class to represent the existence or absence of a value. It's a simple container for a value which may be null or non-null. Think of a method which may return a non-null result but sometimes return nothing. Instead of returning null, you return an Optional in Java 8.

  | Method                                                              | Description                                                                                                                                                                        |
  | ------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
  | `of(T value)`                                                       | Returns an `Optional` describing the specified value, if non-null, otherwise returns an empty `Optional`.                                                                          |
  | `empty()`                                                           | Returns an empty `Optional` instance.                                                                                                                                              |
  | `isPresent()`                                                       | Returns `true` if there is a value present, otherwise `false`.                                                                                                                     |
  | `ifPresent(Consumer<? super T> consumer)`                           | If a value is present, invoke the specified consumer with the value, otherwise do nothing.                                                                                         |
  | `ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)` | If a value is present, performs the given action with the value, otherwise performs the given empty-based action.                                                                  |
  | `orElse(T other)`                                                   | Returns the value if present, otherwise returns `other`.                                                                                                                           |
  | `orElseGet(Supplier<? extends T> supplier)`                         | Returns the value if present, otherwise returns the result produced by the                                                                                                         |
  | `ofNullable(T value)`                                               | Returns an `Optional` describing the specified value, if non-null, otherwise returns an empty `Optional`.                                                                          |
  | `map(Function<? super T, ? extends U> mapper)`                      | If a value is present, apply the provided mapping function to it, and if the result is non-null, return an `Optional` describing the result. Otherwise return an empty `Optional`. |
  | `filter(Predicate<? super T> predicate)`                            | If a value is present, and the value matches the given predicate, return an `Optional` describing the value, otherwise return an empty `Optional`.                                 |
  | `flatMap(Function<? super T, Optional<U>> mapper)`                  | If a value is present, apply the provided `Optional`-bearing mapping function to it, return that result, otherwise return an empty `Optional`.                                     |
  | `or(Supplier<? extends Optional<? extends T>> supplier)`            | If a value is present, returns an `Optional` describing the value, otherwise returns an `Optional` produced by the supplying function.                                             |
  | `or(T value)`                                                       | If a value is present, returns an `Optional` describing the value, otherwise returns an `Optional` produced by the supplying function.                                             |

- Terminal Operations that returns Optional

  | Terminal Operation                      | Return Type      | Description                                                                                                                          |
  | --------------------------------------- | ---------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
  | `average()`                             | `OptionalDouble` | Returns an `OptionalDouble` describing the arithmetic mean of elements of this stream, or an empty optional if this stream is empty. |
  | `findAny()`                             | `Optional<T>`    | Returns an `Optional` describing some element of the stream, or an empty `Optional` if the stream is empty.                          |
  | `findFirst()`                           | `Optional<T>`    | Returns an `Optional` describing the first element of this stream, or an empty `Optional` if the stream is empty.                    |
  | `max(Comparator<? super T> comparator)` | `Optional<T>`    | Returns an `Optional` describing the maximum element of this stream, or an empty `Optional` if the stream is empty.                  |
  | `min(Comparator<? super T> comparator)` | `Optional<T>`    | Returns an `Optional` describing the minimum element of this stream, or an empty `Optional` if the stream is empty.                  |
  | `reduce(BinaryOperator<T> accumulator)` | `Optional<T>`    | Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function.     |

_Remainder:_ The downside of using Optional is that it can encourage overuse of null values. It's better to use Optional as a return type of method, instead of using it as a field. It's also not a good idea to use Optional in collections or arrays.

---

## Stream to Maps

- The `Collectors` class provides a method called `toMap` that you can use to collect a stream into a map. The `toMap` method takes two functions as arguments: one to extract the key from a stream element, and another to extract the value from a stream element. The `toMap` method returns a collector that collects the stream elements into a map.

  ```java
  Map<String, Dish> dishMap = menu.stream()
      .collect(toMap(Dish::getName, Function.identity()));
  ```

- In addition to `toMap`, the `Collectors` class provides several other methods that you can use to collect a stream into a map. The `Collectors` class provides a method called `toMap` that you can use to collect a stream into a map. The `toMap` method takes two functions as arguments: one to extract the key from a stream element, and another to extract the value from a stream element. The `toMap` method returns a collector that collects the stream elements into a map.

  | Method                                                                                           | Description                                                                                                                                                                                                                                                                 |
  | ------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
  | `groupingBy(Function<? super T, ? extends K> classifier)`                                        | Returns a `Collector` implementing a "group by" operation on input elements of type `T`, grouping elements according to a classification function, and returning the results in a `Map`.                                                                                    |
  | `groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)` | Returns a `Collector` implementing a cascaded "group by" operation on input elements of type `T`, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key to map the key to a result. |
  | `partitioningBy(Predicate<? super T> predicate)`                                                 | Returns a `Collector` implementing a "group by" operation on input elements of type `T`, grouping elements according to a classification function, and returning the results in a `Map`.                                                                                    |
  | `partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream)`          | Returns a `Collector` implementing a cascaded "group by" operation on input elements of type `T`, grouping elements according to a classification function, and then performing a reduction operation on the values associated with a given key to map the key to a result. |
  | `joining()`                                                                                      | Returns a `Collector` that concatenates the input elements, separated by the specified delimiter, in encounter order.                                                                                                                                                       |
  | `joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)`                      | Returns a `Collector` that concatenates the input elements, separated by the specified delimiter, with the specified prefix and suffix, in encounter order.                                                                                                                 |
  | `summarizingInt(ToIntFunction<? super T> mapper)`                                                | Returns a `Collector` that produces the arithmetic mean of an integer-valued function applied to the input elements.                                                                                                                                                        |
  | `reducing(BinaryOperator<T> op)`                                                                 | Returns a `Collector` that performs a reduction of its input elements under a specified `BinaryOperator` using the provided identity.                                                                                                                                       |
  | `counting()`                                                                                     | Returns a `Collector` accepting elements of type `T` that counts the number of input elements.                                                                                                                                                                              |

- For more information on collectors, see the [Collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#method.summary) class.

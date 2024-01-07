# Introduction to Concurrency and Threads

- **Process** is a unit of execution that has its own memory space. Most instances of a JVM run as a process. Each
  process has its own memory space, known as the _heap space_. The heap space isn't shared between
  processes/applications, they are isolated from each other.

- **Thread** is a unit of execution within a process. Each process can have multiple threads running in parallel.
  In Java each application has at least one thread, the _main thread_. Threads share the same memory space, the heap
  space, and can access the same variables and objects. This is why we need to be careful when using threads, as
  multiple threads can access the same variables and objects at the same time, which can lead to unexpected results. In
  addition to the heap space, each thread has its own memory space, known as the _stack space_. The stack space is used
  to store local variables and method calls. Each thread has its own stack space, which is not shared between threads.

## Table of Contents

- [Introduction to Threads](./src/com/rishab/threads/Main.java)
- [Interacting with Running Threads](./src/com/rishab/runningThreads/Main.java)
- [Multiple Threads and Memory Sharing](./src/com/rishab/multipleThreads/Main.java)
- [Synchronization](./src/com/rishab/synchronization/Main.java)
- [Deadlocks, wait(), notify() and notifyAll()](./src/com/rishab/consumerProducer/Main.java)
- [ExecutorService, Thread Pools](./src/com/rishab/executors/Main.java)
- [Scheduling Tasks with a ScheduledExecutorService](./src/com/rishab/schedulingTasks/Main.java)
- [Work Stealing Thread Pool/ForkJoinPool](./src/com/rishab/parallelProcesses/Main.java)
- [Parallel Streams](./src/com/rishab/parallelStreams/Main.java)
- [Problems in Multi-Threaded Applications](./src/com/rishab/threadProblems/Main.java)

## Concurrency

Concurrency is the ability to run multiple tasks at the same time. In Java, concurrency is achieved by using threads.
Each thread runs in parallel, and can execute different tasks at the same time. For example, a thread can be used to
download a file, while another thread can be used to process the file. This is useful when we want to perform multiple
tasks at the same time, instead of waiting for one task to finish before starting the next one.

## Java Threads

- Threads are the fundamental building blocks to support concurrency in Java.

- In Java, threads are represented by the `Thread` class. To create a thread, we can extend the `Thread` class and
  override the `run()` method. The `run()` method contains the code that will be executed by the thread. To start the
  thread, we need to call the `start()` method. The `start()` method will call the `run()` method, which will execute
  the code in the thread. The `start()` method will also create a new stack space for the thread, which will be used to
  store local variables and method calls.

```java
public class MyThread extends Thread {

    @Override
    public void run() {
        // Code to be executed by the thread
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
```

- When we see the method implementation of `start()` method, we can see that it calls the `start0()` method, which is
  a native method.

  ```java
  private native void start0();
  ```

- The native modifier indicates that the method is implemented in a language other than Java. The `start0()` method is
  implemented in C++, and it is used to create a new thread and call the `run()` method.

- The `java.util.Thread` class implements the `Runnable` interface. The `Runnable` interface contains a single method,
  `run()`, which is used to execute the code in the thread. To create a thread, we can implement the `Runnable`
  interface and override the `run()` method. To start the thread, we need to create a new `Thread` object and pass
  the `Runnable` object to the constructor. The `Thread` object will call the `run()` method, which will execute the
  code in the thread. The `Thread` object will also create a new stack space for the thread, which will be used to
  store local variables and method calls.

```java
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        // Code to be executed by the thread
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
```

### Thread Priorities

- Threads can have different priorities. The priority of a thread is used by the JVM to decide which thread should be
  executed first. The priority of a thread can be set using the `setPriority()` method. The priority of a thread can be
  retrieved using the `getPriority()` method. The priority of a thread can be set to a value between 1 and 10, where 1
  (Thread.MIN_PRIORITY) is the lowest priority, and 10 (Thread.MAX_PRIORITY) is the highest priority. The default
  priority of a thread is 5 (Thread.NORM_PRIORITY).

```java
public class MyThread extends Thread {

    @Override
    public void run() {
        // Code to be executed by the thread
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
}
```

---

### Thread States and Thread.State

- Threads can be in different states. The state of a thread can be retrieved using the `getState()` method. The
  `Thread.State` enum contains the following states:

  - `NEW`: The thread has been created, but it hasn't been started yet.
  - `RUNNABLE`: The thread is running.
  - `BLOCKED`: The thread is blocked, waiting for a monitor lock.
  - `WAITING`: The thread is waiting.
  - `TIMED_WAITING`: The thread is waiting for a specified amount of time.
  - `TERMINATED`: The thread has finished executing.

- The `getState()` method returns the current state of the thread. The `getState()` method can be used to check if a
  thread is running, waiting, or terminated.

- The `Thread.State` enum contains the following methods:

  - `boolean isAlive()`: Returns true if the thread is alive, otherwise false.
  - `boolean isInterrupted()`: Returns true if the thread has been interrupted, otherwise false.
  - `boolean isDaemon()`: Returns true if the thread is a daemon thread, otherwise false.

---

### Thread accessing shared resources

- Each thread has its own stack space, which is used to store local variables and method calls. Each thread also has
  access to the heap space, which is used to store objects and variables. The heap space is shared between threads, so
  multiple threads can access the same objects and variables at the same time. This can lead to unexpected results, as
  multiple threads can modify the same objects and variables at the same time. To prevent this, we need to use
  synchronization to ensure that only one thread can access the shared objects and variables at a time.

---

### Thread-Safe

- An object or a block of code is thread-safe if it can be used by multiple threads at the same time without any
  problems. For example, the `java.lang.StringBuilder` class is thread-safe, so it can be used by multiple threads at
  the same time without any problems. The `java.lang.StringBuffer` class is also thread-safe, so it can be used by
  multiple threads at the same time without any problems. The `java.lang.String` class is not thread-safe.

- The Atomic operations and immutable objects are examples of thread-safe code.

---

### Memory Consistency Errors

- Memory consistency errors occur when multiple threads access the same objects and variables at the same time. This
  can lead to unexpected results, as multiple threads can modify the same objects and variables at the same time. To
  prevent this, we need to use synchronization to ensure that only one thread can access the shared objects and
  variables at a time.

- The `java.util.concurrent.atomic` package contains classes that can be used to perform atomic operations on shared
  variables. The `java.util.concurrent.atomic.AtomicInteger` class can be used to perform atomic operations on shared
  integers. The `java.util.concurrent.atomic.AtomicLong` class can be used to perform atomic operations on shared
  longs. The `java.util.concurrent.atomic.AtomicBoolean` class can be used to perform atomic operations on shared
  booleans. The `java.util.concurrent.atomic.AtomicReference` class can be used to perform atomic operations on shared
  objects.

- The `volatile` modifier can be used to ensure that the value of a variable is always read from the main memory, and
  not from the thread's local cache. This can prevent memory consistency errors, as the value of the variable will
  always be read from the main memory, and not from the thread's local cache.

---

### Synchronization and Synchronized Methods

- Synchronization is the process of controlling the access to shared resources. Synchronization can be used to ensure
  that only one thread can access the shared resources at a time. This can prevent memory consistency errors, as only
  one thread can access the shared resources at a time.

- The `synchronized` keyword can be used to synchronize a block of code, a method, or an entire class. When a thread
  enters a synchronized block of code, it acquires the lock for that block of code. When a thread exits a synchronized
  block of code, it releases the lock for that block of code.

- When one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods
  for the same object block (suspend execution) until the first thread is done with the object.

- So if the threads are blocked, doesn't that defeat the purpose? Not really, because synchronized methods are
  typically very short, and so the wait time is usually very short.

---

### Critical Section

- The critical section is the code referencing a shared resource like a variable or an object. The critical
  section must be synchronized to prevent memory consistency errors.

- Only one thread can be executed in the critical section at a time. When all critical sections are synchronized, the
  program is thread-safe.

---

### The Object Instance Monitor

- Every object instance in Java has a built-in intrinsic lock, also known as a monitor lock. The monitor lock is used
  to synchronize access to the object's critical section. When a thread enters a synchronized block of code, it acquires
  the monitor lock for that object. When a thread exits a synchronized block of code, it releases the monitor lock for
  that object.

- Only one thread can acquire the monitor lock for an object at a time. When a thread acquires the monitor lock for an
  object, all other threads that try to acquire the monitor lock for the same object will be blocked (suspended) until
  the first thread releases the monitor lock for that object.

- The synchronized statements is usually a better option in most circumstances, since it limits the scope of the lock
  to the block of code that needs it. In other words, it gives you much more granular control over when you want other
  threads to wait for the lock to be released.

---

### Reentrant Synchronization

- Reentrant synchronization is the ability to acquire the same monitor lock multiple times. This is useful when a
  synchronized method calls another synchronized method on the same object. If reentrant synchronization wasn't
  supported, the second synchronized method would block (suspend execution) until the first synchronized method
  finished.

- Reentrant synchronization is supported in Java. When a thread acquires the monitor lock for an object, it can enter
  any synchronized block of code that requires the same monitor lock, without blocking (suspending execution).

---

### Deadlock

- Deadlock is a situation where two or more threads are blocked forever, waiting for each other. Deadlock occurs when
  multiple threads need the same locks, but obtain them in different order. For example, if thread A obtains lock 1 and
  then lock 2, and thread B obtains lock 2 and then lock 1, a deadlock will occur if thread A tries to obtain lock 2 and
  thread B tries to obtain lock 1 at the same time.

---

### The `wait()`, `notify()` and `notifyAll()` Methods on the Object Class

- These methods are used to manage some monitor lock situations, to prevent deadlock and to avoid wasting CPU cycles.

- As these methods are defined in the `Object` class, they can be called on any object, from within a synchronized
  method or statement.

- The `wait()` method causes the current thread to wait until another thread invokes the `notify()` method or the
  `notifyAll()` method for this object. The current thread must own this object's monitor. The thread releases ownership
  of this monitor and waits until another thread notifies threads waiting on this object's monitor to wake up either
  through a call to the `notify()` method or the `notifyAll()` method. The thread then waits until it can re-obtain
  ownership of the monitor and resumes execution.

- The `notify()` method wakes up a single thread waiting on this object's monitor. If any threads are waiting on
  this object, one of them is chosen to be awakened. The choice is arbitrary and occurs at the discretion of the
  implementation. A thread waits on an object's monitor by calling one of the `wait()` methods.

- The `notifyAll()` method wakes up all threads waiting on this object's monitor. A thread waits on an object's monitor
  by calling one of the `wait()` methods.

---

### [`java.util.concurrent.locks` Package](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/package-summary.html)

- The Lock interface, and some of the provided implementations, can give you more control over locking than synchronized
  blocks and methods. For example, the `tryLock()` method of the `Lock` interface allows you to try to acquire the lock
  without waiting for it to become available.

- The overloaded `tryLock()` method allows you to specify a timeout period, after which the thread gives up trying to
  acquire the lock. This can be useful if you want to avoid waiting for a lock indefinitely.

---

### Managing Threads with an `ExecutorService`

- The `java.util.concurrent` package contains the `ExecutorService` interface, which can be used to manage threads.
  The `ExecutorService` interface contains the following methods:

  - `void execute(Runnable command)`: Executes the given command at some time in the future.
  - `Future<?> submit(Runnable task)`: Submits a Runnable task for execution and returns a Future representing that
      task.
  - `Future<T> submit(Callable<T> task)`: Submits a value-returning task for execution and returns a Future
      representing that task.
  - `void shutdown()`: Initiates an orderly shutdown in which previously submitted tasks are executed, but no new
      tasks will be accepted.
  - `List<Runnable> shutdownNow()`: Attempts to stop all actively executing tasks, halts the processing of waiting
      tasks, and returns a list of the tasks that were awaiting execution.
  - `boolean isShutdown()`: Returns true if this executor has been shut down.
  - `boolean isTerminated()`: Returns true if all tasks have completed following shut down.
  - `boolean awaitTermination(long timeout, TimeUnit unit)`: Blocks until all tasks have completed execution after a
      shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.

- The benefits of using an `ExecutorService` are:

  - Simplify thread management, by abstracting execution, to the level of tasks which need to be run.
  - Use Thread Pools, reducing the overhead of creating new threads.
  - Efficient scaling, by utilizing multiple cores.
  - Built-in synchronization, reducing concurrency issues.
  - Implements graceful shutdown by waiting for all running threads to finish, preventing data corruption.
  - Scheduled implementation exists to further help with management workflows.

- Advantages of using an `ExecutorService`:

  - The job of managing threads is handled by the `ExecutorService`, so you don't have to worry about creating new
      threads, or managing the thread pool.
  - The `ExecutorService` uses a thread pool, so it can reuse threads when they are no longer needed, improving
      performance.
  - The `ExecutorService` can be used to execute tasks in the background, so the main thread can continue to execute
      other tasks.

---

### Creating Threads is Expensive, Use a Thread Pool Instead

- Creating threads is expensive, so it is better to use a thread pool instead. A thread pool is a collection of
  pre-initialized threads that are ready to be used. When a thread is needed, it is taken from the pool. When the thread
  is no longer needed, it is returned to the pool, so it can be reused later. This can improve performance, as threads
  can be reused instead of creating new threads every time.

- The `java.util.concurrent.Executors` class can be used to create a thread pool. The `Executors` class contains the
  following methods:

  | Class                 | Description                                                                                              | Executors Method           |
  |-----------------------|----------------------------------------------------------------------------------------------------------|----------------------------|
  | `FixedThreadPool`     | Creates a thread pool that reuses a fixed number of threads.                                             | `newFixedThreadPool()`     |
  | `CachedThreadPool`    | Creates a thread pool that creates new threads as needed.                                                | `newCachedThreadPool()`    |
  | `ScheduledThreadPool` | Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically. | `newScheduledThreadPool()` |
  | `WorkStealingPool`    | Uses a work-stealing algorithm to distribute tasks among the threads in the pool.                        | `newWorkStealingPool()`    |
  | `ForkJoinPool`        | Specialized WorkStealingPool for executing ForkJoinTasks.                                                | `n/a`                      |

- The thread pool consists of three components:

  - **_Worker Threads_** are available in a pool to execute tasks. They are pre-created and kept in the pool so that
      they can be reused.
  - **_Submit Queue_** is a queue that holds tasks that are submitted to the thread pool. The tasks are submitted to
      the queue using the `execute()` method.
  - **_The Pool Manager_** is responsible for managing the worker threads and the submitted queue. It is responsible
      for assigning tasks to the worker threads.

- Difference between Runnable and Callable

  - The `Runnable` interface contains a single method, `run()`, which is used to execute the code in the thread. The
      `Callable` interface contains a single method, `call()`, which is used to execute the code in the thread and
      return a result.
  - The `run()` method doesn't return a result, while the `call()` method returns a result.
  - The `run()` method can't throw checked exceptions, while the `call()` method can throw checked exceptions.

- `execute()` vs `submit()`

  | Method    | Signature                                       | Description                                                                        |
  |-----------|-------------------------------------------------|------------------------------------------------------------------------------------|
  | execute() | `void execute(Runnable command)`                | Executes the given command at some time in the future.                             |
  | submit()  | `Future<?> submit(Runnable task)`               | Submits a Runnable task for execution.                                             |
  |           | `<T> Future<T> submit(Runnable task, T result)` | Submits a Runnable task for execution and returns a Future representing that task. |
  |           | `<T> Future<T> submit(Callable<T> task)`        | Submits a value-returning task for execution.                                      |

---

### The `Future` Interface

- The `java.util.concurrent.Future` interface represents the result of an asynchronous computation. The `Future`
  interface contains the following methods:

  - `boolean cancel(boolean mayInterruptIfRunning)`: Attempts to cancel execution of this task.
  - `boolean isCancelled()`: Returns true if this task was canceled before it completed normally.
  - `boolean isDone()`: Returns true if this task completed.
  - `V get()`: Waits if necessary for the computation to complete, and then retrieves its result.
  - `V get(long timeout, TimeUnit unit)`: Waits if necessary for at most the given time for the computation to
      complete, and then retrieves its result, if available.

- `invokeAll()` vs `invokeAny()`

  | Characteristic | `invokeAny()`                                                                                                   | `invokeAll()`                                                                                           |
  |----------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
  | Tasks Executed | At least one, the fastest one                                                                                   | All tasks get executed                                                                                  |
  | Result         | Result of the first takes to complete, not the Future                                                           | Return a list of results, as Futures, for all the tasks, once they have completed                       |
  | Use cases      | Use this method when we need a quick response back from one of the several tasks, and don't care about the rest | Use this method when we want all the tasks to be executed concurrently, and all the tasks are important |

---

### Scheduling Tasks with a `ScheduledExecutorService`

- The `java.util.concurrent.ScheduledExecutorService` interface can be used to schedule tasks to run after a given
  delay, or to execute periodically. The `ScheduledExecutorService` interface contains the following methods:

  - `ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)`: Creates and executes a one-shot action
      that becomes enabled after the given delay.
  - `ScheduledFuture<?> schedule(Callable<V> callable, long delay, TimeUnit unit)`: Creates and executes a one-shot
      action that becomes enabled after the given delay.
  - `ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)`:
      Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently
      with the given period.
  - `ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)`:
      Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently
      with the given delay between the termination of one execution and the commencement of the next.

---

### Work Stealing Thread Pool

- The work-stealing thread pool is a special type of thread pool that uses a work-stealing algorithm to distribute
  tasks among the threads in the pool.

- The `java.util.concurrent.ForkJoinPool` class is a specialized `ExecutorService` implementation for executing
  `ForkJoinTask`s. The `ForkJoinPool` class contains the following methods:

  - `static ForkJoinPool commonPool()`: Returns the common pool instance.
  - `static ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory`: Returns the default thread
      factory used for constructing new ForkJoinWorkerThreads.
  - `static ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory`: Returns the default handler
      for internal worker threads that terminate due to unrecoverable errors encountered while executing tasks.
  - `static ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory`: Returns the default handler
      for tasks that cannot be executed by worker threads.
  - `static ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory`: Returns the default handler
      for uncaught exceptions thrown from tasks.

---

### Types of Parallelism

- **Task Parallelism** is the ability to perform multiple tasks at the same time. For example, a thread can be used to
  download a file, while another thread can be used to process the file. This is useful when we want to perform multiple
  tasks at the same time, instead of waiting for one task to finish before starting the next one.

- **Data Parallelism** is the ability to perform the same task on multiple pieces of data at the same time. For
  example, a thread can be used to process a list of files. This is useful when we want to perform the same task on
  multiple pieces of data at the same time, instead of processing one piece of data at a time.

---

### Parallel Streams

- Parallel streams allow us to perform operations on collections in parallel, instead of sequentially. This can improve
  performance, as operations can be performed on multiple elements at the same time. The key advantages of parallel
  streams are:
  - Improved performance on multi-core CPUs.
  - Simplified code for concurrent processing.
  - Automatic workload distribution among available threads.

- The `java.util.stream` package contains the `Stream` interface, which can be used to process collections of objects.
  The `Stream` interface contains the following methods:

  - `Stream<T> parallel()`: Returns an equivalent stream that is parallel.
  - `Stream<T> sequential()`: Returns an equivalent stream that is sequential.
  - `boolean isParallel()`: Returns whether this stream, if a terminal operation were to be executed, would execute in
      parallel.

- **NOTE**: Parallel streams are not always faster than sequential streams. Parallel streams are only faster when the
  collection contains a large number of elements, and the operations performed on the collection are expensive. If the
  collection contains a small number of elements, or the operations performed on the collection are not expensive,
  parallel streams will be slower than sequential streams.

- **NOTE**: LinkedList, ArrayList, TreeSet, HashSet are not thread-safe, so we can't use them with parallel streams
  because:
  - Lacks synchronization.
  - There are no guarantees of memory consistency, which means that two threads can see different states of the same
      object, even when both threads are using the same object.

- Map Classes

  |                               | Sorted | Blocking | Thread-Safe | Stream Pipeline - Collectors Method                             |
  |-------------------------------|--------|----------|-------------|-----------------------------------------------------------------|
  | `HashMap`                     | No     | No       | No          | `.collect(groupingBy(Function classifier, ...))`                |
  | `TreeMap`                     | Yes    | No       | No          | `.collect(TreeMap<keyType, valueType>::new, ...)`               |
  | `ConcurrentHashMap`           | No     | No       | Yes         | `.collect(groupingByConcurrent(Function classifier, ...))`      |
  | `ConcurrentSkipListMap`       | Yes    | No       | Yes         | `.collect(ConcurrentSkipListMap<keyType, valueType>::new, ...)` |
  | `Collections$SynchronizedMap` | Yes    | Yes      | Yes         |                                                                 |

- Both concurrent and synchronized collections are thread-safe, and can be used in parallel streams, or in a
  multithreading application.

- **Synchronized collections** are implemented using locks which protect the collection from concurrent access. This
  means a single lock is used to synchronize access to the collection. This means that only one thread can access the
  collection at a time. This can lead to performance issues, as multiple threads can't access the collection at the same
  time.

- **Concurrent collections** are more efficient than synchronized collections, because they use techniques like
  fine-grained locking, or non-blocking algorithms to enable safe concurrent access without the need for heavy handling
  locking, meaning synchronized or single access locks. This means that multiple threads can access the collection at
  the same time, which can improve performance.

- Concurrent collections are recommended over synchronized collections, as they are more efficient, in most cases.

- Concurrent collections for Arrays and Lists:
  - For lists, there are two concurrent collection choices, depending on the type of work, which needs to be done in
      parallel:
    - Use `ConcurrentLinkedQueue` when the work is mostly adding and removing elements from the collection such as
          producer/consumer scenarios or task scheduling.
    - Use `CopyOnWriteArrayList` when the work is to read-heavy workload with infrequent modifications. This type of
          list
          is useful for scenarios like configuration management, or read-only views of data.
    - Or use an `ArrayBlockingQueue`. This is a fixed-size queue that blocks under two circumstances:
      - First is if you try to poll or remove an element when the queue is empty.
      - Second is if you try to offer or add an element when the queue is full. This is designed as a FIFO queue,
              so
              elements are added to the end of the queue, and removed from the beginning of the queue.

- Adding elements to the `ArrayBlockingQueue`

  |                                           | Blocks?     | Returns | Throws InterruptedException?                 | Adds To Queue |
  |-------------------------------------------|-------------|---------|----------------------------------------------|---------------|
  | `add(E e)`                                | No          | boolean | No, throws IllegalStateException (Unchecked) | Yes           |
  | `offer(E e)`                              | No          | boolean | No                                           | Yes           |
  | `offer(E e, long timeout, TimeUnit unit)` | Temporarily | boolean | Yes                                          | Yes           |
  | `put(E e)`                                | Yes         | void    | Yes                                          | Yes           |

---

### The Common Problems in a Multi-Threaded Application

| Problem    | Description                                                                                          |
|------------|------------------------------------------------------------------------------------------------------|
| Deadlock   | Two or more threads are blocked, waiting for each other to release a resource.                       |
| Livelock   | Two or more threads are continuously looping, each waiting for the other thread to take some action. |
| Starvation | A thread is not able to obtain the resource it needs to execute.                                     |

- **Preventing Deadlocks**
  - Organize the locks into a hierarchy, and ensure that all threads acquire the locks in the same order to prevent
      circular waiting, which is a common cause of deadlocks. This approach helps establish a global lock order that
      all threads must follow.
  - Instead of using traditional synchronized blocks or methods, we can use the `tryLock()` method of the `Lock`
      interface to acquire the locks. The `tryLock()` method will return true if the lock was acquired, and false if
      the lock was not acquired. This approach can be used to prevent deadlocks, as the thread can check if the lock
      was acquired before trying to acquire the lock. If the lock was not acquired, the thread can do something else,
      instead of waiting for the lock to be released.

- **Livelock** is a situation where two or more threads are continuously looping, each waiting for the other thread to
  take some action. For example, if thread A is waiting for thread B to take some action, and thread B is waiting for
  thread A to take some action, a livelock will occur if thread A and thread B keep waiting for each other to take some
  action.
  - Livelocks can be challenging to debug and fix; for this reason, it is better to prevent livelocks from occurring in
      the first place.
  - Avoid having thread that is dependent on each other, as this can lead to livelocks.
  - Use timeouts to prevent threads from waiting indefinitely for each other to take some action.
  - Use randomization to break the symmetry between threads, so that one thread can take some action before the other
      thread.

- **Starvation** is a situation where a thread is not able to obtain the resource it needs to execute. For example, if
    thread A is waiting for a lock, and thread B keeps acquiring the lock before thread A, a starvation will occur if
    thread A keeps waiting for the lock to be released.

---

### The `java.util.concurrency.atomic` Package

- A small toolkit of classes that support lock-free, thread-safe programming in single variables.

- Why is lock-free so important?
  - Lock-free programming is a way of doing concurrent programming without using locks. This is important because
      locks can be expensive, and can lead to performance issues.
  - Lock-free programming is also important because it can help prevent deadlocks, as there are no locks to acquire.

- The `java.util.concurrent.atomic` package contains classes that can be used to perform atomic operations on shared
  variables.
  - The `java.util.concurrent.atomic.AtomicInteger` class can be used to perform atomic operations on shared
    integers.
  - The `java.util.concurrent.atomic.AtomicLong` class can be used to perform atomic operations on shared
    longs.
  - The `java.util.concurrent.atomic.AtomicBoolean` class can be used to perform atomic operations on shared
    booleans.
  - The `java.util.concurrent.atomic.AtomicReference` class can be used to perform atomic operations on shared
    objects.

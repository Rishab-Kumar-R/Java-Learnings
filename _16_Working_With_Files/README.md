# Input, Output (I/O), and Files in Java

**Communicating outside the JVM using resources.** Here resources are files, network connections, database connections,
streams, or sockets.

## Table of Contents

- [Exception Handling](./src/com/rishab/fileExceptions/Main.java)
- [Files and Path](./src/com/rishab/fileAndPath/Main.java)
- [Methods on Path Interface](./src/com/rishab/pathListings/Main.java)
- [Methods on Files Class](./src/com/rishab/fileListings/Main.java)
- [Using FileVisitor Interface and SimpleFileVisitor Class](./src/com/rishab/fileWalker/Main.java)
- [Reading from a File](./src/com/rishab/readingFiles/Main.java)
- [Reading File using Scanner](./src/com/rishab/readingFiles/ReadingFileFromScanner.java)
- [Writing to a File](./src/com/rishab/writingFiles/Main.java)
- [Files and Directory Management](./src/com/rishab/managingFiles/Main.java)
- [RandomAccessFile Class](./src/com/rishab/randomAccess/Main.java)
- [DataOutputStream, DataInputStream and Serialization](./src/com/rishab/dataStreams/Main.java)

## Exception Handling

- Exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions.
- Exception handling is a mechanism to handle runtime errors such
  as `ClassNotFoundException`, `IOException`, `SQLException`, `RemoteException`, etc.
- For e.g., Whenever we instantiate one of the Java's file access classes, Java will delegate the task of opening the
  file to the operating system (or the file system). This then performs some or all of the following tasks:
    - First, it checks to see if the file exists.
    - If the file exists, it checks to see if the file is accessible.
    - If this is true, then file metadata is retrieved, and a file descriptor is allocated. This descriptor is an
      internal data structure that the operating system uses to keep track of the file.
    - An entry is made in the file table or file control block table of the operating system, to indicate that the file
      is open.
    - The file may be locked to prevent other processes from accessing it.
    - The file may be buffered by the OS, meaning the memory is allocated, to cache all or part of the file contents, to
      optimize read/write operations.

### Closing an Open File Resource

- When we are done with the file, we need to close it. Many of the methods in Java make opening a file look just like
  instantiating another object.
- We don't have to explicitly call open on a file, so it's easy to forget that we've really opened a resource that needs
  to be closed.
- Closing the file handle will free up the memory used to store any sile related data, and allow other processes to
  access the file.
- Fortunately, most of the Java classes we'll use to interact with files implement the `AutoCloseable` interface, which
  means that they can be used in a try-with-resources statement.
- The try-with-resources statement is a try statement that declares one or more resources. A resource is an object that
  must be closed after the program is finished with it. The try-with-resources statement ensures that each resource is
  closed at the end of the statement. Any object that implements `java.lang.AutoCloseable`, which includes all objects
  which implement `java.io.Closeable`, can be used as a resource.

    ```java
    public class Main {
        public static void main(String[] args) {
            try (FileInputStream fileInputStream = new FileInputStream("file.txt")) {
                // Do something with the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

### IO, NIO, and NIO.2

- Java has what seems like a very confusing and large series of classes, in many packages, to support I/O.
- The original I/O classes are in the `java.io` package. These classes are called the I/O stream classes, and they are
  the oldest way to do I/O in Java. The I/O stream classes are also called the byte stream classes, and the data stored
  in these classes is called a stream.

    ```java
    import java.io.FileInputStream;
    import java.io.IOException;
  
    public class Main {
        public static void main(String[] args) {
            try (FileInputStream fileInputStream = new FileInputStream("file.txt")) {
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    System.out.print((char) i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- The NIO classes are in the `java.nio` package. These classes are called the NIO classes, and they are the newer way to
  do I/O in Java. NIO stands for Non-blocking I/O. The NIO classes are also called the channel I/O classes, and the data
  stored in these classes is called a buffer.

    ```java
    import java.io.IOException;
    import java.nio.ByteBuffer;
    import java.nio.channels.FileChannel;
    import java.io.FileInputStream;
  
    public class Main {
        public static void main(String[] args) {
            try (FileInputStream fileInputStream = new FileInputStream("file.txt")) {
                FileChannel fileChannel = fileInputStream.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                while (fileChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        System.out.print((char) byteBuffer.get());
                    }
                    byteBuffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- The NIO.2 classes are in the `java.nio.file` package. These classes are called the NIO.2 classes, and they are the
  newest way to do I/O in Java. NIO.2 stands for Non-blocking I/O, version 2. The NIO.2 classes are also called the Path
  I/O classes, and the data stored in these classes is called a path.

    ```java
    import java.io.IOException;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.nio.file.Files;

    public class Main {
        public static void main(String[] args) {
            Path path = Paths.get("file.txt");
            try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

### IOException

- IOException is the superclass of all exceptions that can be thrown when using IO classes from the `java.io` package.

- IOException is a **checked exception**, which means that your code must handle it, either by catching it or by
  declaring that your method throws it.

- Assuming that the file exists, and you've made a typo in the file name might be a common occurrence, and the system
  wouldn't be able to locate a file. It's so common, that Java usually has a named exception just for that situation,
  the `FileNotFoundException`, which is a subclass of `IOException`.

- We have two options to handle the checked exceptions:
    - We can wrap the statement that throws a checked exception in a try-catch block, and then handle the situation in
      the catch block.
    - Alternatively, we can declare that our method throws the exception, and let the calling code handle it.

#### LBYL vs EAFP

- LBYL stands for **Look Before You Leap**. It means that you should check for errors before you try to do something.

- EAFP stands for **Easier to Ask Forgiveness than Permission**. It means that you should just do what you want, and if
  something goes wrong, then handle it.

- **Which one is better?**

  | Feature       | LBYL                                            | EAFP                                                                    |
        |---------------|-------------------------------------------------|-------------------------------------------------------------------------|
  | Approach      | Check for errors before performing an operation | Assume that the operation will succeed and handle any errors that occur |
  | Advantages    | Can be more efficient if errors are rare        | Can be more concise and easier to read                                  |
  | Disadvantages | Can be more verbose if errors are common        | Can be more difficult to debug if errors are unexpected                 |

---

## How to recognize a checked exception?

- Checked exceptions are all the exceptions that extend the `java.lang.Exception` class, except for the
  `java.lang.RuntimeException` class and its subclasses.

- The [java.lang.RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html) class and
  its subclasses are called unchecked exceptions. Unchecked exceptions are
  not checked at compile-time, but they are checked at runtime.

<div align="center">
    <img src="https://s3.shunyafoundation.com/s3/cde963c55b3821c73058e607c3680565a80e6097/java-exception-hierarchy.png" alt="Throwable class hierarchy" width="650px">
</div>

---

## [`java.io`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) package

- The `java.io` package contains classes that support input and output through data streams, serialization and the file
  system.

- The `java.io` package contains two main categories of classes:
    - `File`
    - `FileReader` implements `AutoCloseable`

- This class opens the file resource implicitly. In contrast, when we create an instance of `File`, we aren't usually
  opening the file, instead we are working with something called a file handler, that lets us perform OS level
  operations on the file.

---

## [`java.nio`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/Files.html) package

- The `java.nio` package contains classes that support input and output through channels and buffers, serialization and
  the file system.

- The `java.nio.file` package contains an interface called `Path`, which is used to represent a path to a file or
  directory in the file system. The `Paths` class contains static methods that can be used to obtain a `Path` object and
  `Files` class contains static methods that can be used to perform operations on a `Path` object.

- The `java.nio.file` package contains main categories of classes nad interfaces:
    - `Path` interface - used to represent a path to a file or directory in the file system.
    - `Files` class - contains static methods that can be used to perform operations on a file or directory.
    - `Paths` class - contains static methods that can be used to obtain a `Path` object.

### Common Functionalities of `java.io` and `java.nio` packages

| Functionality                | `java.io` package                    | `java.nio` package                                            |
|------------------------------|--------------------------------------|---------------------------------------------------------------|
| **create a file**            | `File.createNewFile()`               | `Files.createFile()`                                          |
| **delete directory of file** | `File.delete()`                      | `Files.delete(path)` or `Files.deleteIfExists(path)`          |
| **check path type**          | `File.isFile()` `File.isDirectory()` | `Files.isRegularFile(path)` `Files.isDirectory(path)`         |
| **get byte size of file**    | `File.length()`                      | `Files.size(path)`                                            |
| **list directory contents**  | `File.listFiles()`                   | `Files.list(path)`                                            |
| **create directory(or)'s**   | `File.mkdir()` `File.mkdirs()`       | `Files.createDirectory(path)` `Files.createDirectories(path)` |
| **rename a file**            | `File.renameTo()`                    | `Files.move(path, newPath)`                                   |

### Methods on Path interface

| Method                                          | Description                                                                                                       |
|-------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `static Path of(String first, String... more)`  | returns a `Path` object by converting a path string or a sequence of strings that when joined form a path string. |
| `static Path get(String first, String... more)` | returns a `Path` object by converting a path string or a sequence of strings that when joined form a path string. |
| `static Path get(URI uri)`                      | returns a `Path` object by converting the given URI.                                                              |
| `Path getFileName()`                            | returns the file name of the file or directory denoted by this path.                                              |
| `Path getParent()`                              | returns the parent path, or null if this path does not have a parent.                                             |
| `Path getRoot()`                                | returns the root component of this path as a `Path` object, or null if this path does not have a root component.  |
| `Path toAbsolutePath()`                         | returns a `Path` object representing the absolute path of this path.                                              |

### Methods on Files class

| Method                                                                    | Description                                                       |
|---------------------------------------------------------------------------|-------------------------------------------------------------------|
| `static Path createFile(Path path, FileAttribute<?>... attrs)`            | creates a new and empty file, failing if the file already exists. |
| `static void delete(Path path)`                                           | deletes a file or an empty directory.                             |
| `static void deleteIfExists(Path path)`                                   | deletes a file or directory.                                      |
| `static boolean isDirectory(Path path, LinkOption... options)`            | tests whether a file is a directory.                              |
| `static boolean isRegularFile(Path path, LinkOption... options)`          | tests whether a file is a regular file with opaque content.       |
| `static long size(Path path)`                                             | returns the size of a file (in bytes).                            |
| `static Map<String, Object> readAttributes(Path path, String attributes)` | reads a file's attributes as a bulk operation.                    |
| `static String probeContentType(Path path)`                               | probes the content type of a file.                                |

---

## FileVisitor Interface and SimpleFileVisitor Class

- The `FileVisitor` interface is used to walk a file tree and perform some action on each file or directory visited.
  This interface contains four methods:
    - `preVisitDirectory(Path dir, BasicFileAttributes attrs)` - invoked before a directory's entries are visited.
    - `visitFile(Path file, BasicFileAttributes attrs)` - invoked on the file being visited.
    - `visitFileFailed(Path file, IOException exc)` - invoked when a file cannot be accessed.
    - `postVisitDirectory(Path dir, IOException exc)` - invoked after all the entries in a directory are visited.

- The `SimpleFileVisitor` class is an implementation of the `FileVisitor` interface that visits all files and
  directories
  in a file tree. This class contains four methods:
    - `preVisitDirectory(Path dir, BasicFileAttributes attrs)` - invoked before a directory's entries are visited.
    - `visitFile(Path file, BasicFileAttributes attrs)` - invoked on the file being visited.
    - `visitFileFailed(Path file, IOException exc)` - invoked when a file cannot be accessed.
    - `postVisitDirectory(Path dir, IOException exc)` - invoked after all the entries in a directory are visited.

---

## Reading from a File

- The `java.io` package contains two main categories of classes:
    - `File`
    - `FileReader` implements `AutoCloseable`

- This class opens the file resource implicitly. In contrast, when we create an instance of `File`, we aren't usually
  opening the file, instead we are working with something called a file handler, that lets us perform OS level
  operations on the file.

    ```java
    import java.io.FileReader;
    import java.io.IOException;

    public class Main {
        public static void main(String[] args) {
            try (FileReader fileReader = new FileReader("file.txt")) {
                int i;
                while ((i = fileReader.read()) != -1) {
                    System.out.print((char) i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- The `fileReader.read()` method reads a single character from the file and returns it as an integer. If the end of the
  file has been reached, then `-1` is returned. And this method is not efficient because it reads one character at a
  time, which increases the **Disk Read** operations.
- Hence, Java provides a more efficient way to read data from a file, using the `BufferedReader` class.

    ```java
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;

    public class Main {
        public static void main(String[] args) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

---

## Reading File using Scanner

- The `java.util.Scanner` class is used to read text from a file. It is used to break the input into tokens using a
  delimiter, which is whitespace by default. It implements the `Iterator` interface and `AutoCloseable` interface.

- The `Scanner` class provides many methods to read and parse various primitive values. It is the easiest way to read
  input in a Java program, though not very efficient if you want an input method for scenarios where time is a
  constraint like in competitive programming.

- The `Scanner` class is not thread-safe, so it is better to use `BufferedReader` class if you are working with multiple
  threads.

- The `Scanner` class is present in the `java.util` package. To use the `Scanner` class, create an object of the class
  and use any of the available methods found in the `Scanner` class documentation.

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("file.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

---

## Reading text from a File with NIO.2 functionality

- The `java.nio.file.Files` class contains a method called `readAllLines()` that can be used to read all the lines from
  a file into a `List` of strings.

- The `readAllLines()` method reads all the lines from a file and returns a `List` of strings. This method throws an
  `IOException` if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.

- These methods support files up to 2 gigabytes in size when called with the default options. A larger file may be read
  by calling the method with the `FileChannel` that is returned by the `newByteChannel(Path, Set, FileAttribute[])`
  method.

| Method                                                    | Description                                           | Closes file?          |
|-----------------------------------------------------------|-------------------------------------------------------|-----------------------|
| `byte[] readAllBytes(Path path) throws IOException`       | Reads entire contents of a file into a byte array.    | Yes                   |
| `String readString(Path path) throws IOException`         | Reads all characters from a file into a string.       | Yes                   |
| `List<String> readAllLines(Path path) throws IOException` | Reads all lines from a file into a `List` of strings. | Yes                   |
| `Stream<String> lines(Path path) throws IOException`      | Reads entire contents of a text file.                 | On Terminal Operation |

---

## Writing to a File

- There are a lot of reasons why you might want to write to a file. These include:
    - Storing data for later use.
    - Logging application activity to a log file.
    - Storing configuration data in a file.
    - Exporting data for exchange of information.
    - Supporting offline usage in a cache file.
    - Generating file products for other applications.

- The `java.io` package contains two main categories of classes:
    - `File`
    - `FileWriter` implements `AutoCloseable`

- This class opens the file resource implicitly. In contrast, when we create an instance of `File`, we aren't usually
  opening the file, instead we are working with something called a file handler, that lets us perform OS level
  operations on the file.

    ```java
    import java.io.FileWriter;
    import java.io.IOException;

    public class Main {
        public static void main(String[] args) {
            try (FileWriter fileWriter = new FileWriter("file.txt")) {
                fileWriter.write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Writer Classes

  |                  | Buffering                                         | Data Format                     | Features                                                                                                                                                                                   | Use Case                                 |
        |------------------|---------------------------------------------------|---------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------|
  | `BufferedWriter` | Yes                                               | Character                       | Writes text to a character-output stream, buffering characters so as to provide for the efficient writing of single characters, arrays, and strings, and appending newlines to the output. | Writing large amounts of text to a file. |
  | `FileWriter`     | Yes but much smaller buffer than `BufferedWriter` | Character                       | Writes text to a character-output stream, buffering characters so as to provide for the efficient writing of single characters, arrays, and strings, no appending newlines to the output.  | Writing small amounts of text to a file. |
  | `PrintWriter`    | No, but ofter used with a `BufferedWriter`        | Characters, numbers and objects | Prints formatted representations of objects to a text-output stream.                                                                                                                       | Writing formatted text to a file.        |

---

## File and Directory Management (Renaming, Copying, Deleting Files Copying and Deleting Directories InputStream and transferTo)

- Renaming a file

    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path source = Paths.get("file.txt");
            Path target = Paths.get("newFile.txt");
            try {
                Files.move(source, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Copying a file

    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path source = Paths.get("file.txt");
            Path target = Paths.get("newFile.txt");
            try {
                Files.copy(source, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Deleting a file

    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path path = Paths.get("file.txt");
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Copying a directory

    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path source = Paths.get("dir1");
            Path target = Paths.get("dir2");
            try {
                Files.walk(source).forEach(sourcePath -> {
                    try {
                        Path targetPath = target.resolve(source.relativize(sourcePath));
                        Files.copy(sourcePath, targetPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Deleting a directory

    ```java
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path path = Paths.get("dir");
            try {
                Files.walk(path).sorted(Comparator.reverseOrder()).forEach(sourcePath -> {
                    try {
                        Files.delete(sourcePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

- Reading a file using InputStream and transferTo

    ```java
    import java.io.IOException;
    import java.io.InputStream;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    public class Main {
        public static void main(String[] args) {
            Path path = Paths.get("file.txt");
            try (InputStream inputStream = Files.newInputStream(path)) {
                inputStream.transferTo(System.out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

---

## [`RandomAccessFile` Class](https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html)

- The `RandomAccessFile` class is used to read from and write to files. The application can read from a specific
  position or write to a specific position in the file. This class does not support file locking.

- The `RandomAccessFile` behaves like a large array of bytes stored in the file system. There is a cursor or index into
  the implied array, called the file pointer; input operations read bytes starting at the file pointer and advance the
  file pointer past the bytes read. If the random access file is created in read/write mode, then output operations are
  also available; output operations write bytes starting at the file pointer and advance the file pointer past the bytes
  written. Output operations that write past the current end of the implied array cause the array to be extended. The
  file pointer can be read by the `getFilePointer` method and set by the `seek` method.

- The `RandomAccessFile` class is not a subclass of the `FileInputStream` or `FileOutputStream` classes. It implements
  the `DataInput` and `DataOutput` interfaces.

- The `RandomAccessFile` class supports both reading and writing to a file. The mode argument specifies the access mode
  in which the file is to be opened. The following values are possible:

  | Value | Description                                                                                                      |
        |-------|------------------------------------------------------------------------------------------------------------------|
  | `r`   | Opens the file for reading only.                                                                                 |
  | `rw`  | Opens the file for both reading and writing.                                                                     |
  | `rws` | Opens the file for reading and writing, telling the JVM to write every update to the file to the storage device. |
  | `rwd` | Opens the file for reading and writing, telling the JVM to write every update to the file to the storage device. |

### Why use `RandomAccessFile`?

- The `RandomAccessFile` class is used to read from and write to files. The application can read from a specific
  position or write to a specific position in the file. This class does not support file locking.

- Let's say we have a file with millions of records, and at any given time, we want to access a specific record. To do
  that, we have to read all the records before the record we want to access. This is not efficient. Instead,
  we can use the `RandomAccessFile` class to access the record directly. So, instead of loading a million records into
  memory, we can just load the record we want to access.

### Methods on `RandomAccessFile` class

| Method                                   | Description                                                                                                     |
|------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `void seek(long pos)`                    | Sets the file-pointer offset, measured from the beginning of this file, at which the next read or write occurs. |
| `long getFilePointer()`                  | Returns the current offset in this file.                                                                        |
| `long length()`                          | Returns the length of this file.                                                                                |
| `int read()`                             | Reads a byte of data from this file.                                                                            |
| `int read(byte[] b)`                     | Reads up to `b.length` bytes of data from this file into an array of bytes.                                     |
| `int read(byte[] b, int off, int len)`   | Reads up to `len` bytes of data from this file into an array of bytes.                                          |
| `void write(int b)`                      | Writes the specified byte to this file.                                                                         |
| `void write(byte[] b)`                   | Writes `b.length` bytes from the specified byte array to this file.                                             |
| `void write(byte[] b, int off, int len)` | Writes `len` bytes from the specified byte array starting at offset `off` to this file.                         |

---

## DataOutputStream, DataInputStream and Serialization

### DataOutputStream

- The `DataOutputStream` class is used to write primitive Java data types to an output stream in a portable way. An
  application can then use a `DataInputStream` to read the data back in.

- The `DataOutputStream` class implements the `DataOutput` interface and extends the `OutputStream` class.
  The `DataOutput`
  interface provides methods for writing bytes, shorts, ints, longs, floats, doubles, booleans, and strings to a stream
  in a machine-independent way.

- The `DataOutputStream` class contains methods for writing the eight Java primitive data types to a stream. For each
  of these data types, the `DataOutputStream` class contains a corresponding to write method. For example, the
  `writeInt(int)` method writes an `int` value to the output stream as four bytes, high byte first. There is also a
  method for writing a `String` to a stream.

### DataInputStream

- The `DataInputStream` class is used to read primitive Java data types from an underlying input stream in a
  machine-independent way. An application uses a `DataInputStream` to read data that has been written by
  a `DataOutputStream`.

- The `DataInputStream` class implements the `DataInput` interface and extends the `InputStream` class. The `DataInput`
  interface provides methods for reading bytes, shorts, ints, longs, floats, doubles, booleans, and strings from a
  stream in a machine-independent way.

- The `DataInputStream` class contains methods for reading the eight Java primitive data types from a stream. For each
  of these data types, the `DataInputStream` class contains a corresponding read method. For example, the `readInt()`
  method reads an `int` value from the input stream as four bytes, high byte first. There is also a method for reading
  a `String` from a stream.

### Serialization

- Serialization is the process of converting an object into a stream of bytes to store the object or transmit it to
  memory, a database, or a file. Its main purpose is to save the state of an object to be able to recreate it
  when needed. The reverse process is called deserialization or reconstitution of the object.

- The `java.io.Serializable` interface must be implemented by the class whose object we want to persist. The
  `java.io.Serializable` interface is a marker interface, which means it has no methods or fields and serves only to
  identify the semantics of being serializable.

- The serialization process is JVM independent, so an object serialized on one platform can be deserialized on a
  different platform.

- To make a Java object serializable, we implement the `java.io.Serializable` interface. The ObjectOutputStream class
  contains writeObject() method for serializing an Object.

- To deserialize an object, we use the ObjectInputStream class. The ObjectInputStream class contains readObject()
  method for deserializing an Object.

- The `serialVersionUID` field is a runtime field, that the compiler will implicitly create, if it's not explicitly
  declared, for the classes that are serializable.

- It's based on class details such as the number of fields, their types, and the declarations. If the class changes in
  any way that affects the serialization process, then the `serialVersionUID` will change.

- When we read an object from a stream, the runtime checks the stored serialVersionUID against the serialVersionUID of
  the class. If they don't match, then an `InvalidClassException` is thrown.

### [What constitutes an Incompatible Change?](https://docs.oracle.com/en/java/javase/17/docs/specs/serialization/version.html#incompatible-changes)

- Changing the declared type of primitive field.

- Deleting fields.

- Changing a non-static field to static, or a non-transient field to transient.

There are other more complicated changes, such as moving a class within a package hierarchy, changing the writeObject
and readObject methods after you've serialized some objects, and so on.

### [What constitutes a Compatible Change?](https://docs.oracle.com/en/java/javase/17/docs/specs/serialization/version.html#compatible-changes)

- Adding fields.

- Adding writeObject/readObject methods.

- Changing the access modifiers of fields.

- Changing a field from static to non-static, or transient.

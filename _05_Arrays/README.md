# Arrays

An array is a collection of items stored at contiguous memory locations. The idea is to store multiple items of the same
type together. This makes it easier to calculate the position of each element by simply adding an offset to a base
value, i.e., the memory location of the first element of the array (generally denoted by the name of the array). The
size of the array usually denoted by n, is always fixed and equal to the total number of elements in the array.

## Table of Contents

- [Introduction to Arrays](./src/com/rishab/Main.java)
- [Reference Types vs. Value Types](./src/com/rishab/ReferenceTypes.java)
- [Variable Length Arguments (Varargs)](./src/com/rishab/VarArgs.java)
- [Multidimensional Arrays](./src/com/rishab/MultiDimensionalArrays.java)

## Declaration

To declare an array in java, you need to define the variable type with square brackets:

```java
int[] integerArray;
String[] stringArray;
String arrayOfStrings[];
```

## Initialization

To initialize an array in java, you need to use the new keyword with the array type:

```java
int[] integerArray = new int[10];
String[] stringArray = new String[10];
```

- Arrays are objects in Java (arrays are treated as special objects)
- Like all other classes, arrays also inherit the Object class

### `java.util.Arrays`

- The `java.util.Arrays` class contains various static methods for sorting and searching arrays, comparing arrays, and
  filling array elements.
- The methods in this class throw a `NullPointerException` if the specified array reference is null, except where noted.
- Most used methods:
  - `Arrays.sort()` - uses a dual-pivot quicksort algorithm
  - `Arrays.binarySearch()` - uses a binary search algorithm
  - `Arrays.equals()` - returns true if the two specified arrays of Objects are equal to one another
  - `Arrays.fill()` - assigns the specified long value to each element of the specified array of longs
  - `Arrays.toString()` - returns a string representation of the contents of the specified array
  - `Arrays.copyOf()` - copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length

---

## Reference Types vs. Value Types

- Reference types store the reference to the value, whereas value types directly contain the value.
- Reference types are accessed through a pointer, whereas value types are accessed directly.
- Reference types are allocated dynamically on the heap, whereas value types are allocated on the stack.

```java
// Reference Types
int[] a = new int[10];
int[] b = a;
b[0] = 1;
System.out.println(a[0]); // 1

// Value Types
int a = 10;
int b = a;
b = 20;
System.out.println(a); // 10
```

---

## Variable Length Arguments (Varargs)

- A method that takes a variable number of arguments is called a variable-arity method, or simply a varargs method (short for variable-length arguments).
- To declare a method that takes a variable number of arguments, specify three periods (...) after the parameter type.
- The three periods are called the varargs operator. The varargs operator can appear only after the last parameter in the method declaration.
- The method can take more than one parameter, but at most one parameter can be a variable-arity parameter.

```java
public class Main {
    public static void main(String[] args) {
        printMax(1, 2, 3); // The max value is 3.0
        printMax(new double[]{1, 2, 3}); // The max value is 3.0
    }

    public static void printMax(double... numbers) {
        if (numbers.length == 0) {
            System.out.println("No arguments passed");
            return;
        }

        double result = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > result) {
                result = numbers[i];
            }
        }

        System.out.println("The max value is " + result);
    }
}
```

---

## Multidimensional Arrays

- A multidimensional array is an array of arrays.
- Each element of a multidimensional array is an array itself.
- A two-dimensional array is an array of one-dimensional arrays.
- A three-dimensional array is an array of two-dimensional arrays.

```java
int[][] a = new int[2][3];
int[][] b = {{1, 2, 3}, {4, 5, 6}};
```

### Jagged Arrays

- A jagged array is an array of arrays, and therefore its elements are reference types and are initialized to null.
- The elements of a jagged array can be of different dimensions and sizes.
- A jagged array is sometimes called an "array of arrays."

```java
int[][] a = new int[2][];
a[0] = new int[3];
a[1] = new int[2];
```

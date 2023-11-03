# Variables and Data Types

## Table of Contents

- [Hello World](./src/com/rishab/HelloWorld.java)
- [Variables](./src/com/rishab/Variables.java)
- [Primitive Data Types](./src/com/rishab/PrimitiveTypes.java)
- [String](./src/com/rishab/Strings.java)
- [Operators, Operands and Expressions](./src/com/rishab/Operators.java)

## Variables

Variables are used to store values. A variable is a name that refers to a value.

### Variable Names

A variable can have a short name (like x and y) or a more descriptive name (age, carname, total_volume). Rules for Java variables:

- Variable names are case-sensitive (age, Age and AGE are three different variables)
- A variable's name must start with a letter, an underscore (\_) or a dollar sign (\$)
- A variable name cannot start with a number
- A variable name can only contain alphanumeric characters and underscores (A-z, 0-9, and \_ )
- Variable names cannot contain spaces
- Variable names should not start with an uppercase letter (technically allowed but not recommended)

### Assigning Values to Variables

Java uses the = operator to assign values to variables. The assignment is done from right to left. For example:

```java
int x = 10;
```

### Multiple Variables

You can declare multiple variables of the same type on one line, using a comma-separated list. For example:

```java
int a = 3, b = 4, c = 5;
```

### Constants

Constants are variables whose values cannot be changed later in the program. Constants are declared using the final keyword. For example:

```java
final int x = 10;
```

## Data Types

Data types specify the different sizes and values that can be stored in the variable. There are two types of data types in Java:

- **Primitive data types** - includes byte, short, int, long, float, double, boolean and char
- **Non-primitive data types** - such as String, Arrays and Classes

### Primitive Data Types

| Data Type | Size    | Description                                                                       |
| --------- | ------- | --------------------------------------------------------------------------------- |
| byte      | 1 byte  | Stores whole numbers from -128 to 127                                             |
| short     | 2 bytes | Stores whole numbers from -32,768 to 32,767                                       |
| int       | 4 bytes | Stores whole numbers from -2,147,483,648 to 2,147,483,647                         |
| long      | 8 bytes | Stores whole numbers from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |
| float     | 4 bytes | Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits           |
| double    | 8 bytes | Stores fractional numbers. Sufficient for storing 15 decimal digits               |
| boolean   | 1 bit   | Stores true or false values                                                       |
| char      | 2 bytes | Stores a single character/letter or ASCII values                                  |

### Non-Primitive Data Types

| Data Type | Description                                                                  |
| --------- | ---------------------------------------------------------------------------- |
| String    | Stores text, such as "Hello". String values are surrounded by double quotes  |
| Arrays    | Stores indexed values. Arrays can store multiple values in a single variable |

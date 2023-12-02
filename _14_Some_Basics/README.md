# Some Additional Basics

## Table of Contents

- [java.lang.Math](./src/com/rishab/mathRandom/Main.java)
- [BigDecimal](./src/com/rishab/bigDecimal/Main.java)
- [LocalDate, LocalTime and LocalDateTime](./src/com/rishab/dateTime/Main.java)
- [ZoneId, TimeZone](./src/com/rishab/moreOnTime/Main.java)
- [Locals](./src/com/rishab/locale/Main.java)
- [Resource Bundle](./src/com/rishab/resourceBundle/Main.java)

## Functionality on `java.lang.Math`

- Some basic math functions are available in `java.lang.Math` class. They are listed below:

  | Method                         | Description                                                                                                                                            |
  | ------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
  | `Math.incrementExact(int x)`   | Returns the argument incremented by one, throwing an exception if the result overflows an int.                                                         |
  | `Math.decrementExact(int x)`   | Returns the argument decremented by one, throwing an exception if the result overflows an int.                                                         |
  | `Math.abs(int x)`              | Returns the absolute value of an int value.                                                                                                            |
  | `Math.absExact(int x)`         | Returns the absolute value of an int value, throwing an exception if the result overflows an int.                                                      |
  | `Math.max(int a, int b)`       | Returns the greater of two int values.                                                                                                                 |
  | `Math.round(float a)`          | Returns the closest int to the argument, with ties rounding to positive infinity.                                                                      |
  | `Math.floor(double a)`         | Returns the largest (closest to positive infinity) double value that is less than or equal to the argument and is equal to a mathematical integer.     |
  | `Math.ceil(double a)`          | Returns the smallest (closest to negative infinity) double value that is greater than or equal to the argument and is equal to a mathematical integer. |
  | `Math.sqrt(double a)`          | Returns the correctly rounded positive square root of a double value.                                                                                  |
  | `Math.cbrt(double a)`          | Returns the cube root of a double value.                                                                                                               |
  | `Math.pow(double a, double b)` | Returns the value of the first argument raised to the power of the second argument.                                                                    |
  | `Math.random()`                | Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.                                                           |

### Why use `Math.random()` over `Random` class?

- `Math.random()` uses `Random` class internally. It is a static method and it uses `Random` class instance to produce random numbers.
- `Random` class instance is not shared between threads, so it is thread safe. But `Math.random()` is shared between threads, so it is not thread safe.
- `Random` class instance can be used to produce random numbers of different types, but `Math.random()` can only produce `double` type random numbers.

  ```java
  // The below code produces random characters between A-Z
  int random = (int) (Math.random() * (26)) + 65;
  ```

---

## `BigDecimal` class

- `BigDecimal` class is used for mathematical operations which require precision. It is used when we need more accuracy than `double` datatype.
- `BigDecimal` class is immutable and it provides operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion.
- The field holds an **unscaled** `BigInteger` value and an **int** value for the **scale**.
- If zero or positive, the scale is the number of digits to the right of the decimal point. If negative, the unscaled value of the number is multiplied by ten to the power of the negation of the scale. The value of the number represented by the `BigDecimal` is therefore `(unscaledValue × 10^-scale)`.

---

## Date and Time API `java.time`

- `java.time` package was introduced in Java 8 to overcome the shortcomings of `java.util.Date` and `java.util.Calendar`.
- `java.time` package has following sub-packages:

  | Package Name         | Description                                                                 |
  | -------------------- | --------------------------------------------------------------------------- |
  | `java.time`          | Contains classes for date and time, timezone, instant, duration, clock etc. |
  | `java.time.chrono`   | Contains classes for non-ISO calendar systems.                              |
  | `java.time.format`   | Contains classes for formatting and parsing date and time.                  |
  | `java.time.temporal` | Contains classes for representing time in generic way.                      |
  | `java.time.zone`     | Contains classes for representing time zones.                               |

- `java.time` package has following important classes:

  | Class Name          | Description                                                                 |
  | ------------------- | --------------------------------------------------------------------------- |
  | `LocalDate`         | Represents a date without time zone.                                        |
  | `LocalTime`         | Represents a time without time zone.                                        |
  | `LocalDateTime`     | Represents a date-time without time zone.                                   |
  | `ZonedDateTime`     | Represents a date-time with time zone.                                      |
  | `DateTimeFormatter` | Formats and parses date-time objects.                                       |
  | `Period`            | Represents a quantity of time in terms of years, months and days.           |
  | `Duration`          | Represents a quantity of time in terms of seconds and nanoseconds.          |
  | `Instant`           | Represents an instant in time to an accuracy of nanoseconds from the epoch. |

- `java.time` package has following important interfaces:

  | Interface Name        | Description                                                                      |
  | --------------------- | -------------------------------------------------------------------------------- |
  | `Temporal`            | Framework-level interface that represents date-time objects.                     |
  | `TemporalUnit`        | Framework-level interface that represents units of date-time.                    |
  | `TemporalField`       | Framework-level interface that represents fields of date-time.                   |
  | `ChronoLocalDate`     | Represents a date without time zone for any calendar system.                     |
  | `ChronoLocalDateTime` | Represents a date-time without time zone for any calendar system.                |
  | `ChronoZonedDateTime` | Represents a date-time with time zone for any calendar system.                   |
  | `ChronoUnit`          | Represents units of date-time for any calendar system.                           |
  | `ChronoField`         | Represents fields of date-time for any calendar system.                          |
  | `TemporalAdjuster`    | Adjusts date-time objects.                                                       |
  | `TemporalQuery`       | Queries date-time objects.                                                       |
  | `TemporalAmount`      | Represents an amount of date-time.                                               |
  | `TemporalAccessor`    | Framework-level interface that represents read-only access to date-time objects. |
  | `TemporalAdjuster`    | Adjusts date-time objects.                                                       |
  | `TemporalQuery`       | Queries date-time objects.                                                       |
  | `TemporalAmount`      | Represents an amount of date-time.                                               |
  | `TemporalAccessor`    | Framework-level interface that represents read-only access to date-time objects. |

- `java.time` package has following important enums:

  | Enum Name        | Description                                                           |
  | ---------------- | --------------------------------------------------------------------- |
  | `DayOfWeek`      | Represents a day-of-week, such as Tuesday.                            |
  | `Month`          | Represents a month-of-year, such as March.                            |
  | `MonthDay`       | Represents a combination of month and day-of-month, such as March 25. |
  | `Year`           | Represents a year.                                                    |
  | `YearMonth`      | Represents a combination of year and month, such as March 2016.       |
  | `ZoneId`         | Represents a time zone identifier.                                    |
  | `ZoneOffset`     | Represents a time zone offset from Greenwich/UTC time.                |
  | `OffsetDateTime` | Represents a date-time with an offset from Greenwich/UTC time.        |
  | `OffsetTime`     | Represents a time with an offset from Greenwich/UTC time.             |
  | `DayOfWeek`      | Represents a day-of-week, such as Tuesday.                            |
  | `Month`          | Represents a month-of-year, such as March.                            |
  | `MonthDay`       | Represents a combination of month and day-of-month, such as March 25. |
  | `Year`           | Represents a year.                                                    |
  | `YearMonth`      | Represents a combination of year and month, such as March 2016.       |
  | `ZoneId`         | Represents a time zone identifier.                                    |
  | `ZoneOffset`     | Represents a time zone offset from Greenwich/UTC time.                |
  | `OffsetDateTime` | Represents a date-time with an offset from Greenwich/UTC time.        |
  | `OffsetTime`     | Represents a time with an offset from Greenwich/UTC time.             |

# Regular Expressions

- Regular expressions are a language of their own. When you learn a new programming language, they're this little sub-language that makes no sense at first glance. Regular expressions are a tool insufficiently sophisticated to understand the constructs they are employed to match. So, do not be surprised if you look at them and think, "what the heck is that?"

## Table of Contents

- [Regular Expressions Introduction](./src/com/rishab/Main.java)
- [Pattern and Matcher](./src/com/rishab/PatternMatching.java)

- Regular expressions are a pattern matching standard for string parsing and replacement. They are supported by most of the programming languages like java, perl, python, ruby, awk, etc. In java, string objects are immutable. So, if a string object is operated on (such as replacing a substring with another string), a new string object is created. Regular expressions are a concise and flexible tool for describing patterns in strings.

  ```java
  // Phone number validation
  String phoneRegex = "\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";

  // Email validation
  String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                      "[a-zA-Z0-9_+&*-]+)*@" +
                      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                      "A-Z]{2,7}$";

  // HTML Tag validation
  String htmlRegex = "<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*";
  ```

- Regular expressions are a powerful tool for matching various kinds of patterns when used appropriately. They are rapid and can be used to search/replace/edit text, validate data, parse complex strings, etc. The term "regular expression" is a mouthful, so you will usually find the term abbreviated as "regex" or "regexp." Regular expressions are used to search and manipulate the text, based on the patterns. Regular expressions are also called regex or regexp. Regular expressions are used to perform pattern-matching and "search-and-replace" functions on text. Regular expressions are a powerful tool for matching various kinds of patterns when used appropriately.

  ```java
  import java.util.regex.Matcher;
  import java.util.regex.Pattern;

  public class RegexMatches {
      public static void main(String[] args) {

          // String to be scanned to find the pattern.
          String line = "This order was placed for QT3000! OK?";
          String pattern = "(.*)(\\d+)(.*)";

          // Create a Pattern object
          Pattern r = Pattern.compile(pattern);

          // Now create a matcher object.
          Matcher m = r.matcher(line);
          if (m.find()) {
              System.out.println("Found value: " + m.group(0));
              System.out.println("Found value: " + m.group(1));
              System.out.println("Found value: " + m.group(2));
          } else {
              System.out.println("NO MATCH");
          }
      }
  }
  ```

## Parts of a Regular Expression

- **Literal Characters**: Characters other than the metacharacters are all literal characters. They simply match themselves. For example, the regular expression `test` matches the literal character sequence `test`.

- **Character Classes**: A character class is a set of characters enclosed within square brackets `[]`. It matches any single character in that list. If the first character of the class is the caret `^`, then it matches any character NOT in the list. For example, the regular expression `[Tt]est` matches either `Test` or `test`.

  | Character Class | Description                                                                        | Pattern Example  | Match Example               |
  | --------------- | ---------------------------------------------------------------------------------- | ---------------- | --------------------------- |
  | `.`             | Any character except newline (`\n`)                                                | `b.t`            | `bat`, `bet`, `bit`, `bot`  |
  | `[abc]`         | Any character in the set (`a`, `b`, or `c`)                                        | `[Tt]est`        | `Test`, `test`              |
  | `[^abc]`        | Any character NOT in the set (`a`, `b`, or `c`)                                    | `[^Tt]est`       | `best`, `cest`, `dest`, ... |
  | `[a-g]`         | Any character in the range (`a` through `g`)                                       | `[a-g]est`       | `aest`, `best`, `cest`, ... |
  | `[a-zA-Z]`      | Any character in the range (`a` through `z` or `A` through `Z`)                    | `[a-zA-Z]est`    | `aest`, `best`, `cest`, ... |
  | `[0-9]`         | Any character in the range (`0` through `9`)                                       | `[0-9]est`       | `0est`, `1est`, `2est`, ... |
  | `[a-zA-Z0-9]`   | Any character in the range (`a` through `z`, `A` through `Z`, or `0` through `9`)  | `[a-zA-Z0-9]est` | `aest`, `best`, `cest`, ... |
  | `\d`            | Any digit (`0` through `9`)                                                        | `\d\d:\d\d`      | `01:59`, `12:09`, `23:59`   |
  | `\D`            | Any character that is not a digit (`0` through `9`)                                | `\D\D:\D\D`      | `aa:bb`, `12:AB`, `AB:CD`   |
  | `\s`            | Any whitespace character (space, tab, newline, and so on)                          | `a\sb`           | `a b`, `a\tb`, `a\nb`, ...  |
  | `\S`            | Any character that is not a whitespace character (space, tab, newline, and so on)  | `a\Sb`           | `a0b`, `a@b`, `a*b`, ...    |
  | `\w`            | Any word character (letter, number, underscore, and so on)                         | `\w\w\w-\d\d\d`  | `ABC-123`, `XYZ-789`, ...   |
  | `\W`            | Any character that is not a word character (letter, number, underscore, and so on) | `\W\W\W-\d\d\d`  | `!!!-123`, `abc-789`, ...   |

- **Quantifiers**: A quantifier after a token (such as a character) or group specifies how often that preceding element is allowed to occur. The most common quantifiers are the question mark `?`, the asterisk `*`, and the plus sign `+`. The question mark indicates zero or one occurrences of the preceding element. For example, the regular expression `colou?r` matches both `colour` and `color`. The asterisk indicates zero or more occurrences of the preceding element. For example, the regular expression `ab*c` matches `ac`, `abc`, `abbc`, `abbbc`, and so on. The plus sign indicates one or more occurrences of the preceding element. For example, the regular expression `ab+c` matches `abc`, `abbc`, `abbbc`, and so on, but not `ac`.

  | Quantifier | Description                                | Pattern Example | Match Example               |
  | ---------- | ------------------------------------------ | --------------- | --------------------------- |
  | `*`        | Zero or more occurrences                   | `b*`            | `""`, `b`, `bb`, `bbb`, ... |
  | `+`        | One or more occurrences                    | `b+`            | `b`, `bb`, `bbb`, ...       |
  | `?`        | Zero or one occurrence                     | `colou?r`       | `color`, `colour`           |
  | `(n)`      | Exactly n occurrences                      | `a{3}`          | `aaa`                       |
  | `(n, )`    | At least n occurrences                     | `a{3,}`         | `aaa`, `aaaa`, ...          |
  | `(n, m)`   | At least n but not more than m occurrences | `a{3,5}`        | `aaa`, `aaaa`, `aaaaa`      |

- **Boundary Matchers**: A boundary matcher matches a position, not a character. The caret `^` and the dollar sign `$` are the most common boundary matchers. The caret matches the position before the first character in the string. Applying `^a` to `abc` matches `a`. The dollar sign matches the position after the last character in the string. Applying `a$` to `abc` matches `a`. The word boundary matcher `\b` matches the position between a word and a non-word character. Applying `\bno` to `nono` matches the first `no`, while applying `\bno` to `nonono` matches the second `no`.

  | Boundary Matcher | Description                                                   | Pattern Example | Match Example                 |
  | ---------------- | ------------------------------------------------------------- | --------------- | ----------------------------- |
  | `^`              | Matches the position before the first character in the string | `^a`            | `abc` matches `a`             |
  | `$`              | Matches the position after the last character in the string   | `a$`            | `abc` matches `a`             |
  | `\b`             | Matches the position between a word and a non-word character  | `\bno`          | `nono` matches the first `no` |

- **Groups**: A group is a subsequence of tokens contained within parentheses `()`. A group can be used as an atom in a larger expression to allow for regular expression operators to be applied to the entire group. For example, the regular expression `(ab)*` matches zero or more occurrences of the sequence `ab`. The regular expression `(ab)+` matches one or more occurrences of the sequence `ab`. The regular expression `(ab)?` matches zero or one occurrences of the sequence `ab`. The regular expression `(ab){2,4}` matches between two and four occurrences of the sequence `ab`. The regular expression `(ab){2,}` matches two or more occurrences of the sequence `ab`. The regular expression `(ab){2}` matches exactly two occurrences of the sequence `ab`.

- The [Java's Pattern Class](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html) provides a lot of useful methods to perform operations on regular expressions.

### What does it mean to compile a regular expression?

- When you compile a regular expression, you are creating an instance of the `Pattern` class, which represents the regular expression. The `Pattern` class provides no public constructors. To create a pattern, you must first invoke one of its public static `compile()` methods, which will then return a `Pattern` object. These methods accept a regular expression as the first argument. The `compile()` methods also accept a second argument that flags can be passed to control various aspects of the regular expression's matching behavior. The `compile()` methods are overloaded to accept a variety of different argument types.

---

## Pattern and Matcher

- The `Pattern` class of the Java API for regular expressions is the engine that drives the matching process. The `Pattern` class provides no public constructors. To create a pattern, you must first invoke one of its public static `compile()` methods, which will then return a `Pattern` object. These methods accept a regular expression as the first argument. The `compile()` methods also accept a second argument that flags can be passed to control various aspects of the regular expression's matching behavior. The `compile()` methods are overloaded to accept a variety of different argument types. The simplest form accepts a single String argument. For example, the following code compiles a regular expression that matches any string containing the word `cat`:

  ```java
  Pattern pattern = Pattern.compile("cat");
  ```

- The `Matcher` class of the Java API for regular expressions is the engine that drives the matching process. The `Matcher` class provides no public constructors. To create a matcher, you must invoke the `matcher()` method on a `Pattern` object, which will then return a `Matcher` object. The `Matcher` class provides a variety of methods for matching text against a pattern. The `Matcher` class also provides methods for querying the results of a match without actually performing a replacement. The `Matcher` class is also used to modify an input sequence by replacing matches of the pattern with a replacement string. The `Matcher` class provides methods for performing both replacements on a string. The `Matcher` class is also used to modify an input sequence by replacing matches of the pattern with a replacement string. The `Matcher` class provides methods for performing both replacements on a string.

  ```java
  Pattern pattern = Pattern.compile("cat");
  Matcher matcher = pattern.matcher("one cat two cats in the yard");
  StringBuffer sb = new StringBuffer();
  while (matcher.find()) {
      matcher.appendReplacement(sb, "dog");
  }
  matcher.appendTail(sb);
  System.out.println(sb.toString());
  ```

### Matcher's matching methods

All return boolean if the reqular expression matches the input string.

| Method Name       | Description                                                                                                                                         |
| ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- |
| `matches()`       | Attempts to match the entire region against the pattern.                                                                                            |
| `lookingAt()`     | Attempts to match the input sequence, starting at the beginning of the region, against the pattern.                                                 |
| `find()`          | Attempts to find the next subsequence of the input sequence that matches the pattern.                                                               |
| `find(int start)` | Resets this matcher and then attempts to find the next subsequence of the input sequence that matches the pattern, starting at the specified index. |

### Alternate Character Classes

|                                                       | Range            | Predefined Character Class | POSIX charcter classes (US-ASCII only) | java.lang.Character classes |
| ----------------------------------------------------- | ---------------- | -------------------------- | -------------------------------------- | --------------------------- |
| Digits                                                | `[0-9]`          | `\d`                       | `\p{Digit}`                            | `\p{javaDigit}`             |
| Lower case letters                                    | `[a-z]`          |                            | `\p{Lower}`                            | `\p{javaLowerCase}`         |
| Upper case letters                                    | `[A-Z]`          |                            | `\p{Upper}`                            | `\p{javaUpperCase}`         |
| Both Upper and Lower case                             | `[a-zA-Z]`       |                            | `\p{Alpha}`                            | `\p{javaAlphabetic}`        |
| All letters, digits and an underscore, called a word. | `[a-zA-Z_0-9]`   | `\w`                       | `\p{Alnum}`                            | `\p{javaLetterOrDigit}`     |
| Whitespace                                            | `[\t\n\x0B\f\r]` | `\s`                       | `\p{Space}`                            | `\p{javaWhitespace}`        |

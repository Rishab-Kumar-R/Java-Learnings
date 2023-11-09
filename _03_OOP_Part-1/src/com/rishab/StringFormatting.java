package com.rishab;

public class StringFormatting {
    public static void main(String[] args) {

        String bulletIt = "Print a Bulleted List: \n" +
            "\t\u2022 First Point\n" +
            "\t\t\u2022 Sub Point";
        System.out.println(bulletIt);

        // Escape Sequences
        // \n - New Line
        // \t - Tab
        // \b - Backspace
        // \r - Carriage Return
        // \' - Single Quote
        // \" - Double Quote
        // \\ - Backslash

        // The above code looks ugly, let's make use of text blocks
        String textBlock = """
            Print a Bulleted List:
                \u2022 First Point
                    \u2022 Sub Point""";
        System.out.println(textBlock);

        int age = 35;
        System.out.printf("Your age is %d%n", age);
        int yearOfBirth = 2023 - age;
        System.out.printf("Your year of birth is %d%n", yearOfBirth);

        // Format Specifiers
        // %d - Integer
        // %f - Float
        // %c - Character
        // %s - String
        // %b - Boolean
        // %e - Scientific Notation
        // %x - Hexadecimal
        // %h - Hashcode
        // %% - Percent Sign
        // %n - New Line

        for (int i = 1; i <= 100000; i *= 10) {
            System.out.printf("Printing %6d%n", i);
        }

        String formattedString = String.format("Your age is %d", age);
        System.out.println(formattedString);

        formattedString = "Your age is %d".formatted(age);
        System.out.println(formattedString);

        System.out.println();

        printInformation("Hello World");
        printInformation("");
        printInformation("\t    \n");

        String str = "Hello World";
        System.out.printf("indexOf(\"l\") = %d%n", str.indexOf("l"));
        System.out.printf("indexOf(\"World\") = %d%n", str.indexOf("World"));
        System.out.printf("lastIndexOf(\"l\") = %d%n", str.lastIndexOf("l"));
        System.out.printf("indexOf(\"l\") = %d%n", str.indexOf("l", 3));
        System.out.printf("lastIndexOf(\"l\") = %d%n", str.lastIndexOf("l", 8));

        System.out.println();

        String lowerCase = str.toLowerCase();
        if (lowerCase.equals(str)) {
            System.out.println("The strings are equal");
        }
        if (lowerCase.equalsIgnoreCase(str)) {
            System.out.println("The strings are equal but case insensitive");
        }
        if (lowerCase.startsWith("hello")) {
            System.out.println("The string starts with Hello");
        }
        if (lowerCase.endsWith("world")) {
            System.out.println("The string ends with World");
        }
        if (lowerCase.contains("lo w")) {
            System.out.println("The string contains lo w");
        }
        if (lowerCase.contentEquals("hello world")) {
            System.out.println("The strings are equal");
        }

        System.out.println();

        String birthDate = "25/11/1982";
        int startingIndex = birthDate.indexOf("1982");
        System.out.println("startingIndex = " + startingIndex);
        System.out.println("Birth year = " + birthDate.substring(startingIndex, startingIndex + 4));
        System.out.println("Month = " + birthDate.substring(3, 5));

        String newDate = String.join("/", "25", "11", "1982");
        System.out.println("newDate = " + newDate);

        newDate = "25";
        newDate = newDate.concat("/");
        newDate = newDate.concat("11");
        newDate = newDate.concat("/");
        newDate = newDate.concat("1982");
        System.out.println("newDate = " + newDate);

        newDate = "25" + "/" + "11" + "/" + "1982";
        System.out.println("newDate = " + newDate);
        // method chaining
        newDate = "25".concat("/").concat("11").concat("/").concat("1982");
        System.out.println("newDate = " + newDate);

        System.out.println(newDate.replace('/', '-'));
        System.out.println(newDate.replace('2', '$'));
        System.out.println(newDate.replaceFirst("/", "-"));
        System.out.println(newDate.replaceFirst("/", "---"));

        System.out.println("ABC\n".repeat(3));
        System.out.println("-".repeat(20));

        System.out.println("ABC\n".repeat(3).indent(8));
        System.out.println("-".repeat(20));

        System.out.println("    ABC\n".repeat(3).indent(-2));
        System.out.println("-".repeat(20));
    }

    public static void printInformation(String string) {
        int length = string.length();
        System.out.printf("The string is %d characters long%n", length);

        if (string.isEmpty()) {
            System.out.println("The string is empty");
            return;
        }

        if (string.isBlank()) {
            System.out.println("The string is blank");
            return;
        }

        System.out.printf("The first character is %c%n", string.charAt(0));
        System.out.printf("The last character is %c%n", string.charAt(length - 1));
    }
}

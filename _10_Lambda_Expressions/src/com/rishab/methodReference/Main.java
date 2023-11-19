package com.rishab.methodReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

class PlainOld {
    private static int last_id = 1;
    private int id;

    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Created a new instance of PlainOld with id: " + id);
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(
            "Alex", "Brian", "Charles", "David", "Edward", "Franco", "George", "Harry"
        ));
        list.forEach(System.out::println); // instance reference

        calculator((a, b) -> a + b, 5, 5);
        calculator(Integer::sum, 5, 5);

        calculator((a, b) -> a + b, 4.3, 5.7);
        calculator(Double::sum, 4.3, 5.7);

        calculator((s1, s2) -> s1.concat(s2), "Hello", " World");
        calculator(String::concat, "Hello", " World");
        BinaryOperator<String> concatenate = String::concat;
        BiFunction<String, String, String> concatenate2 = String::concat;
        // UnaryOperator<String> concatenate3 = String::concat; // can't use because it takes only one argument
        UnaryOperator<String> toUpperCaseLetter = String::toUpperCase;
        System.out.println(concatenate.apply("Hello", " World"));
        System.out.println(toUpperCaseLetter.apply("hello world"));

        String result = "Hello".transform(toUpperCaseLetter);
        System.out.println("Result: " + result);
        result = result.transform(String::toLowerCase);
        System.out.println("Result: " + result);

        Function<String, Boolean> checkIsEmpty = String::isEmpty;
        boolean resultBoolean = result.transform(checkIsEmpty);
        System.out.println("Result: " + resultBoolean);

        Supplier<PlainOld> plainOldSupplier = PlainOld::new; // constructor reference
        System.out.println("Getting array of PlainOld instances");
        seedArray(PlainOld::new, 10);

    }

    private static <T> void calculator(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}

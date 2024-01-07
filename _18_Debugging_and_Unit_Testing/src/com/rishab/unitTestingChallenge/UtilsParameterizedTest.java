package com.rishab.unitTestingChallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsParameterizedTest {
    private Utils util;

    @BeforeEach
    public void setup() {
        util = new Utils();
    }

    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][]{
            {"ABCDEFF", "ABCDEF"},
            {"AB88EFFG", "AB8EFG"},
            {"112233445566", "123456"},
            {"A", "A"}
        });
    }

    @ParameterizedTest
    @MethodSource("testConditions")
    public void removePairs(String input, String output) {
        assertEquals(output, util.removePairs(input));
    }
}

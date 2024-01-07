package com.rishab.unitTestingChallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    private Utils util;

    @BeforeEach
    void setup() {
        util = new Utils();
    }

    @Test
    void everyNthChar() {
        char[] output = util.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 2);
        assertArrayEquals(new char[]{'e', 'l'}, output);
        char[] output2 = util.everyNthChar(new char[]{'h', 'e', 'l', 'l', 'o'}, 8);
        assertArrayEquals(new char[]{'h', 'e', 'l', 'l', 'o'}, output2);
    }

    @Test
    void removePairs() {
        assertEquals("ABCDEF", util.removePairs("AABCDDEFF"));
        assertEquals("ABCABDEF", util.removePairs("ABCCABDEEF"));
        assertNull(util.removePairs(null), "Did not get null returned when argument passed was null");
        assertEquals("A", util.removePairs("A"), "Did not get A returned when A was passed");
        assertEquals("", util.removePairs(""), "Did not get an empty String returned when an empty String was passed");
    }

    @Test
    void converter() {
        assertEquals(300, util.converter(10, 5));
        assertThrows(ArithmeticException.class, () -> {
            util.converter(10, 0);
        }, "ArithmeticException not thrown");
    }

    @Test
    void nullIfOddLength() {
        assertNull(util.nullIfOddLength("odd"));
        assertNotNull(util.nullIfOddLength("even"));
    }
}

package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// INTENTIONAL TEST ERRORS FOR PARTICIPANTS:
// 1. Missing assertion imports
// 2. Wrong expected values
// 3. Wrong exception type

@SpringBootTest
public class FoodNightmareBrokenTest {

    @Test
    void nightmare_brokenMathStyleSanityTest() {
        assertEquals(5, 2 + 2); // BUG: should expect 4
    }

    @Test
    void nightmare_brokenStringExpectation() {
        String appName = "NitHigh Bites";
        assertEquals("Night Food Court", appName); // BUG: wrong project name
    }

    @Test
    void nightmare_brokenExceptionExpectation() {
        assertThrows(NullPointerException.class, () -> {
            throw new IllegalArgumentException("Invalid input");
        }); // BUG: wrong exception type
    }
}
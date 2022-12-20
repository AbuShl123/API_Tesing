package com.abushl123.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class P02_Junit5Assertions {

    /*
     * HALF assert:
     * - Test execution will be aborted it the assert condition is not matching
     * - Rest of the execution will stop
     */
    @Test
    public void halfAssert() {
        assertEquals(10, 5+5);
        System.out.println("first assertion is done");
        assertEquals(7, 5+5);
        System.out.println("second assertion is done");
        assertEquals(10, 5+5);
        System.out.println("third assertion is done");
    }

    /*
     * SOFT assert:
     * - Test execution will continue till the end of the code even if one of the assertions is failing
     */
    @Test
    public void softAssert() {
        assertAll("Learning soft assert",
                () -> assertEquals(10, 5+5),
                () -> assertEquals(9, 5+5),
                () -> assertEquals(10, 6+4),
                () -> assertEquals(4, 5+5));

    }
}
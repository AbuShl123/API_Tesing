package com.abushl123.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class P06_MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {
        System.out.println("name = " + name);
    }

    public static List<String> getNames() {
        return Arrays.asList("Kimberly", "King", "TJ", "Bond");
    }

    /*
    Can we read data from database
        - Create conn / run query / store them and feed Parametrized

    Can we get data from 3d party APIs (that we consume to get data and use into our API)
        - Use Java knowledge + RestAssured

    --> This makes method source more powerful than others
     */
}

package com.abushl123.day11;

import org.junit.jupiter.api.*;

public class P01_PracticeJunit5AnnotationsLifeCycle {
    @BeforeAll
    public static void init() {
        System.out.println("---> BeforeAll is running");
    }

    @BeforeEach
    public void initEach() {
        System.out.println("---> BeforeEach is running");
    }

    @Test
    public void test1() {
        System.out.println("---> Test 1 is running");
    }

    // ignore test --> TestNG = @ignore
    @Disabled("test2() is ignored") // it is possible to use without message as well
    @Test
    public void test2() {
        System.out.println("---> Test 2 is running");
    }

    @AfterEach
    public void destroyEach(){
        System.out.println("---> AfterEach is running");
    }

    @AfterAll
    public static void destroy() {
        System.out.println("---> AfterAll is running");
    }
}

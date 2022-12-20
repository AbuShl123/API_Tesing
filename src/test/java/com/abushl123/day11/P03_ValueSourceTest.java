package com.abushl123.day11;

import io.restassured.http.ContentType;
import joptsimple.internal.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40, 40})
    @Test
    public void test1(int number) {
        System.out.println(number);
        Assertions.assertTrue(number<=40);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Apple", "Waterfall", "Well", "Sky", "Cloud", "Watermelon"})
    @Test
    public void test2(String word) {
        System.out.println(word);
        Assertions.assertTrue(word.length() <= 10);
    }

    @ParameterizedTest(name = "{index} word is {0}")
    @ValueSource(strings = {"Apple", "Waterfall", "Well", "Sky", "Cloud", "Watermelon"})
    public void test3(String word) {
        System.out.println(word);
        Assertions.assertTrue(word.length() <= 10);
    }

    // Send GET request https://zippopotam.us/us/{zipcode}
    // 22030, 22031, 22032, 22033, 22034, 22035, 22036
    // check status code 200
    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036})
    public void test4(int code) {
        given().baseUri("https://zippopotam.us")
                .pathParam("zipcode", code)
                .when().get("/us/{zipcode}").prettyPeek()
                .then().statusCode(200);
    }
}

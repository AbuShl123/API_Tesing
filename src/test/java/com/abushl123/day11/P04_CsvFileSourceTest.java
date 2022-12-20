package com.abushl123.day11;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P04_CsvFileSourceTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv", numLinesToSkip = 1)
    public void test1(int n1, int n2, int total) {
        System.out.println("n1 = " + n1);
        System.out.println("n2 = " + n2);
        System.out.println("total = " + total);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void test2(String state, String city, int numberPlace) {
        JsonPath jsonPath = given().baseUri("https://zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("/us/{state}/{city}").jsonPath();

        assertEquals(numberPlace, jsonPath.getList("places").size());
    }
}

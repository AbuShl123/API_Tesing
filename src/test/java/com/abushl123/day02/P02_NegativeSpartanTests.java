package com.abushl123.day02;

import com.abushl123.my_own.baseClasses.ApiTestConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests implements ApiTestConstants {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = $URL;
    }

    @Test
    public void getAllSpartans() { // fucked by that honestly
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(RestAssured.baseURI + "/api/spartans");

        assertEquals(200, response.statusCode());
    }

    @Test
    public void test2() {
        Response response = RestAssured
                .given()
                    .accept(ContentType.XML)
                .when()
                    .get(RestAssured.baseURI + "/api/spartans/10");


        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }
}

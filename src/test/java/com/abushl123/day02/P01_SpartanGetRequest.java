package com.abushl123.day02;

import com.abushl123.my_own.baseClasses.ApiTestConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanGetRequest extends ApiTestConstants {
    /*
     * Given to accept is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    @DisplayName("GET all spartans")
    @Test
    public void get_all_spartans() {
        Response response = RestAssured
                .given() // Given to accept is application/json
                    .accept(ContentType.JSON)
                .when() // When user sends GET request /api/spartans endpoint
                    .get($URL + "/api/spartans");

        // Then status code should be 200
        int actualStatusCode = response.getStatusCode();
        assertEquals(200, actualStatusCode);

        // And Content type should be application/json
        String actualContentType = response.contentType();
        assertEquals(actualContentType, "application/json");

        System.out.println("actualContentType = " + actualContentType);

        String header = response.header("Connection");
        System.out.println("header = " + header);

        // get content type with header
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.headers().hasHeaderWithName(\"Transfer-Encoding\") = " +
                response.headers().hasHeaderWithName("Transfer-Encoding"));
    }


    @DisplayName("GET single spartan")
    @Test
    public void getSpartan() {
        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .when().get($URL + "/api/spartans/57");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals(ContentType.JSON.toString(), response.header("Content-Type"));

        System.out.println("response.body().asString().contains(\"Freida\") = " + response.body().asString().contains("Freida"));

//      System.out.println(response.body().asString());

        assertTrue(response.body().asString().contains("\"name\":\"Freida\""));

        response.prettyPrint();
    }

    /*
    Given no headers provided
    When Users send GET request to /api/hello
    Then response status code should be 200
    And Content header should be "text/plain;charset=UTF-8"
    And header should contain Date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */
    @DisplayName("hello spartan")
    @Test
    public void helloSpartan() {
        Response response = RestAssured.get($URL + "/api/hello");

        String actualContentType = response.contentType();
        boolean doesContainDate = response.headers().hasHeaderWithName("Date");
        int actualContentLength = Integer.parseInt(response.getHeader("Content-Length"));

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.TEXT+";charset=UTF-8", actualContentType);
        assertTrue(doesContainDate);
        assertEquals(17, actualContentLength);
        assertEquals("Hello from Sparta", response.body().asString());

        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.headers().toString().contains(\"Date\") = " + doesContainDate);
        System.out.println("response.getBody().asString().length() = " + actualContentLength);

        response.prettyPrint();
    }
}

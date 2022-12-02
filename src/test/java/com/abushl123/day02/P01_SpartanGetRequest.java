package com.abushl123.day02;

import com.abushl123.my_own.baseClasses.ApiTestConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanGetRequest extends ApiTestConstants {
    /*
     * Given to accept is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

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
}

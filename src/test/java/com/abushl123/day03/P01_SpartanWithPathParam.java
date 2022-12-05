package com.abushl123.day03;

import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithPathParam extends SpartanTestBase {
    /*
      Given to accept type is Json
      And ID parameter value is 24
      When user sends GET request to /api/spartans/{id}
      Then response status code should be 200
      And response content-type: application/json
      And "Blythe" should be in response payload(body)
   */

    @DisplayName("GET Spartan /api/spartans/{id} with path param 24")
    @Test
    public void test1() {
        Response response =
                given().
                        accept(ContentType.JSON).
                        and().
                        pathParam("id", 24)
                .when().
                        get(baseURI + "/api/spartans/{id}");

        assertEquals(200, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.getContentType());
        assertTrue(response.getBody().asString().contains("Julio"));

        response.prettyPrint();
    }

    @DisplayName("GET Spartan /api/spartans/{id} with path param and invalid id")
    @Test
    public void test2() {
        Response response = given().
                accept(ContentType.JSON).and()
                .pathParam("id", 500).
                when().get(baseURI + "/api/spartans/{id}");

        assertEquals(404, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("\"error\":\"Not Found\""));

        response.prettyPrint();
    }

    @DisplayName("GET response /api/spartan/search with Query Param")
    @Test
    public void test3() {

        Map<String, Object> parametersMap = new HashMap<>(Map.of(
                "nameContains", "e",
                "gender",       "Female"
        ));

        System.out.println(parametersMap);

        Response response = given()
                .accept(ContentType.JSON).queryParams(parametersMap)
                .when().get("/api/spartans/search");

        // Response status code should be 200
        assertEquals(200, response.statusCode());
        // Response content type: application/json
        assertEquals("application/json", response.contentType());
        // Female should be in response payload(body)
        assertTrue(response.body().asString().contains("Female"));
        // Janette should be in response payload(body)
        assertFalse(response.body().asString().contains("Male"));
    }
}

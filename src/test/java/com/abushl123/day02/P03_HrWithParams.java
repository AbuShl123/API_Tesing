package com.abushl123.day02;

import com.abushl123.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_HrWithParams extends HRTestBase {
    /*
    Given accept type is Json
    And parameters: q = {"region_id":2}
    When users sends a GET request to "/countries"
    Then status code is 200
    And Content type is application/json
    And Payload should contain "United States of America"
    */

    @Test
    public void
    test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParams("e", "\"region_id\": 2")
                .when().get(baseURI + "/countries");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));
    }
}

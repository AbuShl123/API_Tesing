package com.abushl123.day05;

import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_HamcrestSpartan extends SpartanTestBase {
    /*
      Given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
      "id": 15,
      "name": "Meta",
      "gender": "Female",
      "phone": 1938695106
    */

    @DisplayName("Get single spartan with Hamcrest")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().
                        statusCode(200).
//                      statusCode(is(200)).
                        contentType(ContentType.JSON.toString()).
                        body("id", is(15),
                                "name", is("Meta"),
                                "gender", is("Female"),
                                "phone", is(1938695106));
    }
}

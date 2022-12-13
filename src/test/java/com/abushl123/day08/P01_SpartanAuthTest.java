package com.abushl123.day08;

import com.abushl123.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @Test
    public void test1() {
        given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then().log().ifValidationFails().statusCode(401).contentType(ContentType.JSON).
                body("error", is("Unauthorized"));
    }


    @Test
    public void test2() {
        given().accept(ContentType.JSON).
                auth().basic("user", "user").log().all().
                when().get("/api/spartans").
                then().log().ifValidationFails().statusCode(200);
    }

    // DELETE /api/spartan/{id} a EDITOR --> EXPECTED --> 403 --> FORBIDDEN
    @Test
    public void test3() {
        given().pathParam("id", 179)
                .auth().basic("editor", "editor")
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403).body("error", is("Forbidden"));
    }

    @Test
    public void test4() {
        given().pathParam("id", 179)
                .auth().basic("admin", "admin")
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
    }
}

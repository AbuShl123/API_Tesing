package com.abushl123.day10;

import com.abushl123.utilities.test_bases.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class P04_JsonSchemaValidation extends SpartanTestBase {
    // GET api/spartans/{id} to validate with JsonSchemaValidator (InClassPath)
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .pathParam("id", 45)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


    }

    // GET api/spartans/{id} to validate with JsonSchemaValidator (File)
    @Test
    public void test2() {
        given().accept(ContentType.JSON)
                .pathParam("id", 45)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json")));
    }

    // GET api/spartans/search to validate with JsonSchemaValidator
    @Test
    public void test3() {
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200).contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SearchSpartanSchema.json")));
    }
}

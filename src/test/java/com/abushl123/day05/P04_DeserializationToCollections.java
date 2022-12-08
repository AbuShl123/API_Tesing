package com.abushl123.day05;

import com.abushl123.utilities.HRTestBase;
import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P04_DeserializationToCollections extends SpartanTestBase {

    @Test
    public void test1() {
        Response response =
                given().accept(ContentType.JSON).and().pathParam("id", 10).
                when().get("/api/spartans/{id}").
                then().
                        statusCode(200).
                        contentType(ContentType.JSON.toString()).
                        extract().response();

        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);
    }
}

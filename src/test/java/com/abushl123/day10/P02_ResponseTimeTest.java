package com.abushl123.day10;

import com.abushl123.utilities.test_bases.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class P02_ResponseTimeTest extends SpartanAuthTestBase {

    /** Get /api/spartans to get response time */
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when().get("/api/spartans")
                .then().statusCode(200).contentType(ContentType.JSON)
                .time(is(both(lessThan(50_000L)).and(greaterThan(1_000L))))
                .extract().response();

        System.out.println("response.getTimeIn(TimeUnit.SECONDS) = " + response.getTimeIn(TimeUnit.SECONDS));
        System.out.println("response.getTimeIn(TimeUnit.DAYS) = " + response.getTimeIn(TimeUnit.DAYS));
        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));
    }
}

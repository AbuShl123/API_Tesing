package com.abushl123.day08;

import com.abushl123.utilities.BookitTestBase;
import com.abushl123.utilities.utils.BookitUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class P02_BookitTest extends BookitTestBase {

    String accessToken = BookitUtils.getToken("lfinnisz@yolasite.com", "lissiefinnis");

    // "GET /api/campuses"
    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);

    }

//  "GET /api/users/me"
    @Test
    public void test2() {
        System.out.println(accessToken);
        given().accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }
}

package com.abushl123.utilities.utils;

import io.restassured.http.ContentType;
import static org.hamcrest.MatcherAssert.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookitUtils {

    public static String getToken(String email, String password) {

        String accessToken = given().accept(ContentType.JSON)
                .and().queryParam("email", email)
                .and().queryParam("password", password).
                when().get("/sign").
                then().statusCode(200).contentType(ContentType.JSON).
                extract().jsonPath().getString("accessToken");

        assertThat(accessToken, not(emptyOrNullString()));

        return "Bearer " + accessToken;
    }

}

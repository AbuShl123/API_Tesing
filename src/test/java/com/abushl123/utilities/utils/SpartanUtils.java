package com.abushl123.utilities.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.abushl123.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SpartanUtils {

    private SpartanUtils() {} // private constructor

    /**
     * creates new Map with spartan name, gender, phone generated randomly with faker
     * @return Map of (K: String V: Object) newSpartan
     */
    public static Map<String, Object> makeRandomSpartanMap() {
        Map<String, Object> spartanMap = new HashMap<>();

        Faker faker = new Faker(Locale.ITALY);

        spartanMap.put("name", faker.name().firstName());
        spartanMap.put("gender", faker.demographic().sex());
        spartanMap.put("phone", faker.numerify("##########"));

        return spartanMap;
    }




    /**
     * GET /api/spartans
     * @param role
     * @param password
     * @param statusCode
     * @return Response object of all spartans
     */
    public static Response getAllSpartans(String role, String password, int statusCode) {
        return given()
                .accept(ContentType.JSON)
                .auth().basic(role, password)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().response();
    }




    /**
     * GET /api/spartans
     * Automatically authorizes as a user
     * @return Response object of all Spartans
     */
    public static Response getAllSpartans() {
        return given()
                .accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }




    /**
     * GET /api/spartans/{id}
     * @param role
     * @param password
     * @param statusCode
     * @param id to get spartan with particular id
     * @return Response object of provided spartan
     */
    public static Response getSingleSpartan(String role, String password, int statusCode, int id) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic(role, password)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().response();
    }


    /**
     * GET /api/spartans/{id}
     * Automatically authorizes as a user
     * @param id to get spartan with particular id
     * @return Response object of provided spartan
     */
    public static Response getSingleSpartan(int id) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic("user", "user")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }


    /**
     * POST new spartan in API database
     * @param role
     * @param password
     * @param statusCode
     * @return int id of newly created Spartan
     */
    public static int createNewSpartan(String role, String password, int statusCode) {
        return given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(makeRandomSpartanMap())
                .auth().basic(role, password)
                .when().post("/api/spartans")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getInt("id");
    }


}

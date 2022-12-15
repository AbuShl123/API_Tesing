package com.abushl123.utilities.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.abushl123.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

public class SpartanUtils {

    private SpartanUtils() {} // private constructor




    /**
     * creates new Map with spartan name, gender, phone generated randomly with faker
     * @return Map of (K: String V: Object) newSpartan
     */
    public static Map<String, Object> getRandomSpartanMap() {
        Map<String, Object> spartanMap = new HashMap<>();

        Faker faker = new Faker(Locale.ITALY);

        spartanMap.put("name", faker.name().firstName());
        spartanMap.put("gender", faker.demographic().sex());
        spartanMap.put("phone", Long.valueOf(faker.numerify("30########")));

        return spartanMap;
    }




    /**
     * GET /api/spartans from API.
     * @param role name of user
     * @param password password of user
     * @param statusCode expected status code
     * @param contentType expected content type of response
     * @return Response object of all spartans
     */
    public static Response getAllSpartans(String role, String password, int statusCode, String contentType) {
        return given()
                .accept(contentType)
                .auth().basic(role, password).log().ifValidationFails()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(statusCode)
                .contentType(contentType)
                .extract().response();
    }

    /**
     * GET /api/spartans from API.
     * Automatically authorizes as a user
     * @return Response object of all Spartans
     */
    public static Response getAllSpartans() {
        return given()
                .accept(ContentType.JSON)
                .auth().basic("user", "user").log().ifValidationFails()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }




    /**
     * GET /api/spartans/{id} from API.
     * @param role name of user
     * @param password password of user
     * @param statusCode expected status code
     * @param id to get spartan with particular id
     * @return Response object of provided spartan
     */
    public static Response getSingleSpartan(String role, String password, int id, int statusCode) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic(role, password).log().ifValidationFails()
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().response();
    }

    /**
     * GET /api/spartans/{id} from API.
     * Automatically authorizes as a user
     * @param id to get spartan with particular id
     * @param statusCode expected status code
     * @return Response object of provided spartan
     */
    public static Response getSingleSpartan(int id, int statusCode) {
        return given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic("user", "user").log().ifValidationFails()
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .extract().response();
    }




    /**
     * POST new spartan in API database
     * @param role name of user
     * @param password password of user
     * @param statusCode expected status code
     * @param contentType expected content type of response
     * @return Response post
     */
    public static Response postNewSpartan(String role, String password, int statusCode, String contentType) {
        return given().accept(contentType)
                .contentType(ContentType.JSON)
                .body(getRandomSpartanMap())
                .auth().basic(role, password).log().ifValidationFails()
                .when().post("/api/spartans")
                .then()
                .statusCode(statusCode)
                .contentType(contentType)
                .extract().response();
    }




    /**
     * PUT /api/spartans/{id} to API.
     * Updates an existing spartan with random properties
     * @param role name of user
     * @param password password of user
     * @param id id of spartan to update
     * @param statusCode expected status code
     */
    public static void putSpartan(String role, String password, int id, int statusCode) {
        Map<String, Object> body = getRandomSpartanMap();
        given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().basic(role, password).log().ifValidationFails()
                .when().put("/api/spartans/{id}")
                .then().statusCode(statusCode);

        if(statusCode == 200) verifySpartan(id, body);
    }

    /**
     * PUT /api/spartans/{id} to API.
     * Updates an existing spartan with provided body
     * @param role name of user
     * @param password password of user
     * @param id id of spartan to update
     * @param statusCode expected status code
     * @param body body of put method
     */
    public static void putSpartan(String role, String password, int id, int statusCode, Map<String, Object> body) {
        given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().basic(role, password).log().ifValidationFails()
                .when().put("/api/spartans/{id}")
                .then().statusCode(statusCode);
    }

    /**
     * PUT /api/spartans/{id} to API.
     * Updates an existing spartan with provided body
     * @param role name of user
     * @param password password of user
     * @param id id of spartan to update
     * @param statusCode expected status code
     * @param body body of put method
     */
    public static void putSpartan(String role, String password, int id, int statusCode, String body) {
        given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().basic(role, password).log().ifValidationFails()
                .when().put("/api/spartans/{id}")
                .then().statusCode(statusCode);
    }

    /**
     * PUT /api/spartans/{id} to API.
     * Updates an existing spartan with provided body
     * @param role name of user
     * @param password password of user
     * @param id id of spartan to update
     * @param statusCode expected status code
     * @param body body of put method
     */
    public static void putSpartan(String role, String password, int id, int statusCode, Spartan body) {
        given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().basic(role, password).log().ifValidationFails()
                .when().put("/api/spartans/{id}")
                .then().statusCode(statusCode);
    }




    /**
     * PATCH /api/spartans/{id} to API.
     * Updates existing spartan with provided info
     * @param role name of user
     * @param password password of user
     * @param id id of spartan to update
     * @param statusCode expected status code
     * @param body body of patch method
     */
    public static void patchSpartan(String role, String password, int id, int statusCode, String body) {
        given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().basic(role, password).log().ifValidationFails()
                .when().patch("/api/spartans/{id}")
                .then().statusCode(statusCode);

    }




    public static void deleteSpartan(String role, String password, int id, int statusCode) {
        given().pathParam("id", id)
                .auth().basic(role, password)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(statusCode);
    }




    /**
     * Gets one spartan from API and verifies its data with provided ExpectedProperties
     * @param id id of existing spartan
     * @param expectedProperties expected results to make assertions
     */
    public static void verifySpartan(int id, Map<String, Object> expectedProperties) {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic("user", "user").log().ifValidationFails()
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().jsonPath();

        assertEquals(jsonPath.getString("name"), expectedProperties.get("name"));
        assertEquals(jsonPath.getString("gender"), expectedProperties.get("gender"));
        assertEquals(jsonPath.getLong("phone"), expectedProperties.get("phone"));
    }

    /**
     * Gets from API one spartan and verifies its data with provided ExpectedProperties
     * @param id id of existing spartan
     * @param expectedProperties expected results to make assertions
     */
    public static void verifySpartan(int id, Spartan expectedProperties) {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic("user", "user").log().ifValidationFails()
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().jsonPath();

        assertEquals(jsonPath.getString("name"), expectedProperties.getName());
        assertEquals(jsonPath.getString("gender"), expectedProperties.getGender());
        assertEquals(jsonPath.getLong("phone"), expectedProperties.getPhone());
    }

    /**
     * Takes two spartans as arguments and compares them
     * @param spartan actual spartan to verify
     * @param expectedProperties expected results to make assertions
     */
    public static void verifySpartan(Spartan spartan, Spartan expectedProperties) {
        assertEquals(spartan.getName(), expectedProperties.getName());
        assertEquals(spartan.getGender(), expectedProperties.getGender());
        assertEquals(spartan.getPhone(), expectedProperties.getPhone());
    }

    /**
     * Takes two spartans as arguments and compares them
     * @param spartan actual spartan to verify
     * @param expectedProperties expected results to make assertions
     */
    public static void verifySpartan(Spartan spartan, Map<String, Object> expectedProperties) {
        assertEquals(spartan.getName(), expectedProperties.get("name"));
        assertEquals(spartan.getGender(), expectedProperties.get("gender"));
        assertEquals(spartan.getPhone(), expectedProperties.get("phone"));
    }
}

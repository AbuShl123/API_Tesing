package com.abushl123.day07;

import com.abushl123.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.abushl123.utilities.test_bases.SpartanTestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanPost extends SpartanTestBase {

    static String expectedMessage = "A Spartan is Born!";

    @DisplayName("POST Spartan with String body")
    @Test
    public void test1() {
        String requestBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Mike\",\n" +
                "  \"phone\": 1234567890\n" +
                "}";

        JsonPath jsonPath = given().accept(ContentType.JSON)  // API send me response in json format
                .contentType(ContentType.JSON) // API I am sending body in JSON format
                .body(requestBody)  // This is that we want to POST in API
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();


        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals("Mike", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("1234567890", jsonPath.getString("data.phone"));

        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

        // If you want to do assertion with GET Request we can use it as Path param to specify
        /*
         {
          "success": "A Spartan is Born!",
          "data": {
                   "id": 379,
                   "name": "Mike",
                   "gender": "Male",
                   "phone": 1234567890
                  }
          }
         */
    }


    @Test
    public void test2() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "James Bond");
        requestBody.put("gender", "Male");
        requestBody.put("phone", 9000812312L);

        JsonPath jsonPath =
                given()
                        .accept(ContentType.JSON).log()
                        .body().contentType(ContentType.JSON)
                        .body(requestBody)
                .when()
                        .post("/api/spartans").prettyPeek()
                .then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().jsonPath();

        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals("James Bond", jsonPath.getString("data.name"));
        assertEquals(9000812312L, jsonPath.getLong("data.phone"));

        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);
    }

    @Test
    public void test3() {
        Spartan requestBody = new Spartan();
        requestBody.setName("Hercules");
        requestBody.setGender("Male");
        requestBody.setPhone(88834911291L);

        System.out.println("requestBody = " + requestBody);

        JsonPath jsonPath =
                given().accept(ContentType.JSON).log()
                        .body().contentType(ContentType.JSON)
                        .body(requestBody)
                .when().post("/api/spartans").prettyPeek()
                        .then().statusCode(201).contentType(ContentType.JSON)
                        .extract().jsonPath();

        assertEquals(expectedMessage, jsonPath.getString("success"));
        assertEquals(requestBody.getName(), jsonPath.getString("data.name"));
        assertEquals(requestBody.getGender(), jsonPath.getString("data.gender"));
        assertEquals(requestBody.getPhone(), jsonPath.getLong("data.phone"));

        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);
    }

    @Test
    public void test4() {
        Spartan requestBody = new Spartan();
        requestBody.setName("Hercules");
        requestBody.setGender("Male");
        requestBody.setPhone(88834911291L);

        System.out.println("requestBody = " + requestBody);

        int idOfNewSpartan = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/spartans").prettyPeek()
                .then().statusCode(201).contentType(ContentType.JSON)
                .extract().jsonPath().getInt("data.id");

        System.out.println("idOfNewSpartan = " + idOfNewSpartan);

        Spartan newSpartan = given().contentType(ContentType.JSON).pathParam("id", idOfNewSpartan)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response().as(Spartan.class);

        assertEquals(requestBody.getName(), newSpartan.getName());
        assertEquals(requestBody.getGender(), newSpartan.getGender());
        assertEquals(requestBody.getPhone(), newSpartan.getPhone());

    }
}


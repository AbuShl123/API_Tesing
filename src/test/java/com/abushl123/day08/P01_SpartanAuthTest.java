package com.abushl123.day08;

import com.abushl123.utilities.test_bases.SpartanAuthTestBase;
import io.restassured.http.ContentType;
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

    /**
     * HOMEWORKS
     *
     *      ROLE Based Control Test --> RBAC
     *
     *      ADMIN   --> GET   POST    PUT     PATCH   DELETE   --> Spartan Flow
     *      EDITOR  --> GET   POST    PUT     PATCH   403
     *      USER    --> GET   403     403     403     403
     *      GUEST   --> 401   401     401     401     401
     *
     *  -- Create RBAC Test for all different roles from Spartan Application including with Negative Test cases
     *  -- Create SpartanUtil Class
     *            public static Map<String, Object> SpartanUtil.getRandomSpartan(){
     *                Map<String, Object> spartanMap = new HashMap();
     *                spartanMap.put("name", faker.funnyName());
     *
     *                user Faker class to create each time different spartan information
     *
     *                return spartanMap
     *            }
     *
     *            public static void GETSpartans (String role, String password, int statusCode, int id){
     *
     *                given().pathParam("id", id)
     *                .auth().basic(role, password).
     *                when().delete("/api/spartans/{id}").then().statusCode(statusCode);
     *
     *
     *            }
     *
     *      Q --> can we create class in utilities class with loop for each user, admin, guest with passport make more dynamic
     *              - YES we can but what if first user test is failing
     *
     *              - We should od it --> Data Driven Test
     *
     *              - JUnit5 DDT to implement
     *
     */
}

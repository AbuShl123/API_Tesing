package com.abushl123.day03;

import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {
    @DisplayName("GET spartan with response path")
    @Test
    public void test1() {
        Response response =
                given()
                    .accept(ContentType.JSON)
                    .and()
                    .pathParam("id", 5)
                .when()
                    .get(baseURI + "/api/spartans/{id}");

        assertEquals(200, response.statusCode());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        String address = response.path("address");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
        System.out.println("address = " + address); // address is null -- because there's no such field

        assertEquals(5, id);
        assertEquals("Blythe", name);
        assertEquals("Female", gender);
        assertEquals(3677539542L, phone);

    }
}

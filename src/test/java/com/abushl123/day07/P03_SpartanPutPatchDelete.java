package com.abushl123.day07;

import com.abushl123.pojo.Spartan;
import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_SpartanPutPatchDelete extends SpartanTestBase {
    @Test
    public void test1() {
        Spartan requestBody = new Spartan();
        requestBody.setName("Hercules");
        requestBody.setGender("Male");
        requestBody.setPhone(88034911091L);

        int existingID = 400;

        given()
                // .accept(ContentType.JSON); since we are not getting any response we don't need this line
                .log().body()
                .contentType(ContentType.JSON)
                .pathParam("id", existingID)
                .body(requestBody)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204); // 204 - success but no response body

        // to test did really put work - we can use get and then just assert

        Spartan updatedSpartan = given().accept(ContentType.JSON)
                .pathParam("id", existingID)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response().as(Spartan.class);

        System.out.println(updatedSpartan);

        assertEquals(requestBody.getName(), updatedSpartan.getName());
        assertEquals(requestBody.getGender(), updatedSpartan.getGender());
        assertEquals(requestBody.getPhone(), updatedSpartan.getPhone());

    }
}

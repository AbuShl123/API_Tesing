package com.abushl123.day06;

import com.abushl123.pojo.Spartan;
import com.abushl123.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P02_SpartanDeserialization extends SpartanTestBase {



    @DisplayName("Get single spartan for deserializing to POJO")
    @Test
    public void test1() {
        Response response =
                given().
                        accept(ContentType.JSON).and().
                        pathParam("id", 15).
                when().
                        get("/api/spartans/{id}").
                then().
                        statusCode(200).
                        extract().response();

        Spartan spartan = response.as(Spartan.class);
        System.out.println(spartan);

        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());
        System.out.println("spartan.getGender() = " + spartan.getPhone());

        JsonPath jsonPath = response.jsonPath();
        Spartan spartanJsonPath = jsonPath.getObject("", Spartan.class);
        System.out.println("\n" + spartanJsonPath);
    }
}

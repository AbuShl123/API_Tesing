package com.abushl123.day05;

import com.abushl123.utilities.test_bases.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class P04_DeserializationToCollections extends SpartanTestBase {

    @Test
    public void test1() {
        Response response =
                given().accept(ContentType.JSON).and().pathParam("id", 10).
                when().get("/api/spartans/{id}").
                then().
                        statusCode(200).
                        contentType(ContentType.JSON.toString()).
                        extract().response();

        Map<String, Object> spartanMap = response.as(Map.class);
        System.out.println(spartanMap);
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then().statusCode(200).contentType(ContentType.JSON.toString()).extract().response();

        List<Map<String, Object>> listResponse = response.as(List.class);

        for (Map<String, Object> eachMap : listResponse) {
            System.out.println(eachMap);
        }

        System.out.println("listResponse.get(0).get(\"id\") = " + listResponse.get(0).get("id"));

        System.out.println("listResponse.get(listResponse.size()-1).get(\"name\") = " + listResponse.get(0).get("name"));

        System.out.println("listResponse.get(0).get(\"gender\") = " + listResponse.get(0).get("gender"));

        System.out.println("listResponse.get(0).get(\"phone\") = " + listResponse.get(0).get("phone"));

        Map<String, Object> mapOf0 = response.path("[0]");
        System.out.println(mapOf0);


        System.out.println("\n\n\n\n-----------------------------------\n\n\n\n");
        // through jsonPath
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> jsonPathList = jsonPath.getList("");
        for (Map<String, Object> eachMap : jsonPathList) {
            System.out.println(eachMap);
        }

        // spartan with index 15 (10+1+4 = 15) (because id of spartans start from 4 and list 0-indexed)
        System.out.println("jsonPathList.get(10).get(\"id\") = " + jsonPathList.get(10).get("id"));
        System.out.println("jsonPathList.get(10).get(\"name\") = " + jsonPathList.get(10).get("name"));
        System.out.println("jsonPathList.get(10).get(\"gender\") = " + jsonPathList.get(10).get("gender"));
        System.out.println("jsonPathList.get(10).get(\"phone\") = " + jsonPathList.get(10).get("phone"));
    }
}

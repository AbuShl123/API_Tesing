package com.abushl123.day06;

import com.abushl123.utilities.test_bases.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P01_HrDeserialization extends HRTestBase {
    /*
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */

    @DisplayName("GET /locations to deserilization into Java Collections")
    @Test
    public void test1() {
        JsonPath jsonPath = given().accept(ContentType.JSON).
                when().get("/locations").
                then().statusCode(200).extract().response().jsonPath();

        System.out.println("\n====== GET FIRST LOCATION  ======");
        System.out.println(jsonPath.getMap("items[0]"));

        System.out.println("\n====== GET FIRST LOCATION LINKS  ======");
        List<Map<String, Object>> firstLocationLinks = jsonPath.getList("items[0].links");
        System.out.println(firstLocationLinks);

        System.out.println("\n====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String, Object>> allLocations = jsonPath.getList("items");

        System.out.println("\n====== FIRST LOCATION ======");
        System.out.println(allLocations.get(0));
        System.out.println("\n====== FIRST LOCATION ID ======");
        System.out.println(allLocations.get(0).get("location_id"));

        System.out.println("\n====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println(allLocations.get(0).get("country_id"));

        System.out.println("\n====== GET FIRST LOCATION FIRST LINK  ====== ");
        List<Map<String, Object>> firstLinks = (List<Map<String, Object>>) allLocations.get(0).get("links");
        System.out.println(firstLinks.get(0).get("href"));

        System.out.println("\n====== LAST LOCATION ID ======");
        System.out.println(allLocations.get(allLocations.size()-1).get("location_id"));
    }
}

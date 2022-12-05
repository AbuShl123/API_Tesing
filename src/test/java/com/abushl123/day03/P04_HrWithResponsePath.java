package com.abushl123.day03;

import com.abushl123.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HRTestBase {
    @DisplayName("GET request to countries wit using Response Path")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .with().get("/countries");

//      response.prettyPrint();

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        System.out.println("response.path(\"items[1].country_id\") = " + response.path("items[1].country_id"));

        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));

        System.out.println("response.path(\"item[3].links[1]\") = " + response.path("items[3].links[0].href"));


        List<String> countryNames = response.path("items.country_name");

        System.out.println("countryNames = " + countryNames);

        // verify all region id is true
        List<Integer> allRegionIds = response.path("items.region_id");
        for (Integer eachId : allRegionIds) {
            assertEquals(2, eachId);
        }

        assertTrue(allRegionIds.stream().allMatch(e -> e==2));

    }
}

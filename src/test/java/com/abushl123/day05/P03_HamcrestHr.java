package com.abushl123.day05;

import com.abushl123.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P03_HamcrestHr extends HRTestBase {

    /*
    Given accept type is Json
    And parameters q = {"job_id":"IT_PROG")
    When users send a GET request to "/employees"Then status code is 200
    And Content type is application/json
    Verify
        - each employees has manager
        - each employees working as IT_PROG
        - each of them getting salary greater than 3000
        - first names are .... (find proper method to check list against list)
        - emails without checking order (provide emails in different order, just make sure it has same emails)
 */

    @Test
    public void test1() {
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON).
                and().queryParams("q", "{\"job_id\":\"IT_PROG\"}").
                when().get("/employees").prettyPeek().
                then().
                        statusCode(200).
                        contentType(ContentType.JSON.toString()).
                        body("items.manager_id", everyItem(notNullValue()),
                                "items.job_id", everyItem(is("IT_PROG")),
                                "items.salary", everyItem(greaterThan(3000)),
                                "items.first_name", equalTo(names),
                                "items.email", containsInAnyOrder("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ"));


    }

}

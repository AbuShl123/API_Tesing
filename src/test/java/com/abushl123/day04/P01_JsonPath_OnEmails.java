package com.abushl123.day04;

import com.abushl123.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_JsonPath_OnEmails extends HRTestBase {
    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 2000)
                .when().get("/employees");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // getting all emails
        List<String> allEmails = response.jsonPath().getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());

        // getting all email who is working as IT_PROG
        List<String> it_progs = response.jsonPath().getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("it_progs = " + it_progs);

        // get first names of all employees whose salary is more than 10_000
        List<String> moreThan10_000 = response.jsonPath().getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println("employees who work for more than 10_000 salary = " + moreThan10_000);

        // get me all info about employee who has max salary
        System.out.println("max salary = " + response.jsonPath().getString("items.max{it.salary}"));

        // get me first_name from Employees who have max salary
        System.out.println("max salary (names) = " + response.jsonPath().getString("items.max{it.salary}.first_name"));

        // get me first_name from Employees who have min salary
        System.out.println("max salary (names) = " + response.jsonPath().getString("items.min{it.salary}.first_name"));
    }
}

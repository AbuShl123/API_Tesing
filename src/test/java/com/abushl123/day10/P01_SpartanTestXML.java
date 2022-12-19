package com.abushl123.day10;

import com.abushl123.utilities.test_bases.SpartanAuthTestBase;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class P01_SpartanTestXML extends SpartanAuthTestBase {

    // GET /api/spartans with using XML Path
    @Test
    public void test1() {
        Response response = given().accept(ContentType.XML)
                .auth().basic("user", "user")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200).contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
//               .body("List.item[0].gender", is("Male"))
                .extract().response();

        response.prettyPeek();
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.XML)
                .auth().basic("user", "user")
                .when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        System.out.println("xmlPath.get(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));
        System.out.println("xmlPath.getString(\"List.item[2].name\") = " + xmlPath.getString("List.item[2].name"));

        System.out.println(xmlPath.getString("List.item[-1].name"));

        System.out.println(xmlPath.getList("List.item.name"));

        System.out.println("number of spartans: " + xmlPath.getList("List.item").size());
    }


}

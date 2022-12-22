package com.abushl123.day13;

import com.abushl123.utilities.test_bases.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends SpartanNewTestBase {

    @Test
    public void getAllSpartans() {
            given().accept(ContentType.JSON)
                    .auth().basic("user", "user")
                    .when().get("/spartans").prettyPeek()
                    .then().statusCode(200).contentType(ContentType.JSON)
                    .body("id[0]", is(0)) // stops execution here and prints one error message
                    .body("id[1]", is(1)); // does not run the rest of the code
    }

    @Test
    public void getAllSpartansOldWay() {
        given().accept(ContentType.JSON)
                .auth().basic("user", "user")
                .expect().statusCode(200).contentType(ContentType.JSON)
                .body("id[0]", is(0)) // although assertion fails it will continue checking other test scripts
                .body("id[1]", is(1)) // as a result it will print two error messages in console
                .when().get("/spartans").prettyPeek();
    }

    /*
    old way works like a soft-assertion  (expect())
    but new way implements hard-assertion (then())
     */
}

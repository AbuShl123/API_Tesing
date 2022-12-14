package com.abushl123.utilities.test_bases;

import com.abushl123.my_own.baseClasses.ApiTestConstants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {



    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://34.203.212.11:8000";
    }

}

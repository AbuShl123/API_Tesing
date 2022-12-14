package com.abushl123.utilities.test_bases;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://34.203.212.11:7000";
    }
}

package com.abushl123.utilities.test_bases;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanNewTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://34.203.212.11";
        port = 7000;
        basePath = "/api";
        // baseURI + port + basePath
    }

}

package com.abushl123.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HRTestBase {
    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://3.80.111.193:1000/ords/hr";
    }
}

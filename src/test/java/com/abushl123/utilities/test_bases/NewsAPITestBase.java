package com.abushl123.utilities.test_bases;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class NewsAPITestBase {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://newsapi.org/v2";
    }
}

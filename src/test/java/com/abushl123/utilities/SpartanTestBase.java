package com.abushl123.utilities;

import com.abushl123.my_own.baseClasses.ApiTestConstants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase implements ApiTestConstants {



    @BeforeAll
    public static void init() {
        RestAssured.baseURI = $URL;
    }

}

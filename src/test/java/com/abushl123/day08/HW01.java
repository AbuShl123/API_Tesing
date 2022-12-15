package com.abushl123.day08;

import com.abushl123.pojo.Spartan;
import com.abushl123.utilities.test_bases.SpartanAuthTestBase;
import static com.abushl123.utilities.utils.SpartanUtils.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class HW01 extends SpartanAuthTestBase {

    @DisplayName("admin test")
    @Test
    public void test1() {
        getAllSpartans("admin", "admin", 200, ContentType.JSON.toString());

        postNewSpartan("admin", "admin", 201, ContentType.JSON.toString());

        putSpartan("admin", "admin", 56, 204); // this method updates spartan and then automatically validates if its updated or not.

        patchSpartan("admin", "admin", 75, 204, "{\"name\": \"Hercules\"}");

        deleteSpartan("admin", "admin", 311, 204);
        getSingleSpartan(311, 404); // verifying that spartan was actually deleted (404)
    }

    @DisplayName("editor test")
    @Test
    public void test2() {
        getAllSpartans("editor", "editor", 200, ContentType.JSON.toString());

        postNewSpartan("editor", "editor", 201, ContentType.JSON.toString());

        Map<String, Object> newSpartan = getRandomSpartanMap();
        putSpartan("editor", "editor", 56, 204, newSpartan); // unlike put method on line 22 this one doesn't validate by itself
        verifySpartan(56, newSpartan); // so I am calling validation method explicitly

        patchSpartan("editor", "editor", 75, 204, "{\"gender\": \"Female\"}"); // I don't know how to validate this -_-

        deleteSpartan("editor", "editor", 310, 403);
        getSingleSpartan(310, 200); // verifying that spartan was not deleted
    }

    @DisplayName("user test")
    @Test
    public void test3() {
        getAllSpartans("user", "user", 200, ContentType.JSON.toString());

        postNewSpartan("user", "user", 403, ContentType.JSON.toString());

        putSpartan("user", "user", 56, 403, new Spartan("Hercules", "Male", 14582360645L));

        patchSpartan("user", "user", 75, 403, "{\"name\": \"Leland\"}");

        deleteSpartan("user", "user", 309, 403);
        getSingleSpartan(309, 200); // verifying that spartan was not deleted
    }

    @DisplayName("unauthorized user test")
    @Test
    public void test4() {
        getAllSpartans("", "", 401, "");

        postNewSpartan("", "", 401, "");

        putSpartan("", "", 56, 401);

        patchSpartan("", "", 75, 401, "{\"name\": \"Hercules\"}");

        deleteSpartan("", "", 308, 401);
        getSingleSpartan(308, 200); // verifying that Spartan was not deleted
    }
}

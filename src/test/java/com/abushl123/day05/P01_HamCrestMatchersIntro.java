package com.abushl123.day05;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_HamCrestMatchersIntro {
    @Test
    public void numbers(){
        // it comes from JUnits to make assertions
        assertEquals(7, 3+4);

        // comes from restAssured dependency
        assertThat(9, is(6+3));

        assertThat(9, is(equalTo(6+3)));

        assertThat(9, equalTo(6+3));

        assertThat(1+4, not(6));
        assertThat(1+4, is(not(9)));
        assertThat(5+5, is(not(equalTo(11))));


        assertThat(98, greaterThan(23));
        assertThat(100, is(greaterThan(34)));
        assertThat(6+1, lessThan(14));
        assertThat(4+1, is(lessThan(45)));

    }

    @Test
    public void testStrings() {
        String msg = "Never give up";

        assertThat(msg, is("Never give up"));
        assertThat(msg, is(equalTo("Never give up")));
        assertThat(msg, is(not("API is fun")));

        msg = "API is fun";
        assertThat(msg, is("API is fun"));
        assertThat(msg, is(equalToIgnoringCase("api is fun")));

        assertThat(msg, startsWith("API"));
        assertThat(msg, endsWith("fun"));
        assertThat(msg, startsWithIgnoringCase("api"));
        assertThat(msg, endsWithIgnoringCase("FUN"));

        assertThat(msg, containsString("is"));
        assertThat(msg, containsStringIgnoringCase("iS fUn"));
    }

    @Test
    public void testCollections() {
        List<Integer> listOfNumbers = Arrays.asList(11, 33, 55, 64, 2, 10, 1);

        assertThat(listOfNumbers, hasSize(7));

        assertThat(listOfNumbers, hasItem(64));
        assertThat(listOfNumbers, hasItems(11, 33, 2));
//      assertThat(listOfNumbers, hasItems(11, 55, 8)); fails

        assertThat(listOfNumbers, hasItem(greaterThan(50)));
//      assertThat(listOfNumbers, hasItem(greaterThan(100))); fails

//      assertThat(listOfNumbers, everyItem(greaterThan(1))); fails

        assertThat(listOfNumbers, everyItem(greaterThanOrEqualTo(1)));
        assertThat(listOfNumbers, containsInRelativeOrder(11, 33, 55));
//        assertThat(listOfNumbers, containsInRelativeOrder(11, 55, 33)); fails
    }
}

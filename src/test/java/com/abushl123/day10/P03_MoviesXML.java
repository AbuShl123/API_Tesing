package com.abushl123.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class P03_MoviesXML {
    @Test
    public void test1() {
        Response response = given().queryParam("t","Superman")
                .queryParam("r","xml")
                .queryParam("apikey","81815fe6")
                .when().get("http://www.omdbapi.com").prettyPeek();

        // create XML path
        XmlPath xmlPath = response.xmlPath();

        // get year attribute
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));

        // get year title
        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));

        // get year genre
        System.out.println("xmlPath.getString(\"root.movie.@genre\") = " + xmlPath.getString("root.movie.@genre"));

        // get write
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@writer"));
    }

    public static void test2() {

        while (true) {
            System.out.println("Please type in the movie you want to take info about");
            String movieName = new Scanner(System.in).next();
            Response response = given().queryParam("t",movieName)
                    .queryParam("r","xml")
                    .queryParam("apikey","81815fe6")
                    .queryParam("page", 2)
                    .when().get("http://www.omdbapi.com");

            if (!response.body().asString().contains("<error>Movie not found!</error>")) printInfo(response.xmlPath());
            else System.out.println("Please write down a correct movie name!");
        }

    }

    public static void printInfo(XmlPath xmlPath) {
        String[] attributes = new String[] {"title", "year", "rated", "released", "runtime", "genre", "director", "writer", "actors", "plot", "language"
        , "country", "awards", "poster", "metascore", "imdbRating", "imdbVotes", "imdbID", "type"};

        for (String attribute : attributes)
            System.out.println(attribute + ": "  + xmlPath.getString("root.movie.@" + attribute));
    }

    public static void main(String[] args) {
        test2();
    }
}

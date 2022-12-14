package com.abushl123;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Time {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);

        for (int i = 0 ; i < 10; i++) {
            System.out.println(faker.name().firstName());
        }
    }
}

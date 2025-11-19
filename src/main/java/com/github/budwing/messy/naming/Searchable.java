package com.github.budwing.messy.naming;

public class Searchable {
    public static final String SLOGAN = "USE SEARCHABLE NAMES";

    /**
     * Non-searchable names make it difficult to search the code.
     * a: age
     * b: birth date
     * c: customer id
     */
    private int a = 30;
    private String b = "1990-01-01";
    private String c = "C123456";

    public void week(String s) {
        System.out.println("Age: " + a + ", Birth Date: " + b + ", Customer ID: " + c);

        for (int i = 0; i < 7; i++) { // 7 is hard to search
            System.out.println("day in a week: " + i);
        }
    }
}

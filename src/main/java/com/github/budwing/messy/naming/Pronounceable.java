package com.github.budwing.messy.naming;

import java.util.Date;

public class Pronounceable {
    public static final String SLOGAN = "USE PRONOUNCEABLE NAMES";

    private class DtaRcrd102 {
        /**
         * Abbreviated, non-pronounceable names make it difficult to discuss the code.
         * genymdhms: generation year month day hour minute second
         * modymdhms: modification year month day hour minute second
         * pszqint: persistent storage zillionth quintillionth integer
         */
        private Date genymdhms;
        private Date modymdhms;
        private final String pszqint = "102";
    };
}
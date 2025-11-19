package com.github.budwing.messy.naming;

import java.util.ArrayList;
import java.util.List;

public class MeaningfulDistinction {
    public static final String SLOGAN = "MAKE MEANINGFUL DISTINCTION";

    /**
     * Copies characters from one array to another.
     * 
     * But the names a1 and a2 do not convey any meaningful distinction between the two arrays.
     * You never know which is the source and which is the destination.
     * We should choose names that reflect their specific roles.
     * @param a1
     * @param a2
     */
    public void copyChars(char a1[], char a2[]) {
        for (int i = 0; i < a1.length; i++) {
            a2[i] = a1[i];
        }
    }

    /** 
     * data and info are the noise words that do not convey any meaningful distinction.
     * We should choose names that reflect their specific roles.
     */
    public void example() {
        List d = getProductData();
        setProductInfo(d);
    }

    private List getProductData() {
        // ...

        return new ArrayList();
    }

    private void setProductInfo(List data) {
        // ...
    }
}

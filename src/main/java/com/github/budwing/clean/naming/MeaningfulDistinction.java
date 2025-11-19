package com.github.budwing.clean.naming;

import java.util.ArrayList;
import java.util.List;

public class MeaningfulDistinction {
    public static final String SLOGAN = "MAKE MEANINGFUL DISTINCTION";

    /**
     * By using names like source and destination, we make a meaningful distinction
     * between the two arrays. The user of the method will never be confused about
     * which array is the source and which is the destination.
     * 
     * @param source
     * @param destination
     */
    public void copyChars(char[] source, char[] destination) {
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    /** 
     * Either using same name if productData and productInfo represent the same concept,
     * or using distinct names that reflect their specific roles.
     */
    public void example() {
        List d = getProduct();
        setProduct(d);
    }

    private List getProduct() {
        // ...

        return new ArrayList();
    }

    private void setProduct(List data) {
        // ...
    }
}

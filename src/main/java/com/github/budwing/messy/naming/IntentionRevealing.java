package com.github.budwing.messy.naming;

import java.util.ArrayList;
import java.util.List;

public class IntentionRevealing {
    public static final String SLOGAN = "USE INTENTION-REVEALING NAMES";

    /**
     * Example of intention-revealing variable names.
     * 
     * The name d reveals nothing. It does not evoke a sense of elapsed time, nor of days. 
     * We should choose a name that specifies what is being measured and the unit of that measurement.
     * There's no reason to abbreviate the name. It won't save time or time. You have to explain it each time others
     * read the code. It's much more expensive than making the name longer and more descriptive.
     * 
     * The broader the scope of a variable, the more descriptive its name should be.
     * You might use a short name like "i" or "d" for a loop index or a temporary variable within a small scope,
     * but for variables with a broader scope, use longer, more descriptive names.
     */
    private int d; // elapsed time in days

    /**
     * It's a mine sweeper game. 
     * The board is a list of cells called theList.
     * Each cell is represented as an array of integers.
     * The first element of the array indicates the status of the cell.
     * 
     * The method name getThem reveals nothing about what "them" refers to.
     * The parameter name theList also reveals nothing about what kind of list it is.
     * The variable name list1 is also non-descriptive.
     * @return
     */
    public List<int[]> getThem(List<int[]> theList) {
        List<int[]> list1 = new ArrayList<int[]>();
        for (int[] x : theList)
            if (x[0] == 4)
                list1.add(x);
    
        return list1;
    }

    /**
     * Exercise for intention-revealing names.
     * 
     * This method returns the value of d, but what is d? what is theList?
     * 
     * theList is actually a list of order line items, where each item is represented
     * as an array of two integers: quantity and unit price.
     * d is actually the total price of an order line item, calculated as quantity * unit price.
     * 
     * Based on this understanding, we can rename variables to make the code more intention-revealing.
     */
    public int getSum(List<int[]> theList) {
        int d = 0; // what is d?
        for (int[] x : theList) {
            d += x[0] * x[1]; // what is d?
            System.out.println(d); // what is d?
        }

        return d;
    }

}
package com.github.budwing.messy.naming;

import java.util.List;

public class AvoidDisinformation {
    public static final String SLOGAN = "AVOID DISINFORMATION";

    /**
     * The name accountList is misleading because it suggests that the list contains Account objects,
     * and list is also misleading because the variable is actually an array. 
     * List is a more appropriate name for a collection that implements the List interface.
     */
    private int[] accountList; // actually contains User ids

    /**
     * The name hp is misleading because it usually means Hewlett-Packard UNIX system for a programmer,
     * but the variable actually holds a home phone number. 
     * A more appropriate name would be homePhoneNumber.
     */
    private String hp; // home phone number

    /**
     * The name win is misleading because it suggests Windows operating system for a programmer,
     * but the variable actually holds a win times.
     * A more appropriate name would be winTimes.
     */
    private int win; // win times

    /**
     * The name db is misleading because it usually means database for a programmer,
     * but the variable actually holds a date of birth. 
     * A more appropriate name would be dateOfBirth.
     */
    private String db; // date of birth

    /**
     * The name url is misleading because it usually means uniform resource locator for a programmer,
     * but the variable actually holds a user registration list. 
     * A more appropriate name would be userRegistrationList.
     */
    private List url; // user registration list

    /**
     * Avoid using O,0,l,1.
     */
    public int exampleO01l(int l, int O, int O1) {
        int a = l;
        if ( O == l )
        a = O1;
        else
        l = 01;

        return a>l?a:l;

    }
}

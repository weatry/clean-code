package com.github.budwing.messy.naming;

public class MeaningfulContext {
    public static final String SLOGAN = "MAKE MEANINGFUL CONTEXT";

    /**
     * The name state is misleading because it does not convey any meaningful context about what state it represents.
     * It may refer to a state of a process, an order, a user, etc. 
     * It may also refer to a state in a country. 
     * We should choose a name that reflects its specific context.
     * For example, addressState would be a more appropriate name if it represents the state of an address.
     * And putting it in the Address class would provide even more context.
     */
    private String state; // state of an address

    /**
     * The method is not easy to understand because it lacks meaningful context.
     */
    private void printGuessStatistics(char candidate, int count) {
        String number;
        String verb;
        String pluralModifier;
        if (count == 0) {
            number = "no";
            verb = "are";
            pluralModifier = "s";
        } else if (count == 1) {
            number = "1";
            verb = "is";
            pluralModifier = "";
        } else {
            number = Integer.toString(count);
            verb = "are";
            pluralModifier = "s";
        }
        String guessMessage = String.format(
            "There %s %s %s%s", verb, number, candidate, pluralModifier
        );
        System.out.println(guessMessage);
    }
}

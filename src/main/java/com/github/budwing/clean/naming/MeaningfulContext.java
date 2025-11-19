package com.github.budwing.clean.naming;

public class MeaningfulContext {
    public static final String SLOGAN = "MAKE MEANINGFUL CONTEXT";

    /**
     * state has multiple meanings. It can be a state of a process, an order, a user, etc.
     * It can also be a state in a country. We should choose a name that reflects its specific context.
     */
    private String addressState;

    /**
     * By defining a separate class to handle the message creation,
     * we provide meaningful context to the process of generating the guess statistics message.
     */
    class GuessStatisticsMessage {
        private String number;
        private String verb;
        private String pluralModifier;

        public String make(char candidate, int count) {
            createPluralDependentMessageParts(count);
            return String.format("There %s %s %s%s", verb, number, candidate, pluralModifier);
        }

        private void createPluralDependentMessageParts(int count) {
            if (count == 0) {
                thereAreNoLetters();
            } else if (count == 1) {
                thereIsOneLetter();
            } else {
                thereAreManyLetters(count);
            }
        }

        private void thereAreManyLetters(int count) {
            number = Integer.toString(count);
            verb = "are";
            pluralModifier = "s";
        }

        private void thereIsOneLetter() {
            number = "1";
            verb = "is";
            pluralModifier = "";
        }

        private void thereAreNoLetters() {
            number = "no";
            verb = "are";
            pluralModifier = "s";
        }
    }
}



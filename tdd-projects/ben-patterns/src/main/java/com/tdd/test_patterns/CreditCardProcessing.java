package com.tdd.test_patterns;

/**
 * We assume that this class if from a merchant to valid credit cards.
 * These protected methods are impossible to be modified. Namely, modifying them is
 * out of the question.
 * What shall we do if we want to test them?
 * The answer is to write a subclass of it and override the cardIsActive method to always
 * return true.
 * */
public class CreditCardProcessing {

    public boolean isValid(String carNumber) {
        return validationCodeMatches(carNumber) && cardIsActive(carNumber);
    }

    protected boolean validationCodeMatches(String cardNumber) {
        // validation logic is omitted
        return true;
    }

    // This method is to be override.
    protected boolean cardIsActive(String cardNumber) {
        // validation logic is omitted
        return false;
    }

}

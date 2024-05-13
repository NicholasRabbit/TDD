package com.tdd.test_patterns;

public class CreditCardProcessingSubclass extends CreditCardProcessing{

    @Override
    protected boolean cardIsActive(String cardNumber) {
        // validation logic is omitted
        return true;
    }
}

package com.tdd.test_patterns;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 6, Test-Specific Subclass
 * There isn't enough code for me to understand this kind of test pattern in the book.
 * See the note in CreditCardProcessing.
 * */
public class TestSpecificSubclassTest {


    @Test
    public void testCreditCard() throws Exception {
        String carNumber = "1111";
        //CreditCardProcessing creditCard = new CreditCardProcessing();
        CreditCardProcessing creditCard = new CreditCardProcessingSubclass();
        boolean valid = creditCard.isValid(carNumber);

        assertTrue(valid);

    }


}

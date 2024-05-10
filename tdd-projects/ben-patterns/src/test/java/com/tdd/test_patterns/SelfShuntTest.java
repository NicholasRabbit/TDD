package com.tdd.test_patterns;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelfShuntTest implements PricingService {

    /**
     * Test Patterns 4.5.3
     * 2, Self-Shunt.
     */

    // Implements PricingService interface.
    @Override
    public float getDiscountPercentage(Customer customer, Product product) {
        return 10.0f;
    }


    @Test
    public void testOrderProcessorWithMockObject() throws Exception {

        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float expectedBalance = initialBalance - listPrice * (1 - discount / 100);
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in action", listPrice);

        OrderProcessor processor = new OrderProcessor();
        // Self-Shunt. Pass "this", namely this test class, to object under test.
        // We use test class as a test double instead of mock object which are normally used.
        // Compare with OrderProcessorEasyMockTest and see the difference.
        processor.setPricingService(this);
        processor.process(new Order(customer, product));

        assertEquals(expectedBalance, customer.getBalance(), 0.001f);

    }

}

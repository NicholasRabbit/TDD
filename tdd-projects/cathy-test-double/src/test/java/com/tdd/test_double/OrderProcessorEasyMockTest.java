package com.tdd.test_double;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import static org.easymock.classextension.EasyMock.*;

public class OrderProcessorEasyMockTest {

    /**
     * Mock.
     * It is one of test doubles.
     * What is mock in TDD?
     * We create a mock object of the class that we test so that we don't need the real class itself.
     * */
    @Test
    public void testOrderProcessorWithEasyMock(){
        // Set up regular objects and data for test.
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float exceptedBalance = initialBalance - initialBalance * (1 - discount / 100);
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in action", listPrice);

        // Create a dynamic mock for PricingService.
        PricingService mock = createMock(PricingService.class);
        expect(mock.getDiscountPercentage(customer, product))
                .andReturn(discount);
        replay(mock);

        // act. Pass mock to code which is under test.
        OrderProcessor processor = new OrderProcessor();
        processor.setPricingService(mock);
        processor.process(new Order(customer, product));

        // assert
        assertEquals(exceptedBalance, customer.getBalance(), 0);

        //Ask mock to verify expectations. (What does this mean? What does the "verify()" do?)
        verify(mock);

    }


}


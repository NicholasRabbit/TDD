package com.tdd.assertion_patterns;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestExpectedInteractions {

    //Having test double record interactions.
    private List<Customer> delivered;

    @Before
    public void setUp() {
        delivered = new ArrayList<>();
    }

    //Test double. Namely mock.
    private class MockCustomer extends Customer {
        @Override
        public void onPaperDelivered(DailyPaper paper) {
            //Other code will be added later.
            delivered.add(this);
        }
    }

    /**
     * 4.5.1
     * 5, Interaction assertion.
     * What is interaction assertion?
     * As its name they verify that our code interacts with is collaborator objects as we expect it to.
     *
     * Note that to write test first and then to write code and then to refactor.
     * */
    @Test
    public void paperBoyShouldDeliverPapers() throws Exception {
        Customer david = new MockCustomer();
        Customer esther = new MockCustomer();
        PaperBoy paperBoy = new PaperBoy();
        paperBoy.addToRoute(david);
        paperBoy.addToRoute(esther);

        paperBoy.deliver(new DailyPaper());
        assertTrue(delivered.contains(david));
        assertTrue(delivered.contains(esther));

    }

}

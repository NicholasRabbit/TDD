package com.tdd.test_patterns;


import junitx.util.PrivateAccessor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Patterns 4.5.3
 * 4,Privilege Access
 * */
public class PrivilegeAccessTest {

    // The following test is failed because I am not familiar with PrivateAccessor.
    // I will figure it out later.
    // The reason
    @Test
    public void testPrivilegeAccess() throws Exception {

        // If the first argument is xx.class, the getField method will get static fields.
        // The "balance" is not a static field so there is an exception threw out.
        // NoSuchFieldException: Could get value for static field com.tdd.test_patterns.Customer.balance
        //Object balance = PrivateAccessor.getField(Customer.class, "balance");

        // "number" is a static field.
        Object number = PrivateAccessor.getField(Customer.class, "number");

        Object balance2 = PrivateAccessor.getField(new Customer(1.0f), "balance");

    }

}

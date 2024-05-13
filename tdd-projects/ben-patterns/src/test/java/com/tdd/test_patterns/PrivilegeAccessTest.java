package com.tdd.test_patterns;


import junitx.util.PrivateAccessor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Patterns 4.5.3
 * 4,Privilege Access
 * */
public class PrivilegeAccessTest {

    // The following test is failed and I will figure it out later.
    @Test
    public void testPrivilegeAccess() throws Exception {
        Object balance = PrivateAccessor.getField(Customer.class, "balance");

    }

}

package com.tdd.test_patterns;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class ParameterizedTest {

    /**
     * Test Patterns 4.5.3
     * 1, Parameterized Test.
     * */

    // (1) Provide parameterized data.
    @Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][] {
                {0, 0, 0}, {1, 1, 0},  // These arguments are correspond to the parameters of the constructors of ParameterizedTest.
                {2, 1, 1}, {3, 2, 1},
                {10, 4, 6}, {9, 5, 4}
        };
        return Arrays.asList(data);
    }

    public int expected, input1, input2;

    // (2) Data is bound through constructor.
    public ParameterizedTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    // (3) Test method invoked once for each data test.
    @Test
    public void executeParameterizedTest() throws Exception {
        assertEquals(expected, input1, input2);
    }


}

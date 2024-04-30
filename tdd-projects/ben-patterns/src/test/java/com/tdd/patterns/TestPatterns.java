package com.tdd.patterns;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPatterns {


    /**
     * 4.2.3 State-based testing.
     * What is state-based testing?
     * See personal notes.
     * */
    @Test
    public void listShouldNotBeEmptyAfterAddingSomethingToIt () {
        List<String> list = new ArrayList<>();
        assertTrue(list.isEmpty());
        list.add("something");
        assertFalse(list.isEmpty());
    }


}

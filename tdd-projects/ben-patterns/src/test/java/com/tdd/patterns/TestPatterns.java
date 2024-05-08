package com.tdd.patterns;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPatterns {


    /**
     * 4.2.3 State-based testing.
     * What is state-based testing?
     * For more elaboration, see personal notes.
     * */
    @Test
    public void listShouldNotBeEmptyAfterAddingSomethingToIt() throws Exception {
        List<String> list = new ArrayList<>();
        assertTrue(list.isEmpty());
        list.add("something");
        assertFalse(list.isEmpty());
    }

}

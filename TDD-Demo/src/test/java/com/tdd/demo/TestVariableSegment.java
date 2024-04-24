package com.tdd.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestVariableSegment {

    @Test
    public void variableEvaluatesToItsValue(){
        Map<String,String> variables = new HashMap<>();
        String name = "var";
        String value = "value";
        variables.put(name, value);
        assertEquals("value", new Variable(name).evaluate(variables));
    }

}

package com.tdd.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestVariableSegment {

    private Map<String,String> variables;

    @Before
    public void setUp(){
        variables = new HashMap<>();
    }

    @Test
    public void variableEvaluatesToItsValue(){
        String name = "var";
        String value = "value";
        variables.put(name, value);
        assertEquals("value", new Variable(name).evaluate(variables));
    }

    @Test
    public void missingVariableRaiseException(){
        String name = "var";
        try {
            new Variable(name).evaluate(variables);
            fail("Missing variable value should raise an exception");
        } catch (MissValueException expected){
            assertEquals("No value for ${" + name + "}", expected.getMessage());
        }
    }

}

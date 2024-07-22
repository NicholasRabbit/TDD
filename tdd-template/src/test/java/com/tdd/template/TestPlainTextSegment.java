package com.tdd.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestPlainTextSegment {


    @Test
    public void plainTextEvaluateAsIs(){
        Map<String,String> variables = new HashMap<>();
        String text = "abc def";
        assertEquals("abc def", new PlainText(text).evaluate(variables));
    }

}

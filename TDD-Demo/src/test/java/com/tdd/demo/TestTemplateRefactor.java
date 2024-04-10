package com.tdd.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplateRefactor {

    private EmailTemplate template;

    /**
     * Refactor TestTemplate.
     *
     * The method annotated by @Before is invoked by Junit before each method annotated by @Test.
     * */
    @Before
    public void setUp() throws Exception {
        this.template = new EmailTemplate("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void multipleVariables () throws Exception {
        assertTemplateEvaluatesTo("1, 2, 4");
    }

    @Test
    public void unknownVariablesAreIgnored(){
        template.set("does not exist", "foo");
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    private void assertTemplateEvaluatesTo(String expected){
        assertEquals(expected, template.evaluate());
    }

}

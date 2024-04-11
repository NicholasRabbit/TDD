package com.tdd.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplateRefactor {

    private EmailTemplate template;

    /**
     * 7, Refactor TestTemplate.
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

    /**
     * 8, Handle expected exceptions by evaluating "${foo}" which is not a variable in EmailTemplate.
     *    8.1 Remember that write the "MissValueException.java" first and then generate it with IDE. We should write test first, shouldn't we?
     * 9, Start to refactor evaluate() to throw an exception when there are variables left in the template.
     *
     * By now we have got we wanted-an expected exception-and the next to do is refactoring because evaluate() has too many line of codes and
     * the readability is terrible. The consistency of the abstraction level of the code is essential for a good programme.
     *
     *
     *
     *
     * */
    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new EmailTemplate("${foo}").evaluate();
            fail("evaluate() should throw an exception if a variable was left without a value");   // fail() is a method of JUnit with given message manually.
        } catch (MissValueException e) {   //8.1
            e.printStackTrace();
        }
    }

}

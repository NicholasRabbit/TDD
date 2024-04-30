package com.tdd.template;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        assertTemplateEvaluatesTo("1, 2, 3");
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
     * 10, A detailed exception.
     *    Now our code is clean, readable and maintainable, but that is not good enough. We haven't offer any detailed information of the exception yet.
     *    I have seen many exceptions without any information during my not so long career as a programmer. Just as Lessa Koskela said that a confusing
     *    exception like "NullPointer:null" always comes out sometimes.
     *    We won't stop refactoring until we have a meaningful exception message.
     *    10.1 First, we write what message we expect and it should contain the name of the variable.
     *         Inevitably, the test failed.
     *    10.2 Write codes to pass the test.
     * */
    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new EmailTemplate("${foo}").evaluate();
            fail("evaluate() should throw an exception if a variable was left without a value");   // fail() is a method of JUnit with given message manually.
        } catch (MissValueException e) {   //8.1
            //10.1
            assertEquals("No value for ${foo}", e.getMessage());  //10.1
            e.printStackTrace();
        }
    }



    /**
     * 11, If the values contain "${}", what would happen?
     * It throws out an exception which is "No group with name..."
     *
     * Reason for that.
     * After reading the source code of Java SE-'Matcher.appendReplacement(Matcher.java:849)' where the exception was threw,
     * I find that symbols such as "${}" of the values will be skipped and that leads to this exception.
     *
     * Now we should dig into regex of Java API to see if we can find something useful to solve
     * the problem we currently have. See RegexLearningTest.java.
     *
     * */
    @Test
    public void variablesGetProcessJustOnce(){
        template.set("one", "${one}");
        template.set("two", "${two}");
        template.set("three", "${three}");
        assertTemplateEvaluatesTo("${one}, ${two}, ${three}");

    }

}

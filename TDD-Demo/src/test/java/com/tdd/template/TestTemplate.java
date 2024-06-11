package com.tdd.template;


import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplate {

    /**
     *  Test --> Code --> Refactor
     * */

    /**
     * The first test.
     * 1, Write test first and then write the codes of EmailTemplate.
     * 2, To make the test pass as quickly as possible by writing hard code in EmailTemplate.java.
     * 3, Write the second test and try different input and then we can see that the second test failed as we expected,
     *    of course. This technique is aptly named triangulation.
     * 4, Keep on triangulating. To pass the second test with minimal effort we should use a variable to replace "Reader".
     * 5, Write the third test to use "Hi" instead of "Hello". Presumably, the test will fail.
     *    Add more faking details to pass the test.
     *
     *  I refactor in another testing class named "TestTemplateRefactor" in order to compare these two testing classes.
     * */
    @Test
    public void oneVariable() throws Exception {

        EmailTemplate template = new EmailTemplate("Hello, ${name}");
        template.set("name", "Reader");
        assertEquals("Hello, Reader", template.evaluate());

    }

    /**
     * The second test.
     * */
    @Test
    public void differentVariable() throws Exception {
        EmailTemplate template = new EmailTemplate("Hello, ${name}");
        template.set("name", "someone else");
        assertEquals("Hello, someone else", template.evaluate());

    }

    /**
     * The third test.
     * */
    @Test
    public void differentVariableAndText() throws Exception {
        EmailTemplate template = new EmailTemplate("Hi, ${name}");
        template.set("name", "someone else");
        assertEquals("Hi, someone else", template.evaluate());

    }

    /**
     * After five steps of test, there are still hard codes in our EmailTemplate, namely "\\$\\{name\\}".
     * Then we should squeeze out the fake stuff.
     * 6, Write a test for multiple variables. Apparently, it will fail at first time.
     *    Now write codes to pass the test.
     * */
    @Test
    public void testMultipleVariables() throws Exception {
        EmailTemplate template = new EmailTemplate("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
        assertEquals("1, 2, 3", template.evaluate());

    }

    /**
     * 7, Test unknown variables.
     * */
    @Test
    public void unknownVariablesAreIgnored(){
        EmailTemplate template = new EmailTemplate("Hello, ${name}");
        template.set("name", "Reader");
        template.set("does not exist", "Tom");
        assertEquals("Hello, Reader", template.evaluate());

    }

}

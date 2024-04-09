package com.tdd.demo;


import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplate {

    /**
     *  Test --> Code --> Refactor
     * */

    /**
     * The first test.
     * 1, Write test first and then write the codes of EmailTemplate.
     * 2, To make the test pass as quickly as possible by writing hard codes in EmailTemplate.java.
     * 3, Write the second test and try different input and then we can see that the second test failed as we expected. Of course.
     *    This technique is aptly named triangulation.
     * 4, Keep on triangulating. To pass the second test with minimal effort we should use a variable to replace "Reader".
     * 5, Write the third test to use "Hi" instead of "Hello". Presumably, the test will fail.
     *    Add more faking details to pass the test.
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

}

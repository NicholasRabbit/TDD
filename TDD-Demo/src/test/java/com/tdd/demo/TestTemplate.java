package com.tdd.demo;


import org.junit.Assert;
import org.junit.Test;

public class TestTemplate {

    /**
     * 1, Write test first and then write the codes of EmailTemplate.
     * */
    @Test
    public void oneVariable() throws Exception {

        EmailTemplate template = new EmailTemplate("Hello, ${name}");
        template.set("name", "Reader");
        Assert.assertEquals("Hello, Reader", template.evaluate());

    }


}

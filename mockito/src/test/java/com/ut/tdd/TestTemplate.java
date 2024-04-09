package com.ut.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTemplate {

    /**
     * 1, Write test first and then write the codes of EmailTemplate.
     * */
    @Test
    public void oneVariable() throws Exception {

        EmailTemplate template = new EmailTemplate("Hello, ${name}");
        template.set("name", "Reader");
        Assertions.assertEquals("Hello, Reader", template.evaluate());

    }


}

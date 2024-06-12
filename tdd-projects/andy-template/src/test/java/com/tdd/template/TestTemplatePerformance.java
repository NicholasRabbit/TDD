package com.tdd.template;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplatePerformance {

    private EmailTemplate template;

    @Before
    public void setUp(){
        buildTemplate();
        populateTemplate();
    }

    private void buildTemplate(){
        StringBuffer text = new StringBuffer(100);
        for(int i = 0, var = 1; i < 100; i++, var++){
            text.append(" template ");
            if(i % 100 / 20 == 0){
                text.append("${var").append(var).append("}");
            }
        }
        template =  new EmailTemplate(text.toString());
    }

    private void populateTemplate(){
        for(int var = 1; var < 100; var++){
            template.set("var" + var, "value of var" + var);
        }
    }


    /**
     * 1, Verify a template of 100 words and 20 variables with values of approximately 15 characters each
     * is evaluated in 200 milliseconds or less.
     *
     * Apparently, the test is easily passed.
     * */
    @Test
    public void templateWith100WordsAnd20Variables(){
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time + " milliseconds while the target was "
                + expected + " milliseconds", time <= expected);

    }

    /**
     * Test the performance of rendering ten thousand words.
     * */
    @Test
    public void testRenderingTenThousandWords() throws Exception {
        buildTemplateThousand();
        populateTemplateThousand();
        long expected = 10L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering 1000 words in the template took " + time + " milliseconds while the target was "
                + expected + " milliseconds", time <= expected);

    }

    private void buildTemplateThousand(){
        StringBuffer text = new StringBuffer(50000);
        for(int i = 0, var = 1; i < 50000; i++, var++){
            text.append(" template ");
            if(i % 50000 / 10000 == 0){
                text.append("${var").append(var).append("}");
            }
        }
        template =  new EmailTemplate(text.toString());
    }

    private void populateTemplateThousand(){
        for(int var = 1; var < 50000; var++){
            template.set("var" + var, "value of var" + var);
        }
    }


}

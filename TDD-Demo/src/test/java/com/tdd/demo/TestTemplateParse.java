package com.tdd.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To tackle the issue in "variablesGetProcessJustOnce()" which throws an exception - "No group for ${one}".
 *
 * After dig into regex of Java, we write a new class name EmailTemplateParse to parse the template.
 * If the new class were good enough, we would swap the implementation in evaluate() of EmailTemplate.
 *
 * What to do?
 * Parse "${a}:${b}:${c}" into a list which consists of "${a}",":","${b}"...
 * */
public class TestTemplateParse {


    /**
     * "To pick low-hanging fruits."
     * 1, First of all, we write a test first and then write codes to pass the test as soon as possible.
     * Remember: test-code-refactor!
     * */
    @Test
    public void emptyTemplateRenderWithEmptyString(){
        //EmailTemplateParse parse = new EmailTemplateParse();
        //List<String> segments = parse.parse("");
        //3.1, refactoring
        List<String> segments = parse("");
        //assertEquals("Number of segments", 1, segments.size());
        //assertEquals("", segments.get(0));
        //3.2, refactoring
        assertSegments(segments, "");
    }

    /**
     * 2, Second, we test only a plain text.
     * We move at a trivial or minor step so that there won't be any big and complicated problems, and we locate
     * the error as soon as it emerged.
     * */
    @Test
    public void templateWithOnlyPlainText(){
        //EmailTemplateParse parse = new EmailTemplateParse();
        //List<String> segments = parse.parse("plain text only");
        //3, refactoring
        List<String> segments = parse("plain text only");
        //assertEquals("Number of segments", 1, segments.size());
        //assertEquals("plain text only", segments.get(0));
        //3.2 refactoring
        assertSegments(segments, "plain text only");
    }

    /**
     * 3, Refactoring is essential when there are duplicate codes in this test.
     *    3.1 Refactoring parse(...)
     *    3.2 Then we use variable-length argument lists to refactor those 'assertEquals(...)'.
     * */
    private List<String> parse(String template){
        return new EmailTemplateParse().parse(template);
    }

    private void assertSegments(List<? extends Object> actual, Object... expected){
        assertEquals("Number of segments doesn't match.", expected.length, actual.size());
        assertEquals(Arrays.asList(expected), actual);
    }


    /**
     * 4, Parsing multiple variables.
     * We expected that the input would be split by colon ":" and it should become an array after being parsed.
     * Presumably, the following test failed. Because at this point, in order to pass the test quickly,
     * we only write an implement as simple as it is in parse(...)  of EmailTemplate.java.
     * */
    @Test
    public void parsingMultipleVariables() throws Exception{
        List<String> segments = parse("text at head, ${a}:${b}:${c}, text at tail");
        assertSegments(segments, "text at head, ", "${a}",":", "${b}", ":", "${c}", ", text at tail");
    }


    /**
     * 5, Then we refactor EmailTemplate.java to pass the test "parsingMultipleVariables()" in 4. See refactoring code in EmailTemplateParse.java.
     * */

    /**
     * 6, After passing NO. 4 test, we are sure about that we can refactor the evaluate() in "EmailTemplate.java"
     *    with our new parse() in EmailTemplateParse.java.
     * */




}
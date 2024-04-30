package com.tdd.template;


import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.*;

public class RegexLearningTest {

    /**
     * 1, To learn how groupCount() works.
     * I assumed that, as the author did, the groupCount() will return the numbers of occurrences of the pattern in the data.
     * Unfortunately, it failed.
     * Analysis:
     * After searching on Google, I know that how groupCount() works. It uses parentheses (plural) to identify the so called capturing groups.
     * Namely, only occurrences of words in parentheses will be matched.
     *
     * */
    @Test
    public void testHowGroupCountWorks() throws Exception {
        //I use the names of variables as the author did because it is nonsense to devise new fancy names.
        String haystack = "The needle shop sells needles";   //The return value is 0.
        String regex = "(needle)";   //The "needles" is not matched.
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        //assertEquals(2, matcher.groupCount());
        assertEquals(1, matcher.groupCount());

    }

    /**
     * 2, To learn find(), start() and end().
     *
     * */
    @Test
    public void testFindStartAndEnd() throws Exception {

        String haystack = "The needle shop sells needles";   //The return value is 0.
        String regex = "(needle)";   //If the parentheses were removed, the test would still pass. Why should we use parentheses?
        Matcher matcher = Pattern.compile(regex).matcher(haystack);

        assertTrue(matcher.find());  //
        assertEquals("Wrong start index of the first match ",4, matcher.start());
        assertEquals("Wrong end index of the first match ", 10, matcher.end());  //The range of index of the first "needle" is from 0 to 9. The end() returns the offset after the character matched.

        assertTrue(matcher.find());
        assertEquals("Wrong start index of the second match ",22, matcher.start());
        assertEquals("Wrong end index of the second match ", 28, matcher.end());  //end() returns the offset after the character matched.

        assertFalse("Should not have any more matches ", matcher.find());

    }

    /**
     * 3, Test matches(). It returns true only if the entire region matches this matcher's pattern. (Quoted from Java Documentation)
     *    It is different from "find()".
     * */
    @Test
    public void testExactMatches(){
        assertTrue(Pattern.compile("exact").matcher("exact").matches());
        assertFalse(Pattern.compile("exact").matcher("exactWhat").matches());
        assertFalse(Pattern.compile("exact").matcher("abcexact").matches());
        assertFalse(Pattern.compile("exact").matcher("abcexactxyz").matches());
    }




}

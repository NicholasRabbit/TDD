package com.tdd.practice.refactor.parser_refactored;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * To write a test to learn about how to use InputStream which is introduced by the instructor in the course.
 * Presumably, it is necessary to know about it before further learning, or I will be confused.
 * */
public class TestInputStream {

    /**
     * 1, -p 8080
     * */
    @Test
    public void shouldBeTrueIfFollowedByValue() throws Exception {
        List<String> arguments = Arrays.asList("-p", "8080");
        int index = arguments.indexOf("-p");
        // Set an int array which ranges from 'index + 1' to the end.
        OptionalInt first = IntStream.range(index + 1, arguments.size())
                .findFirst();
        boolean actual = first.isPresent();
        assertTrue(actual, "should be true if values follow");
    }

    /**
     * 2, -p
     *    -p -x
     *  Trying to use 'IntStream' to get the range of indices of values of an argument.
     *
     * 2.1 -p
     */
    @Test
    public void shouldThrowNoSuchElementException() throws Exception {
        List<String> arguments = Arrays.asList("-p");
        int index = arguments.indexOf("-p");
        try {
            int endIndex = IntStream.range(index + 1, arguments.size())
                    .filter(it -> arguments.get(it).startsWith("-"))
                    .findFirst()
                    .getAsInt();
            fail("should throw No value present");
        } catch (RuntimeException e) {
            assertEquals(NoSuchElementException.class, e.getClass());
        }

    }

    /**
     * 2.2 -p -x
     * */
    @Test
    public void shouldHaveNoValueReturned() throws Exception {
        List<String> arguments = Arrays.asList("-p", "-l");
        int index = arguments.indexOf("-p");
        OptionalInt first = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst();  // find '-l'

        assertTrue(first.isPresent());   // -l presents
        assertEquals(1, first.getAsInt());

        List<String> subList = arguments.subList(index + 1, first.getAsInt());
        assertEquals(0, subList.size());

    }

    /**
     * Combined test of 2.1 and 2.2.
     * */
    @ParameterizedTest
    @ValueSource(strings = {"-p", "-p -l"})
    public void valueSizeShouldBeLessThanOneIfNoValuePresents(String args) throws Exception {
        List<String> arguments = Arrays.asList(args.split(" "));
        int index = arguments.indexOf("-p");
        int endIndex = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).contains("-"))
                .findFirst().orElse(arguments.size());// Use 'orElse()' to avoid throwing any exception if no value.
        List<String> subList = arguments.subList(index + 1, endIndex);

        assertTrue(subList.size() < 1, "Values' size should be 0.");   // Namely, values' size is 0.

    }


    /**
     * 3, -p 8080
     *    -p 8080 8081
     *
     * 3.1 -p 8080
     * */
    @Test
    public void valuesSizeShouldBeOne() throws Exception {
        List<String> arguments = Arrays.asList("-p", "8080");
        int index = arguments.indexOf("-p");
        int endIndex = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst()
                .orElse(arguments.size());
        List<String> values = arguments.subList(index + 1, endIndex);

        assertEquals(1, values.size());

    }

    /**
     * 3.2 -p 8080 8081
     * too many arguments
     * */
    @Test
    public void shouldGetTwoValues() throws Exception {
        List<String> arguments = Arrays.asList("-p", "8080", "8081");
        int index = arguments.indexOf("-p");
        int endIndex = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst()
                .orElse(arguments.size());
        List<String> values = arguments.subList(index + 1, endIndex);

        assertEquals(2, values.size());

    }


}

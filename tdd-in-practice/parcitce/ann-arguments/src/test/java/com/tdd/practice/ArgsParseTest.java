package com.tdd.practice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsParseTest {


    /*
    * A test of parsing arguments of command line.
    * For example: -l true/false -p 8080 -d /usr/logs
    * Explanation:
    * -l : logging, the default value of it is "false";
    * -p : port
    * -d : directory
    * */
    /**
     * It is necessarily needed to write only a single test for the requirement.
     * We only take small steps to approach our final goal.
     * */
    @Test
    @Disabled   // Disabled the complex test for a while.
    public void testMultipleParing() throws Exception {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        options.logging();
        options.port();

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.dir());

    }

    /*
     * "Records" is a new feature of JDK 14. See elaboration in my JavaSE code.
     * */
    static record Options (@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

    }

    /**
     * Decomposing requirements into trivial tasks which are easy to test.
     * 1. Boolean: -l true/false(default value)
     * 1.1 What we are going to do is to test whether the "parse(...)" will return "true" when there is
     * an argument named "-l", otherwise it returns "false".
     * Rule number one: past the test as soon as possible even if you write hard code.
     * */
    @Test
    public void shouldSetTrueIfFlagPresents() throws Exception {
        BooleanOption booleanOption = Args.parse(BooleanOption.class, "-l");
        assertTrue(booleanOption.logging());
    }

    /**
     * 1.2 Because we write hard code in production to pass the first test, that is not the implementation we want.
     * The next step is to write another test to drive us to write more code to implement the task. That's why the
     * approach called Test-Driven Development.
     * This technique is aptly named triangulation.
     *
     * 1.3 The following test will be inevitably failed because the hard code. Then we refactor the code.
     * */
    @Test
    public void shouldSetFalseIfFlagNotPresents() throws Exception {
        BooleanOption booleanOption = Args.parse(BooleanOption.class);
        assertFalse(booleanOption.logging());
    }


    // The boolean record.
    static record BooleanOption (@Option("l") boolean logging) {

    }



}

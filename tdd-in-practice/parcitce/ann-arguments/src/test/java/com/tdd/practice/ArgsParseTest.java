package com.tdd.practice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsParseTest {


    /*
    * A test of parsing arguments of command line.
    * For example: -l true/false -p 8080 -d /usr/logs
    * Explanation:
    * -l : logging, if "-l" is presented, the value is true, and if "-l" is omitted the value is "false"
    * -p : port
    * -d : directory
    * */
    /**
     * It is necessarily needed to write only a single test for the requirement.
     * We only take small steps to approach our final goal.
     *
     * 4.
     * */
    @Test
    //@Disabled   // Disabled the complex test for a while.
    public void testMultipleParing() throws Exception {
        //Options options = Args.parseMultiple(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        Options options = Args.parseRefactoring(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
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
//        BooleanOption booleanOption = Args.parse(BooleanOption.class, "-l");
        BooleanOption booleanOption = Args.parseRefactoring(BooleanOption.class, "-l");
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
        //BooleanOption booleanOption = Args.parse(BooleanOption.class);
        BooleanOption booleanOption = Args.parseRefactoring(BooleanOption.class);
        assertFalse(booleanOption.logging());
    }

    // The boolean record.
    static record BooleanOption (@Option("l") boolean logging) {

    }

    /**
     * 2, Integer: -p 8080
     * Note: We must make sure that the previous tests are still passed when we are refactoring.
     * */
    @Test
    public void shouldParseIntIfPortPresents() throws Exception {
        //IntegerOption integerOption = Args.parse(IntegerOption.class, "-p", "8080");
        IntegerOption integerOption = Args.parseRefactoring(IntegerOption.class, "-p", "8080");

        assertEquals(8080, integerOption.port());

    }

    // The integer record.
    static record IntegerOption (@Option("p") int port) {

    }

    /**
     * TODO: -d /usr/logs
     * 3, String: -d /usr/logs
     * */
    @Test
    public void shouldSetDir() throws Exception {
        //StringOption stringOption = Args.parse(StringOption.class, "-d", "/usr/logs");
        StringOption stringOption = Args.parseRefactoring(StringOption.class, "-d", "/usr/logs");
        assertEquals("/usr/logs", stringOption.dir());
    }

    // The String record.
    static record StringOption (@Option("d") String dir) {

    }





}

package com.tdd.practice.refactor.parser_refactored_2;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Writing a test to test SingleValueOptionParser directly instead of "ArgsRefactor.java",
 * thus we get a smaller granularity of testing. So the parse(...) in SingeOptionParser is
 * called directly.
 * */
public class SingleValueOptionParserTest {

    /**
     * 1, -p 8080 8081
     * should throw a detailed exception if too many arguments present
     * 2, -p / -p -d
     * should throw an insufficient exception if no value after "-p"
     * 3, -p 0
     * the default value is zero if no argument presents.
     * */
    @Test
    public void shouldThrowTooManyArgumentsException() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> {
                    SingleValueOptionParser.unary(Integer::parseInt, 0)
                            .parse(asList("-p", "8080", "8081"), option("p"));
                });
        assertEquals("p", e.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-p", "-p -x"})
    public void shouldThrowInsufficientArgumentsException(String params) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> {
                    SingleValueOptionParser.unary(Integer::parseInt, 0)
                            .parse(asList(params.split(" ")), option("p"));
                });
        assertEquals("p", e.getOption());
    }


    @Test
    public void shouldSetZeroIfNoArgumentPresentForPort() throws Exception {
        Integer port = SingleValueOptionParser.unary(Integer::parseInt, 0)
                .parse(asList(), option("p"));
        assertEquals(0, port);
    }

    /**
     * 1, TODO -d ""
     * default value
     * 2, TODO -d /usr/log /usr/log-info
     * too many arguments exception
     * */
    @Test
    public void shouldSetEmptyIfNoArgumentPresentForDirectory() throws Exception {
        String dir = SingleValueOptionParser.unary(String::valueOf, "")
                .parse(asList(), option("d"));
        assertEquals("", dir);
    }

    @Test
    public void shouldThrowTooManyArgumentsExceptionIfManyDirectoriesPresent() throws Exception {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> {
                    SingleValueOptionParser.unary(String::valueOf, "")
                            .parse(asList("-d", "/local/log", "/local/log-info"), option("d"));
                });
        assertEquals("d", e.getOption());
    }


    static Option option (String value) {
        return new Option() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Option.class;
            }
            @Override
            public String value() {
                return value;
            }
        };
    }

}
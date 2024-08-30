package com.tdd.practice.refactor.parser_refactored_2;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Writing a test to test SingleValueOptionParser directly instead of "ArgsRefactor.java",
 * thus we get a smaller granularity of testing. So the parse(...) in SingeOptionParser is
 * called directly.
 * */
public class OptionParsersTest {

    @Nested
    class UnaryOptionParserTest {
        /**
         * 1, TODO -p 8080 8081
         * should throw a detailed exception if too many arguments present
         * 2, TODO -p / -p -d
         * should throw an insufficient exception if no value after "-p"
         * 3, TODO -p 0
         * the default value is zero if no argument presents.
         * */
        @Test
        public void shouldThrowTooManyArgumentsException() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> {
                        OptionParsers.unary(Integer::parseInt, 0)
                                .parse(asList("-p", "8080", "8081"), option("p"));
                    });
            assertEquals("p", e.getOption());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-p", "-p -x"})
        public void shouldThrowInsufficientArgumentsException(String params) {
            InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                    () -> {
                        OptionParsers.unary(Integer::parseInt, 0)
                                .parse(asList(params.split(" ")), option("p"));
                    });
            assertEquals("p", e.getOption());
        }


        @Test
        public void shouldSetZeroIfNoArgumentPresentForPort() throws Exception {
            Integer port = OptionParsers.unary(Integer::parseInt, 0)
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
            String dir = OptionParsers.unary(String::valueOf, "")
                    .parse(asList(), option("d"));
            assertEquals("", dir);
        }

        @Test
        public void shouldThrowTooManyArgumentsExceptionIfManyDirectoriesPresent() throws Exception {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> {
                        OptionParsers.unary(String::valueOf, "")
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


    @Nested
    class BooleanParserRefactoredTest {

        @Test
        public void shouldThrowTooManyArgumentsException() throws Exception{
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> {
                        OptionParsers.bool().parse(asList("-l", "abc"), option("l"));
                    });
            assertEquals("l", e.getOption());
        }

        /**
         * We add more than one argument after "-l" to test if the code will throw an exception.
         * Then the "TooManyArgumentsException" is thrown so that there is no need to refactor the
         * code, thus the following test is not necessary.
         * */
        @Test
        public void shouldThrowTooManyArgumentsExceptionIfMoreThanOnePresent() throws Exception {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> {
                        OptionParsers.bool().parse(asList("-l", "abc", "xyz"), option("l"));
                    });
            assertEquals("l", e.getOption());

        }


        /**
         * The following test is as same as "shouldSetFalseIfFlagNotPresents" in "ArgsParseRefactorTest", thus
         * it should be deleted.
         * */
        @Test
        public void shouldSetFalseIfNoArgumentPresents() throws Exception {
            assertFalse((Boolean) OptionParsers.bool().parse(asList(), option("l")));
        }

        static Option option (String value) {
            return new Option() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return null;
                }
                @Override
                public String value() {
                    return value;
                }
            };
        }

    }
}
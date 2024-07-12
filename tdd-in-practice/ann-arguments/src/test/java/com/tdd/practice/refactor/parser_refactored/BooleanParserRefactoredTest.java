package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.TooManyArgumentsException;
import com.tdd.practice.refactor.ArgsRefactor;
import com.tdd.practice.refactor.option.BooleanOption;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * We should write more granular tests so that bugs can be located easily.
 * BooleanParserRefactorTest, which is only test BooleanParser, is a more specific test than ArgsParseRefactorTest.
 * */
class BooleanParserRefactoredTest {

    @Test
    public void shouldThrowTooManyArgumentsException() throws Exception{
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> {
                    new BooleanParserRefactored().parse(asList("-l", "abc"), option("l"));
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
                    new BooleanParserRefactored().parse(asList("-l", "abc", "xyz"), option("l"));
                });
        assertEquals("l", e.getOption());

    }


    /**
     * The following test is as same as "shouldSetFalseIfFlagNotPresents" in "ArgsParseRefactorTest", thus
     * it should be deleted.
     * */
    @Test
    public void shouldSetFalseIfNoArgumentPresents() throws Exception {
        assertFalse(new BooleanParserRefactored().parse(asList(), option("l")));
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
package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;

import static com.tdd.practice.refactor.parser_refactored.SingleValueOptionParser.*;

public class BooleanParserRefactored implements OptionParserRefactored<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        return values(arguments, option, 0).map(it -> true).orElse(false);
    }

}

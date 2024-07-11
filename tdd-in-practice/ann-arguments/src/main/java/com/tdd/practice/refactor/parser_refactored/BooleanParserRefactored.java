package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;

public class BooleanParserRefactored implements OptionParserRefactored<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        //return arguments.contains("-" + option.value());
        int index = arguments.indexOf("-" + option.value());
        if (index + 1 < arguments.size() &&
                !arguments.get(index + 1).startsWith("-")) {
            throw new TooManyArgumentsException(option.value());
        }
        return index != -1;
    }

}

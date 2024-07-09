package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;

import java.util.List;

public class BooleanParserRefactored implements OptionParserRefactored {

    @Override
    public Object parse(List<String> arguments, Option option) {
        return arguments.contains("-" + option.value());
    }

}

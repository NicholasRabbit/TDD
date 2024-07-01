package com.tdd.practice.refactor.parser;

import com.tdd.practice.Option;

import java.util.List;

public class IntegerOptionParser implements OptionParser {

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return Integer.valueOf(arguments.get(index + 1));
    }

}

package com.tdd.practice.refactor.parser;

import com.tdd.practice.annotation.Option;

import java.util.List;

public class IntegerOptionParser implements OptionParser {

    /**
     * 1. The following code is duplicated as that in StringOptionParser and should be refactored.
     * 2. The only difference is the function in each class: Integer.parseInt(...) and String.valueOf(...)
     *    so that we can use "Strategy Pattern" or the Function feature of Java. I prefer to use Function
     *    as the tutor did.
     * */
    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return Integer.parseInt(arguments.get(index + 1));
    }

}

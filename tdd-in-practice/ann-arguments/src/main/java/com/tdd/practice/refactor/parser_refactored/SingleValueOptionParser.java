package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;

import java.util.List;
import java.util.function.Function;

public class SingleValueOptionParser<R> implements OptionParserRefactored{

    private Function<String, R> valueParser;

    public SingleValueOptionParser(Function<String, R> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return parseValue(value);
    }

    protected Object parseValue(String value) {
        //return Integer.parseInt(value);   // The key refactor: using Function instead of a specific method.
        return valueParser.apply(value);
    }

}

package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;
import java.util.function.Function;

public class SingleValueOptionParser<T> implements OptionParserRefactored<T>{

    // T is the return value of the function.
    private Function<String, T> valueParser;
    private T defaultValue;

    private SingleValueOptionParser(Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    public SingleValueOptionParser(Function<String, T> valueParser, T defaultValue) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());

        if (index == -1)
            return defaultValue;

        if (index + 1 == arguments.size()
                || arguments.get(index + 1).startsWith("-")) {
            throw new InsufficientArgumentsException(option.value());
        }

        if (index + 2 < arguments.size()
                && !arguments.get(index + 2).startsWith("-")) {
            throw new TooManyArgumentsException(option.value());
        }

        String value = arguments.get(index + 1);
        return parseValue(value);
    }

    protected T parseValue(String value) {
        //return Integer.parseInt(value);   // The key refactor: using Function instead of a specific method.
        return valueParser.apply(value);
    }

}

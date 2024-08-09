package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.IllegalValueException;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SingleValueOptionParser<T> implements OptionParserRefactored<T>{

    // T is the return value of the function.
    private Function<String, T> valueParser;
    private T defaultValue;

    public SingleValueOptionParser(Function<String, T> valueParser, T defaultValue) {
        this.valueParser = valueParser;
        this.defaultValue = defaultValue;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        return  values(arguments, option, 1).map(it -> parseValue(option, it.get(0))).orElse(defaultValue);
    }

    static Optional<List<String>> values(List<String> arguments, Option option, int expectedSize) {
        int index = arguments.indexOf("-" + option.value());
        Optional<List<String>> argumentsList;
        if (index == -1) {
            argumentsList = Optional.empty();
        } else {
            List<String> values = getValues(arguments, index);
            if (values.size() < expectedSize) {
                throw new InsufficientArgumentsException(option.value());
            }
            if (values.size() > expectedSize) {
                throw new TooManyArgumentsException(option.value());
            }
            argumentsList = Optional.of(values);
        }
        return argumentsList;
    }

    static List<String> getValues(List<String> arguments, int index) {
        return arguments.subList(index + 1, IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst()
                .orElse(arguments.size()));
    }

    protected T parseValue(Option option, String value) {
        try {
            return valueParser.apply(value);
        } catch (IllegalValueException e) {
            throw new IllegalValueException(option.value(), value);
        }
    }

}

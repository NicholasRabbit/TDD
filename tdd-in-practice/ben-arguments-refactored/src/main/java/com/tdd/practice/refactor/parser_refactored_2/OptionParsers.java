package com.tdd.practice.refactor.parser_refactored_2;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.IllegalValueException;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

public class OptionParsers<T> {

    public static OptionParserRefactored<Boolean> bool() {
        // To represent an anonymous class with using Lambda function.
        return (arguments, option) -> values(arguments, option, 0)
                .map(it -> true).orElse(false);
    }

    public static <T> OptionParserRefactored<T> unary(Function<String, T> valueParser, T defaultValue) {
        return (arguments, option) -> values(arguments, option, 1)
                .map(it -> parseValue(option, it.get(0), valueParser)).orElse(defaultValue);
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

    protected static <T> T parseValue(Option option, String value, Function<String, T> valueParser) {
        try {
            return valueParser.apply(value);
        } catch (IllegalValueException e) {
            throw new IllegalValueException(option.value(), value);
        }
    }

}

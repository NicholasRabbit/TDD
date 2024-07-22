package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.InsufficientArgumentsException;
import com.tdd.practice.exception.TooManyArgumentsException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

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
        /*
        * The following code is bad smell. Namely, we can know what the code used for at the first sight.
        * Thus, it is necessary to refactor it.
        * */
        if (index == -1)
            return defaultValue;

        /*
        * To get the values of an arguments with the help of "IntStream".
        * */
        List<String> values = getValues(arguments, index);

        if (values.size() < 1) {
            throw new InsufficientArgumentsException(option.value());
        }

        if (values.size() > 1) {
            throw new TooManyArgumentsException(option.value());
        }

        String value = arguments.get(index + 1);
        return parseValue(value);
    }

    private List<String> getValues(List<String> arguments, int index) {
        int endIndex = IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).startsWith("-"))
                .findFirst()
                .orElse(arguments.size());
        List<String> values = arguments.subList(index + 1, endIndex);
        return values;
    }

    protected T parseValue(String value) {
        //return Integer.parseInt(value);   // The key refactor: using Function instead of a specific method.
        return valueParser.apply(value);
    }

}

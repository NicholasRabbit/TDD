package com.tdd.practice.refactor;

import com.tdd.practice.Option;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class ArgsRefactor {


    /*
    * (II). Refactoring the parse(...) as the instructor(of the TDD course) did so that it can be called by testMultipleParing()
    *       and others test methods.
    * */
    public static <T> T parseRefactoring(Class<T> optionsClass, String... args) throws Exception {
        List<String> arguments = Arrays.asList(args);
        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
        // the return value of ".map" is a collection of the return value "parseOption(...)".
        Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();
        return (T) constructor.newInstance(values);

    }

    /**
     * How to refactor the code which is the bad smell?
     * 1, Extract method for the statement in "if...".
     * 2,
     *
     * */
    private static Object parseOption(List<String> arguments, Parameter parameter) {
        Option option = parameter.getAnnotation(Option.class);
        Object value = null;
        OptionParser parser = null;
        if (parameter.getType() == boolean.class) {
            value = parser.parse(arguments, option);
        }
        if (parameter.getType() == int.class) {
            value = parser.parse(arguments, option);
        }
        if (parameter.getType() == String.class) {
            value = parser.parse(arguments, option);
        }

        return value;
    }


    interface OptionParser {
        Object parse(List<String> arguments, Option option);
    }

    static class BooleanParser implements OptionParser {

        @Override
        public Object parse(List<String> arguments, Option option) {
            return arguments.contains("-" + option.value());
        }
    }

    static class IntegerOptionParser implements OptionParser {

        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return Integer.valueOf(arguments.get(index + 1));
        }
    }

    static class StringOptionParser implements OptionParser {

        @Override
        public Object parse(List<String> arguments, Option option) {
            int index = arguments.indexOf("-" + option.value());
            return String.valueOf(arguments.get(index + 1));
        }
    }




}
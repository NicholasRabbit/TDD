package com.tdd.practice.refactor;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.IllegalOptionException;
import com.tdd.practice.refactor.parser_refactored_2.OptionParserRefactored;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import static com.tdd.practice.refactor.parser_refactored_2.SingleValueOptionParser.*;
=======
import static com.tdd.practice.refactor.parser_refactored_2.OptionParsers.bool;
import static com.tdd.practice.refactor.parser_refactored_2.OptionParsers.unary;
>>>>>>> tdd-in-practice

public class ArgsRefactor {

    public static <T> T parseRefactoring(Class<T> optionsClass, String... args) throws Exception {
        List<String> arguments = Arrays.asList(args);
        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];  // Obtain the constructor of Option
        Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();
        return (T) constructor.newInstance(values);

    }

    private static Object parseOption(List<String> arguments, Parameter parameter) {

        if (!parameter.isAnnotationPresent(Option.class))
            throw new IllegalOptionException(parameter.getName());

        return PARSERS.get(parameter.getType())
                .parse(arguments, parameter.getAnnotation(Option.class));

    }

    private static Map<Class<?>, OptionParserRefactored> PARSERS = Map.of(
            boolean.class, bool(),
            int.class, unary(Integer::parseInt, 0),
            String.class, unary( String::valueOf,  ""));

}
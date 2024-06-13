package com.tdd.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class Args {

    public static <T> T parse(Class<T> optionsClass, String... args) throws Exception {
        // 1.1 hard code to pass the test as soon as possible
        //Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
        //return (T) constructor.newInstance(true);   // hard code

        // 1.3 refactor
        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
        Parameter parameter = constructor.getParameters()[0];
        Option option = parameter.getAnnotation(Option.class);
        List<String> arguments = Arrays.asList(args);

        //return (T) constructor.newInstance(arguments.contains("-" + option.value()));   // to be refactored.

        Object value = null;

        // 2. keep on refactoring for different arguments.
        // 2.1 boolean
        if (parameter.getType() == boolean.class) {
            value = arguments.contains("-" + option.value());
        }
        // 2.2 integer
        if (parameter.getType() == int.class) {
            int index = arguments.indexOf("-" + option.value());
            value = Integer.valueOf(arguments.get(index + 1));  // to get the second parameter-"8080".
        }
        // 2.3 String
        if (parameter.getType() == String.class) {
            int index = arguments.indexOf("-" + option.value());
            value = String.valueOf(arguments.get(index + 1));  // to get the second parameter-"8080".
        }

        return (T) constructor.newInstance(value);

    }
}
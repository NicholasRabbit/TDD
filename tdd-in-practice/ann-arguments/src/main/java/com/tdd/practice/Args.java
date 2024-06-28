package com.tdd.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
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
            value = String.valueOf(arguments.get(index + 1));
        }

        return (T) constructor.newInstance(value);

    }

    // (I). refactored the parse(...) by write a mew method-parseMultiple in order to compare with "parse(...)"
    // This method can only be called by testMultipleParing(...)!!
    public static <T> T parseMultiple(Class<T> optionsClass, String... args) throws Exception {

        List<String> arguments = Arrays.asList(args);

        // I, Get all the constructors.
        Constructor<?>[] constructors = optionsClass.getDeclaredConstructors();
        Constructor<?> constructor = constructors[0];
        // parameters : [l, p, d]
        Parameter[] parameters = constructor.getParameters();

        List<Object> list = new ArrayList<>();
        for (Parameter p : parameters) {
            Option option = p.getAnnotation(Option.class);
            if (p.getType() == boolean.class) {
                list.add(arguments.contains("-" + option.value()));
            }
            if (p.getType() == int.class) {
                int index = arguments.indexOf("-" + option.value());
                list.add(Integer.valueOf(arguments.get(index + 1)));
            }
            if (p.getType() == String.class) {
                int index = arguments.indexOf("-" + option.value());
                list.add(String.valueOf(arguments.get(index + 1)));
            }

        }

        return (T) constructor.newInstance(list.get(0), list.get(1), list.get(2));

    }


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
     *  The "if..." is the code which is the so-called bad smell.
     *  Why is it a bad smell?
     *  If we want to add a new type of data we have to add more "if...". Then the code
     *  will definitely not be readable, maintained.
     *
     *  See ArgsRefactor.java.
     * */
    private static Object parseOption(List<String> arguments, Parameter parameter) {
        Option option = parameter.getAnnotation(Option.class);
        Object value = null;
        if (parameter.getType() == boolean.class) {
            value = arguments.contains("-" + option.value());
        }
        if (parameter.getType() == int.class) {
            int index = arguments.indexOf("-" + option.value());
            value = Integer.valueOf(arguments.get(index + 1));  // to get the second parameter-"8080".
        }
        if (parameter.getType() == String.class) {
            int index = arguments.indexOf("-" + option.value());
            value = String.valueOf(arguments.get(index + 1));
        }
        return value;
    }


}
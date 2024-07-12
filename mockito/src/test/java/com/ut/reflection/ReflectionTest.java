package com.ut.reflection;

import com.ut.reflection.annotation.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Review reflection of constructors with unit test.
 * */
public class ReflectionTest {

    private Class<?> optionClass;
    private Constructor<?>[] constructors;

    @BeforeEach
    public void setUp() throws Exception{
        optionClass = Options.class;
        constructors = optionClass.getConstructors();
    }


    @Test
    public void shouldHaveTwoConstructors() throws Exception {
        assertEquals(2, constructors.length);
    }

    @Test
    public void shouldGetNoneIfNoParametersInConstructor() throws Exception {
        Constructor<?> constructor = constructors[0];
        assertEquals(0, constructor.getParameters().length);
    }

    @Test
    public void shouldHaveThreeParametersOfTheSecondConstructor() throws Exception {
        Constructor<?> constructor = constructors[1];
        assertEquals(3, constructor.getParameters().length);
    }

    @Test
    public void shouldBeAnnotationPresent() throws Exception {
        Constructor<?> constructor = constructors[1];
        Parameter[] parameters = constructor.getParameters();
        assertTrue(parameters[0].isAnnotationPresent(Option.class));
    }

    @Test
    public void shouldObtainTheValueOfAnnotationFromParameter() throws Exception{
        Constructor<?> constructor = constructors[1];
        Parameter[] parameters = constructor.getParameters();
        Option option = parameters[0].getAnnotation(Option.class);
        assertEquals("l", option.value());
    }

}

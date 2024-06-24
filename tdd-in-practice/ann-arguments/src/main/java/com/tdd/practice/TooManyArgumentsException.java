package com.tdd.practice;

public class TooManyArgumentsException extends RuntimeException{

    public TooManyArgumentsException() {
    }

    public TooManyArgumentsException(String message) {
        super(message);
    }
}

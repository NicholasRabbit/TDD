package com.tdd.practice.exception;

public class TooManyArgumentsException extends RuntimeException{

    public TooManyArgumentsException() {
    }

    public TooManyArgumentsException(String message) {
        super(message);
    }
}

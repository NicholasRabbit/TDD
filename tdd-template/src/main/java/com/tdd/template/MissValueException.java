package com.tdd.template;

public class MissValueException extends RuntimeException {

    public MissValueException(){

    }

    public MissValueException(String message) {
        super(message);
    }

}

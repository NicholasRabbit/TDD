package com.tdd.demo;

import java.util.Map;

public class PlainText implements  Segment {

    private String text;

    private Map<String,String> variables;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object other){
       return text.equals(((PlainText)other).text);
    }

    @Override
    public String evaluate(Map<String, String> variables) {
        return text;   //Write simple codes to pass test as soon as possible.
    }


}

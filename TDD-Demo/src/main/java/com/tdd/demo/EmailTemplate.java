package com.tdd.demo;

public class EmailTemplate {

    private String variableValue;
    private String templateText;

    public EmailTemplate(String templateText) {
        this.templateText = templateText;
    }

    public void set(String variable, String value) {
        this.variableValue = value;
    }

    public String evaluate() {
        //return "Hello, Reader";   //hard codes. Step 2
        //return "Hello, " + variableValue;   //Step 4
        return this.templateText.replaceAll("\\$\\{name\\}", variableValue);   //Step 5

    }
}

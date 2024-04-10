package com.tdd.demo;

import java.util.HashMap;
import java.util.Map;

public class EmailTemplate {

    //private String variableValue;
    private String templateText;

    private Map<String, String> variables;   // Step 6

    public EmailTemplate(String templateText) {
        this.templateText = templateText;
        this.variables = new HashMap<String, String>();
    }

    public void set(String variable, String value) {
        variables.put(variable, value);
        //this.variableValue = value;
    }

    public String evaluate() {
        //return "Hello, Reader";   //hard codes. Step 2
        //return "Hello, " + variableValue;   //Step 4
        //return this.templateText.replaceAll("\\$\\{name\\}", variableValue);   //Step 5

        String result = templateText;
        for(Map.Entry<String, String> entry : variables.entrySet()){
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;

    }
}

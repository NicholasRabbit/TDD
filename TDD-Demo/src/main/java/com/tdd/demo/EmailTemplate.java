package com.tdd.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //9.2 keep on refactoring.
        /*String result = templateText;
        for(Map.Entry<String, String> entry : variables.entrySet()){
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }*/

        //9.1 refactor
        /*if(templateText.matches(".*\\$\\{.+\\}.*")){
            throw new MissValueException();
        }*/
        //This method is so cluttered and is needed to be refactored after so many lines written above.

        String result = replaceVariables(templateText);
        checkForMissingValues(result);

        return result;

    }

    //9.2
    private String replaceVariables(String templateText){
        String result = templateText;
        for(Map.Entry<String, String> entry : variables.entrySet()){
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    //9.1 refactor
    private void checkForMissingValues(String result){
        /*if(result.matches(".*\\$\\{.+\\}.*")){   //using regular expression to match "xxx${xxx}xxx".
            throw new MissValueException();
        }*/

        //10.2 Detailed and meaningful exception message as expected.
        Pattern pattern = Pattern.compile("\\$\\{.+\\}");
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()){
            throw new MissValueException("No value for " + matcher.group());
        }

    }
}

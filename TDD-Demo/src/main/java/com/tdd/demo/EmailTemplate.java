package com.tdd.demo;

import java.util.HashMap;
import java.util.List;
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

        //String result = replaceVariables(templateText);
        //checkForMissingValues(result);

        //return result;
        //==================

        /*
         * "TestTemplateParse.java" 6. Refactoring.
         * */
        EmailTemplateParse parse = new EmailTemplateParse();
        List<String> segments = parse.parse(templateText);
        /*StringBuilder result = new StringBuilder();
        for(String s : segments){
            append(s, result);
        }*/

        //return result.toString();

        //TestTemplateParse.java: 7. Keep codes in a uniform level of abstraction.
        return concatenate(segments);

    }

    private String concatenate(List<String> segments){
        StringBuilder result = new StringBuilder();
        for(String s : segments){
            append(s, result);
        }
        return result.toString();
    }

    //9.2 - TestTemplateRefactor
    private String replaceVariables(String templateText){
        String result = templateText;
        for(Map.Entry<String, String> entry : variables.entrySet()){
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    //9.1 refactor - TestTemplateRefactor
    private void checkForMissingValues(String result){
        /*if(result.matches(".*\\$\\{.+\\}.*")){   //using regular expression to match "xxx${xxx}xxx".
            throw new MissValueException();
        }*/

        //10.2 Detailed and meaningful exception message as expected.  - TestTemplateRefactor
        Pattern pattern = Pattern.compile("\\$\\{.+\\}");
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()){
            throw new MissValueException("No value for " + matcher.group());
        }

    }

    private void append(String segment, StringBuilder result){
        /*if(segment.startsWith("${") && segment.endsWith("}")){
            String var = segment.substring(2, segment.length() - 1);
            if(!variables.containsKey(var)){
                throw  new MissValueException("No value for " + segment);
            }

            String value = variables.get(var);
            result.append(value);   //append variables
        }else {
            result.append(segment);  //append plain text
        }*/   //Before it was refactored.

        //TestTemplateParse.java: 8. Refactoring append().
        if(isVariable(segment)){
            evaluateVariables(segment, result);
        } else {
            result.append(segment);
        }

    }

    //TestTemplateParse.java: 8. Refactoring append().
    public static boolean isVariable(String segment){
        return segment.startsWith("${") && segment.endsWith("}");
    }

    private void evaluateVariables(String segment, StringBuilder result){
        String var = segment.substring(2, segment.length() - 1);
        if(!variables.containsKey(var)){
            throw  new MissValueException("No value for " + segment);
        }
        String value = variables.get(var);
        result.append(value);   //append variables
    }

}

package com.tdd.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailTemplate {

    private String templateText;

    private Map<String, String> variables;   // Step 6

    public EmailTemplate(String templateText) {
        this.templateText = templateText;
        this.variables = new HashMap<>();
    }

    public void set(String variable, String value) {
        variables.put(variable, value);
    }

    public String evaluate() {
        EmailTemplateParse parser = new EmailTemplateParse();
        List<Segment> segments = parser.parseSegments(templateText);
        return concatenate(segments);

    }


    private String concatenate(List<Segment> segments){
        StringBuilder result = new StringBuilder();
        for(Segment segment : segments){
            result.append(segment.evaluate(variables));   //This is where polymorphism works. Each subclass of Segment will call its "evaluate()".
        }
        return result.toString();
    }







}

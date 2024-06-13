package com.tdd.template;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A new clean and plain class to tackle the issue of "emptyTemplateRenderWithEmptyString()".
 * */
public class EmailTemplateParse {


    public List<String> parse(String template) {
        List<String> segments = new ArrayList<>();
        int index = collectSegments(segments, template);
        addTailText(segments, template, index);  //add the plain text if there is any of them.
        addEmptyStringIfTemplateWasEmpty(segments);
        return segments;
    }


    private int collectSegments(List<String> segments, String template) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");//${any words}
        Matcher matcher = pattern.matcher(template);
        int index = 0;
        while (matcher.find()) {
            addPrecedingPlainText(segments, template, matcher, index);
            addVariables(segments, template, matcher, index);
            index = matcher.end();   //Don't forget to move set the index with the return value of "matcher.end()".
        }

        return index;

    }

    private void addTailText(List<String> segments, String template, int index){
        if(index < template.length()){
            segments.add(template.substring(index));
        }
    }


    private void addVariables(List<String> segments, String src, Matcher m, int index){
        segments.add(src.substring(m.start(), m.end()));
    }

    private void addPrecedingPlainText(List<String> segments, String src, Matcher m, int index){
        if(index < m.start()){
            segments.add(src.substring(index, m.start()));
        }
    }


    private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
        if(segments.size() == 0)
            segments.add("");
    }

    public List<Segment> parseSegments(String template) {
        List<String> strings = parse(template);
        List<Segment> segments = new ArrayList<>();
        for(String s : strings){
            boolean isVariable = isVariable(s);
            if(isVariable){
                String name = s.substring(2, s.length() - 1);
                segments.add(new Variable(name));
            }else{
                segments.add(new PlainText(s));
            }
        }

        return segments;

    }

    public boolean isVariable(String segment){
        return segment.startsWith("${") && segment.endsWith("}");
    }
}

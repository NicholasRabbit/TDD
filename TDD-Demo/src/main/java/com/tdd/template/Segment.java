package com.tdd.template;

import java.util.Map;

public interface Segment {

    public abstract String evaluate(Map<String,String> variables);

}

package com.tdd.demo;

import java.util.Collections;
import java.util.List;

/**
 * A new clean and plain class to tackle the issue of "emptyTemplateRenderWithEmptyString()".
 * */
public class EmailTemplateParse {


    public List<String> parse(String template) {
        return Collections.singletonList(template);
    }
}

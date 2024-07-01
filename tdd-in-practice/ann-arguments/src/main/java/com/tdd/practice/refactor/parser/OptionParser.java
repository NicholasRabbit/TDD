package com.tdd.practice.refactor.parser;

import com.tdd.practice.Option;

import java.util.List;

public interface OptionParser {
    Object parse(List<String> arguments, Option option);
}

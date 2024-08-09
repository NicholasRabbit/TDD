package com.tdd.practice.refactor.parser_refactored;

import com.tdd.practice.annotation.Option;

import java.util.List;

public interface OptionParserRefactored<T> {
    Object parse(List<String> arguments, Option option);
}

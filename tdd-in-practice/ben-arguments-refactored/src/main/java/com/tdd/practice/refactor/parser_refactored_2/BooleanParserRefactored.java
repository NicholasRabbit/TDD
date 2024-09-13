/*package com.tdd.practice.refactor.parser_refactored_2;

<<<<<<< HEAD
import com.tdd.practice.annotation.Option;

import java.util.List;

import static com.tdd.practice.refactor.parser_refactored_2.SingleValueOptionParser.values;

// This class should be deleted after refactoring, but I keep it for comparing.
public class BooleanParserRefactored implements OptionParserRefactored<Boolean> {
=======
/*
 * This class should be deleted after refactoring, but I keep it for comparing.
 * */
/*public class BooleanParserRefactored implements OptionParserRefactored<Boolean> {
>>>>>>> tdd-in-practice

    private BooleanParserRefactored() {
    }

    @Override
    public Boolean parse(List<String> arguments, Option option) {
        return values(arguments, option, 0).map(it -> true).orElse(false);
    }

}*/

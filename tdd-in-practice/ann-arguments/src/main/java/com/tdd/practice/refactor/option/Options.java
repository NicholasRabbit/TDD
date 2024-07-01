package com.tdd.practice.refactor.option;

import com.tdd.practice.Option;

public record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

}

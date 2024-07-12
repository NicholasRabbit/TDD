package com.ut.reflection;

import com.ut.reflection.annotation.Option;

public class Options {

    private boolean log;
    private int port;
    private String dir;

    public Options() {
    }

    public Options(@Option("l") boolean log, @Option("p") int port, @Option("d") String dir) {
        this.log = log;
        this.port = port;
        this.dir = dir;
    }
}

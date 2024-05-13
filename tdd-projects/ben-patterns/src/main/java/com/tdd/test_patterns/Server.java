package com.tdd.test_patterns;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server {

    private ThreadFactory threadFactory = Executors.defaultThreadFactory();

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void start() {

    }

    public void stop() {

    }
}

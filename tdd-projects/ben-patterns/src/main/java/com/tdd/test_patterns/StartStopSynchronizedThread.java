package com.tdd.test_patterns;

import java.util.concurrent.TimeUnit;

public class StartStopSynchronizedThread extends Thread{


    public StartStopSynchronizedThread(Runnable task) {

    }

    @Override
    public void run() {
        super.run();
    }

    public void shouldBeStartedWithin(long timeout, TimeUnit unit) throws InterruptedException {

    }

    public void shouldBeStoppedWithin(long timeout, TimeUnit unit) throws InterruptedException {

    }
}

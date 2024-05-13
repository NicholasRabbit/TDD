package com.tdd.test_patterns;

import org.junit.Test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Test Patterns 4.5.3
 * 3, Intimate Inner Class
 * We can use anonymous inner class or nested inner class to implement this kind of test double.
 *
 * The test throws "NullPointer Exception" because the "thead" in this test is null. I will handle
 * that later. Right now I just write code to understand what is "Intimate Inner Class" in test patters.
 * */
public class IntimateAnonymousInnerClassTest {

    // Shared between the test class and a test double.
    private StartStopSynchronizedThread thread;

    @Test
    public void testStartingAndStoppingThreadsThroughAnExecutorService() throws Exception {
        Server server = new Server();
        // Intimate inner class-the anonymous "ThreadFactory".
        server.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable task) {
                thread =  new StartStopSynchronizedThread(task);
                return thread;
            }
        });

        server.start();
        // Test can access shared fields.
        thread.shouldBeStartedWithin(1L, TimeUnit.SECONDS);
        server.stop();
        thread.shouldBeStoppedWithin(1L, TimeUnit.SECONDS);

    }


}

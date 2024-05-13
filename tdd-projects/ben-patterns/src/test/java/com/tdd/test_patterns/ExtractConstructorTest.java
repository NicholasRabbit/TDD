package com.tdd.test_patterns;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * 5, Extract Constructor
 * */
public class ExtractConstructorTest {

    // The test is failed because I don't know how to use "URL" but I have already understood
    // what the "Extract Constructor" is. I will refactor later to pass the test.
    @Test
    public void testExtractConstructor() throws Exception {
        URL urlA = new URL("/usr/local/a.txt");
        URL urlB = new URL("/usr/local/b.txt");
        LogFileMerge logFile = new LogFileMerge(urlA, urlB);
        assertTrue( "/usr/local/a.txt".equals(logFile.getLogFileA().getPath()));
        assertTrue( "/usr/local/b.txt".equals(logFile.getLogFileB().getPath()));
    }
}

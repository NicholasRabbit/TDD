package com.tdd.test_patterns;

import java.net.URL;

public class LogFileMerge {

    private URL logFileA, logFileB;

    public LogFileMerge() throws Exception {
        //this.logFileA = new URL("https://abc.xyz");
        //this.logFileB = new URL("https://mno.info");
        this(new URL("https://mno.info"), new URL("https://abc.xyz"));
    }

    // Dependencies exposed to tests.
    // Extract constructor
    LogFileMerge(URL logFileA, URL logFileB) {
        this.logFileA = logFileA;
        this.logFileB = logFileB;
    }

    public URL getLogFileA() {
        return logFileA;
    }

    public void setLogFileA(URL logFileA) {
        this.logFileA = logFileA;
    }

    public URL getLogFileB() {
        return logFileB;
    }

    public void setLogFileB(URL logFileB) {
        this.logFileB = logFileB;
    }
}

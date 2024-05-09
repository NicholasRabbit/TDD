package com.tdd.assertion_patterns;

public class PaperBoy {

    private DailyPaper dailyPaper;

    public PaperBoy() {

    }

    public void addToRoute(Customer customer) {
        //There is no implementation in the source code so that I write code myself.
        //And it pass the test!
        customer.onPaperDelivered(dailyPaper);
    }

    public void deliver(DailyPaper dailyPaper) {
        this.dailyPaper = dailyPaper;
    }
}

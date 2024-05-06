package com.tdd.test_double;

public class OrderProcessor {

    private PricingService pricingService;

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public void process(Order order) {

    }
}

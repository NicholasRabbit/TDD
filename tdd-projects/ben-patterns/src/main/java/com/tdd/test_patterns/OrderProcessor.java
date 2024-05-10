package com.tdd.test_patterns;

public class OrderProcessor {

    private PricingService pricingService;

    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public void process(Order order) {
        float discountPercentage = pricingService.getDiscountPercentage(order.getCustomer(), order.getProduct());
        float discountedPrice = order.getProduct().getPrice() * (1 - discountPercentage / 100.0f);
        float balance = order.getCustomer().getBalance() - discountedPrice;
        order.getCustomer().setBalance(balance);
    }
}

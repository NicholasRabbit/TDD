package com.tdd.test_double;

public class PricingServiceTestDouble extends PricingService {

    private float discount;

    public PricingServiceTestDouble(float discount) {
        this.discount = discount;
    }

    public float getDiscountPercentage(Customer customer, Product product){
        return discount;
    }

}

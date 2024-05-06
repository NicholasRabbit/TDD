package com.tdd.test_double;

public class Order {

    private Customer customer;
    private Product product;

    public Order(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

}

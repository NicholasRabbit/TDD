package com.tdd.test_patterns;

public class Customer {

    private float balance;
    private static int number;

    public Customer(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        //return 73.0f;  //hard coded
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}

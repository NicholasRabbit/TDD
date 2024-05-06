package com.tdd.test_double;

public class Customer {

    private float balance;

    public Customer(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        //return 10.0f;  //hard coded
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}

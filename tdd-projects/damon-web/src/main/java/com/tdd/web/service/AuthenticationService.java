package com.tdd.web.service;

public interface AuthenticationService {

    public abstract boolean isValidLogin(String username, String password);

    public abstract void addUser(String username, String password);

}

package com.tdd.web.service;

public interface AuthenticationService {

    public abstract boolean isValidLogin(String username, String password);

}

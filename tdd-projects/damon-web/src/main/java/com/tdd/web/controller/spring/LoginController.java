package com.tdd.web.controller.spring;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tdd.web.service.AuthenticationService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    private AuthenticationService authenticator;

    public void setAuthenticator(AuthenticationService authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        if (authenticator.isValidLogin(username, password)) {
            return new ModelAndView("frontpage");
        }


        return new ModelAndView("wrongpassword");
    }
}

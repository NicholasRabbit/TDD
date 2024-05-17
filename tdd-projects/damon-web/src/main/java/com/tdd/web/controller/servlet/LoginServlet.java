package com.tdd.web.controller.servlet;

import com.tdd.web.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        AuthenticationService authenticator = getAuthenticationService();
        boolean validLogin = authenticator.isValidLogin(username, password);
        if (validLogin) {
            response.sendRedirect("/frontpage");
            request.getSession().setAttribute("username", username);
        } else {
            response.sendRedirect("/invalidLogin");   // hard coded
            // The simulation of storing username and password.
        }
    }

    protected AuthenticationService getAuthenticationService() {
        return null;
    }

}

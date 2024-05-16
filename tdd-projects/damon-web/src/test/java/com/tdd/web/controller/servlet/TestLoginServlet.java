package com.tdd.web.controller.servlet;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServlet;

import static org.junit.Assert.assertEquals;

public class TestLoginServlet {

    /**
     * 1, To test whether it will be to redirect to the error page if a use input a wrong password.
     *
     * Remember that "test, code, refactor".
     * */
    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {

        HttpServlet servlet = new LoginServlet();
        // Create fake a request object and a fake response object.
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", "nosuchuser");
        request.addParameter("j_password", "wrongpassword");
        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);
        assertEquals("/invalidLogin", response.getRedirectedUrl());

    }

    /**
     * The second test. It is to force use to rewrite the logic in service.
     * 2, To test if a valid user login in with correct password will be redirected to the front page.
     * At this point in time the return value is hard coded in the service method of LoginServlet so
     * it is definitely not correct when a valid user is trying to login.
     * We should write some logic in the service method to pass the second test.
     * */
    @Test
    public void validLoginForwardsToFrontPageAndStoresUsername() throws Exception {
        HttpServlet servlet = new LoginServlet();
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", "validuser");
        request.addParameter("j_password", "correctpassword");
        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);

        assertEquals("/frontpage", response.getRedirectedUrl());
        assertEquals("validuser", request.getSession().getAttribute("username"));

    }
}

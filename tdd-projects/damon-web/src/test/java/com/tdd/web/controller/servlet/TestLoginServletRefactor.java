package com.tdd.web.controller.servlet;

import com.tdd.web.service.AuthenticationService;
import com.tdd.web.service.FakeAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServlet;

import static org.junit.Assert.assertEquals;

/**
 * Refactor TestLoginServlet here.
 * Oops! It is much more concise than it was and what the most important is all the tests are passed.
 * */
public class TestLoginServletRefactor {

    private static final String CORRECT_PASSWORD = "correctpassword";
    private static final String VALID_USERNAME = "validuser";

    private LoginServlet servlet;
    private FakeAuthenticationService authenticator;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    @Before
    public void setUp() throws Exception {
        authenticator = new FakeAuthenticationService();
        authenticator.addUser(VALID_USERNAME, CORRECT_PASSWORD);
        servlet = new LoginServlet() {
            // Use fake instead of real thing. Namely return a fake "authenticator".
            @Override
            protected AuthenticationService getAuthenticationService() {
                return authenticator;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

    }

    /**
     * 1, First test.
     * */
    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        // Create fake a request object and a fake response object.
        request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", "wrongpassword");
        response = new MockHttpServletResponse();
        servlet.service(request, response);
        assertEquals("/invalidLogin", response.getRedirectedUrl());

    }

    /**
     * 2, The second test.
     * */
    @Test
    public void validLoginForwardsToFrontPageAndStoresUsername() throws Exception {
        request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", CORRECT_PASSWORD);
        response = new MockHttpServletResponse();
        servlet.service(request, response);

        assertEquals("/frontpage", response.getRedirectedUrl());
        assertEquals("validuser", request.getSession().getAttribute("username"));

    }




}

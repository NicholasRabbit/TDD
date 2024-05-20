package com.tdd.web.controller.spring;

import com.tdd.web.service.AuthenticationService;
import com.tdd.web.service.FakeAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

/**
 * Refactor TestLoginController here.
 * */

public class TestLoginControllerRefactor {

    private static final String VALID_USERNAME = "validuser";
    private static final String CORRECT_PASSWORD = "correctpassword";
    private static final String USERNAME = "j_username";
    private static final String PASSWORD = "j_password";
    private AuthenticationService authenticator;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private LoginController controller;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        authenticator = new FakeAuthenticationService();
        authenticator.addUser(VALID_USERNAME, CORRECT_PASSWORD);  // Simulation of saving usernames and passwords in database.
        controller = new LoginController();
        controller.setAuthenticator(authenticator);
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        request.setParameter(USERNAME, "nosuchusername");
        request.setParameter(PASSWORD, "nosuchpassword");
        ModelAndView v = controller.handleRequest(request, response);
        // User should be redirected to "wrongpassword" page.
        assertEquals("wrongpassword", v.getViewName());

    }

    @Test
    public void validLoginForwardsToFrontPage() throws Exception {
        request.setMethod("GET");
        request.setParameter(USERNAME, VALID_USERNAME);
        request.setParameter(PASSWORD, CORRECT_PASSWORD);
        ModelAndView v = controller.handleRequest(request, response);
        assertEquals("frontpage", v.getViewName());

    }

}

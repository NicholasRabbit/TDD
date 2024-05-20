package com.tdd.web.controller.spring;

import com.tdd.web.service.AuthenticationService;
import com.tdd.web.service.FakeAuthenticationService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import static org.junit.Assert.*;

/**
 * Test Spring MVC
 *
 * I will refactor it in TestLoginControllerRefactor
 * */
public class TestLoginController {

    private static final String VALID_USERNAME = "validuser";
    private static final String CORRECT_PASSWORD = "correctpassword";

    /**
     * 1, Test invalid user or wrong password.
     * Pass the test with hard code.
     * Postscript: this test is failed after refactoring the LoginController and I keep id for the sake of comparison.
     */
    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        // Populate mock objects.
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("j_username", "nosuchusername");
        request.setParameter("j_password", "nosuchpassword");
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Invoke Controller's handleRequest method.
        Controller c = new LoginController();
        ModelAndView v =  c.handleRequest(request, response);

        // User should be redirected to "wrongpassword" page.
        assertEquals("wrongpassword", v.getViewName());

    }

    /**
     * 2, Write the second test and it will definitely fail because of the hard code we write to pass the first test.
     * Then we should refactor the handleRequest in LoginController.
     * First, we delegate the authentication to the same AuthenticationService as we used in LoginServlet.
     * The significant difference between LoginController and LoginServlet is how we populate the "AuthenticationService".
     * In Spring framework, we can adopt DI(dependence injection) by wiring an implementation bean to LoginController in
     * a separate configuration file which is normally named spring.xml.
     * But we still use mock objects in this test.
     */
    @Test
    public void validLoginForwardsToFrontPage() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setParameter("j_username", VALID_USERNAME);
        request.setParameter("j_password", CORRECT_PASSWORD);
        MockHttpServletResponse response = new MockHttpServletResponse();

        // A mock object
        AuthenticationService mock = new FakeAuthenticationService();
        mock.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        LoginController c = new LoginController();
        c.setAuthenticator(mock);
        ModelAndView v =  c.handleRequest(request, response);

        // A valid user should be redirected to "frontpage" page.
        assertEquals("frontpage", v.getViewName());

    }
}

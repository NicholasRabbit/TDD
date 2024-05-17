package com.tdd.web.controller.servlet;

import com.tdd.web.service.AuthenticationService;
import com.tdd.web.service.FakeAuthenticationService;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServlet;

import static org.junit.Assert.assertEquals;

public class TestLoginServlet {

    /**
     * 1, First test.
     * To test whether it will be to redirect to the error page if a use input a wrong password.
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
     * 2, The second test. It forces us to rewrite the logic in service in order to treat invalid and valid users.
     * To test if a valid user login in with correct password will be redirected to the front page.
     * At this point in time the return value is hard coded in the service method of LoginServlet so
     * it is definitely not correct when a valid user is trying to login.
     * We should write some logic in the service method to pass the second test.
     *
     * In case of reviewing code in the future I keep this test failed and rewrite code in another test.
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

    /**
     * Rewrite the second test above.
     * The test is passed with flying colours, but the first test failed because we didn't override the
     * getAuthenticationService in it.
     *
     * Well, some fixture patterns appeared in my mind so it is the right time for me to remove the duplicated code in our
     * code and pass the first test at the same time. I will do it in TestLoginServletRefactor
     *
     * */
    @Test
    public void rewriteTestOfValidLogin() throws Exception {
        final String validUsername = "validuser";
        final String correctPassword = "correctpassword";

        // Configure fake AuthenticationService.
        final FakeAuthenticationService authenticator = new FakeAuthenticationService();
        authenticator.addUser(validUsername, correctPassword);

        LoginServlet servlet = new LoginServlet() {
            // Use fake instead of real thing. Namely return a fake "authenticator".
            @Override
            protected AuthenticationService getAuthenticationService() {
                return authenticator;
            }
        };

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
        request.addParameter("j_username", "validuser");
        request.addParameter("j_password", "correctpassword");
        MockHttpServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);

        assertEquals("/frontpage", response.getRedirectedUrl());
        assertEquals("validuser", request.getSession().getAttribute("username"));

    }


}

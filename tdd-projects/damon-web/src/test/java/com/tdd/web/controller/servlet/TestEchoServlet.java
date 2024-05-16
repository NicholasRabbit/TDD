package com.tdd.web.controller.servlet;

import com.tdd.web.controller.servlet.EchoServlet;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.*;


/**
 * What is "junit.framework.TestCase"?
 * elaboration:
 *
 *
 * */
public class TestEchoServlet {

    /**
     * The following test throws "NoClassDefFoundError: org/springframework/core/CollectionFactory".
     * The reason, which I guess, is the version of servlet-api(3.1.0) is different from
     * the one which is 2.4 in "spring-mock" dependency.
     * I didn't guess correctly. The reason is still not found right now.
     * !!! How stupid I was. The exception stated clearly that there is no "CollectionFactory" which is
     * a class of Spring Core.
     *
     * A possible approach is that we might rewrite the MockHttpServletRequest.
     * */
    @Test
    public void testEchoingParametersWithMultiplyValues() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();  // throws an exception.
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addParameter("param1", "param1value1");
        request.addParameter("param2", "param2value2");
        request.addParameter("param3", "param3value3");
        new EchoServlet().doGet(request, response);
        String[] lines = response.getContentAsString().split("\n");

        assertEquals("Expected as many lines as we have parameter value", 3, lines.length);

    }


}

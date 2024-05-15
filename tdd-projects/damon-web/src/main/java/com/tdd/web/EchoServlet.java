package com.tdd.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class EchoServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type", "text/plain");
        PrintWriter writer = response.getWriter();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String parameter = String.valueOf(names.nextElement());
            String[] values = request.getParameterValues(parameter);
            for (int i = 0; i < values.length; i++) {
                writer.write(parameter + "=" + values[i]);
                writer.write("\n");
            }
        }

        writer.close();

    }
}

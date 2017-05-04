package org.harmony.test.spring.boot.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuxii@foxmail.com
 */
public class GreetServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello " + (req.getParameter("name") == null ? "World" : req.getParameter("name")));
        resp.getWriter().flush();
    }

}

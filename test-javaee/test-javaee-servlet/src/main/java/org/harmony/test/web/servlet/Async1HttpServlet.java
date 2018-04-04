package org.harmony.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuxii@foxmail.com
 */
@WebServlet(urlPatterns = "/async1", asyncSupported = true)
public class Async1HttpServlet extends HttpServlet {

    private static final long serialVersionUID = -925946585433135037L;
    public static final ThreadLocal<Object> vars = new InheritableThreadLocal<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter w = resp.getWriter();
        w.write("\nget var before ac start " + vars.get());
        // AsyncContext ac = req.startAsync(req, resp);
        AsyncContext ac = req.getAsyncContext();
        // ac.start(() -> {
        // w.write("\nget var after ac start " + vars.get()); // vars.get() is => null
        // ac.complete();
        // });

        new Thread(() -> {
            // InheritableThreadLocal
            w.write("\nget var after ac start " + vars.get()); // vars.get() is => not null
            ac.complete();
        }).start();
    }

}

package org.harmony.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wuxii@foxmail.com
 */
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncHttpServlet extends HttpServlet {

    private static final AtomicInteger ids = new AtomicInteger();

    private static final long serialVersionUID = -4858420128324480328L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter w = resp.getWriter();
        ServletContext sc = req.getServletContext();
        int id = ids.getAndIncrement();

        AsyncContext ac = req.startAsync();

        sc.log("step 1");
        w.write(id + " default request run in " + Thread.currentThread().getName() + "\n");
        ac.start(() -> {
            try {
                // some take long time biz logic
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sc.log("step 3");
            w.write(id + " async request run in " + Thread.currentThread().getName() + "\n");
            ac.complete();
        });
        sc.log("step 2");
        w.flush();
    }

}

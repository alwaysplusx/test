package org.harmony.test.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.harmony.test.web.servlet.Async1HttpServlet;

/**
 * @author wuxii@foxmail.com
 */
@WebFilter(urlPatterns = "/async1", asyncSupported = true)
public class Async1Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintWriter w = response.getWriter();
        AsyncContext ac = request.startAsync(request, response);
        Async1HttpServlet.vars.set(Math.random() * 1000);
        ac.addListener(new AsyncListener() {

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                w.write("\ntimeout");
                Async1HttpServlet.vars.remove();
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                w.write("\nstart async");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                w.write("\nerror");
                Async1HttpServlet.vars.remove();
            }

            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                w.write("\ncomplete");
                Async1HttpServlet.vars.remove();
            }

        });
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}

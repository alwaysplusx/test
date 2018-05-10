//package org.harmony.test.ntlm;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import jespa.http.HttpSecurityService;
//
///**
// * @author wuxii@foxmail.com
// */
//public class JespaNtlmHttpFilter extends HttpSecurityService implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//
//		super.doFilter(req, resp, chain);
//
//		System.out.println("request Authorization with: " + request.getHeader("Authorization"));
//		System.out.println("response with WWW-Authenticate: " + response.getHeader("WWW-Authenticate"));
//
//	}
//
//	@Override
//	public void destroy() {
//		super.destroy();
//	}
//
//}

package org.harmony.test.ntlm;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.http.NtlmHttpFilter;
import jcifs.smb.NtlmPasswordAuthentication;

/**
 * @author wuxii@foxmail.com
 */
public class NTLMHttpFilter extends NtlmHttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		super.doFilter(request, response, chain);
	}

	@Override
	protected NtlmPasswordAuthentication negotiate(HttpServletRequest req, HttpServletResponse resp,
			boolean skipAuthentication) throws IOException, ServletException {
		return super.negotiate(req, resp, skipAuthentication);
	}

}

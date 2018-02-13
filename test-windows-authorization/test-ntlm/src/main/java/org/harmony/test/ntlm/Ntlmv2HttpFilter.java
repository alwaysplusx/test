package org.harmony.test.ntlm;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Ntlmv2HttpFilter extends org.ntlmv2.filter.NtlmFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		super.doFilter(req, res, filterChain);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}

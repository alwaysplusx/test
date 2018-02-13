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
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//
//import jcifs.CIFSContext;
//import jcifs.context.SingletonContext;
//import jcifs.http.NtlmSsp;
//import jcifs.netbios.UniAddress;
//import jcifs.smb.NtlmPasswordAuthentication;
//
///**
// * @author wuxii@foxmail.com
// */
//public class NTLMHttpFilter implements Filter {
//
//	private Logger log = LogManager.getLogger(NTLMHttpFilter.class);
//
//	private CIFSContext transportContext;
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		transportContext = SingletonContext.getInstance();
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//
//		UniAddress address = transportContext.getNameServiceClient().getByName("192.168.1.33", true);
//		byte[] challenge = transportContext.getTransportPool().getChallenge(transportContext, address);
//
//		NtlmPasswordAuthentication authenticate = NtlmSsp.authenticate(transportContext, request, response, challenge);
//
//		if (authenticate != null) {
//			transportContext.getTransportPool().logon(transportContext, address);
//		}
//
//		log.info("request Authorization with: " + request.getHeader("Authorization"));
//		log.info("response with WWW-Authenticate: " + response.getHeader("WWW-Authenticate"));
//
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}

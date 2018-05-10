package org.harmony.test.ntlm;

import org.junit.Test;

import jcifs.Config;
import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbSession;
import jcifs.util.LogStream;

/**
 * @author wuxii@foxmail.com
 */
public class SmbSessionTest {

	private static final String domainController = "192.168.1.33";
	private static final String domainDns = "192.168.1.33,192.168.1.255";
	private static final String username = "wuxin";
	private static final String password = "aA123456";

	public static void main(String[] args) throws Exception {
		Config.setProperty("jcifs.http.domainController", domainController);
		Config.setProperty("jcifs.netbios.wins", domainDns);

		// Config.setProperty("jcifs.smb.client.domain", "huiju.com");
		Config.setProperty("jcifs.smb.client.username", username);
		Config.setProperty("jcifs.smb.client.password", password);
		LogStream.setLevel(4);

		// 0.0.0.0<00>/192.168.1.33
		UniAddress dc = UniAddress.getByName(domainController, true);
		byte[] challenge = SmbSession.getChallenge(dc);
		System.out.println(challenge);

		Thread.sleep(1000 * 5);

		challenge = SmbSession.getChallenge(dc);

		System.out.println(challenge);

	}

	@Test
	public void testLogin() throws Exception {
		UniAddress dc = UniAddress.getByName(domainController, true);
		SmbSession.logon(dc, new NtlmPasswordAuthentication(domainController, username, password));
	}

	@Test
	public void test() throws Exception {
		UniAddress dc = UniAddress.getByName("192.168.163.129", true);
		SmbSession.logon(dc, new NtlmPasswordAuthentication("192.168.163.129", "xin", "aA123456"));
	}

}

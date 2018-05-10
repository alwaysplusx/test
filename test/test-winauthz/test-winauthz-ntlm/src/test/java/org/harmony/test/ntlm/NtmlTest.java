package org.harmony.test.ntlm;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;

import org.junit.Test;
import org.ntlmv2.liferay.NtlmManager;

import jcifs.UniAddress;
import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.smb.NtlmContext;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbSession;

/**
 * @author wuxii@foxmail.com
 */
public class NtmlTest {

	private static final byte[] challenge = new byte[8];

	static {
		new SecureRandom().nextBytes(challenge);
	}

	static final String WORKSTATION = "WUXII";
	static final String username = "wuxin";
	static final String password = "aA123456";

	static final String domain = "huiju.com";
	static final String dcHost = "192.168.1.33";
	static final String dcNetbiosName = "192.168.1.33,192.168.1.255";

	static final String serviceAccount = "wuxin$@huiju.com";
	static final String servicePassword = "aA123456";

	static final NtlmManager ntlmManager = new NtlmManager(domain, dcHost, dcNetbiosName, serviceAccount,
			servicePassword);

	static final NtlmPasswordAuthentication ntlmAuth = new NtlmPasswordAuthentication(domain, username, password);

	private static final NtlmContext ntlmContext = new NtlmContext(ntlmAuth, true);

	public static void main(String[] args) throws Exception {
		// Generate type 1 message.
		byte[] rawType1Message = ntlmContext.initSecContext(new byte[] {}, 0, 0);
		// This way or derive a subclass from NtlmContext and set the workstation in the constructor.
		Type1Message type1Message = new Type1Message(rawType1Message);
		type1Message.setSuppliedWorkstation(WORKSTATION);

		// reset type 1 message with work station
		rawType1Message = type1Message.toByteArray();
		assertEquals(1, rawType1Message[8]);

		// Generate type 2 message.
		Type2Message type2Message = ntlmManager.negotiateType2Message(rawType1Message, challenge);
		byte[] rawType2Message = type2Message.toByteArray();
		assertEquals(2, rawType2Message[8]);

		// Generate type 3 message
		byte[] rawType3Message = ntlmContext.initSecContext(type2Message.toByteArray(), 0, 0);
		assertEquals(3, rawType3Message[8]);

		ntlmManager.authenticate(rawType3Message, challenge);
	}

	@Test
	public void testFile() throws Exception {
		SmbFile file = new SmbFile("smb://192.168.163.129/Downloads/a.txt", ntlmAuth);
		file.canRead();
		InputStream is = file.getInputStream();
		FileOutputStream os = new FileOutputStream(new File("./target/a.txt"));

		byte[] buf = new byte[1024 * 8];

		int index = is.read(buf);
		while (index != -1) {
			os.write(buf, 0, index);
			index = is.read(buf);
		}

		is.close();
		os.close();
	}

	@Test
	public void testSession() throws Exception {
		UniAddress dc = UniAddress.getByName("192.168.163.129", false);
		SmbSession.logon(dc, ntlmAuth);
	}

	@Test
	public void testLogon() {

	}

}

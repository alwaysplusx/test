//package org.harmony.test.ntlm;
//
//import java.io.IOException;
//import java.net.UnknownHostException;
//
//import org.junit.Test;
//
//import jcifs.Config;
//import jcifs.ntlmssp.Type1Message;
//import jcifs.ntlmssp.Type2Message;
//import jcifs.smb.NtlmChallenge;
//import jcifs.smb.SmbException;
//import jcifs.smb.SmbFile;
//import jcifs.smb.SmbSession;
//import jcifs.util.Base64;
//
//public class NtlmUniAddressTest {
//
//	private static final String MSG = "NTLM TlRMTVNTUAABAAAAB7IIogUABQAtAAAABQAFACgAAAAKANc6AAAAD1dVWElJSFVJSlU=";
//
//	public static void main(String[] args) throws UnknownHostException, SmbException {
//
//		Config.setProperty("jcifs.smb.client.domain", "192.168.1.33");
//		Config.setProperty("jcifs.http.domainController", "192.168.1.33");
//		Config.setProperty("jcifs.util.loglevel", "4");
//
//		NtlmChallenge challenge = SmbSession.getChallengeForDomain();
//		System.out.println(challenge);
//	}
//
//	@Test
//	public void testFile() throws Exception {
//		Config.setProperty("jcifs.smb.client.domain", "192.168.1.33");
//		Config.setProperty("jcifs.smb.client.username", "zhenghz");
//		Config.setProperty("jcifs.smb.client.password", "zhang123789");
//		SmbFile file = new SmbFile("smb://192.168.1.17/HJ-Install-PAK/HJ-Install-PAK权限记录表.xls");
//		System.out.println(file.canRead());
//	}
//
//	@Test
//	public void test() throws IOException {
//		String msg = MSG;
//		byte[] src = Base64.decode(msg.substring(5));
//		if (src[8] == 1) {
//			Type1Message type1 = new Type1Message(src);
//			Type2Message type2 = new Type2Message(type1, null, null);
//			msg = Base64.encode(type2.toByteArray());
//			System.out.println(msg);
//		} else if (src[8] == 3) {
//		}
//	}
//
//}

//package org.harmony.test.ntlm;
//
//import org.bouncycastle.util.encoders.Base64;
//
//import jcifs.CIFSContext;
//import jcifs.context.SingletonContext;
//import jcifs.netbios.UniAddress;
//import jcifs.ntlmssp.Type1Message;
//import jcifs.ntlmssp.Type2Message;
//
///**
// * @author wuxii@foxmail.com
// */
//public class CIFSContextTest {
//
//	private static final String MSG = "NTLM TlRMTVNTUAABAAAAB7IIogUABQAtAAAABQAFACgAAAAKANc6AAAAD1dVWElJSFVJSlU=";
//
//	public static void main(String[] args) throws Exception {
//		CIFSContext transportContext = SingletonContext.getInstance();
//		UniAddress address = transportContext.getNameServiceClient().getByName("192.168.1.33", true);
//		byte[] challenge = transportContext.getTransportPool().getChallenge(transportContext, address);
//		// authenticate
//		String msg = MSG;
//
//		byte[] src = Base64.decode(msg.substring(5));
//		Type1Message type1 = new Type1Message(src);
//		Type2Message type2 = new Type2Message(transportContext, type1, challenge, null);
//		// 
//		msg = new String(Base64.encode(type2.toByteArray()), "US-ASCII");
//		System.out.println(msg);
//
//	}
//
//}

//package org.harmony.test.negotiate;
//
//import java.util.Base64;
//
//import org.junit.Test;
//
//import waffle.windows.auth.IWindowsCredentialsHandle;
//import waffle.windows.auth.impl.WindowsAccountImpl;
//import waffle.windows.auth.impl.WindowsCredentialsHandleImpl;
//import waffle.windows.auth.impl.WindowsSecurityContextImpl;
//
///**
// * @author wuxii@foxmail.com
// */
//public class WaffleTest {
//
//	public static void main(String[] args) {
//		IWindowsAuthProvider prov = new WindowsAuthProviderImpl();
//		IWindowsIdentity identity = prov.logonDomainUser("wuxin", "huiju.com", "aA123456");
//		System.out.println(identity);
//		// IWindowsIdentity identity = prov.logonUser("wuxii", "xxx");
//		//		System.out.println("User identity: " + identity.getFqn());
//		//		for (IWindowsAccount group : identity.getGroups()) {
//		//			System.out.println(" " + group.getFqn() + " (" + group.getSidString() + ")");
//		//		}
//	}
//
//	@Test
//	public void test() {
//		final String securityPackage = "Negotiate";
//		IWindowsCredentialsHandle clientCredentials = null;
//		WindowsSecurityContextImpl clientContext = null;
//		try {
//			// client credentials handle
//			clientCredentials = WindowsCredentialsHandleImpl.getCurrent(securityPackage);
//			clientCredentials.initialize();
//			// initial client security context
//			clientContext = new WindowsSecurityContextImpl();
//			clientContext.setPrincipalName(WindowsAccountImpl.getCurrentUsername());
//			clientContext.setCredentialsHandle(clientCredentials);
//			clientContext.setSecurityPackage(securityPackage);
//			clientContext.initialize(null, null, WindowsAccountImpl.getCurrentUsername());
//			final String clientToken = Base64.getEncoder().encodeToString(clientContext.getToken());
//			System.out.println(clientToken);
//		} finally {
//			if (clientContext != null) {
//				clientContext.dispose();
//			}
//			if (clientCredentials != null) {
//				clientCredentials.dispose();
//			}
//		}
//	}
//
//}

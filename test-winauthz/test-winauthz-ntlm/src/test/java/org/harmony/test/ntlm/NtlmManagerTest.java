package org.harmony.test.ntlm;

import static org.junit.Assert.*;

import org.ntlmv2.liferay.NtlmManager;

import jcifs.util.Base64;

public class NtlmManagerTest {

	// GET Authorization: NTLM authorization1
	private static final String authorization1 = "TlRMTVNTUAABAAAAB7IIogUABQAtAAAABQAFACgAAAAKANc6AAAAD1dVWElJSFVJSlU=";

	// Response WWW-Authenticate: NTLM challengeMessage
	private static final String challengeMessage = "TlRMTVNTUAACAAAAEgASADAAAAAHsoiipZgbYEPJu10AAAAAAAAAACoAKgBCAAAAaAB1AGkAagB1AC4AYwBvAG0AAQAKAHcAdQB4AGkAbgACABIAaAB1AGkAagB1AC4AYwBvAG0AAAACACAA";

	// GET Authorization: NTLM authorization2
	private static final String authorization2 = "TlRMTVNTUAADAAAAGAAYAHYAAADOAM4AjgAAAAoACgBYAAAACgAKAGIAAAAKAAoAbAAAAAAAAABcAQAABYKIogoA1zoAAAAPnpejwCCB/o6yYdG5uPiYKUgAVQBJAEoAVQB3AHUAeABpAG4AVwBVAFgASQBJAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABNxC8ugjpI1UnVe7eDOx7sBAQAAAAAAAH9Muqpwi9MBplaEJ3UZ544AAAAAAQAKAHcAdQB4AGkAbgACABIAaAB1AGkAagB1AC4AYwBvAG0ACAAwADAAAAAAAAAAAQAAAAAgAAD/5YsU7CHHAoRAHv6wWVjHCxhy9QwMcsZYFX/LaWaSQQoAEAAAAAAAAAAAAAAAAAAAAAAACQAoAEgAVABUAFAALwBXAFUAWABJAEkALgBoAHUAaQBqAHUALgBjAG8AbQAAAAAAAAAAAAAA";

	// server side challenge
	private static final byte[] serverChallenge = new byte[] { -91, -104, 27, 96, 67, -55, -69, 93 };

	//

	public static void main(String[] args) throws Exception {

		String domain = "huiju.com";
		String domainController = "192.168.1.33";
		String dcHostName = "192.168.1.33";
		String account = "wuxin$@huiju.com";
		String password = "aA123456";

		NtlmManager ntlmManager = new NtlmManager(domain, domainController, dcHostName, account, password);

		// client GET with http header: Authorization: NTLM authorization1
		byte[] src = Base64.decode(authorization1);
		// ntlm challenge
		byte[] authorization1Response = ntlmManager.negotiate(src, serverChallenge);

		assertEquals(Base64.encode(authorization1Response), challengeMessage);

		src = Base64.decode(authorization2);

		ntlmManager.authenticate(src, serverChallenge);

	}

}

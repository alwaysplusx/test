package org.moon.test.rmi;

import java.rmi.Naming;

import net.sf.ehcache.Element;
import net.sf.ehcache.distribution.CachePeer;

public class RMIEhcacheTest {

	public static void main(String[] args) throws Exception {
		CachePeer peer = (CachePeer) Naming.lookup("//192.168.0.130:3060/myCache");
		peer.put(new Element("cachePeer", "cachePeer"));
		peer.getQuiet("");
	}

}

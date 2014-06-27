package org.moon.test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

public class EhcacheTest {

	public static void main(String[] args) {
		CacheManager cm = CacheManager.create();
		cm.addCache(new Cache(new CacheConfiguration("cxfClientService", 10000)));
		System.out.println(cm.getCacheNames()[0]);
	}

}

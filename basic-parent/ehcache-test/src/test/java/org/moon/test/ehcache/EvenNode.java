package org.moon.test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EvenNode {

	/*
	 * 偶数
	 */
	public static void main(String[] args) throws Exception {
		CacheManager manager = CacheManager.create();
		Thread.sleep(5000);
		Cache cache = manager.getCache("myCache1");
		int i = 0;
		while (true) {
			i = i + 2;
			cache.put(new Element(i, i));
			Thread.sleep(1000 * 2);
		}
	}
}

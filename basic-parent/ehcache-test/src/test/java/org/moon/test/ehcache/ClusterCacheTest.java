package org.moon.test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class ClusterCacheTest {

	public static void main(String[] args) throws Exception {
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("myCache1");
		int i = 1;
		while (true) {
			cache.put(new Element(i, i));
			i = i + 2;
			Thread.sleep(1000 * 5);
		}
	}
}

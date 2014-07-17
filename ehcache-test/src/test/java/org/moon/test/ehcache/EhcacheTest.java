package org.moon.test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {

	public static void main(String[] args) throws Exception {
		Cache cache = new Cache("testCache",1,false,false,1,1);
		CacheManager manager = CacheManager.create();
		manager.addCache(cache);
		cache.put(new Element("abc", "def"));
		Thread.sleep(1000 * 5);
		System.out.println(cache.get("abc"));
	}

}

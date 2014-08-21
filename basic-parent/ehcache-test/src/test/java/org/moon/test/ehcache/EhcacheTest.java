package org.moon.test.ehcache;


import java.util.Scanner;

import org.junit.Test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {

	public static void main(String[] args) throws Exception {
		CacheManager manager = CacheManager.newInstance("./src/main/resources/ehcache.xml");
		Cache cache = manager.getCache("myCache");
		while(true){
			int read = System.in.read();
			cache.put(new Element(read, read));
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testManual() throws Exception{
		CacheManager manager = CacheManager.newInstance("./src/test/resources/ehcache.xml");
		Cache cache = manager.getCache("myCache");
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("please input:");
			String next = scan.next();
			if (next.startsWith("put")) {
				String ele = next.substring(3).trim();
				cache.put(new Element(ele, ele));
				System.out.println("*****************************************************");
				System.out.println("put ele " + ele + " to cache");
				System.out.println("*****************************************************\n");
			} else if (next.startsWith("get")) {
				String ele = next.substring(3).trim();
				System.out.println("*****************************************************");
				System.out.println("get " + ele + " from cache:" + cache.get(ele));
				System.out.println("*****************************************************\n");
			}
		}
	}
	
	
}

package org.moon.test.ehcache;

import java.util.Scanner;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheAutomaticTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
		CacheManager manager = CacheManager.newInstance("./src/test/resources/ehcache-automatic.xml");
		Cache cache = manager.getCache("myCache");
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("******************************************************");
			System.out.println("*  ehcache use automatic please input                *");
			System.out.println("*   - use 'get [*]' get element from cache           *");
			System.out.println("*   - use 'put [*]' put element into cache           *");
			System.out.println("*   - use 'remove [*]' remove element from cache     *");
			System.out.println("******************************************************");
			System.out.print("please input:");
			String next = scan.nextLine();
			System.out.println();
			if (next.startsWith("put")) {
				String ele = next.substring(3).trim();
				cache.put(new Element(ele, ele));
				System.out.println("------------------------------------------------------");
				System.out.println(">> put ele " + ele + " to cache                     ");
				System.out.println("------------------------------------------------------\n");
			} else if (next.startsWith("get")) {
				String ele = next.substring(3).trim();
				System.out.println("------------------------------------------------------");
				System.out.println(">> get " + ele + " from cache:" + cache.get(ele));
				System.out.println("------------------------------------------------------\n");
			} else if (next.startsWith("remove")) {
				String ele = next.substring(6).trim();
				System.out.println("------------------------------------------------------");
				System.out.println(">> remove " + ele + " from cache " + cache.remove(ele));
				System.out.println("------------------------------------------------------\n");
			}			
		}

	}
}

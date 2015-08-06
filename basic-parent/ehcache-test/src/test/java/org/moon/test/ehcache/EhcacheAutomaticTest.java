package org.moon.test.ehcache;

import java.rmi.RemoteException;
import java.util.Scanner;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheAutomaticTest {

    private static CacheManager manager = null;
    private static Cache cache = null;

    static {
        System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
        try {
            manager = CacheManager.newInstance("./src/test/resources/ehcache-automatic.xml");
            cache = EhCacheUtils.getCacheInstance(manager, "myCache");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws RemoteException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("******************************************************");
            System.out.println("*  Ehcache Automatic Peer Discovery Test             *");
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
                System.out.println(">> put " + ele + " to cache                     ");
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

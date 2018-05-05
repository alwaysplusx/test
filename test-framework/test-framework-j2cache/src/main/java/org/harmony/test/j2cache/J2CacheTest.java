package org.harmony.test.j2cache;

import java.io.IOException;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;

/**
 * @author wuxii@foxmail.com
 */
public class J2CacheTest {

    public static void main(String[] args) throws IOException {
        J2CacheConfig cfg = J2CacheConfig.initFromConfig("/j2cache.properties");
        CacheChannel channel = J2CacheBuilder.init(cfg).getChannel();
        channel.set("defaults", "a", "a");
        CacheObject co = channel.get("defaults", "a");
        System.out.println(co.getValue());
        channel.close();
    }

}

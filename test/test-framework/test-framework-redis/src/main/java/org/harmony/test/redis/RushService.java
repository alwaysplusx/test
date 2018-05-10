package org.harmony.test.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * @author wuxii@foxmail.com
 */
public class RushService {

    private static final Logger log = LoggerFactory.getLogger(RushService.class);

    private JedisPool jedisPool;
    private String key = "rush_slice";
    private int slice;

    public RushService() {
        this.jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.198.134", 6379, 1000 * 5, "123456");
    }

    public void init(int slice) {
        this.slice = slice;
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, slice + "");
    }

    public void rush(String name) {
        Jedis jedis = jedisPool.getResource();
        jedis.watch(key);
        try {
            int val = Integer.parseInt(jedis.get(key));
            if (val > 0) {

                Transaction tx = jedis.multi();
                tx.incrBy(key, -1);
                List<Object> response = tx.exec();

                if (response == null || response.isEmpty()) {
                    log.info("抢购失败，double check 后发现库存不足");
                    return;
                }

                log.info("抢购成功，恭喜您第[{}]个抢购到商品。", (slice - (long) response.get(0)));

            } else {
                log.info("抢购失败，库存不足");
            }
        } finally {
            jedis.close();
        }

    }

    public void destroy() {
        jedisPool.close();
    }

}

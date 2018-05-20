package org.harmony.test.lock.dist;

import java.util.List;
import java.util.UUID;

import org.harmony.test.lock.LockException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * @author wuxii@foxmail.com
 */
public class RedisDistributedLock {

    private JedisPool jedisPool;
    private Jedis client;

    private String key;
    private String uuid;

    private long timeout = 30 * 1000;
    private int lockExpireSeconds = 60;

    public static RedisDistributedLock create(JedisPool pool, String lockName) {
        RedisDistributedLock lock = new RedisDistributedLock(lockName, pool);
        lock.init();
        return lock;
    }

    public RedisDistributedLock(String key, JedisPool jedisPool) {
        this.key = key;
        this.jedisPool = jedisPool;
    }

    public void init() {
        this.uuid = UUID.randomUUID().toString();
        this.client = jedisPool.getResource();
    }

    public void lock() {
        long start = System.currentTimeMillis();
        while (timeout == 0 || (System.currentTimeMillis() - start) < timeout) {
            if (client.setnx(key, uuid) == 1) {
                client.expire(key, lockExpireSeconds);
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        throw new LockException("get lock time out");
    }

    public void unlock() {
        long start = System.currentTimeMillis();
        while (timeout == 0 || (System.currentTimeMillis() - start) < timeout) {
            client.watch(key);
            String val = client.get(key);
            if (uuid.equals(val)) {
                Transaction trans = client.multi();
                trans.del(key);
                List<Object> results = trans.exec();
                if (results != null && !results.isEmpty()) {
                    client.close();
                    return;
                }
            }
            client.unwatch();
            break;
        }
        throw new LockException("unable release lock " + key);
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

}

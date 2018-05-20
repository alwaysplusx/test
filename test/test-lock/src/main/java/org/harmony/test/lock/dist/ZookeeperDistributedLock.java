package org.harmony.test.lock.dist;

import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.harmony.test.lock.LockException;

/**
 * @author wuxii@foxmail.com
 */
public class ZookeeperDistributedLock {

    private ZooKeeper zookeeper;
    private String path;
    private String basePath = "lock";
    private String lockPath;

    public ZookeeperDistributedLock(String path, ZooKeeper zooKeeper) {
        this.zookeeper = zooKeeper;
        this.path = "/" + basePath + "/" + path;
    }

    public void init() {
        try {
            List<String> nodes = zookeeper.getChildren("/", false);
            if (!nodes.contains(basePath)) {
                zookeeper.create(basePath, basePath.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            throw new LockException(e);
        }
    }

    public void lock() {
        try {
            this.lockPath = zookeeper.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            final Object lock = new Object();
            synchronized (lock) {
                while (true) {

                    List<String> nodes = zookeeper.getChildren("/" + basePath, e -> {
                        // listener for zookeeper process
                        synchronized (lock) {
                            // TODO 优化空间, 只notify当前节点的下一个节点
                            lock.notifyAll();
                        }
                    });

                    Collections.sort(nodes);

                    if (lockPath.endsWith(nodes.get(0))) {
                        // 节点排序, 直到是自己当前处理的节点
                        return;
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (Exception e) {
            throw new LockException(e);
        }

    }

    public void unlock() {
        try {
            if (lockPath != null) {
                zookeeper.delete(lockPath, -1);
                lockPath = null;
            }
        } catch (Exception e) {
            throw new LockException(e);
        }
    }

    public static ZookeeperDistributedLock create(ZooKeeper zookeeper, String path) {
        ZookeeperDistributedLock lock = new ZookeeperDistributedLock(path, zookeeper);
        lock.init();
        return lock;
    }

}

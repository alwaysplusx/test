package org.harmony.test.ehcache;

import java.rmi.RemoteException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CachePeer;

public class EhCacheUtils {

    
    /**
     * 根据缓存名称获取缓存,获取后会初始化缓存数据即同步其他节点上的数据到本地当前缓存中
     * @param manager    {@link CacheManager}
     * @param cacheName 缓存名称
     * @return
     * @throws RemoteException {@link CachePeer#getElements(List)}
     */
    @SuppressWarnings("unchecked")
    public static final Cache getCacheInstance(CacheManager manager, String cacheName) throws RemoteException {
        Cache cache = manager.getCache(cacheName);
        CacheManagerPeerProvider provider = manager.getCacheManagerPeerProvider("RMI");
        List<CachePeer> peers = provider.listRemoteCachePeers(cache);
        for (CachePeer peer : peers) {
            peer.getUrl();
            List<Element> elements = peer.getElements(peer.getKeys());
            for (Element ele : elements) {
                cache.putQuiet(ele);
            }
        }
        return cache;
    }

}

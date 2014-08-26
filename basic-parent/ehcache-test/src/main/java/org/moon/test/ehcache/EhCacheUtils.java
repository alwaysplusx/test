package org.moon.test.ehcache;

import java.rmi.RemoteException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CachePeer;

public class EhCacheUtils {

	
	/**
	 * ���ݻ������ƻ�ȡ����,��ȡ����ʼ���������ݼ�ͬ�������ڵ��ϵ����ݵ����ص�ǰ������
	 * @param manager	{@link CacheManager}
	 * @param cacheName ��������
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

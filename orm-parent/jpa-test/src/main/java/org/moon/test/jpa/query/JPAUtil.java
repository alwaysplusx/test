package org.moon.test.jpa.query;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JPAUtil {

	public static Query buildQuery(EntityManager em, BondManager bm) {
		String prepareXQL = bm.toXQL();
		Query query = em.createQuery(prepareXQL);
		Map<String, Object> KVMap = bm.toMap();
		Iterator<String> it = KVMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (prepareXQL.indexOf(key) != -1) {
				query.setParameter(key, KVMap.get(key));
			}
		}
		return query;
	}
	
}

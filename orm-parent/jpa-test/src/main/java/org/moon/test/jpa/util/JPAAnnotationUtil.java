package org.moon.test.jpa.util;

import javax.persistence.Entity;
import javax.persistence.Table;

public class JPAAnnotationUtil {

	public static String getEntityName(Class<?> clazz) {
		Entity ann = clazz.getAnnotation(Entity.class);
		if (ann != null && !"".equals(ann.name())) {
			return ann.name();
		}
		return clazz.getSimpleName();
	}

	public static String getTableName(Class<?> clazz) {
		Table ann = clazz.getAnnotation(Table.class);
		if (ann != null && !"".equals(ann.name())) {
			return ann.name();
		}
		return getEntityName(clazz);
	}

}

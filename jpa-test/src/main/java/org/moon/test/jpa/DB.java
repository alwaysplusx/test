package org.moon.test.jpa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DB {

	private Logger log = Logger.getLogger(DB.class);
	private static EntityManagerFactory entityManagerFactory;
	private static List<EntityManager> entityManagerList = new ArrayList<EntityManager>();
	private String persistenceUnitName;
	private int maxSize = 50;
	private int current = 0;

	public EntityManager createEntityManager() {
		try {
			return createEntityManager(persistenceUnitName);
		} catch (Exception e) {
			log.error("create EntityManager fail");
			return null;
		}
	}

	public EntityManager createEntityManager(String persistenceUnitName) throws Exception {
		if (entityManagerFactory == null) {
			synchronized (entityManagerFactory) {
				if ((this.persistenceUnitName = persistenceUnitName) == null) {
					this.persistenceUnitName = getDefaultPersistenceUnitName();
				}
				entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceUnitName);
			}
		}
		return findEntityManagerInList();
	}

	private EntityManager findEntityManagerInList() {
		EntityManager em = entityManagerFactory.createEntityManager();
		if (entityManagerList.size() <= maxSize) {
			entityManagerList.add(em);
			return em;
		}
		return entityManagerList.get(current = (++current % maxSize));
	}

	private String getDefaultPersistenceUnitName() throws Exception {
		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(new File(classPath + "META-INF/persistence.xml"));
			Node node = document.getDocumentElement().getElementsByTagName("persistence-unit").item(0);
			NamedNodeMap attributes = node.getAttributes();
			for (int i = attributes.getLength() - 1; i >= 0; i--) {
				Node attr = attributes.item(i);
				if ("name".equals(attr.getNodeName())) {
					return attr.getNodeValue();
				}
			}
			throw new Exception("default persistence unit name not find!");
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		int current = 0;
		int maxSize = 50;
		for (int i = 0; i < 1000; i++) {
			System.out.println(current = (++current % maxSize));
			// System.out.println(current++);
			// System.out.println(current = (current++ % maxSize));
			// System.err.println(current);
		}
	}

}

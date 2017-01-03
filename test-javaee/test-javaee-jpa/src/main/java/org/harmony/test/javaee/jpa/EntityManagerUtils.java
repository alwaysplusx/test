package org.harmony.test.javaee.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class EntityManagerUtils {

    static final Logger log = LoggerFactory.getLogger(EntityManagerUtils.class);

    public static final Set<String> persistenceUnitNames = new LinkedHashSet<>();

    static {
        InputStream is = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/persistence.xml");
            Document document = builder.parse(is);
            XPath xPath = XPathFactory.newInstance().newXPath();
            Object obj = xPath.evaluate("persistence/persistence-unit/@name", document, XPathConstants.NODESET);
            if (obj instanceof NodeList) {
                NodeList nodeList = ((NodeList) obj);
                if (nodeList.getLength() > 0) {
                    for (int i = 0, max = nodeList.getLength(); i < max; i++) {
                        persistenceUnitNames.add(nodeList.item(i).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static EntityManager getEntityManager(JPAType type) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("harmony-" + type.name);
        return proxyEntityManager(emf.createEntityManager());
    }

    private static EntityManager proxyEntityManager(EntityManager entityManager) {
        return (EntityManager) Proxy.newProxyInstance(//
                Thread.currentThread().getContextClassLoader(), //
                new Class[] { EntityManager.class }, //
                new EntityManagerProxy(entityManager)//
        );
    }

    public static void main(String[] args) {
        System.out.println(persistenceUnitNames);
    }

    public static class EntityManagerProxy implements InvocationHandler {

        private EntityManager target;
        private String include = "persist, merge, remove, flush, refresh";

        public EntityManagerProxy(EntityManager em) {
            this.target = em;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            String name = method.getName();
            if (include.contains(name)) {
                EntityTransaction tx = target.getTransaction();
                tx.begin();
                try {
                    result = method.invoke(target, args);
                    tx.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    tx.rollback();
                }
            } else {
                result = method.invoke(target, args);
            }
            return result;
        }

    }

    public static enum JPAType {

        Eclipselink("eclipselink"), Hibernate("hibernate");

        private String name;

        private JPAType(String name) {
            this.name = name;
        }
    }
}

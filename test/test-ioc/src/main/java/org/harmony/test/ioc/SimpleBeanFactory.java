package org.harmony.test.ioc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.harmony.test.ioc.core.BeanDefinition;
import org.harmony.test.ioc.core.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SimpleBeanFactory implements BeanFactory {

    private String configResource;
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private Map<String, Object> beanObjects = new ConcurrentHashMap<String, Object>();
    private List<String> beanDefinitionNames = new ArrayList<String>();

    public SimpleBeanFactory(String configResource) throws Exception {
        this.configResource = configResource;
        Document doc = loadDocument(new FileInputStream(configResource));
        Element root = doc.getDocumentElement();
        parseBeanDefinition(root);
        initationBean();
    }

    private void parseBeanDefinition(Element root) {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            if (item instanceof Element) {
                Element ele = (Element) item;
                String nameAttr = ele.getAttribute("name");
                String classAttr = ele.getAttribute("class");
                SimpleBeanDefinition sbd = new SimpleBeanDefinition(nameAttr, classAttr);
                beanDefinitionMap.put(nameAttr, sbd);
                beanDefinitionNames.add(nameAttr);
            }
        }
    }

    private void initationBean() {
        for (String name : beanDefinitionNames) {
            getBean(name);
        }
    }

    private BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    private Document loadDocument(InputStream stream) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(stream);
    }

    @Override
    public Object getBean(String name) {
        BeanDefinition bd = getBeanDefinition(name);
        if (bd.isSingleton() && beanObjects.get(name) != null) {
            return beanObjects.get(name);
        } else {
            try {
                return getObjectForBeanInstance(bd);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private Object getObjectForBeanInstance(BeanDefinition db) throws Exception {
        String beanClassName = db.getBeanClassName();
        return Class.forName(beanClassName).newInstance();
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String name, Class<T> type) {
        return (T) getBean(name);
    }

    @Override
    public boolean containsBean(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    public String getConfigResource() {
        return configResource;
    }

}

class SimpleBeanDefinition implements BeanDefinition {

    private String beanName;
    private String beanClassName;

    public SimpleBeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    @Override
    public String getFactoryBeanName() {
        return null;
    }

    @Override
    public void setFactoryBeanName(String factoryBeanName) {
    }

    @Override
    public String getFactoryMethodName() {
        return null;
    }

    @Override
    public void setFactoryMethodName(String factoryMethodName) {
    }

    @Override
    public String getScope() {
        return null;
    }

    @Override
    public void setScope(String scope) {
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

}

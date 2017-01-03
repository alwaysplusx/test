package org.moon.test.ioc.core.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.moon.test.ioc.core.BeanDefinition;
import org.moon.test.ioc.core.BeanDefinitionRegistry;
import org.moon.test.ioc.core.BeanLoadException;
import org.moon.test.ioc.core.Resource;
import org.moon.test.ioc.core.support.AbstractBeanDefinitionReader;
import org.moon.test.ioc.core.support.DefaultBeanDefinition;
import org.moon.test.ioc.core.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String DEFUALT_NAMESPACE = "http://www.moon.com/scheam/ioc";

    public static final String NESTED_BEANS_ELEMENT = "beans";

    public static final String BEAN_ELEMENT = "bean";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String FACTORY_NAME_ATTRIBUTE = "factoryName";

    public static final String FACTORY_METHOD_ATTRIBUTE = "factoryMethod";

    protected BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanLoadException {
        try {
            return loadBeanDefinitions(new InputSource(resource.getInputStream()));
        } catch (IOException e) {
            throw new BeanLoadException();
        }
    }

    public int loadBeanDefinitions(InputSource inputSource) {
        try {
            Document doc = loadDocument(inputSource);
            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    parseDefaultElement((Element) item);
                }
            }
        } catch (ParserConfigurationException e) {
            throw new BeanLoadException();
        } catch (IOException e) {
            throw new BeanLoadException();
        } catch (SAXException e) {
            throw new BeanLoadException();
        }
        return 0;
    }

    protected void parseDefaultElement(Element ele) {
        if (ele.getNodeName().equals(BEAN_ELEMENT)) {
            processBeanDefinition(ele);
        }
    }

    protected void processBeanDefinition(Element ele) {
        BeanDefinition beanDefintion = createBeanDefinition(ele);
        System.out.println(beanDefintion);
    }

    protected BeanDefinition createBeanDefinition(Element ele) {
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);
        String className = ele.getAttribute(CLASS_ATTRIBUTE);
        if (StringUtils.isBlank(nameAttr)) {
            nameAttr = className;
        }
        DefaultBeanDefinition beanDefinition = new DefaultBeanDefinition(nameAttr, className);
        return beanDefinition;
    }

    protected Document loadDocument(InputSource inputSource) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = createDocumentBuildFactory();
        DocumentBuilder builder = createDocumentBuilder(factory);
        return builder.parse(inputSource);
    }

    protected DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory) throws ParserConfigurationException {
        return factory.newDocumentBuilder();
    }

    protected DocumentBuilderFactory createDocumentBuildFactory() {
        return DocumentBuilderFactory.newInstance();
    }

}

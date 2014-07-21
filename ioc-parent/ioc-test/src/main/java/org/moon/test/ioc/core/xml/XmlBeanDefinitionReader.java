package org.moon.test.ioc.core.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.moon.test.ioc.core.BeanDefinition;
import org.moon.test.ioc.core.BeanLoadException;
import org.moon.test.ioc.core.Resource;
import org.moon.test.ioc.core.support.AbstractBeanDefinitionReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public static final String DEFUALT_NAMESPACE = "http://www.moon.com/scheam/ioc";

	public static final String NESTED_BEANS_ELEMENT = "beans";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String ID_ATTRIBUTE = "id";

	public static final String CLASS_ATTRIBUTE = "class";

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
					processBeanDefinition((Element) item);
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

	private void processBeanDefinition(Element ele) {
		BeanDefinition beanDefintion = createBeanDefinition(ele);
		System.out.println(beanDefintion);
	}

	private BeanDefinition createBeanDefinition(Element ele) {
		return null;
	}

	private Document loadDocument(InputSource inputSource) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = createDocumentBuildFactory();
		DocumentBuilder builder = createDocumentBuilder(factory);
		return builder.parse(inputSource);
	}

	private DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory) throws ParserConfigurationException {
		return factory.newDocumentBuilder();
	}

	private DocumentBuilderFactory createDocumentBuildFactory() {
		return DocumentBuilderFactory.newInstance();
	}

	public static void main(String[] args) {
		new XmlBeanDefinitionReader().loadBeanDefinitions("beans.xml");
	}

}

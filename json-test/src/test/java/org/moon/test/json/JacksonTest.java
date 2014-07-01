package org.moon.test.json;

import static org.junit.Assert.assertNotEquals;

import java.io.FileOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class JacksonTest {

	private JsonFactory jsonFactory;
	private JsonGenerator jsonGenerator;

	@Before
	public void setUp() throws Exception {
		jsonFactory = new JsonFactory();
		jsonGenerator = jsonFactory.createGenerator(new FileOutputStream("target/jackson.json"));
	}

	@Test
	public void testWriteJson() throws Exception {
		assertNotEquals(null, jsonFactory);
		assertNotEquals(null, jsonGenerator);
		jsonGenerator.writeStartObject();
		jsonGenerator.writeObjectField("name", "david");
		jsonGenerator.writeEndObject();
		jsonGenerator.close();
		System.out.println(jsonGenerator);
	}

	@After
	public void tearDown() throws Exception {
		jsonGenerator.close();
	}

}

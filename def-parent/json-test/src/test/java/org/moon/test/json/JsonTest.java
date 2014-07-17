package org.moon.test.json;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonTest {

	private JSONObject jsonObject;

	@Before
	public void setUp() throws Exception {
		jsonObject = new JSONObject();
	}

	@Test
	public void test() {
		jsonObject.put("name", "david");
		assertEquals("{\"name\":\"david\"}", jsonObject.toString());
	}

	@After
	public void tearDown() throws Exception {
	}

}

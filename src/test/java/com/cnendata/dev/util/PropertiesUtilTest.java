package com.cnendata.dev.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

public class PropertiesUtilTest {

	@Test
	public void testGetProperties() {
		try {
			Properties prop = PropertiesUtil
					.getProperties("/collector.properties");
			assertTrue("com.cnendata.dev.collector.website.amazon.AmazonCollector"
					.equals(String.valueOf(prop.get("amazon"))));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}

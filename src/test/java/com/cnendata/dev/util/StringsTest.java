package com.cnendata.dev.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringsTest {

	@Test
	public void testGet() {
		String eng = Strings.get("我i 是am中国American人", "[a-zA-Z]");
		assertTrue("iamAmerican".equals(eng));
	}

	@Test
	public void testGetCnCharacter() {
		String cn = Strings.getCnCharacter("我i 是am中国American人.");
		assertTrue("我是中国人".equals(cn));
	}

	@Test
	public void testGetEnCharacter() {
		String eng = Strings.getEnCharacter("我i 是am中国American人");
		assertTrue("iamAmerican".equals(eng));
	}

}

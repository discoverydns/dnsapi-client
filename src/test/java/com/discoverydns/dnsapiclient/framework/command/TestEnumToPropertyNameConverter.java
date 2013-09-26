package com.discoverydns.dnsapiclient.framework.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestEnumToPropertyNameConverter {

	enum Test1 {
		value1, value2
	}

	enum Test2 {
		value3
	}

	@Test
	public void testConvert() {
		assertEquals("Test1.value1",
				EnumToPropertyNameConverter.convert(Test1.value1));
		final Test1 test1 = Test1.value2;
		assertEquals("Test1.value2", EnumToPropertyNameConverter.convert(test1));
		final Enum<?> enumGeneric = Test2.value3;
		assertEquals("Test2.value3",
				EnumToPropertyNameConverter.convert(enumGeneric));
	}

}

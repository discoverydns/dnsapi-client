package com.discoverydns.dnsapiclient.framework.internationalisation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestInternationalisedMessages {

	private final InternationalisedMessages im = new InternationalisedMessages(
			"testLocale");

	@Test
	public void testLocale() {

		assertEquals("english", im.getMessage("test.language", "en"));
		assertEquals("french", im.getMessage("test.language", "fr"));
		assertEquals("english", im.getMessage("test.language", "ar"));
	}

	@Test
	public void testObjectFormat() {
		final String object1 = "one";
		final int object2 = 2;

		assertEquals("This is param 0 'one' and this is param 1 2",
				im.getMessage("test.message", "en", object1, object2));

		final Object[] objectArray = { object1, object2 };

		assertEquals("This is param 0 'one' and this is param 1 2",
				im.getMessage("test.message", "en", objectArray));

	}

}

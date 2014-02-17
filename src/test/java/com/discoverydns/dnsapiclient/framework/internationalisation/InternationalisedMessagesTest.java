package com.discoverydns.dnsapiclient.framework.internationalisation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Locale;

public class InternationalisedMessagesTest {

	private final InternationalisedMessages im = new InternationalisedMessages(
			"testLocale");

	@Test
	public void testLocale() {

		assertEquals("english", im.getMessage("test.language", Locale.ENGLISH));
		assertEquals("french", im.getMessage("test.language", Locale.FRENCH));
		assertEquals("english", im.getMessage("test.language", Locale.UK));
	}

	@Test
	public void testObjectFormat() {
		final String object1 = "one";
		final int object2 = 2;

		assertEquals("This is param 0 'one' and this is param 1 2",
				im.getMessage("test.message", Locale.ENGLISH, object1, object2));

		final Object[] objectArray = { object1, object2 };

		assertEquals("This is param 0 'one' and this is param 1 2",
				im.getMessage("test.message", Locale.ENGLISH, objectArray));

	}

}

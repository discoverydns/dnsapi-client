package com.discoverydns.dnsapiclient.framework.internationalisation;

import java.util.Locale;

public class InternationalisedMessage {

	private static InternationalisedMessages internationalisedMessages = new InternationalisedMessages(
			"dnsapiLocale");

	private final String propertyName;
	private final Object[] objects;

	public InternationalisedMessage(final String propertyName,
			final Object... objects) {
		this.propertyName = propertyName;
		this.objects = objects;
	}

	@Override
	public String toString() {
		return getString();
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getString() {
		return internationalisedMessages.getMessage(propertyName,
				Locale.ENGLISH.toLanguageTag(), objects);
	}

	public String getString(final String languageTag) {
		return internationalisedMessages.getMessage(propertyName, languageTag,
				objects);
	}

}

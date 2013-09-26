package com.discoverydns.dnsapiclient.framework.internationalisation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class InternationalisedMessages {

	private final String resourceName;

	public InternationalisedMessages(final String resourceName) {
		this.resourceName = resourceName;
	}

	public String getMessage(final String propertyName,
			final String languageTag, final Object... objects) {
		final Locale locale = Locale.forLanguageTag(languageTag);
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
				resourceName, locale);
		return MessageFormat.format(resourceBundle.getString(propertyName),
				objects);
	}

}

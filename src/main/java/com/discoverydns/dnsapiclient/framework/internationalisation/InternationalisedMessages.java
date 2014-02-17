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
			final Locale locale, final Object... objects) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
				resourceName, locale);
		return MessageFormat.format(resourceBundle.getString(propertyName),
				objects);
	}
}

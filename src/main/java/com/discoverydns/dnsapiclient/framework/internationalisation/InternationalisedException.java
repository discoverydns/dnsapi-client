package com.discoverydns.dnsapiclient.framework.internationalisation;

import java.util.Locale;

public class InternationalisedException extends RuntimeException {

	private static final long serialVersionUID = -7383158504886816380L;

	private static InternationalisedMessages internationalisedMessages = new InternationalisedMessages(
			"dnsapiLocale");

	private final String propertyName;
	private final Object[] objects;

	public InternationalisedException(final String propertyName,
			final Throwable cause, final Object... objects) {
		super(internationalisedMessages.getMessage(propertyName,
				Locale.ENGLISH, objects), cause);
		this.propertyName = propertyName;
		this.objects = objects;
	}

	public InternationalisedException(final String propertyName,
			final Object... objects) {
		super(internationalisedMessages.getMessage(propertyName,
				Locale.ENGLISH, objects));
		this.propertyName = propertyName;
		this.objects = objects;
	}

	public String getProperty() {
		return propertyName;
	}

	public Object[] getObjects() {
		return objects;
	}
}

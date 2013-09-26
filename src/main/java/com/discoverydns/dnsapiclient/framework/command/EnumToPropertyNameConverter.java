package com.discoverydns.dnsapiclient.framework.command;

public final class EnumToPropertyNameConverter {
	private static final int ESTIMATED_PROPERTY_STRING_SIZE = 255;

	public static String convert(final Enum<?> e) {
		final StringBuilder propertyNameBuilder = new StringBuilder(
				ESTIMATED_PROPERTY_STRING_SIZE);

		propertyNameBuilder.append(e.getClass().getSimpleName());
		propertyNameBuilder.append(".");
		propertyNameBuilder.append(e.toString());

		return propertyNameBuilder.toString();
	}

}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class AbstractRecordSerializer<T extends Record> extends
		StdSerializer<T> {

	public AbstractRecordSerializer(final Class<T> t) {
		super(t);
	}

	@Override
	public void serialize(final T value, final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) {
		try {
			jsonGenerator.writeStartObject();

			// Common fields
			jsonGenerator.writeStringField("name", value.getName().toString());
			jsonGenerator.writeStringField("class", "IN");
			jsonGenerator.writeStringField("ttl", formatNumber(value.getTTL()));
			jsonGenerator.writeStringField("type",
					formatNumber(value.getType()));

			// RDATA fields
			serializeRDataFields(value, jsonGenerator, serializerProvider);

			jsonGenerator.writeEndObject();
		} catch (final IOException e) {
			throw new DNSAPIClientJsonGenerationException(
					DNSAPIClientJsonGenerationExceptionCode.unexpectedGenerationError,
					e, Type.string(value.getType()),
					value.getName() != null ? value.getName().toString()
							: "unknown", e.getMessage());
		}
	}

	protected abstract void serializeRDataFields(T value,
			JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonGenerationException;

	public String formatNumber(final Number number) {
		return String.valueOf(number);
	}

	public String escapeCharacterString(final String characterString) {
		final boolean containsWhiteSpaces = characterString.indexOf(' ') >= 0;
		return (containsWhiteSpaces ? "\\\"" : "")
				+ characterString.replaceAll("\\\"", "\\\\\\\\\"")
				+ (containsWhiteSpaces ? "\\\"" : "");
	}

	public String escapeSpecialCharacters(final String string) {
		return string.replaceAll("\n", "\\\\n").replaceAll("\t", "\\\\t");
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.SPFRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SPFRecordSerializer extends AbstractRecordSerializer<SPFRecord> {

	public SPFRecordSerializer() {
		super(SPFRecord.class);
	}

	@Override
	protected void serializeRDataFields(final SPFRecord txtRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		String strings = "";
		for (final Object characterString : txtRecord.getStrings()) {
			if (strings.length() > 0) {
				strings += " ";
			}
			strings += escapeCharacterString((String) characterString);
		}
		jsonGenerator.writeStringField("strings", strings);
	}
}

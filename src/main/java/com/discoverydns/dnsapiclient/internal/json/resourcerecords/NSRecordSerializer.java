package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.NSRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NSRecordSerializer extends AbstractRecordSerializer<NSRecord> {

	public NSRecordSerializer() {
		super(NSRecord.class);
	}

	@Override
	protected void serializeRDataFields(final NSRecord nsRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		if (nsRecord.getTarget() != null) {
			jsonGenerator.writeStringField("target", nsRecord.getTarget()
					.toString());
		}
	}
}

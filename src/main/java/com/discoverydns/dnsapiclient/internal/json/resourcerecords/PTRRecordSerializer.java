package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.PTRRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PTRRecordSerializer extends AbstractRecordSerializer<PTRRecord> {

	public PTRRecordSerializer() {
		super(PTRRecord.class);
	}

	@Override
	protected void serializeRDataFields(final PTRRecord ptrRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		if (ptrRecord.getTarget() != null) {
			jsonGenerator.writeStringField("target", ptrRecord.getTarget()
					.toString());
		}
	}
}

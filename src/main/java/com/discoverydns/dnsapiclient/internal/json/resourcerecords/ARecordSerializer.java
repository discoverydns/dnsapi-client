package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.ARecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ARecordSerializer extends AbstractRecordSerializer<ARecord> {

	public ARecordSerializer() {
		super(ARecord.class);
	}

	@Override
	protected void serializeRDataFields(final ARecord aRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		if (aRecord.getAddress() != null) {
			jsonGenerator.writeStringField("address", aRecord.getAddress()
					.getHostAddress());
		}
	}
}

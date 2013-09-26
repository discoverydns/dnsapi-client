package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.DSRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DSRecordSerializer extends AbstractRecordSerializer<DSRecord> {

	public DSRecordSerializer() {
		super(DSRecord.class);
	}

	@Override
	protected void serializeRDataFields(final DSRecord dsRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		jsonGenerator.writeStringField("footprint",
				formatNumber(dsRecord.getFootprint()));
		jsonGenerator.writeStringField("algorithm",
				formatNumber(dsRecord.getAlgorithm()));
		jsonGenerator.writeStringField("digestId",
				formatNumber(dsRecord.getDigestID()));
		jsonGenerator.writeStringField("digest",
				escapeSpecialCharacters(dsRecord.getTextualDigest()));
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.LOCRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LOCRecordSerializer extends AbstractRecordSerializer<LOCRecord> {

	public LOCRecordSerializer() {
		super(LOCRecord.class);
	}

	@Override
	protected void serializeRDataFields(final LOCRecord locRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		jsonGenerator.writeStringField("size", locRecord.getTextualSize());
		jsonGenerator.writeStringField("hPrecision",
				locRecord.getTextualHPrecision());
		jsonGenerator.writeStringField("vPrecision",
				locRecord.getTextualVPrecision());
		jsonGenerator.writeStringField("latitude",
				locRecord.getTextualLatitude());
		jsonGenerator.writeStringField("longitude",
				locRecord.getTextualLongitude());
		jsonGenerator.writeStringField("altitude",
				locRecord.getTextualAltitude());
	}
}

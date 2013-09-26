package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.NAPTRRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NAPTRRecordSerializer extends
		AbstractRecordSerializer<NAPTRRecord> {

	public NAPTRRecordSerializer() {
		super(NAPTRRecord.class);
	}

	@Override
	protected void serializeRDataFields(final NAPTRRecord naptrRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		jsonGenerator.writeStringField("order",
				formatNumber(naptrRecord.getOrder()));
		jsonGenerator.writeStringField("preference",
				formatNumber(naptrRecord.getPreference()));
		jsonGenerator.writeStringField("flags", naptrRecord.getFlags());
		jsonGenerator.writeStringField("service", naptrRecord.getService());
		jsonGenerator.writeStringField("regexp", naptrRecord.getRegexp());
		if (naptrRecord.getReplacement() != null) {
			jsonGenerator.writeStringField("replacement", naptrRecord
					.getReplacement().toString());
		}
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.MXRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MXRecordSerializer extends AbstractRecordSerializer<MXRecord> {

	public MXRecordSerializer() {
		super(MXRecord.class);
	}

	@Override
	protected void serializeRDataFields(final MXRecord mxRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		jsonGenerator.writeStringField("priority",
				formatNumber(mxRecord.getPriority()));
		if (mxRecord.getTarget() != null) {
			jsonGenerator.writeStringField("target", mxRecord.getTarget()
					.toString());
		}
	}
}

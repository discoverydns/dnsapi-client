package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.NULLRecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class NULLRecordSerializer extends StdSerializer<NULLRecord> {
	public NULLRecordSerializer() {
		super(NULLRecord.class);
	}

	@Override
	public void serialize(final NULLRecord value, final JsonGenerator jgen,
			final SerializerProvider provider) throws IOException,
			JsonGenerationException {
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.TLSARecord;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TLSARecordSerializer extends AbstractRecordSerializer<TLSARecord> {

	public TLSARecordSerializer() {
		super(TLSARecord.class);
	}

	@Override
	protected void serializeRDataFields(final TLSARecord tlsaRecord,
			final JsonGenerator jsonGenerator,
			final SerializerProvider serializerProvider) throws IOException,
			JsonGenerationException {
		jsonGenerator.writeStringField("certificateUsage",
				formatNumber(tlsaRecord.getCertificateUsage()));
		jsonGenerator.writeStringField("selector",
				formatNumber(tlsaRecord.getSelector()));
		jsonGenerator.writeStringField("matchingType",
				formatNumber(tlsaRecord.getMatchingType()));
		jsonGenerator.writeStringField("certificateAssociationData",
				escapeSpecialCharacters(tlsaRecord
						.getTextualCertificateAssociationData()));
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.CERTRecord;
import org.xbill.DNS.Name;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CERTRecordDeserializer extends
		AbstractRecordDeserializer<CERTRecord> {
	private static final long serialVersionUID = 1976274354370729318L;

	public CERTRecordDeserializer() {
		super(CERTRecord.class);
	}

	@Override
	protected CERTRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		try {
			return new CERTRecord(name, dclass, ttl, getNodeIntegerValue(
					recordNode, "certType"), getNodeIntegerValue(recordNode,
					"keyTag"), getNodeIntegerValue(recordNode, "algorithm"),
					getNodeStringValue(recordNode, "cert")
							.replaceAll("\\n", ""));
		} catch (final IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}
	}

	@Override
	protected String getTextualRecordType() {
		return "CERT";
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.LOCRecord;
import org.xbill.DNS.Name;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LOCRecordDeserializer extends
		AbstractRecordDeserializer<LOCRecord> {
	private static final long serialVersionUID = -6394722407564343923L;

	public LOCRecordDeserializer() {
		super(LOCRecord.class);
	}

	@Override
	protected LOCRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		try {
			return new LOCRecord(name, dclass, ttl, getNodeStringValue(
					recordNode, "latitude"), getNodeStringValue(recordNode,
					"longitude"), getNodeStringValue(recordNode, "altitude"),
					getNodeStringValue(recordNode, "size"), getNodeStringValue(
							recordNode, "hPrecision"), getNodeStringValue(
							recordNode, "vPrecision"));
		} catch (final IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}
	}

	@Override
	protected String getTextualRecordType() {
		return "LOC";
	}
}

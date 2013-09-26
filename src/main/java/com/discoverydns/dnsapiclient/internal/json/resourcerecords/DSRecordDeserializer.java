package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.DSRecord;
import org.xbill.DNS.Name;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DSRecordDeserializer extends AbstractRecordDeserializer<DSRecord> {
	private static final long serialVersionUID = 9205297205421105521L;

	public DSRecordDeserializer() {
		super(DSRecord.class);
	}

	@Override
	protected DSRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		try {
			return new DSRecord(name, dclass, ttl, getNodeIntegerValue(
					recordNode, "footprint"), getNodeIntegerValue(recordNode,
					"algorithm"), getNodeIntegerValue(recordNode, "digestId"),
					getNodeStringValue(recordNode, "digest").replaceAll("\\n",
							""));
		} catch (final IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}
	}

	@Override
	protected String getTextualRecordType() {
		return "DS";
	}
}

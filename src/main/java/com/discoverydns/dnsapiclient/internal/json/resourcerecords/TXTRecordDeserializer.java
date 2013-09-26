package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.Name;
import org.xbill.DNS.TXTRecord;
import org.xbill.DNS.Type;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TXTRecordDeserializer extends
		AbstractRecordDeserializer<TXTRecord> {
	private static final String STRINGS_FIELD_NAME = "strings";
	private static final long serialVersionUID = -6018035758494572491L;

	public TXTRecordDeserializer() {
		super(TXTRecord.class);
	}

	@Override
	protected TXTRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		try {
			return (TXTRecord) TXTRecord.fromString(name, Type.TXT, dclass,
					ttl, getNodeStringValue(recordNode, STRINGS_FIELD_NAME),
					Name.root);
		} catch (final IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}
	}

	@Override
	protected String getTextualRecordType() {
		return "TXT";
	}
}

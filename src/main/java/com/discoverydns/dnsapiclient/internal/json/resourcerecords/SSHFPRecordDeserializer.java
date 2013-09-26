package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.Name;
import org.xbill.DNS.SSHFPRecord;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SSHFPRecordDeserializer extends
		AbstractRecordDeserializer<SSHFPRecord> {
	private static final long serialVersionUID = -3992024906736725094L;

	public SSHFPRecordDeserializer() {
		super(SSHFPRecord.class);
	}

	@Override
	protected SSHFPRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		try {
			return new SSHFPRecord(name, dclass, ttl, getNodeIntegerValue(
					recordNode, "algorithm"), getNodeIntegerValue(recordNode,
					"digestType"),
					getNodeStringValue(recordNode, "fingerprint").replaceAll(
							"\\n", ""));
		} catch (final IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}
	}

	@Override
	protected String getTextualRecordType() {
		return "SSHFP";
	}
}

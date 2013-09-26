package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class SRVRecordDeserializer extends
		AbstractRecordDeserializer<SRVRecord> {
	private static final long serialVersionUID = -6051918053495550554L;

	public SRVRecordDeserializer() {
		super(SRVRecord.class);
	}

	@Override
	protected SRVRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		return new SRVRecord(name, dclass, ttl, getNodeIntegerValue(recordNode,
				"priority"), getNodeIntegerValue(recordNode, "weight"),
				getNodeIntegerValue(recordNode, "port"), getNodeNameValue(
						recordNode, "target"));
	}

	@Override
	protected String getTextualRecordType() {
		return "SRV";
	}
}

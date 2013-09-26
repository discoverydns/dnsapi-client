package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import org.xbill.DNS.Name;
import org.xbill.DNS.PTRRecord;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class PTRRecordDeserializer extends
		AbstractRecordDeserializer<PTRRecord> {
	private static final long serialVersionUID = -7237324659966254820L;

	public PTRRecordDeserializer() {
		super(PTRRecord.class);
	}

	@Override
	protected PTRRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		return new PTRRecord(name, dclass, ttl, getNodeNameValue(recordNode,
				"target"));
	}

	@Override
	protected String getTextualRecordType() {
		return "PTR";
	}
}

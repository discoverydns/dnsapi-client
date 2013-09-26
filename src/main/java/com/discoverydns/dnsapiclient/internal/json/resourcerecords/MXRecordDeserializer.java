package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MXRecordDeserializer extends AbstractRecordDeserializer<MXRecord> {
	private static final long serialVersionUID = -9157137850645403440L;

	public MXRecordDeserializer() {
		super(MXRecord.class);
	}

	@Override
	protected MXRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		return new MXRecord(name, dclass, ttl, getNodeIntegerValue(recordNode,
				"priority"), getNodeNameValue(recordNode, "target"));
	}

	@Override
	protected String getTextualRecordType() {
		return "MX";
	}
}

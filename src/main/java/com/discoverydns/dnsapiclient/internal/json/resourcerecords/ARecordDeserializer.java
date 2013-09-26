package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ARecordDeserializer extends
		AbstractAddressRecordDeserializer<ARecord> {
	private static final long serialVersionUID = -330875628382721963L;

	public ARecordDeserializer() {
		super(ARecord.class);
	}

	@Override
	protected ARecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		return new ARecord(name, dclass, ttl, getNodeAddressValue(recordNode));
	}

	@Override
	protected String getTextualRecordType() {
		return "A";
	}
}

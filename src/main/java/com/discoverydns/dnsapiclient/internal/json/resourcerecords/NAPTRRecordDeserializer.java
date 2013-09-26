package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import org.xbill.DNS.NAPTRRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class NAPTRRecordDeserializer extends
		AbstractRecordDeserializer<NAPTRRecord> {
	private static final long serialVersionUID = 1755167755563629606L;

	public NAPTRRecordDeserializer() {
		super(NAPTRRecord.class);
	}

	@Override
	protected NAPTRRecord createRecord(final Name name, final int dclass,
			final long ttl, final ObjectNode recordNode) {
		return new NAPTRRecord(name, dclass, ttl, getNodeIntegerValue(
				recordNode, "order"), getNodeIntegerValue(recordNode,
				"preference"), getNodeStringValue(recordNode, "flags"),
				getNodeStringValue(recordNode, "service"), getNodeStringValue(
						recordNode, "regexp"), getNodeNameValue(recordNode,
						"replacement"));
	}

	@Override
	protected String getTextualRecordType() {
		return "NAPTR";
	}
}

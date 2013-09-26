package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.net.InetAddress;

import org.xbill.DNS.Record;

import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class AbstractAddressRecordDeserializer<T extends Record>
		extends AbstractRecordDeserializer<T> {
	private static final long serialVersionUID = 8261282382283892468L;

	private static final String ADDRESS_FIELD_NAME = "address";

	protected AbstractAddressRecordDeserializer(final Class<?> recordClass) {
		super(recordClass);
	}

	protected InetAddress getNodeAddressValue(final ObjectNode recordNode) {
		return getNodeAddressValue(recordNode, ADDRESS_FIELD_NAME);
	}
}

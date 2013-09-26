package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;

import org.xbill.DNS.DClass;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;

import com.discoverydns.dnsapiclient.internal.json.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class AbstractRecordDeserializer<T extends Record> extends
		AbstractDeserializer<T> {
	private static final long serialVersionUID = 1189405106065540372L;

	private static final String NAME_FIELD_NAME = "name";
	private static final String TTL_FIELD_NAME = "ttl";

	protected AbstractRecordDeserializer(final Class<?> recordClass) {
		super(recordClass);
	}

	@Override
	public T deserialize(final JsonParser jsonParser,
			final DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		final ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		final ObjectNode recordNode = (ObjectNode) mapper.reader()
				.without(DeserializationFeature.UNWRAP_ROOT_VALUE)
				.readTree(jsonParser);
		return createRecord(getRecordName(recordNode), getDClass(),
				getRecordTTL(recordNode), recordNode);
	}

	protected abstract T createRecord(Name name, int dclass, long ttl,
			ObjectNode recordNode);

	protected abstract String getTextualRecordType();

	protected String getTextualBeanType() {
		return getTextualRecordType() + " record";
	}

	protected Name getRecordName(final ObjectNode recordNode) {
		return getNodeNameValue(recordNode, NAME_FIELD_NAME);
	}

	protected int getDClass() {
		return DClass.IN;
	}

	protected int getRecordTTL(final ObjectNode recordNode) {
		return getNodeIntegerValue(recordNode, TTL_FIELD_NAME);
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xbill.DNS.Record;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RecordTypeReferenceDeserializer extends StdDeserializer<Record> {
	private static final long serialVersionUID = 3048754137709241877L;

	private static final String TYPE_FIELD_NAME = "type";

	private final Map<String, Class<? extends Record>> recordClassesRegistry = new HashMap<String, Class<? extends Record>>();

	public RecordTypeReferenceDeserializer() {
		super(Record.class);
	}

	public void registerRecordType(final String typeValue,
			final Class<? extends Record> recordClass) {
		recordClassesRegistry.put(typeValue, recordClass);
	}

	@Override
	public Record deserialize(final JsonParser jsonParser,
			final DeserializationContext ctxt) throws JsonProcessingException,
			IOException {
		final ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		final ObjectReader reader = mapper.reader().without(
				DeserializationFeature.UNWRAP_ROOT_VALUE);
		final ObjectNode recordNode = (ObjectNode) reader.readTree(jsonParser);

		final JsonNode recordTypeNode = recordNode.get(TYPE_FIELD_NAME);
		if (recordTypeNode == null) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.missingField,
					TYPE_FIELD_NAME, "resource record");
		}
		final String recordType = recordTypeNode.textValue();
		final Class<? extends Record> recordClass = recordClassesRegistry
				.get(recordType);
		if (recordClass == null) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unknownResourceRecordType,
					recordType);
		}
		return reader.withType(recordClass).readValue(recordNode.toString());
	}
}

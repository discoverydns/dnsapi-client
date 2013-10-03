package com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.discoverydns.dnsapiclient.internal.json.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class NameServerInterfaceDeserializer extends
		AbstractDeserializer<NameServerInterface> {
	private static final long serialVersionUID = 7935830687484799360L;

	public NameServerInterfaceDeserializer() {
		super(NameServerInterface.class);
	}

	@Override
	public NameServerInterface deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) {
		ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
		ObjectNode objectNode;
		try {
			objectNode = (ObjectNode) mapper.reader()
					.without(DeserializationFeature.UNWRAP_ROOT_VALUE)
					.readTree(jsonParser);
		} catch (IOException e) {
			throw new DNSAPIClientJsonMappingException(
					DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
					e, getTextualBeanType(), e.getMessage());
		}

		NameServerInterface nameServerInterface = new NameServerInterface();
		nameServerInterface.setOrder(findFieldNode(objectNode, "order")
				.intValue());
		nameServerInterface.setName(findFieldNode(objectNode, "name")
				.textValue());
		nameServerInterface.setIpv4Address((Inet4Address) getNodeAddressValue(
				objectNode, "ipv4Address"));
		nameServerInterface.setIpv6Address((Inet6Address) getNodeAddressValue(
				objectNode, "ipv6Address"));

		return nameServerInterface;
	}

	@Override
	protected String getTextualBeanType() {
		return "nameServer Interface";
	}
}

package com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset;

import java.io.IOException;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.net.InetAddresses;

public class NameServerInterfaceSerializer extends
		StdSerializer<NameServerInterface> {

	public NameServerInterfaceSerializer() {
		super(NameServerInterface.class);
	}

	@Override
	public void serialize(NameServerInterface nameServerInterface,
			JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonGenerationException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("order", nameServerInterface.getOrder());
		jsonGenerator.writeStringField("name", nameServerInterface.getName());
		jsonGenerator.writeStringField("ipv4Address", InetAddresses
				.toAddrString(nameServerInterface.getIpv4Address()));
		jsonGenerator.writeStringField("ipv6Address", InetAddresses
				.toAddrString(nameServerInterface.getIpv6Address()));
		jsonGenerator.writeEndObject();
	}
}

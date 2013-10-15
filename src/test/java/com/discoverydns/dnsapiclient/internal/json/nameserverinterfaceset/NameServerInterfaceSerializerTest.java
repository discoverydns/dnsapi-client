package com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.Inet4Address;
import java.net.Inet6Address;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.net.InetAddresses;

@RunWith(MockitoJUnitRunner.class)
public class NameServerInterfaceSerializerTest {
	@Mock
	private NameServerInterface mockNameServerInterface;
	@Mock
	private JsonGenerator mockJsonGenerator;
	@Mock
	private SerializerProvider mockSerializerProvider;

	private NameServerInterfaceSerializer nameServerInterfaceSerializer;
	
	private String name = "name";
	private String ipv4Address = "1.2.3.4";
	private String nonCompressableAddress = "1:2:3:4:5:6:7:8";
	private String compressableAddress = "1:0:0:0:0:0:0:0";
    private Inet4Address inet4Address = (Inet4Address) InetAddresses.forString(ipv4Address);
	private Inet6Address nonCompressableInetAddress = (Inet6Address) InetAddresses.forString(nonCompressableAddress);
	private Inet6Address compressableInetAddress = (Inet6Address) InetAddresses.forString(compressableAddress);
	
	@Before
	public void setup() throws Throwable {
		when(mockNameServerInterface.getName()).thenReturn(name);
		when(mockNameServerInterface.getIpv4Address()).thenReturn(inet4Address);
		when(mockNameServerInterface.getIpv6Address()).thenReturn(nonCompressableInetAddress);
		
		nameServerInterfaceSerializer = new NameServerInterfaceSerializer();	
	}
	
	@Test
	public void shouldWriteExpectedObject() throws Exception {
		nameServerInterfaceSerializer.serialize(mockNameServerInterface,
				mockJsonGenerator, mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStartObject();
		verify(mockJsonGenerator).writeStringField("name", name);
		verify(mockJsonGenerator).writeStringField("ipv4Address", ipv4Address);
		verify(mockJsonGenerator).writeStringField("ipv6Address", nonCompressableAddress);
		verify(mockJsonGenerator).writeEndObject();
	}
	
	@Test
	public void shouldWriteCompressedIPv6Address() throws Exception {
        String compressedAddress = "1::";
		when(mockNameServerInterface.getIpv6Address()).thenReturn(compressableInetAddress);

		nameServerInterfaceSerializer.serialize(mockNameServerInterface,
				mockJsonGenerator, mockSerializerProvider);

		verify(mockJsonGenerator).writeStartObject();
		verify(mockJsonGenerator).writeStringField("name", name);
		verify(mockJsonGenerator).writeStringField("ipv4Address", ipv4Address);
        verify(mockJsonGenerator).writeStringField("ipv6Address", compressedAddress);
		verify(mockJsonGenerator).writeEndObject();
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.AAAARecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.net.InetAddresses;

@RunWith(MockitoJUnitRunner.class)
public class AAAARecordDeserializerTest {
	@Mock
	private JsonNode mockJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private AAAARecordDeserializer aaaaRecordDeserializer;

	private ObjectNode fakeObjectNode;
    private InetAddress inetaddress;

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

        String address = "0:0:0:0:0:0:0:0";
        inetaddress = InetAddresses.forString(address);
		when(mockJsonNode.textValue()).thenReturn(address);
		fakeObjectNode.put("address", mockJsonNode);

		aaaaRecordDeserializer = new AAAARecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("AAAA", aaaaRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        AAAARecord aaaaRecord = aaaaRecordDeserializer.createRecord(name,
                dclass, ttl, fakeObjectNode);

		assertEquals(name, aaaaRecord.getName());
		assertEquals(dclass, aaaaRecord.getDClass());
		assertEquals(ttl, aaaaRecord.getTTL());
		assertEquals(inetaddress, aaaaRecord.getAddress());
	}
}

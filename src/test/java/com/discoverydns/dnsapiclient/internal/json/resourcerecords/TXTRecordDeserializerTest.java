package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.TXTRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class TXTRecordDeserializerTest {
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockStringsJsonNode;

	private TXTRecordDeserializer txtRecordDeserializer;

	private ObjectNode fakeObjectNode;
    private String string1 = "string1";
	private String string2 = "string 2";
	private String string3 = "string3";
	private String strings = string1 + " \"" + string2 + "\" " + string3;

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockStringsJsonNode.textValue()).thenReturn(strings);
		fakeObjectNode.put("strings", mockStringsJsonNode);

		txtRecordDeserializer = new TXTRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("TXT", txtRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        TXTRecord txtRecord = txtRecordDeserializer.createRecord(name, dclass,
                ttl, fakeObjectNode);

		assertEquals(name, txtRecord.getName());
		assertEquals(dclass, txtRecord.getDClass());
		assertEquals(ttl, txtRecord.getTTL());
		assertArrayEquals(new String[] { string1, string2, string3 }, txtRecord
				.getStrings().toArray());
	}
}

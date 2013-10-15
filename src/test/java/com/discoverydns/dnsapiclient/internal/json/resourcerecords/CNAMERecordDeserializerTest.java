package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.CNAMERecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class CNAMERecordDeserializerTest {
	@Mock
	private JsonNode mockJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private CNAMERecordDeserializer cnameRecordDeserializer;

	private ObjectNode fakeObjectNode;
    private Name targetName;

    @Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

        String target = "target.domain.com.";
        targetName = Name.fromString(target);
		when(mockJsonNode.textValue()).thenReturn(target);
		fakeObjectNode.put("target", mockJsonNode);

		cnameRecordDeserializer = new CNAMERecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("CNAME", cnameRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        CNAMERecord cnameRecord = cnameRecordDeserializer.createRecord(name,
                dclass, ttl, fakeObjectNode);

		assertEquals(name, cnameRecord.getName());
		assertEquals(dclass, cnameRecord.getDClass());
		assertEquals(ttl, cnameRecord.getTTL());
		assertEquals(targetName, cnameRecord.getTarget());
	}
}

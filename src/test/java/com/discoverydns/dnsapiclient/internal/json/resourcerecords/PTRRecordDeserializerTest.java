package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.PTRRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class PTRRecordDeserializerTest {
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockJsonNode;

	private PTRRecordDeserializer ptrRecordDeserializer;

	private ObjectNode fakeObjectNode;
    private Name targetName;

    @Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

        String target = "target.domain.com.";
        targetName = Name.fromString(target);
		when(mockJsonNode.textValue()).thenReturn(target);
		fakeObjectNode.put("target", mockJsonNode);

		ptrRecordDeserializer = new PTRRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("PTR", ptrRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        PTRRecord ptrRecord = ptrRecordDeserializer.createRecord(name, dclass,
                ttl, fakeObjectNode);

		assertEquals(name, ptrRecord.getName());
		assertEquals(dclass, ptrRecord.getDClass());
		assertEquals(ttl, ptrRecord.getTTL());
		assertEquals(targetName, ptrRecord.getTarget());
	}
}

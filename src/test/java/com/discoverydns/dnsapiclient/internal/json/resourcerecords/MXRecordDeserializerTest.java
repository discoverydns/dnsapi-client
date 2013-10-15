package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class MXRecordDeserializerTest {
	@Mock
	private JsonNode mockTargetJsonNode;
	@Mock
	private JsonNode mockPriorityJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private MXRecordDeserializer mxRecordDeserializer;

	private ObjectNode fakeObjectNode;

    private int priority = 2;
	private Name targetName;

    @Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockPriorityJsonNode.textValue()).thenReturn(
				String.valueOf(priority));
		fakeObjectNode.put("priority", mockPriorityJsonNode);

        String target = "host.domain.com.";
        targetName = Name.fromString(target);
		when(mockTargetJsonNode.textValue()).thenReturn(target);
		fakeObjectNode.put("target", mockTargetJsonNode);

		mxRecordDeserializer = new MXRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("MX", mxRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        MXRecord mxRecord = mxRecordDeserializer.createRecord(name, dclass,
                ttl, fakeObjectNode);

		assertEquals(name, mxRecord.getName());
		assertEquals(dclass, mxRecord.getDClass());
		assertEquals(ttl, mxRecord.getTTL());
		assertEquals(targetName, mxRecord.getTarget());
		assertEquals(priority, mxRecord.getPriority());
	}
}

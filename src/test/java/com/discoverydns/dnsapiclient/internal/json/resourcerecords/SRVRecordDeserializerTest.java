package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class SRVRecordDeserializerTest {
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockPriorityJsonNode;
	@Mock
	private JsonNode mockWeightJsonNode;
	@Mock
	private JsonNode mockPortJsonNode;
	@Mock
	private JsonNode mockTargetJsonNode;

	private ObjectNode fakeObjectNode;
    private int priority = 2;
	private int weight = 3;
	private int port = 4;
	private Name targetName;

    private SRVRecordDeserializer srvRecordDeserializer;

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockPriorityJsonNode.textValue()).thenReturn(
				String.valueOf(priority));
		fakeObjectNode.put("priority", mockPriorityJsonNode);
		when(mockWeightJsonNode.textValue()).thenReturn(String.valueOf(weight));
		fakeObjectNode.put("weight", mockWeightJsonNode);
		when(mockPortJsonNode.textValue()).thenReturn(String.valueOf(port));
		fakeObjectNode.put("port", mockPortJsonNode);

        String target = "target.com.";
        targetName = Name.fromString(target);
		when(mockTargetJsonNode.textValue()).thenReturn(target);
		fakeObjectNode.put("target", mockTargetJsonNode);

		srvRecordDeserializer = new SRVRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("SRV", srvRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        SRVRecord srvRecord = srvRecordDeserializer.createRecord(name, dclass,
                ttl, fakeObjectNode);

		assertEquals(name, srvRecord.getName());
		assertEquals(dclass, srvRecord.getDClass());
		assertEquals(ttl, srvRecord.getTTL());
		assertEquals(priority, srvRecord.getPriority());
		assertEquals(weight, srvRecord.getWeight());
		assertEquals(port, srvRecord.getPort());
		assertEquals(targetName, srvRecord.getTarget());
	}
}

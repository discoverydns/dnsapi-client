package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.DSRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class DSRecordDeserializerTest {
	@Mock
	private JsonNode mockAlgorithmJsonNode;
	@Mock
	private JsonNode mockDigestIdJsonNode;
	@Mock
	private JsonNode mockFootprintJsonNode;
	@Mock
	private JsonNode mockDigestJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private DSRecordDeserializer dsRecordDeserializer;

	private ObjectNode fakeObjectNode;
	private Name name;
	private int dclass = 1;
	private long ttl = 3600L;
	private int footprint = 2;
	private int algorithm = 3;
	private int digestId = 4;
	private String digest = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		name = Name.fromString("test.domain.com.");

		when(mockFootprintJsonNode.textValue()).thenReturn(
				String.valueOf(footprint));
		fakeObjectNode.put("footprint", mockFootprintJsonNode);

		when(mockAlgorithmJsonNode.textValue()).thenReturn(
				String.valueOf(algorithm));
		fakeObjectNode.put("algorithm", mockAlgorithmJsonNode);

		when(mockDigestIdJsonNode.textValue()).thenReturn(
				String.valueOf(digestId));
		fakeObjectNode.put("digestId", mockDigestIdJsonNode);

		when(mockDigestJsonNode.textValue()).thenReturn(digest);
		fakeObjectNode.put("digest", mockDigestJsonNode);

		dsRecordDeserializer = new DSRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("DS", dsRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
		DSRecord dsRecord = dsRecordDeserializer.createRecord(name, dclass,
				ttl, fakeObjectNode);

		assertEquals(name, dsRecord.getName());
		assertEquals(dclass, dsRecord.getDClass());
		assertEquals(ttl, dsRecord.getTTL());
		assertEquals(footprint, dsRecord.getFootprint());
		assertEquals(algorithm, dsRecord.getAlgorithm());
		assertEquals(digestId, dsRecord.getDigestID());
		assertEquals(digest, dsRecord.getTextualDigest());
	}
	
	@Test
	public void shouldIgnoreLineBreaksFromDigestInput() throws Exception {
		String digestWithLineBreaks = "abcd\nefab";
		when(mockDigestJsonNode.textValue()).thenReturn(digestWithLineBreaks);
		fakeObjectNode.put("digest", mockDigestJsonNode);
		
		DSRecord dsRecord = dsRecordDeserializer.createRecord(name, dclass,
				ttl, fakeObjectNode);

		assertEquals(digestWithLineBreaks.replaceAll("\\n", "").toUpperCase(),
				dsRecord.getTextualDigest());
	}
}

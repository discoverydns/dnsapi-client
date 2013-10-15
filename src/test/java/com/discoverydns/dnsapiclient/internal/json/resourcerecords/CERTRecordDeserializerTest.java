package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.CERTRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class CERTRecordDeserializerTest {
	@Mock
	private JsonNode mockCertTypeJsonNode;
	@Mock
	private JsonNode mockKeyTagJsonNode;
	@Mock
	private JsonNode mockAlgorithmJsonNode;
	@Mock
	private JsonNode mockCertJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private CERTRecordDeserializer certRecordDeserializer;

	private ObjectNode fakeObjectNode;
	private Name name;
	private int dclass = 1;
	private long ttl = 3600L;
	private int certType = 2;
	private int keyTag = 3;
	private int algorithm = 4;
	private String cert = "4MS4SFN10P02UCOG4M1AMDC41CAO1ITQ";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		name = Name.fromString("test.domain.com.");

		when(mockCertTypeJsonNode.textValue()).thenReturn(
				String.valueOf(certType));
		fakeObjectNode.put("certType", mockCertTypeJsonNode);

		when(mockKeyTagJsonNode.textValue()).thenReturn(String.valueOf(keyTag));
		fakeObjectNode.put("keyTag", mockKeyTagJsonNode);

		when(mockAlgorithmJsonNode.textValue()).thenReturn(
				String.valueOf(algorithm));
		fakeObjectNode.put("algorithm", mockAlgorithmJsonNode);

		when(mockCertJsonNode.textValue()).thenReturn(cert);
		fakeObjectNode.put("cert", mockCertJsonNode);

		certRecordDeserializer = new CERTRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("CERT", certRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
		CERTRecord certRecord = certRecordDeserializer.createRecord(name,
				dclass, ttl, fakeObjectNode);

		assertEquals(name, certRecord.getName());
		assertEquals(dclass, certRecord.getDClass());
		assertEquals(ttl, certRecord.getTTL());
		assertEquals(certType, certRecord.getCertType());
		assertEquals(keyTag, certRecord.getKeyTag());
		assertEquals(algorithm, certRecord.getAlgorithm());
		assertEquals(cert, certRecord.getTextualCert());
	}
	
	@Test
	public void shouldIgnoreLineBreaksFromCertInput() throws Exception {
		String certWithLineBreaks = "abcd\nefgh";
		when(mockCertJsonNode.textValue()).thenReturn(certWithLineBreaks);
		fakeObjectNode.put("cert", mockCertJsonNode);
		
		CERTRecord certRecord = certRecordDeserializer.createRecord(name,
				dclass, ttl, fakeObjectNode);
		
		assertEquals(certWithLineBreaks.replaceAll("\\n", ""),
				certRecord.getTextualCert());
	}
}

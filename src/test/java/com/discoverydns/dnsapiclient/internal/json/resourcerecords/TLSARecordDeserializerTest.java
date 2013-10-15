package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.TLSARecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class TLSARecordDeserializerTest {
	@Mock
	private JsonNode mockCertificateUsageJsonNode;
	@Mock
	private JsonNode mockSelectorJsonNode;
	@Mock
	private JsonNode mockMatchingTypeJsonNode;
	@Mock
	private JsonNode mockCertificateAssociationDataJsonNode;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private TLSARecordDeserializer tlsaRecordDeserializer;
	
	private ObjectNode fakeObjectNode;
	private Name name;
	private int dclass = 1;
	private long ttl = 3600L;
	private int certificateUsage = 2;
	private int selector = 3;
	private int matchingType = 4;
	private String certificateAssociationData = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		name = Name.fromString("test.domain.com.");

		when(mockCertificateUsageJsonNode.textValue()).thenReturn(
				String.valueOf(certificateUsage));
		fakeObjectNode.put("certificateUsage", mockCertificateUsageJsonNode);
		when(mockSelectorJsonNode.textValue()).thenReturn(
				String.valueOf(selector));
		fakeObjectNode.put("selector", mockSelectorJsonNode);
		when(mockMatchingTypeJsonNode.textValue()).thenReturn(
				String.valueOf(matchingType));
		fakeObjectNode.put("matchingType", mockMatchingTypeJsonNode);

		when(mockCertificateAssociationDataJsonNode.textValue()).thenReturn(certificateAssociationData);
		fakeObjectNode.put("certificateAssociationData", mockCertificateAssociationDataJsonNode);
		
		tlsaRecordDeserializer = new TLSARecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("TLSA", tlsaRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
		TLSARecord tlsaRecord = tlsaRecordDeserializer.createRecord(name, dclass,
				ttl, fakeObjectNode);
		
		assertEquals(name, tlsaRecord.getName());
		assertEquals(dclass, tlsaRecord.getDClass());
		assertEquals(ttl, tlsaRecord.getTTL());
		assertEquals(certificateUsage, tlsaRecord.getCertificateUsage());
		assertEquals(selector, tlsaRecord.getSelector());
		assertEquals(matchingType, tlsaRecord.getMatchingType());
		assertEquals(certificateAssociationData, tlsaRecord.getTextualCertificateAssociationData());
	}

	@Test
	public void shouldIgnoreLineBreaksFromCertificateAssociationDataInput() throws Exception {
		String certificateAssociationDataWithLineBreaks = "abcd\nefab";
		when(mockCertificateAssociationDataJsonNode.textValue()).thenReturn(
				certificateAssociationDataWithLineBreaks);
		fakeObjectNode.put("certificateAssociationData", mockCertificateAssociationDataJsonNode);
		
		TLSARecord tlsaRecord = tlsaRecordDeserializer.createRecord(name, dclass,
				ttl, fakeObjectNode);

		assertEquals(certificateAssociationDataWithLineBreaks.replaceAll("\\n", "").toUpperCase(),
				tlsaRecord.getTextualCertificateAssociationData());
	}
}

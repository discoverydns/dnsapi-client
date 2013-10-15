package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.SSHFPRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class SSHFPRecordDeserializerTest {
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock private JsonNode mockAlgorithmJsonNode;
	@Mock private JsonNode mockDigestTypeJsonNode;
	@Mock private JsonNode mockFingerprintJsonNode;

	private SSHFPRecordDeserializer sshfpRecordDeserializer;

	private ObjectNode fakeObjectNode;
	private Name name;
	private int dclass = 1;
	private long ttl = 3600L;
	private int algorithm = 3;
	private int fingerprintType = 4;
	private String fingerprint = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		name = Name.fromString("test.domain.com.");

		when(mockAlgorithmJsonNode.textValue()).thenReturn(String.valueOf(algorithm));
		fakeObjectNode.put("algorithm", mockAlgorithmJsonNode);
		when(mockDigestTypeJsonNode.textValue()).thenReturn(String.valueOf(fingerprintType));
		fakeObjectNode.put("digestType", mockDigestTypeJsonNode);
		when(mockFingerprintJsonNode.textValue()).thenReturn(fingerprint);
		fakeObjectNode.put("fingerprint", mockFingerprintJsonNode);

		sshfpRecordDeserializer = new SSHFPRecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("SSHFP", sshfpRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
		SSHFPRecord sshfpRecord = sshfpRecordDeserializer.createRecord(
				name, dclass, ttl, fakeObjectNode);

		assertEquals(name, sshfpRecord.getName());
		assertEquals(dclass, sshfpRecord.getDClass());
		assertEquals(ttl, sshfpRecord.getTTL());
		assertEquals(algorithm, sshfpRecord.getAlgorithm());
		assertEquals(fingerprintType, sshfpRecord.getDigestType());
		assertEquals(fingerprint, sshfpRecord.getTextualFingerPrint());
	}

	@Test
	public void shouldIgnoreLineBreaksFromFingerprintInput() throws Exception {
		String fingerprintWithLineBreaks = "abcd\nefab";
		when(mockFingerprintJsonNode.textValue()).thenReturn(fingerprintWithLineBreaks);
		fakeObjectNode.put("fingerprint", mockFingerprintJsonNode);
		
		SSHFPRecord sshfpRecord = sshfpRecordDeserializer.createRecord(
				name, dclass, ttl, fakeObjectNode);

		assertEquals(fingerprintWithLineBreaks.replaceAll("\\n", "").toUpperCase(),
				sshfpRecord.getTextualFingerPrint());
	}
}

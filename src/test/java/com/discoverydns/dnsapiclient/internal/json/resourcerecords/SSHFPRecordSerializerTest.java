package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.SSHFPRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class SSHFPRecordSerializerTest {
	@Mock private SSHFPRecord mockSSHFPRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;

	private SSHFPRecordSerializer sshfpRecordSerializer;

	private int algorithm = 3;
	private int digestType = 4;
	private String fingerprint = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

    @Before
	public void setup() throws Throwable {
		when(mockSSHFPRecord.getAlgorithm()).thenReturn(algorithm);
		when(mockSSHFPRecord.getDigestType()).thenReturn(digestType);
		when(mockSSHFPRecord.getTextualFingerPrint()).thenReturn(fingerprint);

		sshfpRecordSerializer = new SSHFPRecordSerializer();
	}

	@Test
	public void shouldGenerateFormattedAlgorithmField() throws Exception {
        String formattedAlgorithm = "formattedAlgorithm";
		sshfpRecordSerializer = spy(sshfpRecordSerializer);
        when(sshfpRecordSerializer.formatNumber(algorithm)).thenReturn(formattedAlgorithm);

		sshfpRecordSerializer.serializeRDataFields(mockSSHFPRecord, mockJsonGenerator,
				mockSerializerProvider);

		verify(mockJsonGenerator).writeStringField("algorithm", formattedAlgorithm);
	}

	@Test
	public void shouldGenerateFormattedDigestTypeField() throws Exception {
        String formattedDigestType = "formattedDigestType";
		sshfpRecordSerializer = spy(sshfpRecordSerializer);
        when(sshfpRecordSerializer.formatNumber(digestType)).thenReturn(formattedDigestType);

		sshfpRecordSerializer.serializeRDataFields(mockSSHFPRecord, mockJsonGenerator,
				mockSerializerProvider);

		verify(mockJsonGenerator).writeStringField("digestType", formattedDigestType);
	}

	@Test
	public void shouldGenerateDigestField() throws Exception {
		sshfpRecordSerializer = spy(sshfpRecordSerializer);

		sshfpRecordSerializer.serializeRDataFields(mockSSHFPRecord, mockJsonGenerator,
				mockSerializerProvider);

		verify(mockJsonGenerator).writeStringField("fingerprint", fingerprint);
	}
}
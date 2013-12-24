package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.DSRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class DSRecordSerializerTest {
	@Mock private DSRecord mockDSRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private DSRecordSerializer dsRecordSerializer;
	
	private int footprint = 2;
	private int algorithm = 3;
	private int digestId = 4;
	private String digest = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

    @Before
	public void setup() throws Throwable {
		when(mockDSRecord.getFootprint()).thenReturn(footprint);
		when(mockDSRecord.getAlgorithm()).thenReturn(algorithm);
		when(mockDSRecord.getDigestID()).thenReturn(digestId);
		when(mockDSRecord.getTextualDigest()).thenReturn(digest);
		
		dsRecordSerializer = new DSRecordSerializer();
	}
	
	@Test
	public void shouldGenerateFormattedFootprintField() throws Exception {
        String formattedFootprint = "formattedFootprint";
		dsRecordSerializer = spy(dsRecordSerializer);
        when(dsRecordSerializer.formatNumber(footprint)).thenReturn(formattedFootprint);
		
		dsRecordSerializer.serializeRDataFields(mockDSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("footprint", formattedFootprint);
	}
	
	@Test
	public void shouldGenerateFormattedAlgorithmField() throws Exception {
        String formattedAlgorithm = "formattedAlgorithm";
		dsRecordSerializer = spy(dsRecordSerializer);
        when(dsRecordSerializer.formatNumber(algorithm)).thenReturn(formattedAlgorithm);
		
		dsRecordSerializer.serializeRDataFields(mockDSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("algorithm", formattedAlgorithm);
	}
	
	@Test
	public void shouldGenerateFormattedDigestIdField() throws Exception {
        String formattedDigestId = "formattedDigestId";
		dsRecordSerializer = spy(dsRecordSerializer);
        when(dsRecordSerializer.formatNumber(digestId)).thenReturn(formattedDigestId);
		
		dsRecordSerializer.serializeRDataFields(mockDSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("digestId", formattedDigestId);
	}
	
	@Test
	public void shouldGenerateDigestField() throws Exception {
		dsRecordSerializer = spy(dsRecordSerializer);

		dsRecordSerializer.serializeRDataFields(mockDSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("digest", digest);
	}	
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class SRVRecordSerializerTest {
	@Mock private SRVRecord mockSRVRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockTargetName;

	private SRVRecordSerializer srvRecordSerializer;
	
	private int priority = 1;
	private int weight = 2;
	private int port = 3;
	private String targetName = "targetName";

    @Before
	public void setup() throws Throwable {
		when(mockSRVRecord.getTarget()).thenReturn(mockTargetName);
		when(mockTargetName.toString()).thenReturn(targetName);
		when(mockSRVRecord.getPriority()).thenReturn(priority);
		when(mockSRVRecord.getWeight()).thenReturn(weight);
		when(mockSRVRecord.getPort()).thenReturn(port);
		
		srvRecordSerializer = new SRVRecordSerializer();
	}
	
	@Test
	public void shouldGeneratePriorityField() throws Exception {
        String formattedPriority = "formattedPriority";
		srvRecordSerializer = spy(srvRecordSerializer);
        when(srvRecordSerializer.formatNumber(priority)).thenReturn(formattedPriority);
		
		srvRecordSerializer.serializeRDataFields(mockSRVRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("priority", formattedPriority);
	}
	
	@Test
	public void shouldGenerateWeightField() throws Exception {
        String formattedWeight = "formattedWeight";
		srvRecordSerializer = spy(srvRecordSerializer);
        when(srvRecordSerializer.formatNumber(weight)).thenReturn(formattedWeight);
		
		srvRecordSerializer.serializeRDataFields(mockSRVRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("weight", formattedWeight);
	}
	
	@Test
	public void shouldGeneratePortField() throws Exception {
        String formattedPort = "formattedPort";
		srvRecordSerializer = spy(srvRecordSerializer);
        when(srvRecordSerializer.formatNumber(port)).thenReturn(formattedPort);
		
		srvRecordSerializer.serializeRDataFields(mockSRVRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("port", formattedPort);
	}
	
	@Test
	public void shouldGenerateTargetNameField() throws Exception {
		srvRecordSerializer.serializeRDataFields(mockSRVRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("target", targetName);
	}
	
	@Test
	public void shouldDoNothingIfTargetFieldIsNull() throws Exception {
		when(mockSRVRecord.getTarget()).thenReturn(null);

		srvRecordSerializer.serializeRDataFields(mockSRVRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator, times(0)).writeStringField("target", targetName);
	}
}

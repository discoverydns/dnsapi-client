package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.CNAMERecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class CNAMERecordSerializerTest {
	@Mock private CNAMERecord mockCNAMERecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockTargetName;
	
	private CNAMERecordSerializer cnameRecordSerializer;
	
	private String targetName = "targetName";
	
	@Before
	public void setup() throws Throwable {
		when(mockCNAMERecord.getTarget()).thenReturn(mockTargetName);
		when(mockTargetName.toString()).thenReturn(targetName);
		
		cnameRecordSerializer = new CNAMERecordSerializer();
	}
	
	@Test
	public void shouldGenerateTargetNameField() throws Exception {
		cnameRecordSerializer.serializeRDataFields(mockCNAMERecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("target", targetName);
	}
	
	@Test
	public void shouldDoNothingIfTargetFieldIsNull() throws Exception {
		when(mockCNAMERecord.getTarget()).thenReturn(null);

		cnameRecordSerializer.serializeRDataFields(mockCNAMERecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verifyNoMoreInteractions(mockJsonGenerator);
	}
}

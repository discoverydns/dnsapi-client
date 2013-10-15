package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.NSRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class NSRecordSerializerTest {
	@Mock private NSRecord mockNSRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockTargetName;
	
	private NSRecordSerializer nsRecordSerializer;
	
	private String targetName = "targetName";
	
	@Before
	public void setup() throws Throwable {
		when(mockNSRecord.getTarget()).thenReturn(mockTargetName);
		when(mockTargetName.toString()).thenReturn(targetName);
		
		nsRecordSerializer = new NSRecordSerializer();
	}
	
	@Test
	public void shouldGenerateTargetNameField() throws Exception {
		nsRecordSerializer.serializeRDataFields(mockNSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("target", targetName);
	}
	
	@Test
	public void shouldDoNothingIfTargetFieldIsNull() throws Exception {
		when(mockNSRecord.getTarget()).thenReturn(null);

		nsRecordSerializer.serializeRDataFields(mockNSRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verifyNoMoreInteractions(mockJsonGenerator);
	}
}

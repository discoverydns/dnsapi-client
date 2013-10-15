package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.PTRRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class PTRRecordSerializerTest {
	@Mock private PTRRecord mockPTRRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockTargetName;
	
	private PTRRecordSerializer ptrRecordSerializer;
	
	private String targetName = "targetName";
	
	@Before
	public void setup() throws Throwable {
		when(mockPTRRecord.getTarget()).thenReturn(mockTargetName);
		when(mockTargetName.toString()).thenReturn(targetName);
		
		ptrRecordSerializer = new PTRRecordSerializer();
	}
	
	@Test
	public void shouldGenerateTargetNameField() throws Exception {
		ptrRecordSerializer.serializeRDataFields(mockPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("target", targetName);
	}
	
	@Test
	public void shouldDoNothingIfTargetFieldIsNull() throws Exception {
		when(mockPTRRecord.getTarget()).thenReturn(null);

		ptrRecordSerializer.serializeRDataFields(mockPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verifyNoMoreInteractions(mockJsonGenerator);
	}
}

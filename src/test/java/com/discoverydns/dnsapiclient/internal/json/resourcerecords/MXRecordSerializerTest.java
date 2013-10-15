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
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class MXRecordSerializerTest {
	@Mock private MXRecord mockMXRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockTargetName;
	
	private MXRecordSerializer mxRecordSerializer;
	
	private String targetName = "targetName";
	private int priority = 1;

    @Before
	public void setup() throws Throwable {
		when(mockMXRecord.getPriority()).thenReturn(priority);
		when(mockMXRecord.getTarget()).thenReturn(mockTargetName);
		when(mockTargetName.toString()).thenReturn(targetName);
		
		mxRecordSerializer = new MXRecordSerializer();
	}
	
	@Test
	public void shouldGenerateFromattedPriorityField() throws Exception {
        String formattedPriority = "formattedPriority";
		mxRecordSerializer = spy(mxRecordSerializer);
        when(mxRecordSerializer.formatNumber(priority)).thenReturn(formattedPriority);
		
		mxRecordSerializer.serializeRDataFields(mockMXRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("priority", formattedPriority);
	}
	
	@Test
	public void shouldGenerateTargetNameField() throws Exception {
		mxRecordSerializer.serializeRDataFields(mockMXRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("target", targetName);
	}
	
	@Test
	public void shouldDoNothingIfTargetFieldIsNull() throws Exception {
		when(mockMXRecord.getTarget()).thenReturn(null);

		mxRecordSerializer.serializeRDataFields(mockMXRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator, times(0)).writeStringField("target", targetName);
	}
}

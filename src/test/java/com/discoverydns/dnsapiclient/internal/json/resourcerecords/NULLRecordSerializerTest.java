package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.NULLRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class NULLRecordSerializerTest {
	@Mock private NULLRecord mockNULLRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private NULLRecordSerializer nullRecordSerializer;
	
	@Before
	public void setup() throws Throwable {
		nullRecordSerializer = new NULLRecordSerializer();
	}
	
	@Test
	public void shouldIgnoreNULLRecords() throws Exception {
		nullRecordSerializer.serialize(mockNULLRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verifyZeroInteractions(mockNULLRecord);
		verifyZeroInteractions(mockJsonGenerator);
		verifyZeroInteractions(mockSerializerProvider);
	}
}

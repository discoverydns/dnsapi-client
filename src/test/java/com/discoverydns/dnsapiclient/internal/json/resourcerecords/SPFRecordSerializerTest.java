package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.SPFRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class SPFRecordSerializerTest {
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private SPFRecordSerializer spfRecordSerializer;
	
	private SPFRecord spfRecord;
    private String string1 = "string1";
	private String string2 = "string2";

    @Before
	public void setup() throws Throwable {
        List<String> strings = new ArrayList<>();
		strings.add(string1);
		strings.add(string2);
		spfRecord = new SPFRecord(new Name("name.com."), 1, 1, strings);
		
		spfRecordSerializer = new SPFRecordSerializer();
	}
	
	@Test
	public void shouldGenerateStringsFieldAsSingleString() throws Exception {
        String escapedString1 = "escapedString1";
        String escapedString2 = "escapedString2";
		spfRecordSerializer = spy(spfRecordSerializer);
        when(spfRecordSerializer.escapeCharacterString(string1)).thenReturn(escapedString1);
        when(spfRecordSerializer.escapeCharacterString(string2)).thenReturn(escapedString2);
		
		spfRecordSerializer.serializeRDataFields(spfRecord, mockJsonGenerator, mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("strings", escapedString1 + " " + escapedString2);
	}
}

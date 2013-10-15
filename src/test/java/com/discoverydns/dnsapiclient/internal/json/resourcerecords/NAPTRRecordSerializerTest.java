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
import org.xbill.DNS.NAPTRRecord;
import org.xbill.DNS.Name;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class NAPTRRecordSerializerTest {
	@Mock private NAPTRRecord mockNAPTRRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockReplacementName;

	private NAPTRRecordSerializer naptrRecordSerializer;
	
	private int order = 1;
    private int preference = 2;
    private String flags = "flags";
	private String service = "service";
	private String regexp = "regexp";
	private String replacementName = "replacementName";
	
	@Before
	public void setup() throws Throwable {
		when(mockNAPTRRecord.getOrder()).thenReturn(order);
		when(mockNAPTRRecord.getPreference()).thenReturn(preference);
		when(mockNAPTRRecord.getFlags()).thenReturn(flags);
		when(mockNAPTRRecord.getService()).thenReturn(service);
		when(mockNAPTRRecord.getRegexp()).thenReturn(regexp);
		when(mockNAPTRRecord.getReplacement()).thenReturn(mockReplacementName);
		when(mockReplacementName.toString()).thenReturn(replacementName);
		
		naptrRecordSerializer = new NAPTRRecordSerializer();
	}
	
	@Test
	public void shouldGenerateFromattedOrderField() throws Exception {
        String formattedOrder = "formattedOrder";
		naptrRecordSerializer = spy(naptrRecordSerializer);
        when(naptrRecordSerializer.formatNumber(order)).thenReturn(formattedOrder);
		
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("order", formattedOrder);
	}
	
	@Test
	public void shouldGenerateFromattedPreferenceField() throws Exception {
        String formattedPreference = "formattedPreference";
		naptrRecordSerializer = spy(naptrRecordSerializer);
        when(naptrRecordSerializer.formatNumber(preference)).thenReturn(formattedPreference);
		
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("preference", formattedPreference);
	}
	
	@Test
	public void shouldGenerateFlagsField() throws Exception {
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("flags", flags);
	}
	
	@Test
	public void shouldGenerateServiceField() throws Exception {
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("service", service);
	}
	
	@Test
	public void shouldGenerateRegexpField() throws Exception {
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("regexp", regexp);
	}
	
	@Test
	public void shouldGenerateReplacementNameField() throws Exception {
		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("replacement", replacementName);
	}
	
	@Test
	public void shouldDoNothingIfReplacementFieldIsNull() throws Exception {
		when(mockNAPTRRecord.getReplacement()).thenReturn(null);

		naptrRecordSerializer.serializeRDataFields(mockNAPTRRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator, times(0)).writeStringField("replacement", replacementName);
	}
}

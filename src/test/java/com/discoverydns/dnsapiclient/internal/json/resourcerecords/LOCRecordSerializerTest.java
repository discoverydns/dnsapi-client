package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.LOCRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class LOCRecordSerializerTest {
	@Mock private LOCRecord mockLOCRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private LOCRecordSerializer locRecordSerializer;
	
	private String size = "size";
	private String hPrecision = "hPrecision";
	private String vPrecision = "vPrecision";
	private String latitude = "latitude";
	private String longitude = "longitude";
	private String altitude = "altitude";
	
	@Before
	public void setup() throws Throwable {
		when(mockLOCRecord.getTextualSize()).thenReturn(size);
		when(mockLOCRecord.getTextualHPrecision()).thenReturn(hPrecision);
		when(mockLOCRecord.getTextualVPrecision()).thenReturn(vPrecision);
		when(mockLOCRecord.getTextualLatitude()).thenReturn(latitude);
		when(mockLOCRecord.getTextualLongitude()).thenReturn(longitude);
		when(mockLOCRecord.getTextualAltitude()).thenReturn(altitude);
		
		locRecordSerializer = new LOCRecordSerializer();
	}
	
	@Test
	public void shouldGenerateSizeField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("size", size);
	}
	
	@Test
	public void shouldGenerateHPrecisionField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("hPrecision", hPrecision);
	}
	
	@Test
	public void shouldGenerateVPrecisionField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("vPrecision", vPrecision);
	}
	
	@Test
	public void shouldGenerateLatitudeField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("latitude", latitude);
	}
	
	@Test
	public void shouldGenerateLongitudeField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("longitude", longitude);
	}
	
	@Test
	public void shouldGenerateAltitudeField() throws Exception {
		locRecordSerializer.serializeRDataFields(mockLOCRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("altitude", altitude);
	}
}

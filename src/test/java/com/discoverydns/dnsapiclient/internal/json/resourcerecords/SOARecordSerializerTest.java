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
import org.xbill.DNS.SOARecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class SOARecordSerializerTest {
	@Mock private SOARecord mockSOARecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockHostName;
	@Mock private Name mockAdminName;
	
	private SOARecordSerializer soaRecordSerializer;
	
	private String hostName = "hostName";
	private String adminName = "adminName";
	private long serial = 1L;
	private long refresh = 2L;
	private long retry = 3L;
	private long expire = 4L;
	private long minimum = 5L;

    @Before
	public void setup() throws Throwable {
		when(mockSOARecord.getHost()).thenReturn(mockHostName);
		when(mockHostName.toString()).thenReturn(hostName);
		when(mockSOARecord.getAdmin()).thenReturn(mockAdminName);
		when(mockAdminName.toString()).thenReturn(adminName);
		when(mockSOARecord.getSerial()).thenReturn(serial);
		when(mockSOARecord.getRefresh()).thenReturn(refresh);
		when(mockSOARecord.getRetry()).thenReturn(retry);
		when(mockSOARecord.getExpire()).thenReturn(expire);
		when(mockSOARecord.getMinimum()).thenReturn(minimum);
		
		soaRecordSerializer = new SOARecordSerializer();
	}
	
	@Test
	public void shouldGenerateHostNameField() throws Exception {
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("host", hostName);
	}
	
	@Test
	public void shouldDoNothingIfHostFieldIsNull() throws Exception {
		when(mockSOARecord.getHost()).thenReturn(null);

		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator, times(0)).writeStringField("host", hostName);
	}
	
	@Test
	public void shouldGenerateAdminNameField() throws Exception {
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("admin", adminName);
	}
	
	@Test
	public void shouldDoNothingIfAdminFieldIsNull() throws Exception {
		when(mockSOARecord.getAdmin()).thenReturn(null);

		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator, times(0)).writeStringField("admin", adminName);
	}
	
	@Test
	public void shouldGenerateFormattedSerialField() throws Exception {
        String formattedSerial = "formattedSerial";
		soaRecordSerializer = spy(soaRecordSerializer);
        when(soaRecordSerializer.formatNumber(serial)).thenReturn(formattedSerial);
		
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("serial", formattedSerial);
	}
	
	@Test
	public void shouldGenerateFormattedRefreshField() throws Exception {
        String formattedRefresh = "formattedRefresh";
		soaRecordSerializer = spy(soaRecordSerializer);
        when(soaRecordSerializer.formatNumber(refresh)).thenReturn(formattedRefresh);
		
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("refresh", formattedRefresh);
	}
	
	@Test
	public void shouldGenerateFormattedRetryField() throws Exception {
        String formattedRetry = "formattedRetry";
		soaRecordSerializer = spy(soaRecordSerializer);
        when(soaRecordSerializer.formatNumber(retry)).thenReturn(formattedRetry);
		
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("retry", formattedRetry);
	}
	
	@Test
	public void shouldGenerateFormattedExpireField() throws Exception {
        String formattedExpire = "formattedExpire";
		soaRecordSerializer = spy(soaRecordSerializer);
        when(soaRecordSerializer.formatNumber(expire)).thenReturn(formattedExpire);
		
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("expire", formattedExpire);
	}
	
	@Test
	public void shouldGenerateFormattedMinimumField() throws Exception {
        String formattedMinimum = "formattedMinimum";
		soaRecordSerializer = spy(soaRecordSerializer);
        when(soaRecordSerializer.formatNumber(minimum)).thenReturn(formattedMinimum);
		
		soaRecordSerializer.serializeRDataFields(mockSOARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("minimum", formattedMinimum);
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.AAAARecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.net.InetAddresses;

@RunWith(MockitoJUnitRunner.class)
public class AAAARecordSerializerTest {
	@Mock private AAAARecord mockAAAARecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;

	
	private AAAARecordSerializer aaaaRecordSerializer;

	private String nonCompressableAddress = "1:2:3:4:5:6:7:8";
	private String compressableAddress = "1:0:0:0:0:0:0:0";
    private InetAddress nonCompressableInetAddress = InetAddresses.forString(nonCompressableAddress);
	private InetAddress compressableInetAddress = InetAddresses.forString(compressableAddress);
	
	@Before
	public void setup() throws Throwable {
		aaaaRecordSerializer = new AAAARecordSerializer();
	}
	
	@Test
	public void shouldGenerateAddressField() throws Exception {
		when(mockAAAARecord.getAddress()).thenReturn(nonCompressableInetAddress);
		
		aaaaRecordSerializer.serializeRDataFields(mockAAAARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("address", nonCompressableAddress);
	}

	@Test
	public void shouldCompressCompressableAddress() throws Exception {
        String compressedAddress = "1::";
		when(mockAAAARecord.getAddress()).thenReturn(compressableInetAddress);

		aaaaRecordSerializer.serializeRDataFields(mockAAAARecord, mockJsonGenerator,
				mockSerializerProvider);

        verify(mockJsonGenerator).writeStringField("address", compressedAddress);
	}
	
	@Test
	public void shouldDoNothingIfAddressFieldIsNull() throws Exception {
		when(mockAAAARecord.getAddress()).thenReturn(null);

		aaaaRecordSerializer.serializeRDataFields(mockAAAARecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verifyNoMoreInteractions(mockJsonGenerator);
	}
}

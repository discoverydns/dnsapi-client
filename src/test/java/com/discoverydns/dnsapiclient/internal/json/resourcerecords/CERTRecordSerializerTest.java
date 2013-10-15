package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.CERTRecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class CERTRecordSerializerTest {
	@Mock private CERTRecord mockCERTRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private CERTRecordSerializer certRecordSerializer;
	
	private int certType = 2;
	private int keyTag = 3;
	private int algorithm = 4;
	private String cert = "4MS4SFN10P02UCOG4M1AMDC41CAO1ITQ";

    @Before
	public void setup() throws Throwable {
		when(mockCERTRecord.getCertType()).thenReturn(certType);
		when(mockCERTRecord.getKeyTag()).thenReturn(keyTag);
		when(mockCERTRecord.getAlgorithm()).thenReturn(algorithm);
		when(mockCERTRecord.getTextualCert()).thenReturn(cert);
		
		certRecordSerializer = new CERTRecordSerializer();
	}
	
	@Test
	public void shouldGenerateFormattedCertTypeField() throws Exception {
        String formattedCertType = "formattedCertType";
		certRecordSerializer = spy(certRecordSerializer);
        when(certRecordSerializer.formatNumber(certType)).thenReturn(formattedCertType);
		
		certRecordSerializer.serializeRDataFields(mockCERTRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("certType", formattedCertType);
	}
	
	@Test
	public void shouldGenerateFormattedKeyTagField() throws Exception {
        String formattedKeyTag = "formattedKeyTag";
		certRecordSerializer = spy(certRecordSerializer);
        when(certRecordSerializer.formatNumber(keyTag)).thenReturn(formattedKeyTag);
		
		certRecordSerializer.serializeRDataFields(mockCERTRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("keyTag", formattedKeyTag);
	}
	
	@Test
	public void shouldGenerateFormattedAlgorithmField() throws Exception {
        String formattedAlgorithm = "formattedAlgorithm";
		certRecordSerializer = spy(certRecordSerializer);
        when(certRecordSerializer.formatNumber(algorithm)).thenReturn(formattedAlgorithm);
		
		certRecordSerializer.serializeRDataFields(mockCERTRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("algorithm", formattedAlgorithm);
	}
	
	@Test
	public void shouldGenerateCertField() throws Exception {
        String escapedCert = "escapedCert";
		certRecordSerializer = spy(certRecordSerializer);
        when(certRecordSerializer.escapeSpecialCharacters(cert)).thenReturn(escapedCert);
		
		certRecordSerializer.serializeRDataFields(mockCERTRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("cert", escapedCert);
	}	
}

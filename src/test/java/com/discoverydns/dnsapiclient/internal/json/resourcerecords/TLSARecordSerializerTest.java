package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.TLSARecord;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class TLSARecordSerializerTest {
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	
	private TLSARecordSerializer tlsaRecordSerializer;
	
	private TLSARecord tlsaRecord;
	private int certificateUsage = 2;
	private int selector = 3;
	private int matchingType = 4;
	private String certificateAssociationData = "3FF4FFF1FF02FCEF3F1AFDC41C9F0FEF";

    @Before
	public void setup() throws Throwable {
		//Can't use mock as getCertificateAssociationData() is final
		tlsaRecord = new TLSARecord(Name.fromString("name.com."),
				1, 2, certificateUsage, selector,
				matchingType, certificateAssociationData);
		
		tlsaRecordSerializer = new TLSARecordSerializer();
	}
	
	@Test
	public void shouldGenerateFormattedCertificateUsageField() throws Exception {
        String formattedCertificateUsage = "formattedCertificateUsage";
		tlsaRecordSerializer = spy(tlsaRecordSerializer);
        when(tlsaRecordSerializer.formatNumber(certificateUsage)).thenReturn(formattedCertificateUsage);
		
		tlsaRecordSerializer.serializeRDataFields(tlsaRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("certificateUsage", formattedCertificateUsage);
	}
	
	@Test
	public void shouldGenerateFormattedSelectorField() throws Exception {
        String formattedSelector = "formattedSelector";
		tlsaRecordSerializer = spy(tlsaRecordSerializer);
        when(tlsaRecordSerializer.formatNumber(selector)).thenReturn(formattedSelector);
		
		tlsaRecordSerializer.serializeRDataFields(tlsaRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("selector", formattedSelector);
	}
	
	@Test
	public void shouldGenerateFormattedMatchingTypeField() throws Exception {
        String formattedMatchingType = "formattedMatchingType";
		tlsaRecordSerializer = spy(tlsaRecordSerializer);
        when(tlsaRecordSerializer.formatNumber(matchingType)).thenReturn(formattedMatchingType);
		
		tlsaRecordSerializer.serializeRDataFields(tlsaRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("matchingType", formattedMatchingType);
	}
	
	@Test
	public void shouldGenerateCertificateAssociationDataField() throws Exception {
        String escapedCertificateAssociationData = "escapedCertificateAssociationData";
		tlsaRecordSerializer = spy(tlsaRecordSerializer);
        when(tlsaRecordSerializer.escapeSpecialCharacters(certificateAssociationData))
					.thenReturn(escapedCertificateAssociationData);
		
		tlsaRecordSerializer.serializeRDataFields(tlsaRecord, mockJsonGenerator,
				mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStringField("certificateAssociationData",
                escapedCertificateAssociationData);
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonGenerationException;
import com.discoverydns.dnsapiclient.test.infrastructure.DNSAPIClientJsonGenerationExceptionMatcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@RunWith(MockitoJUnitRunner.class)
public class AbstractRecordSerializerTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private AbstractRecordSerializer<Record> abstractRecordSerializer;

	@Mock private Record mockRecord;
	@Mock private JsonGenerator mockJsonGenerator;
	@Mock private SerializerProvider mockSerializerProvider;
	@Mock private Name mockName;
	
	private String name = "name";
	private Long ttl = 1L;
    private Integer type = 2;

    @Before
	public void setup() throws Throwable {
		when(mockRecord.getName()).thenReturn(mockName);
		when(mockName.toString()).thenReturn(name);
		when(mockRecord.getTTL()).thenReturn(ttl);
		when(mockRecord.getType()).thenReturn(type);
		when(mockRecord.getDClass()).thenReturn(DClass.IN);
		
		abstractRecordSerializer = new AbstractRecordSerializer<Record>(Record.class){
			@Override
			protected void serializeRDataFields(Record value,
					JsonGenerator jgen, SerializerProvider provider)
					throws IOException, JsonGenerationException {
			}
		};
	}
	
	@Test
	public void shouldWriteExpectedObject() throws Exception {
		abstractRecordSerializer = spy(abstractRecordSerializer);
        String formattedTTL = "formattedTTL";
        when(abstractRecordSerializer.formatNumber(ttl)).thenReturn(formattedTTL);
		
		abstractRecordSerializer.serialize(mockRecord, mockJsonGenerator, mockSerializerProvider);
		
		verify(mockJsonGenerator).writeStartObject();
		verify(mockJsonGenerator).writeStringField("name", name);
		verify(mockJsonGenerator).writeStringField("class", "IN");
		verify(mockJsonGenerator).writeStringField("ttl", formattedTTL);
		verify(mockJsonGenerator).writeStringField("type", "NS");
		verify(mockJsonGenerator).writeEndObject();
	}
	
	@Test
	public void shouldDelegateToChildClassToWriteRDataFields() throws Exception {
		AbstractRecordSerializer<Record> spyAbstractRecordSerializer = spy(abstractRecordSerializer);
		
		spyAbstractRecordSerializer.serialize(mockRecord, mockJsonGenerator, mockSerializerProvider);
		
		verify(spyAbstractRecordSerializer).serializeRDataFields(mockRecord, mockJsonGenerator,
				mockSerializerProvider);
	}

	@Test
	public void shouldRethrowThrownDnsApiJsonGenerationExceptionWhenGeneratingNode() throws Exception {
		final DNSAPIClientJsonGenerationException exception = new DNSAPIClientJsonGenerationException(
                DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedGenerationError,
                new Exception("cause"), "arguments");
		doThrow(exception).when(mockJsonGenerator).writeStartObject();
		
		thrown.expect(new BaseMatcher<Throwable>() {
			@Override
			public boolean matches(Object item) {
				return exception.equals(item);
			}

			@Override
			public void describeTo(Description description) {}
		});
		
		abstractRecordSerializer.serialize(mockRecord, mockJsonGenerator, mockSerializerProvider);
	}
	
	@Test
	public void shouldWrapThrownExceptionIfNotDnsApiJsonGenerationExceptionWhenGeneratingNode() throws Exception {
		IOException exception = new IOException("message");
		doThrow(exception).when(mockJsonGenerator).writeStartObject();
		
		thrown.expect(new DNSAPIClientJsonGenerationExceptionMatcher(
                DNSAPIClientJsonGenerationException.DNSAPIClientJsonGenerationExceptionCode.unexpectedGenerationError,
                exception, new Object[] {Type.string(type), name, exception.getMessage()}));
		
		abstractRecordSerializer.serialize(mockRecord, mockJsonGenerator, mockSerializerProvider);
	}

    @Test
    public void shouldEscapeCharacterStringIfNecessary() throws Exception {
        String stringWithoutSpace = "string1";
        String stringWithSpace = "string 2";

        assertEquals(stringWithoutSpace,
                abstractRecordSerializer.escapeCharacterString(stringWithoutSpace));
        assertEquals("\"" + stringWithSpace + "\"",
                abstractRecordSerializer.escapeCharacterString(stringWithSpace));
    }
}

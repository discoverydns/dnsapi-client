package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class AbstractRecordDeserializerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private Record mockRecord;
	@Mock
	private JsonParser mockJsonParser;
	@Mock
	private ObjectMapper mockObjectMapper;
	@Mock
	private ObjectReader mockObjectReader;
	@Mock
	private DeserializationContext mockDeserializationContext;
	@Mock
	private JsonNode mockNameJsonNode;
	@Mock
	private JsonNode mockClassJsonNode;
	@Mock
	private JsonNode mockTTLJsonNode;
	@Mock
	private Name mockName;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private Number mockNumber;

	private ObjectNode fakeObjectNode;
	private Integer ttl = 2;
	private String textualRecordType = "textualRecordType";

	private AbstractRecordDeserializer<Record> abstractRecordDeserializer;

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockJsonParser.getCodec()).thenReturn(mockObjectMapper);
		when(mockObjectMapper.reader()).thenReturn(mockObjectReader);
		when(mockObjectReader.without(DeserializationFeature.UNWRAP_ROOT_VALUE))
				.thenReturn(mockObjectReader);
		when(mockObjectReader.readTree(mockJsonParser)).thenReturn(
				fakeObjectNode);

		when(mockNameJsonNode.textValue()).thenReturn("name");
		fakeObjectNode.put("name", mockNameJsonNode);

		when(mockClassJsonNode.textValue()).thenReturn("IN");
		fakeObjectNode.put("class", mockClassJsonNode);

		when(mockTTLJsonNode.textValue()).thenReturn(Integer.toString(ttl));
		fakeObjectNode.put("ttl", mockTTLJsonNode);

		abstractRecordDeserializer = new AbstractRecordDeserializer<Record>(
				Record.class) {
			private static final long serialVersionUID = -9180572573127241556L;

			@Override
			protected Record createRecord(Name name, int dclass, long ttl,
					ObjectNode recordNode) {
				return mockRecord;
			}

			@Override
			protected String getTextualRecordType() {
				return textualRecordType;
			}
		};
	}

	@Test
	public void shouldReturnExpectedTextualBeanType() throws Exception {
		AbstractRecordDeserializer<Record> spyAbstractRecordDeserializer = spy(abstractRecordDeserializer);
		when(spyAbstractRecordDeserializer.getTextualRecordType()).thenReturn(
				textualRecordType);

		assertEquals(textualRecordType + " record",
				spyAbstractRecordDeserializer.getTextualBeanType());
	}

	@Test
	public void shouldDelegateToChildClassToCreateRecord() throws Exception {
		AbstractRecordDeserializer<Record> spyAbstractRecordDeserializer = spy(abstractRecordDeserializer);
		when(spyAbstractRecordDeserializer.getRecordName(fakeObjectNode))
				.thenReturn(mockName);

		spyAbstractRecordDeserializer.deserialize(mockJsonParser,
				mockDeserializationContext);

		verify(spyAbstractRecordDeserializer).createRecord(mockName, DClass.IN,
				ttl, fakeObjectNode);
	}

	@Test
	public void shouldRethrowThrownDnsApiJsonMappingExceptionWhenCreatingNode()
			throws Exception {
		final DNSAPIClientJsonMappingException exception = new DNSAPIClientJsonMappingException(
				DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
				new Exception("cause"), "arguments");
		AbstractRecordDeserializer<Record> spyAbstractRecordDeserializer = spy(abstractRecordDeserializer);
		when(spyAbstractRecordDeserializer.getRecordName(fakeObjectNode))
				.thenReturn(mockName);
		when(
				spyAbstractRecordDeserializer.createRecord(mockName, DClass.IN,
						ttl, fakeObjectNode)).thenThrow(exception);

		thrown.expect(new BaseMatcher<Throwable>() {
			@Override
			public boolean matches(Object item) {
				return exception.equals(item);
			}

			@Override
			public void describeTo(Description description) {
			}
		});

		spyAbstractRecordDeserializer.deserialize(mockJsonParser,
				mockDeserializationContext);
	}

	@Test
	public void shouldINRecordClass() throws Exception {
		assertEquals(DClass.IN,
				abstractRecordDeserializer.getDClass(fakeObjectNode));
	}

	@Test
	public void shouldReturnRecordTTLValue() throws Exception {
		assertEquals(ttl.intValue(),
				abstractRecordDeserializer.getRecordTTL(fakeObjectNode));
	}
}

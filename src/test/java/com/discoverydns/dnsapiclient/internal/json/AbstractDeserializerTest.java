package com.discoverydns.dnsapiclient.internal.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.discoverydns.dnsapiclient.test.infrastructure.DNSAPIClientJsonMappingExceptionMatcher;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDeserializerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockJsonNode;
	@Mock
	private JsonNode mockNameJsonNode;
	@Mock
	private JsonNode mockAddressJsonNode;
    @Mock
    private JsonNode mockLocalDateTimeJsonNode;
    @Mock
    private JsonNode mockURIJsonNode;
	@Mock
	private JsonObject mockJsonObject;
	@Mock
	private Number mockNumber;
	@Mock
	private InetAddress mockInetAddress;

	private static class JsonObject {

	}

	private AbstractDeserializer<JsonObject> abstractDeserializer;

	private ObjectNode fakeObjectNode;
	private String fieldName = "fieldName";
	private String nodeTextValue = "nodeTextValue";
    private String fieldAddress = "address";
	private String address = "1.2.3.4";
	private String textualBeanType = "textualBeanType";
    private String fieldLocalDateTime = "localDateTime";
    private LocalDateTime localDateTime = LocalDateTime.now(DateTimeZone.UTC);

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockJsonNode.textValue()).thenReturn(nodeTextValue);
		fakeObjectNode.set(fieldName, mockJsonNode);

        String name = "name";
        when(mockNameJsonNode.textValue()).thenReturn(name);
        String nameFieldName = "nameFieldName";
        fakeObjectNode.set(nameFieldName, mockNameJsonNode);

		fakeObjectNode.set(fieldAddress, mockAddressJsonNode);
		when(mockAddressJsonNode.textValue()).thenReturn(address);

        fakeObjectNode.set(fieldLocalDateTime, mockLocalDateTimeJsonNode);
        when(mockLocalDateTimeJsonNode.textValue()).thenReturn(localDateTime.toString());

		abstractDeserializer = new AbstractDeserializer<AbstractDeserializerTest.JsonObject>(
				AbstractDeserializerTest.JsonObject.class) {
			private static final long serialVersionUID = -400379380279488958L;

			@Override
			public JsonObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
				return mockJsonObject;
			}

			@Override
			protected String getTextualBeanType() {
				return textualBeanType;
			}
		};
	}

    @Test
	public void shouldThrowExceptionIfNodeFoundWhenSearchingForFieldNode()
			throws Exception {
		fakeObjectNode.set(fieldName, null);

		thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.missingField,
				new Object[] {fieldName, textualBeanType}));

		abstractDeserializer.findFieldNode(fakeObjectNode, fieldName);
	}

	@Test
	public void shouldReturnNodeIfFoundWhenSearchingForFieldNode()
			throws Exception {
		assertEquals(mockJsonNode, abstractDeserializer.findFieldNode(fakeObjectNode, fieldName));
	}

	@Test
	public void shouldReturnNodeStringValue() throws Exception {
		assertEquals(nodeTextValue, abstractDeserializer.getNodeStringValue(fakeObjectNode, fieldName));

		verify(mockJsonNode).textValue();
	}

	@Test
	public void shouldThrowExceptionForInvalidNumberValueWhenGettingTextualNodeNumberValue()
			throws Exception {
        when(mockJsonNode.getNodeType()).thenReturn(JsonNodeType.STRING);

		thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                ParseException.class,
                new Object[] {fieldName, textualBeanType, "Unparseable number: \"" + nodeTextValue + "\""}));

		abstractDeserializer.getNodeNumberValue(fakeObjectNode, fieldName);
	}

	@Test
	public void shouldCreateAndReturnNumberValueIfFieldIsFoundWhenGettingTextualNodeNumberValue()
			throws Exception {
		Long numberValue = 3L;
		when(mockJsonNode.textValue()).thenReturn(numberValue.toString());
        when(mockJsonNode.getNodeType()).thenReturn(JsonNodeType.STRING);

		assertEquals(numberValue.longValue(),
                abstractDeserializer.getNodeNumberValue(fakeObjectNode, fieldName).longValue());
	}

    @Test
    public void shouldReturnNumberValueForFoundNumberNode() {
        Long numberValue = 3L;
        when(mockJsonNode.numberValue()).thenReturn(numberValue);
        when(mockJsonNode.getNodeType()).thenReturn(JsonNodeType.NUMBER);

        assertEquals(numberValue.longValue(),
                abstractDeserializer.getNodeNumberValue(fakeObjectNode, fieldName).longValue());
    }

    @Test
    public void shouldThrowExceptionForOtherTypesWhenGettingNodeNumberValue() {
        when(mockJsonNode.getNodeType()).thenReturn(JsonNodeType.BINARY);

        thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                new Object[] {fieldName, textualBeanType, "Field cannot be read as a number"}));

        abstractDeserializer.getNodeNumberValue(fakeObjectNode, fieldName);
    }

	@Test
	public void shouldCreateAndReturnLongValueIfFieldIsFoundWhenGettingLongValue()
			throws Exception {
		Long longValue = 3L;
		abstractDeserializer = spy(abstractDeserializer);
		when(mockJsonNode.textValue()).thenReturn(longValue.toString());
		doReturn(mockNumber).when(abstractDeserializer).getNodeNumberValue(fakeObjectNode, fieldName);
		when(mockNumber.longValue()).thenReturn(longValue);

		assertEquals(longValue, abstractDeserializer.getNodeLongValue(fakeObjectNode, fieldName));
	}

	@Test
	public void shouldCreateAndReturnIntegerValueIfFieldIsFoundWhenGettingIntegerValue()
			throws Exception {
		Integer integerValue = 3;
		abstractDeserializer = spy(abstractDeserializer);
		when(mockJsonNode.textValue()).thenReturn(integerValue.toString());
		doReturn(mockNumber).when(abstractDeserializer).getNodeNumberValue(fakeObjectNode, fieldName);
		when(mockNumber.intValue()).thenReturn(integerValue);

		assertEquals(integerValue, abstractDeserializer.getNodeIntegerValue(fakeObjectNode, fieldName));
	}

	@Test
	public void shouldCreateAndReturnDoubleValueIfFieldIsFoundWhenGettingDoubleValue()
			throws Exception {
		Double doubleValue = (double) 3;
		abstractDeserializer = spy(abstractDeserializer);
		when(mockJsonNode.textValue()).thenReturn(doubleValue.toString());
		doReturn(mockNumber).when(abstractDeserializer).getNodeNumberValue(fakeObjectNode, fieldName);
		when(mockNumber.doubleValue()).thenReturn(doubleValue);

		assertEquals(doubleValue, abstractDeserializer.getNodeDoubleValue(fakeObjectNode, fieldName));
	}

	@Test
	public void shouldThrowExceptionForInvalidAddressWhenGettingAddressValue() throws Exception {
		abstractDeserializer = spy(abstractDeserializer);

		String rootErrorMessage = "rootErrorMessage";
        final IllegalArgumentException illegalArgumentException = new IllegalArgumentException(rootErrorMessage);
        when(abstractDeserializer.getAddressFromString(address)).thenThrow(illegalArgumentException);

		thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                illegalArgumentException,
				new Object[] {fieldAddress, textualBeanType, rootErrorMessage}));

		abstractDeserializer.getNodeAddressValue(fakeObjectNode, fieldAddress);
	}

	@Test
	public void shouldCreateAndReturnAddressIfFieldIsFoundWhenGettingAddressValue() throws Exception {
		abstractDeserializer = spy(abstractDeserializer);
		when(abstractDeserializer.getAddressFromString(address)).thenReturn(mockInetAddress);

		assertEquals(mockInetAddress,
				abstractDeserializer.getNodeAddressValue(fakeObjectNode, fieldAddress));
	}

    @Test
    public void shouldThrowExceptionForInvalidLocalDateTimeWhenGettingLocalDateTimeValue() throws Exception {
        when(mockLocalDateTimeJsonNode.textValue()).thenReturn("invalidDateTime");

        thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                IllegalArgumentException.class,
                new Object[] {fieldLocalDateTime, textualBeanType, "Invalid format: \"invalidDateTime\""}));

        abstractDeserializer.getNodeLocalDateTimeValue(fakeObjectNode, fieldLocalDateTime);
    }

    @Test
    public void shouldCreateAndReturnLocalDateTimeIfFieldIsFoundWhenGettingLocalDateTimeValue() throws Exception {
        assertEquals(localDateTime, abstractDeserializer.getNodeLocalDateTimeValue(fakeObjectNode, fieldLocalDateTime));
    }

    @Test
    public void shouldThrowExceptionForInvalidLocalDateTimeWhenGettingOptionalLocalDateTimeValue()
            throws Exception {
        when(mockLocalDateTimeJsonNode.textValue()).thenReturn("invalidDateTime");

        thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                IllegalArgumentException.class,
                new Object[] {fieldLocalDateTime, textualBeanType, "Invalid format: \"invalidDateTime\""}));

        abstractDeserializer.getOptionalNodeLocalDateTimeValue(fakeObjectNode, fieldLocalDateTime);
    }

    @Test
    public void shouldReturnNullIfFieldIsNullWhenGettingOptionalLocalDateTimeValue() throws Exception {
        when(mockLocalDateTimeJsonNode.textValue()).thenReturn(null);

        assertNull(abstractDeserializer.getOptionalNodeLocalDateTimeValue(fakeObjectNode, fieldLocalDateTime));
    }

    @Test
    public void shouldCreateAndReturnLocalDateTimeIfFieldIsFoundWhenGettingOptionalLocalDateTimeValue()
            throws Exception {
        assertEquals(localDateTime,
                abstractDeserializer.getOptionalNodeLocalDateTimeValue(fakeObjectNode, fieldLocalDateTime));
    }
}

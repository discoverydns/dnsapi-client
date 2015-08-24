package com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.InetAddress;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.test.infrastructure.DNSAPIClientJsonMappingExceptionMatcher;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class NameServerInterfaceDeserializerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private JsonParser mockJsonParser;
	@Mock
	private ObjectMapper mockObjectMapper;
	@Mock
	private ObjectReader mockObjectReader;
	@Mock
	private DeserializationContext mockDeserializationContext;
	@Mock
	private JsonNode mockOrderJsonNode;
    @Mock
	private JsonNode mockNameJsonNode;
	@Mock
	private JsonNode mockIPv4AddressJsonNode;
	@Mock
	private JsonNode mockIPv6AddressJsonNode;
	@Mock
	private InetAddress mockInetAddress;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;

	private ObjectNode fakeObjectNode;
	private NameServerInterfaceDeserializer nameServerInterfaceDeserializer;
    private String fieldIPv4Address = "ipv4Address";
    private Integer order = 3;
	private String name = "name";
	private String ipv4Address = "1.2.3.4";
	private String ipv6Address = "1:2:3:4:5:6:7:8";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		when(mockJsonParser.getCodec()).thenReturn(mockObjectMapper);
		when(mockObjectMapper.reader()).thenReturn(mockObjectReader);
		when(mockObjectReader.without(DeserializationFeature.UNWRAP_ROOT_VALUE)).thenReturn(mockObjectReader);
		when(mockObjectReader.readTree(mockJsonParser)).thenReturn(fakeObjectNode);

		when(mockOrderJsonNode.intValue()).thenReturn(order);
        fakeObjectNode.set("order", mockOrderJsonNode);

        when(mockNameJsonNode.textValue()).thenReturn(name);
        fakeObjectNode.set("name", mockNameJsonNode);

		when(mockIPv4AddressJsonNode.textValue()).thenReturn(ipv4Address);
		fakeObjectNode.set(fieldIPv4Address, mockIPv4AddressJsonNode);

		when(mockIPv6AddressJsonNode.textValue()).thenReturn(ipv6Address);
        fakeObjectNode.set("ipv6Address", mockIPv6AddressJsonNode);

		nameServerInterfaceDeserializer = new NameServerInterfaceDeserializer();
	}

	@Test
	public void shouldRethrowThrownDnsApiJsonMappingExceptionWhenCreatingNode()
			throws Exception {
		final DNSAPIClientJsonMappingException exception = new DNSAPIClientJsonMappingException(
                DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.invalidFieldValue,
                new Exception("cause"), "arguments");
		nameServerInterfaceDeserializer = spy(nameServerInterfaceDeserializer);
		when(nameServerInterfaceDeserializer.getNodeAddressValue(fakeObjectNode, fieldIPv4Address))
				.thenThrow(exception);

		thrown.expect(new BaseMatcher<Throwable>() {
			@Override
			public boolean matches(Object item) {
				return exception.equals(item);
			}

			@Override
			public void describeTo(Description description) {
			}
		});

		nameServerInterfaceDeserializer.deserialize(mockJsonParser, mockDeserializationContext);
	}

	@Test
	public void shouldWrapThrownExceptionIfNotDNSAPIClientJsonMappingExceptionWhenCreatingNode() throws Exception {
		nameServerInterfaceDeserializer = spy(nameServerInterfaceDeserializer);
        IOException exception = new IOException("message");
		when(mockObjectReader.readTree(mockJsonParser)).thenThrow(exception);
        String textualBeanType = "textualBeanType";
        when(nameServerInterfaceDeserializer.getTextualBeanType()).thenReturn(textualBeanType);

		thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode.unexpectedMappingError,
                exception, new Object[] {textualBeanType, exception.getMessage()}));

		nameServerInterfaceDeserializer.deserialize(mockJsonParser, mockDeserializationContext);
	}

	@Test
	public void shouldReturnExpectedNameServerInterface() throws Exception {
		NameServerInterface nameServerInterface = nameServerInterfaceDeserializer
				.deserialize(mockJsonParser, mockDeserializationContext);

		assertEquals(order.intValue(), nameServerInterface.getOrder());
		assertEquals(name, nameServerInterface.getName());
		assertEquals(ipv4Address, nameServerInterface.getIpv4Address().getHostAddress());
		assertEquals(ipv6Address, nameServerInterface.getIpv6Address().getHostAddress());
	}

	@Test
	public void shouldReturnExpectedTextualBeanType() throws Exception {
		assertEquals("nameServer Interface", nameServerInterfaceDeserializer.getTextualBeanType());
	}
}

package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class AbstractAddressRecordDeserializerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private Record mockRecord;
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockJsonNode;
	@Mock
	private InetAddress mockInetAddress;

	private AbstractAddressRecordDeserializer<Record> abstractAddressRecordDeserializer;

	private ObjectNode fakeObjectNode;
	private String textualRecordType = "textualRecordType";
	private String address = "1.2.3.4";

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

		fakeObjectNode.put("address", mockJsonNode);
		when(mockJsonNode.textValue()).thenReturn(address);

		abstractAddressRecordDeserializer = new AbstractAddressRecordDeserializer<Record>(
				Record.class) {
			private static final long serialVersionUID = 1519465044854151854L;

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
	public void shouldCreateAndReturnAddressIfFieldIsFoundWhenGettingAddressValue()
			throws Exception {
		AbstractAddressRecordDeserializer<Record> spyAbstractAddressRecordDeserializer = spy(abstractAddressRecordDeserializer);
		when(spyAbstractAddressRecordDeserializer.getAddressFromString(address))
				.thenReturn(mockInetAddress);

		assertEquals(mockInetAddress,
				spyAbstractAddressRecordDeserializer.getNodeAddressValue(fakeObjectNode));
		
		verify(spyAbstractAddressRecordDeserializer).getNodeAddressValue(fakeObjectNode, "address");
	}
}

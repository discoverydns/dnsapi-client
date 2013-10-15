package com.discoverydns.dnsapiclient.internal.json.resourcerecords;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xbill.DNS.Name;
import org.xbill.DNS.SOARecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(MockitoJUnitRunner.class)
public class SOARecordDeserializerTest {
	@Mock
	private JsonNodeFactory mockJsonNodeFactory;
	@Mock
	private JsonNode mockHostJsonNode;
	@Mock
	private JsonNode mockAdminJsonNode;
	@Mock
	private JsonNode mockSerialJsonNode;
	@Mock
	private JsonNode mockRefreshJsonNode;
	@Mock
	private JsonNode mockRetryJsonNode;
	@Mock
	private JsonNode mockExpireJsonNode;
	@Mock
	private JsonNode mockMinimumJsonNode;

	private SOARecordDeserializer soaRecordDeserializer;

	private ObjectNode fakeObjectNode;
    private Name hostName;
    private Name adminName;
    private long serial = 2L;
	private long refresh = 3L;
	private long retry = 4L;
	private long expire = 5L;
	private long minimum = 6L;

	@Before
	public void setup() throws Throwable {
		fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

        String host = "host.domain.com.";
        hostName = Name.fromString(host);
		when(mockHostJsonNode.textValue()).thenReturn(host);
		fakeObjectNode.put("host", mockHostJsonNode);

        String admin = "admin.domain.com.";
        adminName = Name.fromString(admin);
		when(mockAdminJsonNode.textValue()).thenReturn(admin);
		fakeObjectNode.put("admin", mockAdminJsonNode);

		when(mockSerialJsonNode.textValue()).thenReturn(String.valueOf(serial));
		fakeObjectNode.put("serial", mockSerialJsonNode);

		when(mockRefreshJsonNode.textValue()).thenReturn(
				String.valueOf(refresh));
		fakeObjectNode.put("refresh", mockRefreshJsonNode);

		when(mockRetryJsonNode.textValue()).thenReturn(String.valueOf(retry));
		fakeObjectNode.put("retry", mockRetryJsonNode);

		when(mockExpireJsonNode.textValue()).thenReturn(String.valueOf(expire));
		fakeObjectNode.put("expire", mockExpireJsonNode);

		when(mockMinimumJsonNode.textValue()).thenReturn(
				String.valueOf(minimum));
		fakeObjectNode.put("minimum", mockMinimumJsonNode);

		soaRecordDeserializer = new SOARecordDeserializer();
	}

	@Test
	public void shouldReturnTheExpectedTextualRecordType() throws Exception {
		assertEquals("SOA", soaRecordDeserializer.getTextualRecordType());
	}

	@Test
	public void shouldCreateExpectedRecord() throws Exception {
        Name name = Name.fromString("test.domain.com.");
        int dclass = 1;
        long ttl = 3600L;
        SOARecord soaRecord = soaRecordDeserializer.createRecord(name, dclass,
                ttl, fakeObjectNode);

		assertEquals(name, soaRecord.getName());
		assertEquals(dclass, soaRecord.getDClass());
		assertEquals(ttl, soaRecord.getTTL());
		assertEquals(hostName, soaRecord.getHost());
		assertEquals(adminName, soaRecord.getAdmin());
		assertEquals(serial, soaRecord.getSerial());
		assertEquals(refresh, soaRecord.getRefresh());
		assertEquals(retry, soaRecord.getRetry());
		assertEquals(expire, soaRecord.getExpire());
		assertEquals(minimum, soaRecord.getMinimum());
	}
}

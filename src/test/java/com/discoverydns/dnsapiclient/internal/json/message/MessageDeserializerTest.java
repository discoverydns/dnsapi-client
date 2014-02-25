package com.discoverydns.dnsapiclient.internal.json.message;

import com.discoverydns.dnsapiclient.command.message.Message;
import com.discoverydns.dnsapiclient.command.message.MessageType;
import com.discoverydns.dnsapiclient.command.message.contents.MessageContents;
import com.discoverydns.dnsapiclient.command.message.contents.ZoneDNSSECSigningCompletedMessageContents;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.discoverydns.dnsapiclient.test.infrastructure.DNSAPIClientJsonMappingExceptionMatcher;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageDeserializerTest {
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
    private JsonNodeFactory mockJsonNodeFactory;
    @Mock
    private JsonNode mockURIJsonNode;
    @Mock
    private JsonNode mockIdJsonNode;
    @Mock
    private JsonNode mockTypeJsonNode;
    @Mock
    private JsonNode mockRelatedObjectIdJsonNode;
    @Mock
    private JsonNode mockSponsorAccountIdJsonNode;
    @Mock
    private JsonNode mockCreateDateJsonNode;
    @Mock
    private JsonNode mockAcknowledgedDateJsonNode;
    @Mock
    private JsonNode mockAcknowledgedByUserIdJsonNode;
    @Mock
    private JsonNode mockMessageJsonNode;
    @Mock
    private JsonNode mockMessageContentsJsonNode;
    @Mock
    private MessageContents mockMessageContents;

    private MessageDeserializer messageDeserializer;

    private String id = "id";
    private MessageType type = MessageType.zoneDNSSECSigningCompleted;
    private String relatedObjectId = "relatedObjectId";
    private String sponsorAccountId = "sponsorAccountId";
    private LocalDateTime createDate = LocalDateTime.now(DateTimeZone.UTC);
    private LocalDateTime acknowledgedDate = LocalDateTime.now(DateTimeZone.UTC);
    private String acknowledgedByUserId = "acknowledgedByUserId";
    private String message = "message";
    private Class<ZoneDNSSECSigningCompletedMessageContents> messageContentsClass = ZoneDNSSECSigningCompletedMessageContents.class;

    @Before
    public void setup() throws Throwable {
        ObjectNode fakeObjectNode = new ObjectNode(mockJsonNodeFactory);

        when(mockJsonParser.getCodec()).thenReturn(mockObjectMapper);
        when(mockObjectMapper.reader()).thenReturn(mockObjectReader);
        when(mockObjectReader.without(DeserializationFeature.UNWRAP_ROOT_VALUE)).thenReturn(mockObjectReader);
        when(mockObjectReader.readTree(mockJsonParser)).thenReturn(
                fakeObjectNode);

        when(mockIdJsonNode.textValue()).thenReturn(id);
        fakeObjectNode.put("id", mockIdJsonNode);

        when(mockTypeJsonNode.textValue()).thenReturn(type.name());
        fakeObjectNode.put("messageType", mockTypeJsonNode);

        when(mockRelatedObjectIdJsonNode.textValue()).thenReturn(relatedObjectId);
        fakeObjectNode.put("relatedObjectId", mockRelatedObjectIdJsonNode);

        when(mockSponsorAccountIdJsonNode.textValue()).thenReturn(sponsorAccountId);
        fakeObjectNode.put("sponsorAccountId", mockSponsorAccountIdJsonNode);

        when(mockCreateDateJsonNode.textValue()).thenReturn(createDate.toString());
        fakeObjectNode.put("createDate", mockCreateDateJsonNode);

        when(mockAcknowledgedDateJsonNode.textValue()).thenReturn(acknowledgedDate.toString());
        fakeObjectNode.put("acknowledgedDate", mockAcknowledgedDateJsonNode);

        when(mockAcknowledgedByUserIdJsonNode.textValue()).thenReturn(acknowledgedByUserId);
        fakeObjectNode.put("acknowledgedByUserId", mockAcknowledgedByUserIdJsonNode);

        when(mockMessageJsonNode.textValue()).thenReturn(message);
        fakeObjectNode.put("message", mockMessageJsonNode);

        String messageContents = "messageContents";
        when(mockMessageContentsJsonNode.toString()).thenReturn(messageContents);
        fakeObjectNode.put("messageContents", mockMessageContentsJsonNode);
        when(mockObjectReader.withType(messageContentsClass)).thenReturn(mockObjectReader);
        when(mockObjectReader.readValue(messageContents)).thenReturn(mockMessageContents);

        messageDeserializer = new MessageDeserializer();
        messageDeserializer.registerRecordType(type, messageContentsClass);
    }

    @Test
    public void shouldReturnNullMessageIfTypeIsNull() throws Exception {
        when(mockTypeJsonNode.textValue()).thenReturn(null);

        assertNull(messageDeserializer.deserialize(mockJsonParser, mockDeserializationContext));
    }

    @Test
    public void shouldThrowExceptionIfTypeIsUnknown() throws Exception {
        String type = "unknown";
        when(mockTypeJsonNode.textValue()).thenReturn(type);

        thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.unknownMessageType,
                new Object[] { type }));

        messageDeserializer.deserialize(mockJsonParser, mockDeserializationContext);
    }

    @Test
    public void shouldThrowExceptionIfTypeIsUnhandled() throws Exception {
        MessageType unhandledType = MessageType.zoneDNSSECSigningFailed;
        when(mockTypeJsonNode.textValue()).thenReturn(unhandledType.name());

        thrown.expect(new DNSAPIClientJsonMappingExceptionMatcher(
                DNSAPIClientJsonMappingExceptionCode.unhandledMessageType,
                new Object[] { unhandledType }));

        messageDeserializer.deserialize(mockJsonParser, mockDeserializationContext);
    }

    @Test
    public void shouldReturnExpectedMessageOtherwise() throws Exception {
        Message deserializedMessage = messageDeserializer.deserialize(mockJsonParser, mockDeserializationContext);

        assertEquals(id, deserializedMessage.getId());
        assertEquals(type, deserializedMessage.getMessageType());
        assertEquals(relatedObjectId, deserializedMessage.getRelatedObjectId());
        assertEquals(sponsorAccountId, deserializedMessage.getSponsorAccountId());
        assertEquals(createDate, deserializedMessage.getCreateDate());
        assertEquals(acknowledgedDate, deserializedMessage.getAcknowledgedDate());
        assertEquals(acknowledgedByUserId, deserializedMessage.getAcknowledgedByUserId());
        assertEquals(message, deserializedMessage.getMessage());
        assertEquals(mockMessageContents, deserializedMessage.getMessageContents());
    }
}

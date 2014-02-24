package com.discoverydns.dnsapiclient.internal.json.message;

import com.discoverydns.dnsapiclient.command.message.Message;
import com.discoverydns.dnsapiclient.command.message.MessageType;
import com.discoverydns.dnsapiclient.command.message.contents.MessageContents;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientJsonMappingException.DNSAPIClientJsonMappingExceptionCode;
import com.discoverydns.dnsapiclient.internal.json.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageDeserializer extends AbstractDeserializer<Message> {
    public static final String TYPE_FIELD_NAME = "messageType";
    private static final long serialVersionUID = 2243078829501110326L;

    private final Map<MessageType, Class<? extends MessageContents>> messageContentsClassesRegistry =
            new HashMap<MessageType, Class<? extends MessageContents>>();

    public MessageDeserializer() {
        super(Message.class);
    }

    public void registerRecordType(final MessageType messageType,
                                   final Class<? extends MessageContents> messageContentsClass) {
        messageContentsClassesRegistry.put(messageType, messageContentsClass);
    }

    @Override
    protected String getTextualBeanType() {
        return "message";
    }

    @Override
    public Message deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        final ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        final ObjectReader reader = mapper.reader().without(
                DeserializationFeature.UNWRAP_ROOT_VALUE);
        final ObjectNode recordNode = reader.readTree(jsonParser);

        String type = getNodeStringValue(recordNode, TYPE_FIELD_NAME);
        if (type == null) {
            return null;
        }
        MessageType messageType;
        try {
            messageType = MessageType.valueOf(type);
        } catch (IllegalArgumentException ex) {
            throw new DNSAPIClientJsonMappingException(
                    DNSAPIClientJsonMappingExceptionCode.unknownMessageType,
                    type);
        }

        Class<? extends MessageContents> messageContentsClass = messageContentsClassesRegistry.get(messageType);
        if (messageContentsClass == null) {
            throw new DNSAPIClientJsonMappingException(
                    DNSAPIClientJsonMappingExceptionCode.unhandledMessageType,
                    messageType);
        }

        return new Message(
                getNodeURIValue(recordNode, "@uri"),
                getNodeStringValue(recordNode, "id"),
                messageType,
                getNodeStringValue(recordNode, "relatedObjectId"),
                getNodeStringValue(recordNode, "sponsorAccountId"),
                getNodeLocalDateTimeValue(recordNode, "createDate"),
                getNodeLocalDateTimeValue(recordNode, "acknowledgedDate"),
                getNodeStringValue(recordNode, "message"),
                (MessageContents) reader.withType(messageContentsClass).readValue(
                        getNodeStringValue(recordNode, "messageContents")));
    }
}

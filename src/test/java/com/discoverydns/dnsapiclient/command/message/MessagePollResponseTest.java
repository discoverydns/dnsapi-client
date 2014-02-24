package com.discoverydns.dnsapiclient.command.message;

import com.discoverydns.dnsapiclient.command.message.contents.MessageContents;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessagePollResponseTest {
    @Test
    public void shouldReturnExpectedResponse() throws Exception {
        Message mockMessage = mock(Message.class);
        URI uri = new URI("http://fake.com");
        when(mockMessage.getURI()).thenReturn(uri);
        String id = "id";
        when(mockMessage.getId()).thenReturn(id);
        MessageType messageType = MessageType.zoneDNSSECSigningCompleted;
        when(mockMessage.getMessageType()).thenReturn(messageType);
        String relatedObjectId = "relatedObjectId";
        when(mockMessage.getRelatedObjectId()).thenReturn(relatedObjectId);
        String sponsorAccountId = "sponsorAccountId";
        when(mockMessage.getSponsorAccountId()).thenReturn(sponsorAccountId);
        LocalDateTime createDate = LocalDateTime.now(DateTimeZone.UTC);
        when(mockMessage.getCreateDate()).thenReturn(createDate);
        LocalDateTime acknowledgedDate = LocalDateTime.now(DateTimeZone.UTC);
        when(mockMessage.getAcknowledgedDate()).thenReturn(acknowledgedDate);
        String message = "message";
        when(mockMessage.getMessage()).thenReturn(message);
        MessageContents mockMessageContents = mock(MessageContents.class);
        when(mockMessage.getMessageContents()).thenReturn(mockMessageContents);

        MessagePollResponse messagePollResponse = new MessagePollResponse(mockMessage);

        assertEquals(uri, messagePollResponse.getMessage().getURI());
        assertEquals(id, messagePollResponse.getMessage().getId());
        assertEquals(messageType, messagePollResponse.getMessage().getMessageType());
        assertEquals(relatedObjectId, messagePollResponse.getMessage().getRelatedObjectId());
        assertEquals(sponsorAccountId, messagePollResponse.getMessage().getSponsorAccountId());
        assertEquals(createDate, messagePollResponse.getMessage().getCreateDate());
        assertEquals(acknowledgedDate, messagePollResponse.getMessage().getAcknowledgedDate());
        assertEquals(message, messagePollResponse.getMessage().getMessage());
        assertEquals(mockMessageContents, messagePollResponse.getMessage().getMessageContents());
    }
}

package com.discoverydns.dnsapiclient.command.message;

import com.discoverydns.dnsapiclient.internal.views.MessagePollView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessagePollResponseTest {
    @Test
    public void shouldReturnExpectedResponse() throws Exception {
        Integer outstandingMessagesCount = 3;
        Message mockMessage = mock(Message.class);
        MessagePollView mockMessagePollView = mock(MessagePollView.class);
        when(mockMessagePollView.getMessage()).thenReturn(mockMessage);
        when(mockMessagePollView.getOutstandingMessagesCount()).thenReturn(outstandingMessagesCount);

        MessagePollResponse messagePollResponse = new MessagePollResponse(mockMessagePollView);

        assertEquals(outstandingMessagesCount, messagePollResponse.getOutstandingMessagesCount());
        assertEquals(mockMessage, messagePollResponse.getMessage());
    }
}

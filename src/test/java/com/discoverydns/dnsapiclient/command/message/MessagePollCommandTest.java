package com.discoverydns.dnsapiclient.command.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class MessagePollCommandTest {
    @Test
    public void shouldReturnTheExpectedCommand() {
        MessageType searchMessageType = MessageType.zoneDNSSECSigningCompleted;
        String searchRelatedObjectId = "searchRelatedObjectId";

        MessagePollCommand messagePollCommand =
                new MessagePollCommand.Builder()
                        .withSearchMessageType(searchMessageType)
                        .withSearchRelatedObjectId(searchRelatedObjectId)
                        .build();

        assertEquals(searchMessageType.name(), messagePollCommand.getSearchMessageType());
        assertEquals(searchRelatedObjectId, messagePollCommand.getSearchRelatedObjectId());
    }

    @Test
    public void shouldReturnANewCommandEveryTime() {
        MessagePollCommand.Builder builder = new MessagePollCommand.Builder();
        assertNotSame(builder.build(), builder.build());
    }
}

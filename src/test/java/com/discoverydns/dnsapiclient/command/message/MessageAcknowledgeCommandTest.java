package com.discoverydns.dnsapiclient.command.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class MessageAcknowledgeCommandTest {
    @Test
    public void shouldReturnTheExpectedCommand() {
        String id = "id";

        MessageAcknowledgeCommand messageAcknowledgeCommand =
                new MessageAcknowledgeCommand.Builder()
                        .withId(id)
                        .build();

        assertEquals(id, messageAcknowledgeCommand.getId());
    }

    @Test
    public void shouldReturnANewCommandEveryTime() {
        MessageAcknowledgeCommand.Builder builder = new MessageAcknowledgeCommand.Builder();
        assertNotSame(builder.build(), builder.build());
    }
}

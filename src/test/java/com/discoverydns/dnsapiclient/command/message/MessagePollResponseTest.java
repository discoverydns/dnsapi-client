package com.discoverydns.dnsapiclient.command.message;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.discoverydns.dnsapiclient.internal.views.MessagePollView;

public class MessagePollResponseTest {
	@Test
	public void shouldReturnExpectedResponse() throws Exception {
		Long outstandingMessagesCount = 3L;
		MessageRecord mockMessage = mock(MessageRecord.class);
		MessagePollView mockMessagePollView = mock(MessagePollView.class);
		when(mockMessagePollView.getMessageRecord()).thenReturn(mockMessage);
		when(mockMessagePollView.getOutstandingMessageCount()).thenReturn(
				outstandingMessagesCount);

		MessagePollResponse messagePollResponse = new MessagePollResponse(
				mockMessagePollView);

		assertEquals(outstandingMessagesCount,
				messagePollResponse.getOutstandingMessageCount());
		assertEquals(mockMessage, messagePollResponse.getMessageRecord());
	}
}

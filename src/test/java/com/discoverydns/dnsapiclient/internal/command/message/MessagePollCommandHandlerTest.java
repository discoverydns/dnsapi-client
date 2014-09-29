package com.discoverydns.dnsapiclient.internal.command.message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.message.MessagePollCommand;
import com.discoverydns.dnsapiclient.command.message.MessagePollResponse;
import com.discoverydns.dnsapiclient.command.message.MessageRecord;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.MessagePollView;

@RunWith(MockitoJUnitRunner.class)
public class MessagePollCommandHandlerTest {
	@Mock
	private WebTarget mockBaseWebTarget;
	@Mock
	private WebTarget mockMessagePollTarget;
	@Mock
	private MessagePollCommand mockMessagePollCommand;
	@Mock
	private CommandMetaData mockCommandMetaData;

	private MessagePollCommandHandler messagePollCommandHandler;

	@Before
	public void setup() throws Throwable {
		when(mockBaseWebTarget.path("messages/poll")).thenReturn(
				mockMessagePollTarget);

		messagePollCommandHandler = new MessagePollCommandHandler(
				mockBaseWebTarget);
	}

	@Test
	public void shouldInitialiseAccountGetTarget() {
		verify(mockBaseWebTarget).path("messages/poll");
	}

	@Test
	public void shouldNotAddAnyQueryParamsOtherwise() {
		messagePollCommandHandler.getWebTarget(mockMessagePollCommand,
				mockCommandMetaData);

		verifyZeroInteractions(mockMessagePollTarget);
	}

	@Test
	public void shouldListTargetRelativeToProvidedCommand() {
		assertEquals(mockMessagePollTarget,
				messagePollCommandHandler.getWebTarget(mockMessagePollCommand,
						mockCommandMetaData));
	}

	@Test
	public void shouldReturnNoEntityInvocationBuilderFactory() {
		assertThat(messagePollCommandHandler.getInvocationBuilderFactory(
				mockMessagePollCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuilderFactory.class));
	}

	@Test
	public void shouldReturnNoEntityInvocationBuildInvoker() {
		assertThat(messagePollCommandHandler.getInvocationBuildInvoker(
				mockMessagePollCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuildInvoker.class));
	}

	@Test
	public void shouldReturnExpectedResponse() throws Throwable {
		String id = "id";
		Long outstandingMessagesCount = 3L;
		Response mockRestResponse = mock(Response.class);
		when(mockRestResponse.hasEntity()).thenReturn(true);
		MessageRecord mockMessage = mock(MessageRecord.class);
		when(mockMessage.getId()).thenReturn(id);
		MessagePollView mockMessagePollView = mock(MessagePollView.class);
		when(mockMessagePollView.getMessageRecord()).thenReturn(mockMessage);
		when(mockMessagePollView.getOutstandingMessageCount()).thenReturn(
				outstandingMessagesCount);
		when(mockRestResponse.readEntity(MessagePollView.class)).thenReturn(
				mockMessagePollView);

		MessagePollResponse messagePollResponse = messagePollCommandHandler
				.getCommandResponse(mockRestResponse, mockCommandMetaData);

		assertEquals(id, messagePollResponse.getMessageRecord().getId());
		assertEquals(outstandingMessagesCount,
				messagePollResponse.getOutstandingMessageCount());
	}
}

package com.discoverydns.dnsapiclient.internal.command.message;

import com.discoverydns.dnsapiclient.command.message.Message;
import com.discoverydns.dnsapiclient.command.message.MessagePollCommand;
import com.discoverydns.dnsapiclient.command.message.MessagePollResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        when(mockBaseWebTarget.path("messages/")).thenReturn(mockMessagePollTarget);

        messagePollCommandHandler = new MessagePollCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseAccountGetTarget() {
        verify(mockBaseWebTarget).path("messages/");
    }

    @Test
    public void shouldAddSearchMessageTypeAsQueryParamIfNotNull() {
        String searchMessageType = "searchMessageType";
        when(mockMessagePollCommand.getSearchMessageType()).thenReturn(searchMessageType);

        messagePollCommandHandler.getWebTarget(
                mockMessagePollCommand, mockCommandMetaData);

        verify(mockMessagePollTarget).queryParam("searchMessageType", searchMessageType);
    }

    @Test
    public void shouldAddSearchRelatedObjectIdAsQueryParamIfNotNull() {
        String searchRelatedObjectId = "searchRelatedObjectId";
        when(mockMessagePollCommand.getSearchRelatedObjectId())
                .thenReturn(searchRelatedObjectId);

        messagePollCommandHandler.getWebTarget(
                mockMessagePollCommand, mockCommandMetaData);

        verify(mockMessagePollTarget).queryParam("searchRelatedObjectId",
                searchRelatedObjectId);
    }

    @Test
    public void shouldNotAddQueryParamsOtherwise() {
        messagePollCommandHandler.getWebTarget(
                mockMessagePollCommand, mockCommandMetaData);

        verifyZeroInteractions(mockMessagePollTarget);
    }

    @Test
    public void shouldListTargetRelativeToProvidedCommand() {
        assertEquals(mockMessagePollTarget,
                messagePollCommandHandler.getWebTarget(
                        mockMessagePollCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                messagePollCommandHandler.getInvocationBuilderFactory(
                        mockMessagePollCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                messagePollCommandHandler.getInvocationBuildInvoker(
                        mockMessagePollCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedResponse() throws Throwable {
        Response mockRestResponse = mock(Response.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        Message mockMessage = mock(Message.class);
        String id = "id";
        when(mockMessage.getId()).thenReturn(id);
        when(mockRestResponse.readEntity(Message.class)).thenReturn(mockMessage);

        MessagePollResponse messagePollResponse =
                messagePollCommandHandler.getCommandResponse(mockRestResponse, mockCommandMetaData);

        assertEquals(id, messagePollResponse.getMessage().getId());
    }
}

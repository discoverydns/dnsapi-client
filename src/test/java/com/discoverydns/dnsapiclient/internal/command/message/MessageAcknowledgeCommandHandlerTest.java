package com.discoverydns.dnsapiclient.internal.command.message;

import com.discoverydns.dnsapiclient.command.message.MessageAcknowledgeCommand;
import com.discoverydns.dnsapiclient.command.message.MessageAcknowledgeResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageAcknowledgeCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockMessageAcknowledgeTarget;
    @Mock
    private MessageAcknowledgeCommand mockMessageAcknowledgeCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private MessageAcknowledgeCommandHandler messageAcknowledgeCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("messages/{messageId}/acknowledge")).thenReturn(mockMessageAcknowledgeTarget);

        messageAcknowledgeCommandHandler = new MessageAcknowledgeCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseAccountGetTarget() {
        verify(mockBaseWebTarget).path("messages/{messageId}/acknowledge");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockMessageAcknowledgeCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockMessageAcknowledgeTarget.resolveTemplate("messageId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                messageAcknowledgeCommandHandler.getWebTarget(mockMessageAcknowledgeCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockMessageAcknowledgeCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockMessageAcknowledgeTarget.resolveTemplate("messageId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        messageAcknowledgeCommandHandler.getWebTarget(mockMessageAcknowledgeCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        assertThat(
                messageAcknowledgeCommandHandler.getInvocationBuilderFactory(
                        mockMessageAcknowledgeCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnWithEntityInvocationBuildInvoker() {
        assertThat(
                messageAcknowledgeCommandHandler.getInvocationBuildInvoker(
                        mockMessageAcknowledgeCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedResponse() throws Throwable {
        Response mockRestResponse = mock(Response.class);

        assertThat(
                messageAcknowledgeCommandHandler.getCommandResponse(mockRestResponse, mockCommandMetaData),
                instanceOf(MessageAcknowledgeResponse.class));
    }
}

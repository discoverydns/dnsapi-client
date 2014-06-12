package com.discoverydns.dnsapiclient.internal.command.message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.message.MessageGetCommand;
import com.discoverydns.dnsapiclient.command.message.MessageGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.MessageGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class MessageGetCommandHandlerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private WebTarget mockBaseWebTarget;
	@Mock
	private WebTarget mockMessageGetTarget;
	@Mock
	private MessageGetCommand mockMessageGetCommand;
	@Mock
	private CommandMetaData mockCommandMetaData;

	private MessageGetCommandHandler messageGetCommandHandler;

	@Before
	public void setup() throws Throwable {
		when(mockBaseWebTarget.path("messages/{messageId}")).thenReturn(
				mockMessageGetTarget);

		messageGetCommandHandler = new MessageGetCommandHandler(
				mockBaseWebTarget);
	}

	@Test
	public void shouldInitialiseNameServerSetGetTarget() {
		verify(mockBaseWebTarget).path("messages/{messageId}");
	}

	@Test
	public void shouldGetTargetRelativeToProvidedCommand() {
		String id = "<message-id>";
		when(mockMessageGetCommand.getId()).thenReturn(id);
		WebTarget resultingWebTarget = mock(WebTarget.class);
		when(mockMessageGetTarget.resolveTemplate("messageId", id)).thenReturn(
				resultingWebTarget);

		assertEquals(resultingWebTarget, messageGetCommandHandler.getWebTarget(
				mockMessageGetCommand, mockCommandMetaData));
	}

	@Test
	public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
		String id = "<message-id>";
		when(mockMessageGetCommand.getId()).thenReturn(id);
		RuntimeException mockRuntimeException = mock(RuntimeException.class);
		when(mockMessageGetTarget.resolveTemplate("messageId", id)).thenThrow(
				mockRuntimeException);

		thrown.expect(new BaseExceptionMatcher(
				DNSAPIClientException.class,
				DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
				new Object[] { "id" }, mockRuntimeException));

		messageGetCommandHandler.getWebTarget(mockMessageGetCommand,
				mockCommandMetaData);
	}

	@Test
	public void shouldReturnNoEntityInvocationBuilderFactory() {
		assertThat(messageGetCommandHandler.getInvocationBuilderFactory(
				mockMessageGetCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuilderFactory.class));
	}

	@Test
	public void shouldReturnNoEntityInvocationBuildInvoker() {
		assertThat(messageGetCommandHandler.getInvocationBuildInvoker(
				mockMessageGetCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuildInvoker.class));
	}

	@Test
	public void shouldReturnExpectedMessageGetResponse() {
		Response mockRestResponse = mock(Response.class);
		MessageGetView mockMessageGetView = mock(MessageGetView.class);
		when(mockRestResponse.hasEntity()).thenReturn(true);
		when(mockRestResponse.readEntity(MessageGetView.class)).thenReturn(
				mockMessageGetView);
		String id = "id";
		when(mockMessageGetView.getId()).thenReturn(id);

		MessageGetResponse commandResponse = messageGetCommandHandler
				.getCommandResponse(mockRestResponse, mockCommandMetaData);

		assertEquals(id, commandResponse.getId());
	}
}

package com.discoverydns.dnsapiclient.internal.command.nameserverset;

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

import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetGetCommand;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.NameServerSetGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class NameServerSetGetCommandHandlerTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private WebTarget mockBaseWebTarget;
	@Mock
	private WebTarget mockNameServerSetGetTarget;
	@Mock
	private NameServerSetGetCommand mockNameServerSetGetCommand;
	@Mock
	private CommandMetaData mockCommandMetaData;

	private NameServerSetGetCommandHandler nameServerSetGetCommandHandler;

	@Before
	public void setup() throws Throwable {
		when(mockBaseWebTarget.path("nameserversets/{nameServerSetId}"))
				.thenReturn(mockNameServerSetGetTarget);

		nameServerSetGetCommandHandler = new NameServerSetGetCommandHandler(
				mockBaseWebTarget);
	}

	@Test
	public void shouldInitialiseNameServerSetGetTarget() {
		verify(mockBaseWebTarget).path("nameserversets/{nameServerSetId}");
	}

	@Test
	public void shouldGetTargetRelativeToProvidedCommand() {
		String idOrName = "idOrName";
		when(mockNameServerSetGetCommand.getIdOrName()).thenReturn(idOrName);
		WebTarget resultingWebTarget = mock(WebTarget.class);
		when(
				mockNameServerSetGetTarget.resolveTemplate("nameServerSetId",
						idOrName)).thenReturn(resultingWebTarget);

		assertEquals(resultingWebTarget,
				nameServerSetGetCommandHandler.getWebTarget(
						mockNameServerSetGetCommand, mockCommandMetaData));
	}

	@Test
	public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
		String idOrName = "idOrName";
		when(mockNameServerSetGetCommand.getIdOrName()).thenReturn(idOrName);
		RuntimeException mockRuntimeException = mock(RuntimeException.class);
		when(
				mockNameServerSetGetTarget.resolveTemplate("nameServerSetId",
						idOrName)).thenThrow(mockRuntimeException);

		thrown.expect(new BaseExceptionMatcher(
				DNSAPIClientException.class,
				DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
				new Object[] { "idOrName" }, mockRuntimeException));

		nameServerSetGetCommandHandler.getWebTarget(
				mockNameServerSetGetCommand, mockCommandMetaData);
	}

	@Test
	public void shouldReturnNoEntityInvocationBuilderFactory() {
		assertThat(nameServerSetGetCommandHandler.getInvocationBuilderFactory(
				mockNameServerSetGetCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuilderFactory.class));
	}

	@Test
	public void shouldReturnNoEntityInvocationBuildInvoker() {
		assertThat(nameServerSetGetCommandHandler.getInvocationBuildInvoker(
				mockNameServerSetGetCommand, mockCommandMetaData),
				instanceOf(NoEntityInvocationBuildInvoker.class));
	}

	@Test
	public void shouldReturnExpectedNameServerSetGetResponse() {
		Response mockRestResponse = mock(Response.class);
		NameServerSetGetView mockNameServerSetGetView = mock(NameServerSetGetView.class);
		when(mockRestResponse.hasEntity()).thenReturn(true);
		when(mockRestResponse.readEntity(NameServerSetGetView.class))
				.thenReturn(mockNameServerSetGetView);
		String id = "id";
		when(mockNameServerSetGetView.getId()).thenReturn(id);

		NameServerSetGetResponse commandResponse = nameServerSetGetCommandHandler
				.getCommandResponse(mockRestResponse, mockCommandMetaData);

		assertEquals(id, commandResponse.getId());
	}

}

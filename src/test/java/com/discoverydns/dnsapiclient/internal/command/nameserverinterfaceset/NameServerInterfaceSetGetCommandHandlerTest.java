package com.discoverydns.dnsapiclient.internal.command.nameserverinterfaceset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetCommand;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterfaceSetGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.NameServerInterfaceSetGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NameServerInterfaceSetGetCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockNameServerInterfaceSetGetTarget;
    @Mock
    private NameServerInterfaceSetGetCommand mockNameServerInterfaceSetGetCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private NameServerInterfaceSetGetCommandHandler nameServerInterfaceSetGetCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("nameserverinterfacesets/{nameServerInterfaceSetId}"))
                .thenReturn(mockNameServerInterfaceSetGetTarget);

        nameServerInterfaceSetGetCommandHandler =
                new NameServerInterfaceSetGetCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseNameServerInterfaceSetGetTarget() {
        verify(mockBaseWebTarget).path("nameserverinterfacesets/{nameServerInterfaceSetId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String idOrName = "idOrName";
        when(mockNameServerInterfaceSetGetCommand.getIdOrName()).thenReturn(idOrName);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockNameServerInterfaceSetGetTarget.resolveTemplate("nameServerInterfaceSetId", idOrName))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                nameServerInterfaceSetGetCommandHandler.getWebTarget(
                        mockNameServerInterfaceSetGetCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String idOrName = "idOrName";
        when(mockNameServerInterfaceSetGetCommand.getIdOrName()).thenReturn(idOrName);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockNameServerInterfaceSetGetTarget.resolveTemplate("nameServerInterfaceSetId", idOrName))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"idOrName"}, mockRuntimeException));

        nameServerInterfaceSetGetCommandHandler.getWebTarget(
                mockNameServerInterfaceSetGetCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                nameServerInterfaceSetGetCommandHandler.getInvocationBuilderFactory(
                        mockNameServerInterfaceSetGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                nameServerInterfaceSetGetCommandHandler.getInvocationBuildInvoker(
                        mockNameServerInterfaceSetGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedNameServerInterfaceSetGetResponse() {
        Response mockRestResponse = mock(Response.class);
        NameServerInterfaceSetGetView mockNameServerInterfaceSetGetView
                = mock(NameServerInterfaceSetGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(NameServerInterfaceSetGetView.class))
                .thenReturn(mockNameServerInterfaceSetGetView);
        String id = "id";
        when(mockNameServerInterfaceSetGetView.getId()).thenReturn(id);

        NameServerInterfaceSetGetResponse commandResponse =
                nameServerInterfaceSetGetCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(id, commandResponse.getId());
    }
}

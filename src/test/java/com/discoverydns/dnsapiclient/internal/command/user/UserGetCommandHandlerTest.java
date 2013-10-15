package com.discoverydns.dnsapiclient.internal.command.user;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.user.UserGetCommand;
import com.discoverydns.dnsapiclient.command.user.UserGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.UserGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserGetCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockUserGetTarget;
    @Mock
    private UserGetCommand mockUserGetCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private UserGetCommandHandler userGetCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("users/{userId}")).thenReturn(mockUserGetTarget);

        userGetCommandHandler = new UserGetCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseUserGetTarget() {
        verify(mockBaseWebTarget).path("users/{userId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String idOrUsername = "idOrUsername";
        when(mockUserGetCommand.getIdOrUsername()).thenReturn(idOrUsername);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockUserGetTarget.resolveTemplate("userId", idOrUsername))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                userGetCommandHandler.getWebTarget(mockUserGetCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String idOrUsername = "idOrUsername";
        when(mockUserGetCommand.getIdOrUsername()).thenReturn(idOrUsername);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockUserGetTarget.resolveTemplate("userId", idOrUsername))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"idOrUsername"}, mockRuntimeException));

        userGetCommandHandler.getWebTarget(mockUserGetCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                userGetCommandHandler.getInvocationBuilderFactory(
                        mockUserGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                userGetCommandHandler.getInvocationBuildInvoker(
                        mockUserGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedUserGetResponse() {
        Response mockRestResponse = mock(Response.class);
        UserGetView mockUserGetView = mock(UserGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(UserGetView.class)).thenReturn(mockUserGetView);
        String id = "id";
        when(mockUserGetView.getId()).thenReturn(id);

        UserGetResponse commandResponse =
                userGetCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(id, commandResponse.getId());
    }
}


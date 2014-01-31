package com.discoverydns.dnsapiclient.internal.command.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;
import com.discoverydns.dnsapiclient.command.account.AccountGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.AccountGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountGetCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockAccountGetTarget;
    @Mock
    private AccountGetCommand mockAccountGetCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private AccountGetCommandHandler accountGetCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("accounts/{accountId}")).thenReturn(mockAccountGetTarget);

        accountGetCommandHandler = new AccountGetCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseAccountGetTarget() {
        verify(mockBaseWebTarget).path("accounts/{accountId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String idOrIdentifier = "idOrIdentifier";
        when(mockAccountGetCommand.getIdOrIdentifier()).thenReturn(idOrIdentifier);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockAccountGetTarget.resolveTemplate("accountId", idOrIdentifier))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                accountGetCommandHandler.getWebTarget(mockAccountGetCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String idOrIdentifier = "idOrIdentifier";
        when(mockAccountGetCommand.getIdOrIdentifier()).thenReturn(idOrIdentifier);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockAccountGetTarget.resolveTemplate("accountId", idOrIdentifier))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"idOrIdentifier"}, mockRuntimeException));

        accountGetCommandHandler.getWebTarget(mockAccountGetCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                accountGetCommandHandler.getInvocationBuilderFactory(
                        mockAccountGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                accountGetCommandHandler.getInvocationBuildInvoker(
                        mockAccountGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedAccountGetResponse() {
        Response mockRestResponse = mock(Response.class);
        AccountGetView mockAccountGetView = mock(AccountGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(AccountGetView.class)).thenReturn(mockAccountGetView);
        String id = "id";
        when(mockAccountGetView.getId()).thenReturn(id);

        AccountGetResponse commandResponse =
                accountGetCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(id, commandResponse.getId());
    }
}

package com.discoverydns.dnsapiclient.internal.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.DNSAPIClientCommandMetaData;
import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;
import com.discoverydns.dnsapiclient.command.account.AccountGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientHttpException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.views.AccountGetView;
import com.discoverydns.dnsapiclient.internal.views.ErrorView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import com.discoverydns.dnsapiclient.test.infrastructure.DNSAPIClientHttpExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaseRestCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockWebTarget;
    @Mock
    private InvocationBuildInvoker mockInvocationBuildInvoker;
    @Mock
    private InvocationBuilderFactory mockInvocationBuilderFactory;
    @Mock
    private AccountGetCommand mockCommand;
    @Mock
    private AccountGetResponse mockCommandResponse;
    @Mock
    private MediaType mockExpectedMediaType;
    @Mock
    private Response mockResponse;
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private Invocation.Builder mockBuilder;
    @Mock
    private Invocation mockInvocation;

    private final Method method = Method.GET;
    private String clientTransactionId = "clientTransactionId";
    private String serverTransactionId = "serverTransactionId";

    private BaseRestCommandHandler<AccountGetCommand, AccountGetResponse> baseRestCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockInvocationBuilderFactory.buildInvocationBuilder(mockWebTarget))
                .thenReturn(mockBuilder);
        when(mockCommandMetaData.get(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID))
                .thenReturn(clientTransactionId);
        when(mockBuilder.header(
                DNSAPIClientHeaders.CLIENT_TRANSACTION_ID, clientTransactionId))
                .thenReturn(mockBuilder);
        when(mockBuilder.accept(mockExpectedMediaType)).thenReturn(mockBuilder);
        when(mockInvocationBuildInvoker.invoke(mockBuilder, method)).thenReturn(mockInvocation);
        when(mockInvocation.invoke()).thenReturn(mockResponse);
        when(mockResponse.getHeaderString(DNSAPIClientHeaders.SERVER_TRANSACTION_ID))
                .thenReturn(serverTransactionId);
        int expectedStatusCode = 200;
        when(mockResponse.getStatus()).thenReturn(expectedStatusCode);

        baseRestCommandHandler = new BaseRestCommandHandler<AccountGetCommand, AccountGetResponse>(
                method, expectedStatusCode, mockExpectedMediaType) {
            @Override
            public WebTarget getWebTarget(AccountGetCommand command, CommandMetaData commandMetaData) {
                return mockWebTarget;
            }

            @Override
            public InvocationBuildInvoker getInvocationBuildInvoker(
                    AccountGetCommand command, CommandMetaData commandMetaData) {
                return mockInvocationBuildInvoker;
            }

            @Override
            public InvocationBuilderFactory getInvocationBuilderFactory(
                    AccountGetCommand command, CommandMetaData commandMetaData) {
                return mockInvocationBuilderFactory;
            }

            @Override
            public AccountGetResponse getCommandResponse(Response restResponse,
                                             CommandMetaData commandMetaData) {
                return mockCommandResponse;
            }
        };
    }

    @Test
    public void shouldThrowExceptionIfResponseDoesntHaveAnEntity() {
        when(mockResponse.hasEntity()).thenReturn(false);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.expectedEntity,
                new Object[] {}));

        baseRestCommandHandler.getResponseEntity(AccountGetView.class, mockResponse);
    }

    @Test
    public void shouldReturnResponseEntity() {
        when(mockResponse.hasEntity()).thenReturn(true);
        AccountGetView mockAccountGetView = mock(AccountGetView.class);
        when(mockResponse.readEntity(AccountGetView.class))
                .thenReturn(mockAccountGetView);

        assertEquals(mockAccountGetView,
                baseRestCommandHandler.getResponseEntity(AccountGetView.class, mockResponse));
    }

    @Test
    public void shouldSetClientTransactionIdWhenExecutingCommand() throws Throwable {
        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);

        verify(mockBuilder).header(DNSAPIClientHeaders.CLIENT_TRANSACTION_ID, clientTransactionId);
    }

    @Test
    public void shouldAcceptGivenMediaType() throws Throwable {
        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);

        verify(mockBuilder).accept(mockExpectedMediaType);
    }

    @Test
    public void shouldInvokeRequestToServer() throws Throwable {
        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);

        verify(mockInvocation).invoke();
    }

    @Test
    public void shouldSetServerTransactionIdOnCommandMetaData() throws Throwable {
        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);

        verify(mockCommandMetaData).put(DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID,
                serverTransactionId);
    }

    @Test
    public void shouldCloseRestResponse() throws Throwable {
        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);

        verify(mockResponse).close();
    }

    @Test
    public void shouldReturnExpectedCommandResponse() throws Throwable {
        assertEquals(mockCommandResponse,
                baseRestCommandHandler.execute(mockCommand, mockCommandMetaData));
    }

    @Test
    public void shouldThrowExceptionWithEntityMessageIfResponseIsErrorWithEntity() throws Throwable {
        int errorStatusCode = 500;
        when(mockResponse.getStatus()).thenReturn(errorStatusCode);
        when(mockResponse.hasEntity()).thenReturn(true);
        ErrorView mockErrorView = mock(ErrorView.class);
        when(mockResponse.readEntity(ErrorView.class))
                .thenReturn(mockErrorView);
        String errorMessage = "errorMessage";
        when(mockErrorView.getMessage()).thenReturn(errorMessage);

        thrown.expect(new DNSAPIClientHttpExceptionMatcher(
                DNSAPIClientHttpException.DNSAPIClientHttpExceptionCode.serverError,
                errorStatusCode,
                new Object[]{errorStatusCode, errorMessage}));

        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);
    }

    @Test
    public void shouldThrowExceptionWithResponseStatusMessageIfResponseIsErrorWithoutEntity() throws Throwable {
        int errorStatusCode = 500;
        when(mockResponse.getStatus()).thenReturn(errorStatusCode);
        when(mockResponse.hasEntity()).thenReturn(false);
        String errorMessage = "errorMessage";
        Response.StatusType mockStatusInfo = mock(Response.StatusType.class);
        when(mockResponse.getStatusInfo()).thenReturn(mockStatusInfo);
        when(mockStatusInfo.getReasonPhrase()).thenReturn(errorMessage);

        thrown.expect(new DNSAPIClientHttpExceptionMatcher(
                DNSAPIClientHttpException.DNSAPIClientHttpExceptionCode.serverError,
                errorStatusCode,
                new Object[]{errorStatusCode, errorMessage}));

        baseRestCommandHandler.execute(mockCommand, mockCommandMetaData);
    }
}

package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discoverydns.dnsapiclient.ClientTransactionIdStrategy;
import com.discoverydns.dnsapiclient.DNSAPIClientCommandMetaData;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientTransactionIdCommandInterceptorTest {
    @Mock
    private ClientTransactionIdStrategy mockClientTransactionIdStrategy;
    @Mock
    private Object mockCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private CommandInterceptorChain mockCommandInterceptorChain;

    private ClientTransactionIdCommandInterceptor clientTransactionIdCommandInterceptor;

    @Before
    public void setup() throws Throwable {
        clientTransactionIdCommandInterceptor =
                new ClientTransactionIdCommandInterceptor(mockClientTransactionIdStrategy);
    }

    @Test
    public void shouldGenerateClientTransactionIdAndPutItInMetadata() throws Throwable {
        String serverTransactionId = "serverTransactionId";
        when(mockClientTransactionIdStrategy.generateTransactionId())
                .thenReturn(serverTransactionId);

        clientTransactionIdCommandInterceptor.intercept(mockCommand,
                mockCommandMetaData, mockCommandInterceptorChain);

        verify(mockCommandMetaData).put(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID,
                serverTransactionId);
    }

    @Test
    public void shouldProceedTheRestOfTheChain() throws Throwable {
        clientTransactionIdCommandInterceptor.intercept(mockCommand,
                mockCommandMetaData, mockCommandInterceptorChain);

        verify(mockCommandInterceptorChain).proceed();
    }
}

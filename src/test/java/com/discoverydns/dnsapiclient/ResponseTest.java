package com.discoverydns.dnsapiclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import com.discoverydns.dnsapiclient.command.account.AccountGetResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.commandinterceptors.StopwatchCommandInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponseTest {
    @Mock
    private CommandMetaData mockCommandMetaData;
    @Mock
    private AccountGetResponse mockResponseObject;

    private Response<AccountGetResponse> response;

    @Before
    public void setup() {
        response = new Response<AccountGetResponse>(mockCommandMetaData, mockResponseObject);
    }

    @Test
    public void shouldReturnResponseObject() {
        assertEquals(mockResponseObject, response.getResponseObject());
    }

    @Test
    public void shouldReturnServerTransactionId() {
        String serverTransactionId = "serverTransactionId";
        when(mockCommandMetaData.get(
                DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID))
                .thenReturn(serverTransactionId);

        assertEquals(serverTransactionId, response.getServerTransactionId());
    }

    @Test
    public void shouldReturnClientTransactionId() {
        String clientTransactionId = "clientTransactionId";
        when(mockCommandMetaData.get(
                DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID))
                .thenReturn(clientTransactionId);

        assertEquals(clientTransactionId, response.getClientTransactionId());
    }

    @Test
    public void shouldReturnTransactionProcessingTime() {
        Double processingTime = 2D;
        when(mockCommandMetaData.get(
                StopwatchCommandInterceptor.COMMAND_TIME_MS))
                .thenReturn(processingTime);

        assertEquals(processingTime, response.getTransactionProcessingTime());
    }

    @Test
    public void shouldReturnNullValuesIfCommandMetaDataIsNull() {
        response = new Response<AccountGetResponse>(null, mockResponseObject);

        assertEquals(mockResponseObject, response.getResponseObject());
        assertNull(response.getServerTransactionId());
        assertNull(response.getClientTransactionId());
        assertNull(response.getTransactionProcessingTime());
    }
}

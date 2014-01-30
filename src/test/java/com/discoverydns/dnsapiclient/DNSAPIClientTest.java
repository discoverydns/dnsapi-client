package com.discoverydns.dnsapiclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.BlockingCommandExecutor;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class DNSAPIClientTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private Client mockClient;
	@Mock
	private BlockingCommandExecutor mockBlockingCommandExecutor;
	@Mock
	private Object mockCommand;

	private DNSAPIClient dnsapiClient;

	@Before
	public void setup() {
		dnsapiClient = new DNSAPIClient(mockClient, mockBlockingCommandExecutor);
	}

	@Test
	public void shouldCloseClient() {
		dnsapiClient.close();

		verify(mockClient).close();
	}

	@Test
	public void shouldNotCloseClientIfAlreadyClosed() {
		dnsapiClient.close();
		dnsapiClient.close();

		verify(mockClient, times(1)).close();
	}

	@Test
	public void shouldThrowExceptionWhenProcessingCommandIfClientIsClosed()
			throws Throwable {
		dnsapiClient.close();

		thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
				DNSAPIClientException.DNSAPIClientExceptionCode.clientClosed,
				new Object[] {}));

		dnsapiClient.process(mockCommand);
	}

	@Test
	public void shouldDelegateToBlockingCommandExecutorToProcessCommand()
			throws Throwable {
		dnsapiClient.process(mockCommand);

		verify(mockBlockingCommandExecutor).process(eq(mockCommand),
				any(DNSAPIClientCommandMetaData.class));
	}

	@Test
	public void shouldReturnExpectedResponse() throws Throwable {
		Object mockResponse = mock(Object.class);
		when(
				mockBlockingCommandExecutor.process(eq(mockCommand),
						any(DNSAPIClientCommandMetaData.class))).thenReturn(
				mockResponse);

		Response<Object> response = dnsapiClient.process(mockCommand);
		assertEquals(mockResponse, response.getResponseObject());
	}
}

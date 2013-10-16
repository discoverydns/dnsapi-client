package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.TransactionLogHandler;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

@RunWith(MockitoJUnitRunner.class)
public class TransactionLogCommandInterceptorTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private TransactionLogHandler mockTransactionLogHandler;
	@Mock
	private Object mockCommand;
	@Mock
	private CommandMetaData mockCommandMetaData;
	@Mock
	private CommandInterceptorChain mockCommandInterceptorChain;

	private TransactionLogCommandInterceptor transactionLogCommandInterceptor;

	@Before
	public void setup() throws Throwable {
		transactionLogCommandInterceptor = new TransactionLogCommandInterceptor(
				mockTransactionLogHandler);
	}

	@Test
	public void shouldJustProceedRestOfTheChainIfTransactionLogHandlerIsNull()
			throws Throwable {
		new TransactionLogCommandInterceptor(null).intercept(mockCommand,
				mockCommandMetaData, mockCommandInterceptorChain);

		verify(mockCommandInterceptorChain).proceed();
		verifyNoMoreInteractions(mockCommandInterceptorChain);
		verifyZeroInteractions(mockCommand);
		verifyZeroInteractions(mockCommandMetaData);
	}

	@Test
	public void shouldProceedRestOfTheChain() throws Throwable {
		transactionLogCommandInterceptor.intercept(mockCommand,
				mockCommandMetaData, mockCommandInterceptorChain);

		verify(mockCommandInterceptorChain).proceed();
	}

	@Test
	public void shouldLogCommandSuccess() throws Throwable {
		Object mockResponse = mock(Object.class);
		when(mockCommandInterceptorChain.proceed()).thenReturn(mockResponse);

		transactionLogCommandInterceptor.intercept(mockCommand,
				mockCommandMetaData, mockCommandInterceptorChain);

		verify(mockTransactionLogHandler).logTransaction(mockCommand,
				mockResponse, mockCommandMetaData);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void shouldLogCommandFailure() throws Throwable {
		final RuntimeException mockRuntimeException = mock(RuntimeException.class);
		when(mockCommandInterceptorChain.proceed()).thenThrow(
				mockRuntimeException);

		thrown.expect(new BaseMatcher() {
			@Override
			public boolean matches(Object item) {
				return mockRuntimeException.equals(item);
			}

			@Override
			public void describeTo(Description description) {
			}
		});

		try {
			transactionLogCommandInterceptor.intercept(mockCommand,
					mockCommandMetaData, mockCommandInterceptorChain);
		} catch (Throwable t) {
			verify(mockTransactionLogHandler).logFailedTransaction(mockCommand,
					mockRuntimeException, mockCommandMetaData);
			throw t;
		}
	}
}

package com.discoverydns.dnsapiclient.framework.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.account.AccountGetCommand;

@RunWith(MockitoJUnitRunner.class)
public class CommandInterceptorChainTest {
	@Mock
	private AccountGetCommand mockCommand;
	@Mock
	private CommandMetaData mockCommandMetaData;
	@Mock
	private Iterator<CommandInterceptor> mockInterceptorChainIterator;
	@Mock
	private CommandHandler<AccountGetCommand> mockCommandHandler;
	@Mock
	private CommandInterceptor mockNextCommandInterceptorInChain;

	private CommandInterceptorChain commandInterceptorChain;

	@Before
	public void setup() throws Throwable {
		commandInterceptorChain = new CommandInterceptorChain(mockCommand,
				mockCommandMetaData, mockInterceptorChainIterator,
				mockCommandHandler);
	}

	@Test
	public void shouldDelegateToNextInterceptorIfNotLastInChain()
			throws Throwable {
		when(mockInterceptorChainIterator.hasNext()).thenReturn(true);
		when(mockInterceptorChainIterator.next()).thenReturn(
				mockNextCommandInterceptorInChain);

		commandInterceptorChain.proceed();

		verify(mockNextCommandInterceptorInChain).intercept(mockCommand,
				mockCommandMetaData, commandInterceptorChain);
	}

	@Test
	public void shouldDelegateToCommandHandlerToProcessCommandIfLastInChain()
			throws Throwable {
		when(mockInterceptorChainIterator.hasNext()).thenReturn(false);

		commandInterceptorChain.proceed();

		verify(mockCommandHandler).execute(mockCommand, mockCommandMetaData);
	}
}

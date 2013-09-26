package com.discoverydns.dnsapiclient.framework.command;

import java.util.Iterator;

public class CommandInterceptorChain {
	private final Object command;
	private final CommandMetaData commandMetaData;
	private final Iterator<? extends CommandInterceptor> interceptorChainIterator;
	private final CommandHandler<?> commandHandler;

	public CommandInterceptorChain(
			final Object command,
			final CommandMetaData commandMetaData,
			final Iterator<? extends CommandInterceptor> interceptorChainIterator,
			final CommandHandler<?> commandHandler) {
		this.command = command;
		this.commandMetaData = commandMetaData;
		this.interceptorChainIterator = interceptorChainIterator;
		this.commandHandler = commandHandler;
	}

	public Object proceed() throws Throwable {
		if (interceptorChainIterator.hasNext()) {
			return interceptorChainIterator.next().intercept(command,
					commandMetaData, this);
		} else {
			return callCommandHandler(command, commandMetaData, commandHandler);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object callCommandHandler(final Object command,
			final CommandMetaData commandMetaData,
			final CommandHandler commandHandler) throws Throwable {
		return commandHandler.execute(command, commandMetaData);
	}

}

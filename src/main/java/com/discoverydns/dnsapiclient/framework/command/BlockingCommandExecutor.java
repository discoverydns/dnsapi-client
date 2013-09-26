package com.discoverydns.dnsapiclient.framework.command;

public class BlockingCommandExecutor {

	final CommandProcessor commandProcessor;

	public BlockingCommandExecutor(final CommandProcessor commandProcessor) {
		this.commandProcessor = commandProcessor;
	}

	public Object process(final Object command,
			final CommandMetaData commandMetaData) throws Throwable {

		final CommandFuture commandFuture = commandProcessor.process(command,
				commandMetaData);

		commandFuture.awaitUninterruptibly();

		if (commandFuture.isSuccess()) {
			return commandFuture.getResponse();
		} else {
			throw commandFuture.getCause();
		}
	}

}

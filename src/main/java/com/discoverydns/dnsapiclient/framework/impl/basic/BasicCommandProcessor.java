package com.discoverydns.dnsapiclient.framework.impl.basic;

import static java.lang.String.format;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.discoverydns.dnsapiclient.framework.command.CommandFuture;
import com.discoverydns.dnsapiclient.framework.command.CommandHandler;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptor;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.framework.command.CommandProcessor;

public class BasicCommandProcessor implements CommandProcessor {

	private final ConcurrentMap<Class<?>, CommandHandler<?>> commandHandlers = new ConcurrentHashMap<Class<?>, CommandHandler<?>>();
	private final List<CommandInterceptor> commandInterceptors = new CopyOnWriteArrayList<CommandInterceptor>();

	public BasicCommandProcessor() {
	}

	@Override
	public CommandFuture process(final Object command,
			final CommandMetaData commandMetaData) {

		CommandFuture commandFuture;
		try {
			final Object response = processCommand(command, commandMetaData);
			commandFuture = BasicCommandFuture.createSuccessfulFuture(command,
					commandMetaData, response);
		} catch (final Throwable throwable) {
			commandFuture = BasicCommandFuture.createFailedFuture(command,
					commandMetaData, throwable);
		}

		return commandFuture;
	}

	private Object processCommand(final Object command,
			final CommandMetaData commandMetaData) throws Throwable {

		final CommandHandler<?> commandHandler = findCommandHandler(command);

		final CommandInterceptorChain commandInterceptorChain = new CommandInterceptorChain(
				command, commandMetaData, commandInterceptors.iterator(),
				commandHandler);

		return commandInterceptorChain.proceed();
	}

	private CommandHandler<?> findCommandHandler(final Object command) {
		final CommandHandler<?> commandHandler = commandHandlers.get(command
				.getClass());
		if (commandHandler == null) {
			throw new IllegalArgumentException(format(
					"No command handler for command type [%s]", command
							.getClass().getSimpleName()));
		}

		return commandHandler;
	}

	@Override
	public <C> void subscribe(final Class<C> commandType,
			final CommandHandler<? super C> handler) {
		commandHandlers.put(commandType, handler);
	}

	@Override
	public <C> void unsubscribe(final Class<C> commandType,
			final CommandHandler<? super C> handler) {
		commandHandlers.remove(commandType, handler);
	}

	@Override
	public void addCommandInterceptor(
			final CommandInterceptor commandInterceptor) {
		commandInterceptors.add(commandInterceptor);
	}

	@Override
	public void removeCommandInterceptor(
			final CommandInterceptor commandInterceptor) {
		commandInterceptors.remove(commandInterceptor);
	}

}

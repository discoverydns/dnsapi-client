package com.discoverydns.dnsapiclient.framework.command;

public interface CommandProcessor {
	public CommandFuture process(Object command, CommandMetaData commandMetaData);

	public <C> void subscribe(Class<C> commandType,
			CommandHandler<? super C> handler);

	public <C> void unsubscribe(Class<C> commandType,
			CommandHandler<? super C> handler);

	public void addCommandInterceptor(CommandInterceptor commandInterceptor);

	public void removeCommandInterceptor(CommandInterceptor commandInterceptor);

}

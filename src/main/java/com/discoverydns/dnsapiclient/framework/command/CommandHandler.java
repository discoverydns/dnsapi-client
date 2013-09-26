package com.discoverydns.dnsapiclient.framework.command;

public interface CommandHandler<T> {
	public Object execute(T command, CommandMetaData commandMetaData)
			throws Throwable;
}

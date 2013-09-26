package com.discoverydns.dnsapiclient.framework.command;

public interface CommandInterceptor {

	public Object intercept(Object command, CommandMetaData commandMetaData,
			CommandInterceptorChain commandInterceptorChain) throws Throwable;

}

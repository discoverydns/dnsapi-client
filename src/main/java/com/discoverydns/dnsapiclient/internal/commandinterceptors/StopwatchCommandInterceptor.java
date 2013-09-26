package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import com.discoverydns.dnsapiclient.framework.command.CommandInterceptor;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.util.Stopwatch;

public class StopwatchCommandInterceptor implements CommandInterceptor {

	public final static String COMMAND_TIME_MS = "StopwatchCommandInterceptor.CommandTimeMs";

	@Override
	public Object intercept(final Object command,
			final CommandMetaData commandMetaData,
			final CommandInterceptorChain commandInterceptorChain)
			throws Throwable {
		Throwable exception = null;
		final Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		Object response = null;
		try {
			response = commandInterceptorChain.proceed();
		} catch (final Throwable t) {
			exception = t;
		}
		stopwatch.stop();
		commandMetaData.put(COMMAND_TIME_MS,
				new Double(stopwatch.elapsedTimeMsDecimal()));
		if (exception != null) {
			throw exception;
		}
		return response;
	}

}

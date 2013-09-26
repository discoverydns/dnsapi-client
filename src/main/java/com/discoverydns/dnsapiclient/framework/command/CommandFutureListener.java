package com.discoverydns.dnsapiclient.framework.command;

public interface CommandFutureListener {
	void operationComplete(CommandFuture commandFuture);
}

package com.discoverydns.dnsapiclient.framework.impl.basic;

import com.discoverydns.dnsapiclient.framework.command.CommandFuture;
import com.discoverydns.dnsapiclient.framework.command.CommandFutureListener;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

public class BasicCommandFuture implements CommandFuture {
	private Object response;
	private Object command;
	private CommandMetaData commandMetaData;
	private Throwable cause;
	private boolean success;

	private BasicCommandFuture() {
	}

	public static BasicCommandFuture createSuccessfulFuture(
			final Object command, final CommandMetaData commandMetaData,
			final Object response) {
		final BasicCommandFuture basicCommandFuture = new BasicCommandFuture();
		basicCommandFuture.command = command;
		basicCommandFuture.commandMetaData = commandMetaData;
		basicCommandFuture.response = response;
		basicCommandFuture.cause = null;
		basicCommandFuture.success = true;
		return basicCommandFuture;
	}

	public static BasicCommandFuture createFailedFuture(final Object command,
			final CommandMetaData commandMetaData, final Throwable cause) {
		final BasicCommandFuture basicCommandFuture = new BasicCommandFuture();
		basicCommandFuture.command = command;
		basicCommandFuture.commandMetaData = commandMetaData;
		basicCommandFuture.cause = cause;
		basicCommandFuture.response = null;
		basicCommandFuture.success = false;
		return basicCommandFuture;
	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public boolean isFailure() {
		return !success;
	}

	@Override
	public Object getResponse() {
		return response;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	@Override
	public void addListener(final CommandFutureListener commandFutureListener) {
		commandFutureListener.operationComplete(this);
	}

	@Override
	public void removeListener(final CommandFutureListener commandFutureListener) {
		return;
	}

	@Override
	public void await() throws InterruptedException {
		return;
	}

	@Override
	public void await(final long timeout) throws InterruptedException {
		return;
	}

	@Override
	public void awaitUninterruptibly() {
		return;
	}

	@Override
	public void awaitUninterruptibly(final long timeout) {
		return;
	}

	@Override
	public Object getCommand() {
		return command;
	}

	@Override
	public CommandMetaData getCommandMetaData() {
		return commandMetaData;
	}

}

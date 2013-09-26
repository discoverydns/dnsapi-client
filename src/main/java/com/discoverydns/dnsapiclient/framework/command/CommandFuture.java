package com.discoverydns.dnsapiclient.framework.command;

public interface CommandFuture {
	public boolean isDone();

	public boolean isSuccess();

	public boolean isFailure();

	public Object getResponse();

	public Object getCommand();

	public CommandMetaData getCommandMetaData();

	public Throwable getCause();

	public void addListener(CommandFutureListener commandFutureListener);

	public void removeListener(CommandFutureListener commandFutureListener);

	public void await() throws InterruptedException;

	public void await(long timeout) throws InterruptedException;

	public void awaitUninterruptibly();

	public void awaitUninterruptibly(long timeout);
}

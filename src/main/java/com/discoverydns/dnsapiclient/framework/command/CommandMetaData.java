package com.discoverydns.dnsapiclient.framework.command;

public interface CommandMetaData {
	public Object get(String key);

	public void put(String key, Object value);

	public boolean contains(String key);
}

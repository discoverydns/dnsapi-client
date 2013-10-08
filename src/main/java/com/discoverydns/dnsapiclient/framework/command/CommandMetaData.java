package com.discoverydns.dnsapiclient.framework.command;

/**
 * The meta-data (String-Object pairs) associated with a command/response, filled by the framework.
 * @author Chris Wright
 */
public interface CommandMetaData {
    /**
     * Gets a meta-data Object from its key.
     * @param key The meta-data Object key.
     * @return The corresponding meta-data Object.
     */
	public Object get(String key);

    /**
     * Adds a String-Object pair to the meta-data.
     * @param key The meta-data Object key.
     * @param value The meta-data Object.
     */
	public void put(String key, Object value);

    /**
     * Returns if an Object is present in the meta-data.
     * @param key The meta-data Object key.
     * @return true if the Object is in the meta-data. false otherwise.
     */
	public boolean contains(String key);
}

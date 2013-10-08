package com.discoverydns.dnsapiclient;

import java.util.HashMap;
import java.util.Map;

import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

/**
 * Instance of {@link CommandMetaData}, used by the {@link DNSAPIClient}
 * to exchange additional information with the DNSAPI server.
 * @author Chris Wright
 */
public class DNSAPIClientCommandMetaData implements CommandMetaData {

	final public static String SERVER_TRANSACTION_ID = "serverTransactionId";
	final public static String CLIENT_TRANSACTION_ID = "clientTransactionId";

	final private static int EXPECTED_META_DATA_COUNT = 10;
	final private Map<String, Object> metaData = new HashMap<>(
			EXPECTED_META_DATA_COUNT);

	@Override
	public Object get(final String key) {
		return metaData.get(key);
	}

	@Override
	public void put(final String key, final Object value) {
		metaData.put(key, value);
	}

	@Override
	public boolean contains(final String key) {
		return metaData.containsKey(key);
	}

}

package com.discoverydns.dnsapiclient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The factory used to create {@link ObjectMapper} instances,
 * used by the created {@link DNSAPIClient} instances
 * to serialize and deserialize the JSON data sent to or coming from the DNSAPI server.
 * @author Chris Wright
 * @see com.discoverydns.dnsapiclient.internal.DefaultObjectMapperFactory
 */
public interface ObjectMapperFactory {

    /**
     * Creates an instance of configured {@link ObjectMapper},
     * to serialize and deserialize the JSON data sent to or coming from the DNSAPI server.
     * @return The created {@link ObjectMapper} instance.
     */
	public ObjectMapper createInstance();

}
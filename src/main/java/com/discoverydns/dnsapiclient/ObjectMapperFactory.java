package com.discoverydns.dnsapiclient;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ObjectMapperFactory {

	public ObjectMapper createInstance();

}
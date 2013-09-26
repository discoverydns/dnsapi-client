package com.discoverydns.dnsapiclient.command.nameServerSet;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSet")
public class NameServerSetRecord {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("createDate")
	private String createDate;
	@JsonProperty("lastUpdateDate")
	private String lastUpdateDate;

	public URI getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

}

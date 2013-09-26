package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zone")
public class ZoneRecord {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;
	@JsonProperty("dnssecSigned")
	private Boolean dnssecSigned;
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

	public Boolean getBrandedNameServers() {
		return brandedNameServers;
	}

	public Boolean getDnssecSigned() {
		return dnssecSigned;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

}

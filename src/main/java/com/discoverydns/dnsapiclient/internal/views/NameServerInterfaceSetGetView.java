package com.discoverydns.dnsapiclient.internal.views;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NameServerInterfaceSetGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("nameServerInterfaces")
	private Set<NameServerInterface> nameServerInterfaces;
	@JsonProperty("createDate")
	private LocalDateTime createDate;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createUserName")
	private String createUserName;
	@JsonProperty("createAccountId")
	private String createAccountId;
	@JsonProperty("createAccountIdentifier")
	private String createAccountIdentifier;
	@JsonProperty("lastUpdateDate")
	private LocalDateTime lastUpdateDate;
	@JsonProperty("lastUpdateUserId")
	private String lastUpdateUserId;
	@JsonProperty("lastUpdateUserName")
	private String lastUpdateUserName;
	@JsonProperty("lastUpdateAccountId")
	private String lastUpdateAccountId;
	@JsonProperty("lastUpdateAccountIdentifier")
	private String lastUpdateAccountIdentifier;

	public URI getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Set<NameServerInterface> getNameServerInterfaces() {
		return nameServerInterfaces;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public String getCreateAccountId() {
		return createAccountId;
	}

	public String getCreateAccountIdentifier() {
		return createAccountIdentifier;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public String getLastUpdateAccountId() {
		return lastUpdateAccountId;
	}

	public String getLastUpdateAccountIdentifier() {
		return lastUpdateAccountIdentifier;
	}

}

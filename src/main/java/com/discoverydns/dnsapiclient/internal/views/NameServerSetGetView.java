package com.discoverydns.dnsapiclient.internal.views;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSet")
public class NameServerSetGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("prefix")
	private String prefix;
	@JsonProperty("domainName")
	private String domainName;
	@JsonProperty("nameServerInterfaceSetId")
	private String nameServerInterfaceSetId;
	@JsonProperty("nameServerInterfaceSetName")
	private String nameServerInterfaceSetName;
	@JsonProperty("nameServerInterfaceSetInterfaces")
	private Set<NameServerInterface> nameServerInterfaceSetInterfaces;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("createAccountId")
	private String createAccountId;
	@JsonProperty("createAccountIdentifier")
	private String createAccountIdentifier;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createUserName")
	private String createUserName;
	@JsonProperty("createDate")
	private LocalDateTime createDate;
	@JsonProperty("lastUpdateAccountId")
	private String lastUpdateAccountId;
	@JsonProperty("lastUpdateAccountIdentifier")
	private String lastUpdateAccountIdentifier;
	@JsonProperty("lastUpdateUserId")
	private String lastUpdateUserId;
	@JsonProperty("lastUpdateUserName")
	private String lastUpdateUserName;
	@JsonProperty("lastUpdateDate")
	private LocalDateTime lastUpdateDate;

	public URI getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDomainName() {
		return domainName;
	}

	public String getNameServerInterfaceSetId() {
		return nameServerInterfaceSetId;
	}

	public String getNameServerInterfaceSetName() {
		return nameServerInterfaceSetName;
	}

	public Set<NameServerInterface> getNameServerInterfaceSetInterfaces() {
		return nameServerInterfaceSetInterfaces;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCreateAccountId() {
		return createAccountId;
	}

	public String getCreateAccountIdentifier() {
		return createAccountIdentifier;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getLastUpdateAccountId() {
		return lastUpdateAccountId;
	}

	public String getLastUpdateAccountIdentifier() {
		return lastUpdateAccountIdentifier;
	}

	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

}

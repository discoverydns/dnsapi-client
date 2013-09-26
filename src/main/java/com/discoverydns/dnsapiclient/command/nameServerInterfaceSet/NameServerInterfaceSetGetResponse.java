package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.NameServerInterfaceSetGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerInterfaceSetGetResponse")
@JsonPropertyOrder({ "@uri", "id", "name", "version", "status", "createDate",
		"createAccountId", "createAccountIdentifier", "createUserId",
		"createUserName", "lastUpdateDate", "lastUpdateAccountId",
		"lastUpdateAccountIdentifier", "lastUpdateUserId",
		"lastUpdateUserName", "nameServerInterfaces" })
public class NameServerInterfaceSetGetResponse {

	@JsonIgnore
	private final NameServerInterfaceSetGetView nameServerInterfaceSetGetView;

	public NameServerInterfaceSetGetResponse(
			final NameServerInterfaceSetGetView nameServerInterfaceSetGetView) {
		this.nameServerInterfaceSetGetView = nameServerInterfaceSetGetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerInterfaceSetGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return nameServerInterfaceSetGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return nameServerInterfaceSetGetView.getVersion();
	}

	@JsonProperty("name")
	public String getName() {
		return nameServerInterfaceSetGetView.getName();
	}

	@JsonProperty("status")
	public String getStatus() {
		return nameServerInterfaceSetGetView.getStatus();
	}

	@JsonProperty("nameServerInterfaces")
	public Set<NameServerInterface> getNameServerInterfaces() {
		return nameServerInterfaceSetGetView.getNameServerInterfaces();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return nameServerInterfaceSetGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return nameServerInterfaceSetGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return nameServerInterfaceSetGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return nameServerInterfaceSetGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return nameServerInterfaceSetGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return nameServerInterfaceSetGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return nameServerInterfaceSetGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return nameServerInterfaceSetGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return nameServerInterfaceSetGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return nameServerInterfaceSetGetView.getLastUpdateDate();
	}

}

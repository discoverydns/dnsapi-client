package com.discoverydns.dnsapiclient.command.nameServerSet;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.discoverydns.dnsapiclient.internal.views.NameServerSetGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSetGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "prefix", "domainName",
		"email", "nameServerInterfaceSetId", "nameServerInterfaceSetName",
		"createDate", "createAccountId", "createAccountIdentifier",
		"createUserId", "createUserName", "lastUpdateDate",
		"lastUpdateAccountId", "lastUpdateAccountIdentifier",
		"lastUpdateUserId", "lastUpdateUserName",
		"nameServerInterfaceSetInterfaces" })
public class NameServerSetGetResponse {

	@JsonIgnore
	private final NameServerSetGetView nameServerSetGetView;

	public NameServerSetGetResponse(final NameServerSetGetView nameServerSetView) {
		this.nameServerSetGetView = nameServerSetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerSetGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return nameServerSetGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return nameServerSetGetView.getVersion();
	}

	@JsonProperty("name")
	public String getName() {
		return nameServerSetGetView.getName();
	}

	@JsonProperty("domainName")
	public String getDomainName() {
		return nameServerSetGetView.getDomainName();
	}

	@JsonProperty("email")
	public String getEmail() {
		return nameServerSetGetView.getEmail();
	}

	@JsonProperty("nameServerInterfaceSetId")
	public String getNameServerInterfaceSetId() {
		return nameServerSetGetView.getNameServerInterfaceSetId();
	}

	@JsonProperty("nameServerInterfaceSetInterfaces")
	public Set<NameServerInterface> getNameServerInterfaceSetInterfaces() {
		return nameServerSetGetView.getNameServerInterfaceSetInterfaces();
	}

	@JsonProperty("nameServerInterfaceSetName")
	public String getNameServerInterfaceSetName() {
		return nameServerSetGetView.getNameServerInterfaceSetName();
	}

	@JsonProperty("prefix")
	public String getPrefix() {
		return nameServerSetGetView.getPrefix();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return nameServerSetGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return nameServerSetGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return nameServerSetGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return nameServerSetGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return nameServerSetGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return nameServerSetGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return nameServerSetGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return nameServerSetGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return nameServerSetGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return nameServerSetGetView.getLastUpdateDate();
	}

}

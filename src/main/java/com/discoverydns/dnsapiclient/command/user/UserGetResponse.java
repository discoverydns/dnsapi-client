package com.discoverydns.dnsapiclient.command.user;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.UserGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("userGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "username", "status", "name",
		"email", "passwordExpireDate", "sponsorAccountId",
		"sponsorAccountIdentifier", "createDate", "createAccountId",
		"createAccountIdentifier", "createUserId", "createUserName",
		"lastUpdateDate", "lastUpdateAccountId", "lastUpdateAccountIdentifier",
		"lastUpdateUserId", "lastUpdateUserName", "roles" })
public class UserGetResponse {

	@JsonIgnore
	private final UserGetView userGetView;

	public UserGetResponse(final UserGetView userGetView) {
		this.userGetView = userGetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return userGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return userGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return userGetView.getVersion();
	}

	@JsonProperty("username")
	public String getUsername() {
		return userGetView.getUsername();
	}

	@JsonProperty("status")
	public String getStatus() {
		return userGetView.getStatus();
	}

	@JsonProperty("name")
	public String getName() {
		return userGetView.getName();
	}

	@JsonProperty("email")
	public String getEmail() {
		return userGetView.getEmail();
	}

	@JsonProperty("roles")
	public Set<String> getRoles() {
		return userGetView.getRoles();
	}

	@JsonProperty("passwordExpireDate")
	public LocalDateTime getPasswordExpireDate() {
		return userGetView.getPasswordExpireDate();
	}

	@JsonProperty("sponsorAccountId")
	public String getSponsorAccountId() {
		return userGetView.getSponsorAccountId();
	}

	@JsonProperty("sponsorAccountIdentifier")
	public String getSponsorAccountIdentifier() {
		return userGetView.getSponsorAccountIdentifier();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return userGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return userGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return userGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return userGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return userGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return userGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return userGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return userGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return userGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return userGetView.getLastUpdateDate();
	}

}

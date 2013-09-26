package com.discoverydns.dnsapiclient.internal.views;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public class UserGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("username")
	private String username;
	@JsonProperty("status")
	private String status;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("roles")
	private Set<String> roles;
	@JsonProperty("passwordExpireDate")
	private LocalDateTime passwordExpireDate;
	@JsonProperty("sponsorAccountId")
	private String sponsorAccountId;
	@JsonProperty("sponsorAccountIdentifier")
	private String sponsorAccountIdentifier;
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

	public String getUsername() {
		return username;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public LocalDateTime getPasswordExpireDate() {
		return passwordExpireDate;
	}

	public String getSponsorAccountId() {
		return sponsorAccountId;
	}

	public String getSponsorAccountIdentifier() {
		return sponsorAccountIdentifier;
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

package com.discoverydns.dnsapiclient.command.account;

import java.net.URI;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.AccountGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("AccountGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "identifier", "status",
		"currency", "minimumCommitment", "minimumCommitmentStartDate",
		"createDate", "createAccountId", "createAccountIdentifier",
		"createUserId", "createUserName", "lastUpdateDate",
		"lastUpdateAccountId", "lastUpdateAccountIdentifier",
		"lastUpdateUserId", "lastUpdateUserName" })
public class AccountGetResponse {

	@JsonIgnore
	private final AccountGetView accountGetView;

	public AccountGetResponse(final AccountGetView accountGetView) {
		this.accountGetView = accountGetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return accountGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return accountGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return accountGetView.getVersion();
	}

	@JsonProperty("name")
	public String getName() {
		return accountGetView.getName();
	}

	@JsonProperty("identifier")
	public String getIdentifier() {
		return accountGetView.getIdentifier();
	}

	@JsonProperty("status")
	public String getStatus() {
		return accountGetView.getStatus();
	}

	@JsonProperty("minimumCommitment")
	public Double getMinimumCommitment() {
		return accountGetView.getMinimumCommitment();
	}

	@JsonProperty("currency")
	public String getCurrency() {
		return accountGetView.getCurrency();
	}

	@JsonProperty("minimumCommitmentStartDate")
	public LocalDateTime getMinimumCommitmentStartDate() {
		return accountGetView.getMinimumCommitmentStartDate();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return accountGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return accountGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return accountGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return accountGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return accountGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return accountGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return accountGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return accountGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return accountGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return accountGetView.getLastUpdateDate();
	}

}

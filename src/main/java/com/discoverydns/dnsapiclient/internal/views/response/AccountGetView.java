package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account")
public class AccountGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("name")
	private String name;
	@JsonProperty("identifier")
	private String identifier;
	@JsonProperty("status")
	private String status;
	@JsonProperty("minimumCommitment")
	private Double minimumCommitment;
	@JsonProperty("minimumCommitmentStartDate")
	private LocalDateTime minimumCommitmentStartDate;
	@JsonProperty("currency")
	private String currency;
    @JsonProperty("email")
    private String email;
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

	public String getIdentifier() {
		return identifier;
	}

	public String getStatus() {
		return status;
	}

	public Double getMinimumCommitment() {
		return minimumCommitment;
	}

	public LocalDateTime getMinimumCommitmentStartDate() {
		return minimumCommitmentStartDate;
	}

	public String getCurrency() {
		return currency;
	}

    public String getEmail() {
        return email;
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

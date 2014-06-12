package com.discoverydns.dnsapiclient.command.account;

import java.net.URI;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.AccountGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the
 * {@link com.discoverydns.dnsapiclient.DNSAPIClient} from the DNSAPI server
 * subsequently to the sending of a {@link AccountGetCommand}, describing the
 * details of the retrieved Account.
 * 
 * An Account represents a company or organisation who has the ability to
 * connect to and interact with the DNSAPI server, through its users.
 * 
 * @author Chris Wright
 */
@JsonRootName("AccountGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "identifier", "status",
		"currency", "minimumCommitment", "minimumCommitmentStartDate", "email",
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

	/**
	 * @return The URI representing the retrieved Account on the DNSAPI server
	 */
	@JsonProperty("@uri")
	public URI getURI() {
		return accountGetView.getUri();
	}

	/**
	 * @return The Account UUID
	 */
	@JsonProperty("id")
	public String getId() {
		return accountGetView.getId();
	}

	/**
	 * @return The Account version
	 */
	@JsonProperty("version")
	public Long getVersion() {
		return accountGetView.getVersion();
	}

	/**
	 * @return The Account name
	 */
	@JsonProperty("name")
	public String getName() {
		return accountGetView.getName();
	}

	/**
	 * @return The Account unique identifier
	 */
	@JsonProperty("identifier")
	public String getIdentifier() {
		return accountGetView.getIdentifier();
	}

	/**
	 * @return The Account status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return accountGetView.getStatus();
	}

	/**
	 * @return The monthly minimum commitment the account is committed to
	 */
	@JsonProperty("minimumCommitment")
	public Double getMinimumCommitment() {
		return accountGetView.getMinimumCommitment();
	}

	/**
	 * @return The 3 letter currency code of the currency used for billing
	 *         operations on this account
	 */
	@JsonProperty("currency")
	public String getCurrency() {
		return accountGetView.getCurrency();
	}

	/**
	 * @return The date on which the monthly minimum commitment will start being
	 *         charged
	 */
	@JsonProperty("minimumCommitmentStartDate")
	public LocalDateTime getMinimumCommitmentStartDate() {
		return accountGetView.getMinimumCommitmentStartDate();
	}

	/**
	 * @return The email of this account, or null if no notification emails
	 *         should be sent to it
	 */
	@JsonProperty("email")
	public String getEmail() {
		return accountGetView.getEmail();
	}

	/**
	 * @return The UUID of the Account who created this Account
	 */
	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return accountGetView.getCreateAccountId();
	}

	/**
	 * @return The unique identifier of the Account who created this Account
	 */
	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return accountGetView.getCreateAccountIdentifier();
	}

	/**
	 * @return The UUID of the User who created this Account
	 */
	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return accountGetView.getCreateUserId();
	}

	/**
	 * @return The name of the User who created this Account
	 */
	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return accountGetView.getCreateUserName();
	}

	/**
	 * @return The creation date of this Account
	 */
	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return accountGetView.getCreateDate();
	}

	/**
	 * @return The UUID of the Account who last updated this Account
	 */
	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return accountGetView.getLastUpdateAccountId();
	}

	/**
	 * @return The unique identifier of the Account who last updated this
	 *         Account
	 */
	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return accountGetView.getLastUpdateAccountIdentifier();
	}

	/**
	 * @return The UUID of the User who last updated this Account
	 */
	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return accountGetView.getLastUpdateUserId();
	}

	/**
	 * @return The name of the User who last updated this Account
	 */
	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return accountGetView.getLastUpdateUserName();
	}

	/**
	 * @return The last update date of this Account
	 */
	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return accountGetView.getLastUpdateDate();
	}

}

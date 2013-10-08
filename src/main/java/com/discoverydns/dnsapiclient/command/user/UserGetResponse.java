package com.discoverydns.dnsapiclient.command.user;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.UserGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link UserGetCommand},
 * describing the details of the retrieved User.
 *
 * A User is a member of an Account, who can connect to the DNSAPI server
 * to perform operations on his behalf.
 *
 * @author Chris Wright
 */
@JsonRootName("UserGetResponse")
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

    /**
     * @return The URI representing the retrieved User on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return userGetView.getUri();
	}

    /**
     * @return The User UUID
     */
	@JsonProperty("id")
	public String getId() {
		return userGetView.getId();
	}

    /**
     * @return The User modification version
     */
	@JsonProperty("version")
	public Long getVersion() {
		return userGetView.getVersion();
	}

    /**
     * @return The User unique username
     */
	@JsonProperty("username")
	public String getUsername() {
		return userGetView.getUsername();
	}

    /**
     * @return The User status
     */
	@JsonProperty("status")
	public String getStatus() {
		return userGetView.getStatus();
	}

    /**
     * @return The User name
     */
	@JsonProperty("name")
	public String getName() {
		return userGetView.getName();
	}

    /**
     * @return The User email
     */
	@JsonProperty("email")
	public String getEmail() {
		return userGetView.getEmail();
	}

    /**
     * @return The list of names of the roles granted to the User
     */
	@JsonProperty("roles")
	public Set<String> getRoles() {
		return userGetView.getRoles();
	}

    /**
     * @return The expiration date of the User password
     */
	@JsonProperty("passwordExpireDate")
	public LocalDateTime getPasswordExpireDate() {
		return userGetView.getPasswordExpireDate();
	}

    /**
     * @return The UUID of the Account who sponsors this User
     */
	@JsonProperty("sponsorAccountId")
	public String getSponsorAccountId() {
		return userGetView.getSponsorAccountId();
	}

    /**
     * @return The unique identifier of the Account who sponsors this User
     */
	@JsonProperty("sponsorAccountIdentifier")
	public String getSponsorAccountIdentifier() {
		return userGetView.getSponsorAccountIdentifier();
	}

    /**
     * @return The UUID of the Account who created this User
     */
	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return userGetView.getCreateAccountId();
	}

    /**
     * @return The unique identifier of the Account who created this User
     */
	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return userGetView.getCreateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who created this User
     */
	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return userGetView.getCreateUserId();
	}

    /**
     * @return The name of the User who created this User
     */
	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return userGetView.getCreateUserName();
	}

    /**
     * @return The creation date of this User
     */
	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return userGetView.getCreateDate();
	}

    /**
     * @return The UUID of the Account who last updated this User
     */
	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return userGetView.getLastUpdateAccountId();
	}

    /**
     * @return The unique identifier of the Account who last updated this User
     */
	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return userGetView.getLastUpdateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who last updated this User
     */
	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return userGetView.getLastUpdateUserId();
	}

    /**
     * @return The name of the User who last updated this User
     */
	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return userGetView.getLastUpdateUserName();
	}

    /**
     * @return The last update date of this User
     */
	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return userGetView.getLastUpdateDate();
	}

}

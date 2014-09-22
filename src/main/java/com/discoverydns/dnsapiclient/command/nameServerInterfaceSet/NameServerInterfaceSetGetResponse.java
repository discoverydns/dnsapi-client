package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.response.NameServerInterfaceSetGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link NameServerInterfaceSetGetCommand},
 * describing the details of the retrieved NameServer Interface Set.
 *
 * A NameServer Interface Set represents a physical entity of the DNSAPI architecture,
 * capable of answering to DNS queries.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerInterfaceSetGetResponse")
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

    /**
     * @return The URI representing the retrieved NameServer Interface Set on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerInterfaceSetGetView.getUri();
	}

    /**
     * @return The NameServer Interface Set UUID
     */
	@JsonProperty("id")
	public String getId() {
		return nameServerInterfaceSetGetView.getId();
	}

    /**
     * @return The NameServer Interface Set modification version
     */
	@JsonProperty("version")
	public Long getVersion() {
		return nameServerInterfaceSetGetView.getVersion();
	}

    /**
     * @return The NameServer Interface Set name
     */
	@JsonProperty("name")
	public String getName() {
		return nameServerInterfaceSetGetView.getName();
	}

    /**
     * @return The NameServer Interface Set status
     */
	@JsonProperty("status")
	public String getStatus() {
		return nameServerInterfaceSetGetView.getStatus();
	}

    /**
     * @return The list of physical {@link NameServerInterface}s of the NameServer Interface Set
     */
	@JsonProperty("nameServerInterfaces")
	public Set<NameServerInterface> getNameServerInterfaces() {
		return nameServerInterfaceSetGetView.getNameServerInterfaces();
	}

    /**
     * @return The UUID of the Account who created this NameServer Interface Set
     */
	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return nameServerInterfaceSetGetView.getCreateAccountId();
	}

    /**
     * @return The unique identifier of the Account who created this NameServer Interface Set
     */
	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return nameServerInterfaceSetGetView.getCreateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who created this NameServer Interface Set
     */
	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return nameServerInterfaceSetGetView.getCreateUserId();
	}

    /**
     * @return The name of the User who created this NameServer Interface Set
     */
	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return nameServerInterfaceSetGetView.getCreateUserName();
	}

    /**
     * @return The creation date of this NameServer Interface Set
     */
	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return nameServerInterfaceSetGetView.getCreateDate();
	}

    /**
     * @return The UUID of the Account who last updated this NameServer Interface Set
     */
	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return nameServerInterfaceSetGetView.getLastUpdateAccountId();
	}

    /**
     * @return The unique identifier of the Account who last updated this NameServer Interface Set
     */
	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return nameServerInterfaceSetGetView.getLastUpdateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who last updated this NameServer Interface Set
     */
	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return nameServerInterfaceSetGetView.getLastUpdateUserId();
	}

    /**
     * @return The name of the User who last updated this NameServer Interface Set
     */
	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return nameServerInterfaceSetGetView.getLastUpdateUserName();
	}

    /**
     * @return The last update date of this NameServer Interface Set
     */
	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return nameServerInterfaceSetGetView.getLastUpdateDate();
	}

}

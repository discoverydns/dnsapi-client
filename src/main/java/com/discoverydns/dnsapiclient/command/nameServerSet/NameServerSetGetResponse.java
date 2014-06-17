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

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link NameServerSetGetCommand},
 * describing the details of the retrieved NameServer Set.
 *
 * A NameServer Set represents a logical entity of the DNSAPI architecture,
 * to link an Account with a NameServer Interface Set,
 * through the configuration of the Name Server names that will be provided to the Account's Zones.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerSetGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "prefix", "domainName",
		"email", "nameServerInterfaceSetId", "nameServerInterfaceSetName",
		"createDate", "createAccountId", "createAccountIdentifier",
		"createUserId", "createUserName", "lastUpdateDate",
		"lastUpdateAccountId", "lastUpdateAccountIdentifier",
		"lastUpdateUserId", "lastUpdateUserName", "nameServerInterfaceSetStatus",
		"nameServerInterfaceSetInterfaces" })
public class NameServerSetGetResponse {

	@JsonIgnore
	private final NameServerSetGetView nameServerSetGetView;

	public NameServerSetGetResponse(final NameServerSetGetView nameServerSetView) {
		this.nameServerSetGetView = nameServerSetView;
	}

    /**
     * @return The URI representing the retrieved NameServer Set on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerSetGetView.getUri();
	}

    /**
     * @return The NameServer Set UUID
     */
	@JsonProperty("id")
	public String getId() {
		return nameServerSetGetView.getId();
	}

    /**
     * @return The NameServer Set modification version
     */
	@JsonProperty("version")
	public Long getVersion() {
		return nameServerSetGetView.getVersion();
	}

    /**
     * @return The NameServer Set name
     */
	@JsonProperty("name")
	public String getName() {
		return nameServerSetGetView.getName();
	}

    /**
     * @return The domain name used in the generation of NS records for unbranded Zones that use this NameServer Set
     */
	@JsonProperty("domainName")
	public String getDomainName() {
		return nameServerSetGetView.getDomainName();
	}

    /**
     * @return The email address used in the generation of the SOA record for Zones that use this NameServer Set
     */
	@JsonProperty("email")
	public String getEmail() {
		return nameServerSetGetView.getEmail();
	}

    /**
     * @return The UUID of the Name Server Interface Set that this NameServer Set is associated with
     */
	@JsonProperty("nameServerInterfaceSetId")
	public String getNameServerInterfaceSetId() {
		return nameServerSetGetView.getNameServerInterfaceSetId();
	}

    /**
     * @return The status of the NameServer Interface Set that this NameServer Set is associated with
     */
	@JsonProperty("nameServerInterfaceSetStatus")
	public String getNameServerInterfaceSetStatus() {
		return nameServerSetGetView.getNameServerInterfaceSetStatus();
	}

    /**
     * @return The list of physical {@link NameServerInterface}s of the NameServer Interface Set
     * that this NameServer Set is associated with
     */
	@JsonProperty("nameServerInterfaceSetInterfaces")
	public Set<NameServerInterface> getNameServerInterfaceSetInterfaces() {
		return nameServerSetGetView.getNameServerInterfaceSetInterfaces();
	}

    /**
     * @return The name of the Name Server Interface Set that this NameServer Set is associated with
     */
	@JsonProperty("nameServerInterfaceSetName")
	public String getNameServerInterfaceSetName() {
		return nameServerSetGetView.getNameServerInterfaceSetName();
	}

    /**
     * @return The prefix used in the generation of NS records for Zones that use this NameServer Set
     */
	@JsonProperty("prefix")
	public String getPrefix() {
		return nameServerSetGetView.getPrefix();
	}

    /**
     * @return The UUID of the Account who created this NameServer Set
     */
	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return nameServerSetGetView.getCreateAccountId();
	}

    /**
     * @return The unique identifier of the Account who created this NameServer Set
     */
	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return nameServerSetGetView.getCreateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who created this NameServer Set
     */
	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return nameServerSetGetView.getCreateUserId();
	}

    /**
     * @return The name of the User who created this NameServer Set
     */
	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return nameServerSetGetView.getCreateUserName();
	}

    /**
     * @return The creation date of this NameServer Set
     */
	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return nameServerSetGetView.getCreateDate();
	}

    /**
     * @return The UUID of the Account who last updated this NameServer Set
     */
	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return nameServerSetGetView.getLastUpdateAccountId();
	}

    /**
     * @return The unique identifier of the Account who last updated this NameServer Set
     */
	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return nameServerSetGetView.getLastUpdateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who last updated this NameServer Set
     */
	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return nameServerSetGetView.getLastUpdateUserId();
	}

    /**
     * @return The name of the User who last updated this NameServer Set
     */
	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return nameServerSetGetView.getLastUpdateUserName();
	}

    /**
     * @return The last update date of this NameServer Set
     */
	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return nameServerSetGetView.getLastUpdateDate();
	}

}

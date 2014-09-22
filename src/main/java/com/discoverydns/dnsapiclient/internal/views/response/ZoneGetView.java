package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.xbill.DNS.Record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zone")
public class ZoneGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("serial")
	private Long serial;
	@JsonProperty("name")
	private String name;
	@JsonProperty("brandedNameServers")
	private Boolean brandedNameServers;
	@JsonProperty("dnssecSigned")
	private Boolean dnssecSigned;
	@JsonProperty("zskRollOverState")
    private String zskRollOverState;
    @JsonProperty("pendingOperation")
    private String pendingOperation;
    @JsonProperty("lastPublishDate")
    private LocalDateTime lastPublishDate;
	@JsonProperty("sponsorAccountId")
	private String sponsorAccountId;
	@JsonProperty("sponsorAccountIdentifier")
	private String sponsorAccountIdentifier;
	@JsonProperty("nameServerSetId")
	private String nameServerSetId;
	@JsonProperty("nameServerSetName")
	private String nameServerSetName;
	@JsonProperty("nameServerInterfaceSetId")
	private String nameServerInterfaceSetId;
	@JsonProperty("nameServerInterfaceSetName")
	private String nameServerInterfaceSetName;
	@JsonProperty("createDate")
	private LocalDateTime createDate;
	@JsonProperty("planId")
	private String planId;
	@JsonProperty("planName")
	private String planName;
	@JsonProperty("group")
	private String group;
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
	@JsonProperty("resourceRecords")
	private Set<Record> userResourceRecords;
	@JsonProperty("ddnsResourceRecords")
	private Set<Record> ddnsResourceRecords;
	@JsonProperty("delegationResourceRecords")
    private Set<Record> delegationDDNSResourceRecords;

	public URI getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public Long getSerial() {
		return serial;
	}

	public String getName() {
		return name;
	}

	public Boolean getBrandedNameServers() {
		return brandedNameServers;
	}

	public Boolean getDNSSECSigned() {
		return dnssecSigned;
	}

    public String getZskRollOverState() {
        return zskRollOverState;
    }

    public String getPendingOperation() {
        return pendingOperation;
    }

    public LocalDateTime getLastPublishDate() {
        return lastPublishDate;
    }

	public String getSponsorAccountId() {
		return sponsorAccountId;
	}

	public String getSponsorAccountIdentifier() {
		return sponsorAccountIdentifier;
	}

	public String getNameServerSetId() {
		return nameServerSetId;
	}

	public String getNameServerSetName() {
		return nameServerSetName;
	}

	public String getNameServerInterfaceSetId() {
		return nameServerInterfaceSetId;
	}

	public String getNameServerInterfaceSetName() {
		return nameServerInterfaceSetName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getPlanId() {
		return planId;
	}

	public String getPlanName() {
		return planName;
	}

	public String getGroup() {
		return group;
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

	public Set<Record> getUserResourceRecords() {
		return userResourceRecords;
	}

	public Set<Record> getDDNSResourceRecords() {
		return ddnsResourceRecords;
	}

    public Set<Record> getDelegationResourceRecords() {
        return delegationDDNSResourceRecords;
    }
}

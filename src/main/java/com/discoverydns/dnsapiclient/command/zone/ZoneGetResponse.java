package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.xbill.DNS.Record;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "serial",
		"brandedNameServers", "dnssecSigned", "nameServerSetId",
		"nameServerSetName", "nameServerInterfaceSetId",
		"nameServerInterfaceSetName", "planId", "planName", "group",
		"sponsorAccountId", "sponsorAccountIdentifier", "createDate",
		"createAccountId", "createAccountIdentifier", "createUserId",
		"createUserName", "lastUpdateDate", "lastUpdateAccountId",
		"lastUpdateAccountIdentifier", "lastUpdateUserId",
		"lastUpdateUserName", "ddnsResourceRecords", "resourceRecords" })
public class ZoneGetResponse {

	@JsonIgnore
	private final ZoneGetView zoneGetView;

	public ZoneGetResponse(final ZoneGetView zoneGetView) {
		this.zoneGetView = zoneGetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return zoneGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return zoneGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return zoneGetView.getVersion();
	}

	@JsonProperty("name")
	public String getName() {
		return zoneGetView.getName();
	}

	@JsonProperty("brandedNameServers")
	public Boolean getBrandedNameServers() {
		return zoneGetView.getBrandedNameServers();
	}

	@JsonProperty("ddnsResourceRecords")
	public Set<Record> getDDNSResourceRecords() {
		return zoneGetView.getDDNSResourceRecords();
	}

	@JsonProperty("dnssecSigned")
	public Boolean getDNSSECSigned() {
		return zoneGetView.getDNSSECSigned();
	}

	@JsonProperty("nameServerInterfaceSetId")
	public String getNameServerInterfaceSetId() {
		return zoneGetView.getNameServerInterfaceSetId();
	}

	@JsonProperty("nameServerInterfaceSetName")
	public String getNameServerInterfaceSetName() {
		return zoneGetView.getNameServerInterfaceSetName();
	}

	@JsonProperty("nameServerSetId")
	public String getNameServerSetId() {
		return zoneGetView.getNameServerSetId();
	}

	@JsonProperty("nameServerSetName")
	public String getNameServerSetName() {
		return zoneGetView.getNameServerSetName();
	}

	@JsonProperty("planId")
	public String getPlanId() {
		return zoneGetView.getPlanId();
	}

	@JsonProperty("planName")
	public String getPlanName() {
		return zoneGetView.getPlanName();
	}

	@JsonProperty("group")
	public String getGroup() {
		return zoneGetView.getGroup();
	}

	@JsonProperty("serial")
	public Long getSerial() {
		return zoneGetView.getSerial();
	}

	@JsonProperty("resourceRecords")
	public Set<Record> getUserResourceRecords() {
		return zoneGetView.getUserResourceRecords();
	}

	@JsonProperty("sponsorAccountId")
	public String getSponsorAccountId() {
		return zoneGetView.getSponsorAccountId();
	}

	@JsonProperty("sponsorAccountIdentifier")
	public String getSponsorAccountIdentifier() {
		return zoneGetView.getSponsorAccountIdentifier();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return zoneGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return zoneGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return zoneGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return zoneGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return zoneGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return zoneGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return zoneGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return zoneGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return zoneGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return zoneGetView.getLastUpdateDate();
	}

}

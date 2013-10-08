package com.discoverydns.dnsapiclient.command.plan;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.PlanGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PlanGetResponse")
@JsonPropertyOrder({ "@uri", "id", "version", "name", "status",
		"baseMonthlyRate", "currency", "excessGraceMonths",
		"excessGraceMonthsPeriod", "trialPeriod", "createDate",
		"createAccountId", "createAccountIdentifier", "createUserId",
		"createUserName", "lastUpdateDate", "lastUpdateAccountId",
		"lastUpdateAccountIdentifier", "lastUpdateUserId",
		"lastUpdateUserName", "units", "features" })
public class PlanGetResponse {

	@JsonIgnore
	private final PlanGetView planGetView;

	public PlanGetResponse(final PlanGetView planGetView) {
		this.planGetView = planGetView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return planGetView.getUri();
	}

	@JsonProperty("id")
	public String getId() {
		return planGetView.getId();
	}

	@JsonProperty("version")
	public Long getVersion() {
		return planGetView.getVersion();
	}

	@JsonProperty("status")
	public String getStatus() {
		return planGetView.getStatus();
	}

	@JsonProperty("name")
	public String getName() {
		return planGetView.getName();
	}

	@JsonProperty("baseMonthlyRate")
	public Double getBaseMonthlyRate() {
		return planGetView.getBaseMonthlyRate();
	}

	@JsonProperty("currency")
	public String getCurrency() {
		return planGetView.getCurrency();
	}

	@JsonProperty("excessGraceMonths")
	public Integer getExcessGraceMonths() {
		return planGetView.getExcessGraceMonths();
	}

	@JsonProperty("excessGraceMonthsPeriod")
	public Integer getExcessGraceMonthsPeriod() {
		return planGetView.getExcessGraceMonthsPeriod();
	}

	@JsonProperty("features")
	public Set<PlanFeature> getFeatures() {
		return planGetView.getFeatures();
	}

	@JsonProperty("units")
	public Set<PlanUnit> getUnits() {
		return planGetView.getUnits();
	}

	@JsonProperty("trialPeriod")
	public Integer getTrialPeriod() {
		return planGetView.getTrialPeriod();
	}

	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return planGetView.getCreateAccountId();
	}

	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return planGetView.getCreateAccountIdentifier();
	}

	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return planGetView.getCreateUserId();
	}

	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return planGetView.getCreateUserName();
	}

	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return planGetView.getCreateDate();
	}

	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return planGetView.getLastUpdateAccountId();
	}

	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return planGetView.getLastUpdateAccountIdentifier();
	}

	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return planGetView.getLastUpdateUserId();
	}

	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return planGetView.getLastUpdateUserName();
	}

	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return planGetView.getLastUpdateDate();
	}

}

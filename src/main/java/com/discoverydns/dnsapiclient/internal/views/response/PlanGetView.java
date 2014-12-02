package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.command.plan.PlanFeature;
import com.discoverydns.dnsapiclient.command.plan.PlanUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("plan")
public class PlanGetView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("id")
	private String id;
	@JsonProperty("version")
	private Long version;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("baseMonthlyRate")
	private Double baseMonthlyRate;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("excessGraceMonths")
	private Integer excessGraceMonths;
	@JsonProperty("excessGraceMonthsPeriod")
	private Integer excessGraceMonthsPeriod;
	@JsonProperty("trialPeriod")
	private Integer trialPeriod;
	@JsonProperty("features")
	private Set<PlanFeature> features;
	@JsonProperty("units")
	private Set<PlanUnit> units;
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

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Double getBaseMonthlyRate() {
		return baseMonthlyRate;
	}

	public String getCurrency() {
		return currency;
	}

	public Integer getExcessGraceMonths() {
		return excessGraceMonths;
	}

	public Integer getExcessGraceMonthsPeriod() {
		return excessGraceMonthsPeriod;
	}

	public Integer getTrialPeriod() {
		return trialPeriod;
	}

	public Set<PlanFeature> getFeatures() {
		return features;
	}

	public Set<PlanUnit> getUnits() {
		return units;
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

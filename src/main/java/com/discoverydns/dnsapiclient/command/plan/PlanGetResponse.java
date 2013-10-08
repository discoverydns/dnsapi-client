package com.discoverydns.dnsapiclient.command.plan;

import java.net.URI;
import java.util.Set;

import org.joda.time.LocalDateTime;

import com.discoverydns.dnsapiclient.internal.views.PlanGetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link PlanGetCommand},
 * describing the details of the retrieved Plan.
 *
 * A Plan describes the available application's additional features granted to an Account,
 * as well as the details and units used for billing this Account.
 *
 * @author Chris Wright
 */
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

    /**
     * @return The URI representing the retrieved Plan on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return planGetView.getUri();
	}

    /**
     * @return The Plan UUID
     */
	@JsonProperty("id")
	public String getId() {
		return planGetView.getId();
	}

    /**
     * @return The Plan modification version
     */
	@JsonProperty("version")
	public Long getVersion() {
		return planGetView.getVersion();
	}

    /**
     * @return The Plan status
     */
	@JsonProperty("status")
	public String getStatus() {
		return planGetView.getStatus();
	}

    /**
     * @return The Plan name
     */
	@JsonProperty("name")
	public String getName() {
		return planGetView.getName();
	}

    /**
     * @return The monthly billing rate of the plan,  represented in units of the currency of the plan
     */
	@JsonProperty("baseMonthlyRate")
	public Double getBaseMonthlyRate() {
		return planGetView.getBaseMonthlyRate();
	}

    /**
     * @return The 3 letter currency code of the currency used for billing operations on this plan
     */
	@JsonProperty("currency")
	public String getCurrency() {
		return planGetView.getCurrency();
	}

    /**
     * @return The number of months grace period allowed for the plan
     */
	@JsonProperty("excessGraceMonths")
	public Integer getExcessGraceMonths() {
		return planGetView.getExcessGraceMonths();
	}

    /**
     * @return The number of months that the excess grace is counted over
     */
	@JsonProperty("excessGraceMonthsPeriod")
	public Integer getExcessGraceMonthsPeriod() {
		return planGetView.getExcessGraceMonthsPeriod();
	}

    /**
     * @return The list of {@link PlanFeature}s granted to Accounts using this Plan
     */
	@JsonProperty("features")
	public Set<PlanFeature> getFeatures() {
		return planGetView.getFeatures();
	}

    /**
     * @return The list of {@link PlanUnit}s used to calculate the billing for Accounts using this Plan
     */
	@JsonProperty("units")
	public Set<PlanUnit> getUnits() {
		return planGetView.getUnits();
	}

    /**
     * @return The number of months trial allowed for zones using this plan
     */
	@JsonProperty("trialPeriod")
	public Integer getTrialPeriod() {
		return planGetView.getTrialPeriod();
	}

    /**
     * @return The UUID of the Account who created this Plan
     */
	@JsonProperty("createAccountId")
	public String getCreateAccountId() {
		return planGetView.getCreateAccountId();
	}

    /**
     * @return The unique identifier of the Account who created this Plan
     */
	@JsonProperty("createAccountIdentifier")
	public String getCreateAccountIdentifier() {
		return planGetView.getCreateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who created this Plan
     */
	@JsonProperty("createUserId")
	public String getCreateUserId() {
		return planGetView.getCreateUserId();
	}

    /**
     * @return The name of the User who created this Plan
     */
	@JsonProperty("createUserName")
	public String getCreateUserName() {
		return planGetView.getCreateUserName();
	}

    /**
     * @return The creation date of this Plan
     */
	@JsonProperty("createDate")
	public LocalDateTime getCreateDate() {
		return planGetView.getCreateDate();
	}

    /**
     * @return The UUID of the Account who last updated this Plan
     */
	@JsonProperty("lastUpdateAccountId")
	public String getLastUpdateAccountId() {
		return planGetView.getLastUpdateAccountId();
	}

    /**
     * @return The unique identifier of the Account who last updated this Plan
     */
	@JsonProperty("lastUpdateAccountIdentifier")
	public String getLastUpdateAccountIdentifier() {
		return planGetView.getLastUpdateAccountIdentifier();
	}

    /**
     * @return The UUID of the User who last updated this Plan
     */
	@JsonProperty("lastUpdateUserId")
	public String getLastUpdateUserId() {
		return planGetView.getLastUpdateUserId();
	}

    /**
     * @return The name of the User who last updated this Plan
     */
	@JsonProperty("lastUpdateUserName")
	public String getLastUpdateUserName() {
		return planGetView.getLastUpdateUserName();
	}

    /**
     * @return The last update date of this Plan
     */
	@JsonProperty("lastUpdateDate")
	public LocalDateTime getLastUpdateDate() {
		return planGetView.getLastUpdateDate();
	}

}

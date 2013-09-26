package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanFeature {

	@JsonProperty("featureType")
	private String featureType;
	@JsonProperty("additionRate")
	private Double additionalRate;

	public String getFeatureType() {
		return featureType;
	}

	public Double getAdditionalRate() {
		return additionalRate;
	}

}

package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Application additional feature granted to an Account using the Plan.
 *
 * @author Chris Wright
 */
public class PlanFeature {

	@JsonProperty("featureType")
	private String featureType;
	@JsonProperty("additionalRate")
	private Double additionalRate;

    /**
     * @return The feature type
     */
	public String getFeatureType() {
		return featureType;
	}

    /**
     * @return The additional monthly rate (if any) applied when billing an Account using this feature
     */
	public Double getAdditionalRate() {
		return additionalRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((additionalRate == null) ? 0 : additionalRate.hashCode());
		result = prime * result
				+ ((featureType == null) ? 0 : featureType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PlanFeature)) {
			return false;
		}
		PlanFeature other = (PlanFeature) obj;
		if (additionalRate == null) {
			if (other.additionalRate != null) {
				return false;
			}
		} else if (!additionalRate.equals(other.additionalRate)) {
			return false;
		}
		if (featureType == null) {
			if (other.featureType != null) {
				return false;
			}
		} else if (!featureType.equals(other.featureType)) {
			return false;
		}
		return true;
	}

}

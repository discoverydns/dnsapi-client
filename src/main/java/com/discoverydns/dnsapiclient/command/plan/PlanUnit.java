package com.discoverydns.dnsapiclient.command.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanUnit {

	@JsonProperty("unitType")
	private String unitType;
	@JsonProperty("includedUnits")
	private Long includedUnits;
	@JsonProperty("excessUnitsBatchSize")
	private Long excessUnitsBatchSize;
	@JsonProperty("excessUnitsBatchRate")
	private Double excessUnitsBatchRate;

	public String getUnitType() {
		return unitType;
	}

	public Long getIncludedUnits() {
		return includedUnits;
	}

	public Long getExcessUnitsBatchSize() {
		return excessUnitsBatchSize;
	}

	public Double getExcessUnitsBatchRate() {
		return excessUnitsBatchRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((excessUnitsBatchRate == null) ? 0 : excessUnitsBatchRate
						.hashCode());
		result = prime
				* result
				+ ((excessUnitsBatchSize == null) ? 0 : excessUnitsBatchSize
						.hashCode());
		result = prime * result
				+ ((includedUnits == null) ? 0 : includedUnits.hashCode());
		result = prime * result
				+ ((unitType == null) ? 0 : unitType.hashCode());
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
		if (!(obj instanceof PlanUnit)) {
			return false;
		}
		PlanUnit other = (PlanUnit) obj;
		if (excessUnitsBatchRate == null) {
			if (other.excessUnitsBatchRate != null) {
				return false;
			}
		} else if (!excessUnitsBatchRate.equals(other.excessUnitsBatchRate)) {
			return false;
		}
		if (excessUnitsBatchSize == null) {
			if (other.excessUnitsBatchSize != null) {
				return false;
			}
		} else if (!excessUnitsBatchSize.equals(other.excessUnitsBatchSize)) {
			return false;
		}
		if (includedUnits == null) {
			if (other.includedUnits != null) {
				return false;
			}
		} else if (!includedUnits.equals(other.includedUnits)) {
			return false;
		}
		if (unitType == null) {
			if (other.unitType != null) {
				return false;
			}
		} else if (!unitType.equals(other.unitType)) {
			return false;
		}
		return true;
	}

}

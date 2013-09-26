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

}

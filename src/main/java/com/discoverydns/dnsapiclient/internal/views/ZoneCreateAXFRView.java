package com.discoverydns.dnsapiclient.internal.views;

import java.util.List;

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneCreateAXFR")
public class ZoneCreateAXFRView {

	private final ZoneCreateAXFRCommand zoneCreateAXFRCommand;

	public ZoneCreateAXFRView(final ZoneCreateAXFRCommand zoneCreateAXFRCommand) {
		this.zoneCreateAXFRCommand = zoneCreateAXFRCommand;
	}

	@JsonProperty("nameServerSetId")
	public String getNameServerSetId() {
		return zoneCreateAXFRCommand.getNameServerSetId();
	}

	@JsonProperty("name")
	public String getName() {
		return zoneCreateAXFRCommand.getName();
	}

	@JsonProperty("planId")
	public String getPlanId() {
		return zoneCreateAXFRCommand.getPlanId();
	}

	@JsonProperty("group")
	public String getGroup() {
		return zoneCreateAXFRCommand.getGroup();
	}

    @JsonProperty("axfrServers")
    public List<String> getAxfrServers() {
        return zoneCreateAXFRCommand.getAxfrServers();
    }

    @JsonProperty("tsigKey")
    public String getTsigKey() {
        return zoneCreateAXFRCommand.getTsigKey();
    }

    @JsonProperty("tsigName")
    public String getTsigName() {
        return zoneCreateAXFRCommand.getTsigName();
    }

    @JsonProperty("tsigAlgorithm")
    public String getTsigAlgorithm() {
        return zoneCreateAXFRCommand.getTsigAlgorithm();
    }
}

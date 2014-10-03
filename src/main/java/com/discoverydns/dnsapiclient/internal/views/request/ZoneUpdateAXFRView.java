package com.discoverydns.dnsapiclient.internal.views.request;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("xfrDetailsUpdate")
public class ZoneUpdateAXFRView {

    private final ZoneUpdateAXFRCommand zoneUpdateAXFRCommand;

    public ZoneUpdateAXFRView(ZoneUpdateAXFRCommand zoneUpdateAXFRCommand) {
        this.zoneUpdateAXFRCommand = zoneUpdateAXFRCommand;
    }

    @JsonProperty("version")
    public Long version() {
        return zoneUpdateAXFRCommand.getVersion();
    }
    @JsonProperty("tsigName")
    public String tsigName() {
        return zoneUpdateAXFRCommand.getTsigName();
    }
    @JsonProperty("tsigKey")
    public String tsigKey() {
        return zoneUpdateAXFRCommand.getTsigKey();
    }
    @JsonProperty("tsigAlgorithm")
    public String tsigAlgorighm() {
        return zoneUpdateAXFRCommand.getTsigAlgorithm();
    }
    @JsonProperty("axfrServers")
    public List<String> axfrServers() {
        return zoneUpdateAXFRCommand.getAxfrServers();
    }

}

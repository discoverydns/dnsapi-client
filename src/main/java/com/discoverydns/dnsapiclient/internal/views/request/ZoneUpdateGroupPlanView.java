package com.discoverydns.dnsapiclient.internal.views.request;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneUpdateGroupPlan")
public class ZoneUpdateGroupPlanView {
    private final ZoneUpdateGroupPlanCommand command;

    public ZoneUpdateGroupPlanView(ZoneUpdateGroupPlanCommand command) {
        this.command = command;
    }

    @JsonProperty("planId")
    public String getPlanId() {
        return command.getPlanId();
    }
}

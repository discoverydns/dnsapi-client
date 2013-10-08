package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneCreateResponse")
public class ZoneCreateResponse extends ZoneGetResponse {

	public ZoneCreateResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

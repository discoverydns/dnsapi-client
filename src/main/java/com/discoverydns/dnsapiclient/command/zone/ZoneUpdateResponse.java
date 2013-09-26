package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneUpdateResponse")
public class ZoneUpdateResponse extends ZoneGetResponse {

	public ZoneUpdateResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

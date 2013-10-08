package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ZoneUpdateResourceRecordsResponse")
public class ZoneUpdateResourceRecordsResponse extends ZoneGetResponse {

	public ZoneUpdateResourceRecordsResponse(final ZoneGetView zoneGetView) {
		super(zoneGetView);
	}

}

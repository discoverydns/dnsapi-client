package com.discoverydns.dnsapiclient.internal.views;

public class ZoneGetZoneFileView {
	private final byte[] zoneFile;

	public ZoneGetZoneFileView(byte[] zoneFile) {
		this.zoneFile = zoneFile;
	}

	public byte[] getZoneFile() {
		return zoneFile;
	}
}

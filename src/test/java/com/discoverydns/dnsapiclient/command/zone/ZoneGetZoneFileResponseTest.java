package com.discoverydns.dnsapiclient.command.zone;

import org.junit.Test;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetZoneFileView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ZoneGetZoneFileResponseTest {
	@Test
	public void shouldReturnTheExpectedResponse() {
		byte[] zoneFile = "zoneFile".getBytes();
		ZoneGetZoneFileView mockZoneGetZoneFileView = mock(ZoneGetZoneFileView.class);
		when(mockZoneGetZoneFileView.getZoneFile()).thenReturn(zoneFile);
		
		ZoneGetZoneFileResponse zoneGetZoneFileResponse = new ZoneGetZoneFileResponse(mockZoneGetZoneFileView);
		assertEquals(zoneFile, zoneGetZoneFileResponse.getZoneFile());
	}
}

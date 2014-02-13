package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneGetZoneFileCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        ZoneGetZoneFileCommand command =
                new ZoneGetZoneFileCommand.Builder()
                        .withId(id)
                        .build();

        assertEquals(id, command.getId());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneGetZoneFileCommand.Builder().build(),
                new ZoneGetZoneFileCommand.Builder().build());
    }
}

package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        ZoneGetCommand command =
                new ZoneGetCommand.Builder()
                        .withId(id)
                        .build();

        assertEquals(id, command.getId());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneGetCommand.Builder().build(),
                new ZoneGetCommand.Builder().build());
    }
}

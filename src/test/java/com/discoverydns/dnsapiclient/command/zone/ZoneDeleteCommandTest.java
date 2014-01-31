package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneDeleteCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        ZoneDeleteCommand command =
                new ZoneDeleteCommand.Builder()
                        .withId(id)
                        .build();

        assertEquals(id, command.getId());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneDeleteCommand.Builder().build(),
                new ZoneDeleteCommand.Builder().build());
    }
}

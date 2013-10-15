package com.discoverydns.dnsapiclient.command.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class PlanGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String idOrName = "idOrName";
        PlanGetCommand command =
                new PlanGetCommand.Builder()
                        .withIdOrName(idOrName)
                        .build();

        assertEquals(idOrName, command.getIdOrName());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new PlanGetCommand.Builder().build(),
                new PlanGetCommand.Builder().build());
    }
}

package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneCreateCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String name = "name";
        String nameServerSetId = "nameServerSetId";
        String planId = "planId";
        String group = "group";
        Boolean brandedNameServers = true;
        ZoneCreateCommand command =
                new ZoneCreateCommand.Builder()
                        .withName(name)
                        .withNameServerSetId(nameServerSetId)
                        .withPlanId(planId)
                        .withBrandedNameServers(brandedNameServers)
                        .withGroup(group)
                        .build();

        assertEquals(name, command.getName());
        assertEquals(nameServerSetId, command.getNameServerSetId());
        assertEquals(planId, command.getPlanId());
        assertEquals(brandedNameServers, command.isBrandedNameServers());
        assertEquals(group, command.getGroup());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneCreateCommand.Builder().build(),
                new ZoneCreateCommand.Builder().build());
    }
}

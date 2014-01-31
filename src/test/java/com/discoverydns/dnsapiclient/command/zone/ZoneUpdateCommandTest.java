package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneUpdateCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        String nameServerSetId = "nameServerSetId";
        String planId = "planId";
        String group = "group";
        Boolean dnssecSigned = true;
        Boolean brandedNameServers = true;
        Long version = 1L;
        ZoneUpdateCommand command =
                new ZoneUpdateCommand.Builder()
                        .withId(id)
                        .withVersion(version)
                        .withNameServerSetId(nameServerSetId)
                        .withPlanId(planId)
                        .withDnssecSigned(dnssecSigned)
                        .withBrandedNameServers(brandedNameServers)
                        .withGroup(group)
                        .build();

        assertEquals(id, command.getId());
        assertEquals(version, command.getVersion());
        assertEquals(nameServerSetId, command.getNameServerSetId());
        assertEquals(planId, command.getPlanId());
        assertEquals(dnssecSigned, command.isDnssecSigned());
        assertEquals(brandedNameServers, command.isBrandedNameServers());
        assertEquals(group, command.getGroup());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneUpdateCommand.Builder().build(),
                new ZoneUpdateCommand.Builder().build());
    }
}

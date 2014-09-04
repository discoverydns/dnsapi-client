package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;

import java.util.Set;

import org.junit.Test;
import org.xbill.DNS.Record;

import com.google.common.collect.Sets;

public class ZoneCreateCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String name = "name";
        String nameServerSetId = "nameServerSetId";
        String planId = "planId";
        String group = "group";
        Boolean dnssecSigned = true;
        Boolean brandedNameServers = true;
        Set<Record> records = Sets.newHashSet(mock(Record.class), mock(Record.class));
        ZoneCreateCommand command =
                new ZoneCreateCommand.Builder()
                        .withName(name)
                        .withNameServerSetId(nameServerSetId)
                        .withPlanId(planId)
                        .withDnssecSigned(dnssecSigned)
                        .withBrandedNameServers(brandedNameServers)
                        .withGroup(group)
                        .withResourceRecords(records)
                        .build();

        assertEquals(name, command.getName());
        assertEquals(nameServerSetId, command.getNameServerSetId());
        assertEquals(planId, command.getPlanId());
        assertEquals(dnssecSigned, command.isDnssecSigned());
        assertEquals(brandedNameServers, command.isBrandedNameServers());
        assertEquals(group, command.getGroup());
        assertEquals(records, command.getRecords());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneCreateCommand.Builder().build(),
                new ZoneCreateCommand.Builder().build());
    }
}

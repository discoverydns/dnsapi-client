package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ZoneListCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String searchName = "searchName";
        String searchNameServerInterfaceSetId = "searchNameServerInterfaceSetId";
        String searchNameServerSetId = "searchNameServerSetId";
        String searchPlanId = "searchPlanId";
        String searchGroup = "searchGroup";
        Boolean searchBrandedNameServers = true;
        Boolean searchDNSSECSigned = false;
        ZoneListCommand command =
                new ZoneListCommand.Builder()
                        .withSearchName(searchName)
                        .withSearchNameServerInterfaceSetId(searchNameServerInterfaceSetId)
                        .withSearchNameServerSetId(searchNameServerSetId)
                        .withSearchPlanId(searchPlanId)
                        .withSearchGroup(searchGroup)
                        .withSearchBrandedNameServers(searchBrandedNameServers)
                        .withSearchDNSSECSigned(searchDNSSECSigned)
                        .build();

        assertEquals(searchName, command.getSearchName());
        assertEquals(searchNameServerInterfaceSetId,
                command.getSearchNameServerInterfaceSetId());
        assertEquals(searchNameServerSetId,
                command.getSearchNameServerSetId());
        assertEquals(searchPlanId,
                command.getSearchPlanId());
        assertEquals(searchGroup, command.getSearchGroup());
        assertEquals(searchBrandedNameServers, command.getSearchBrandedNameServers());
        assertEquals(searchDNSSECSigned, command.getSearchDNSSECSigned());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneListCommand.Builder().build(),
                new ZoneListCommand.Builder().build());
    }
}

package com.discoverydns.dnsapiclient.command.nameServerSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class NameServerSetListCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String searchName = "searchName";
        String searchNameServerInterfaceSetId = "searchNameServerInterfaceSetId";
        String searchStatus = "searchStatus";
        NameServerSetListCommand command =
                new NameServerSetListCommand.Builder()
                        .withSearchName(searchName)
                        .withSearchNameServerInterfaceSetId(searchNameServerInterfaceSetId)
                        .withSearchStatus(searchStatus)
                        .build();

        assertEquals(searchName, command.getSearchName());
        assertEquals(searchNameServerInterfaceSetId,
                command.getSearchNameServerInterfaceSetId());
        assertEquals(searchStatus, command.getSearchStatus());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new NameServerSetListCommand.Builder().build(),
                new NameServerSetListCommand.Builder().build());
    }
}

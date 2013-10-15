package com.discoverydns.dnsapiclient.command.nameServerInterfaceSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class NameServerInterfaceSetGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String idOrName = "idOrName";
        NameServerInterfaceSetGetCommand command =
                new NameServerInterfaceSetGetCommand.Builder()
                        .withIdOrName(idOrName)
                        .build();

        assertEquals(idOrName, command.getIdOrName());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new NameServerInterfaceSetGetCommand.Builder().build(),
                new NameServerInterfaceSetGetCommand.Builder().build());
    }
}

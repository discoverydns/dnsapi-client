package com.discoverydns.dnsapiclient.command.nameServerSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class NameServerSetGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String idOrName = "idOrName";
        NameServerSetGetCommand command =
                new NameServerSetGetCommand.Builder()
                        .withIdOrName(idOrName)
                        .build();

        assertEquals(idOrName, command.getIdOrName());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new NameServerSetGetCommand.Builder().build(),
                new NameServerSetGetCommand.Builder().build());
    }
}

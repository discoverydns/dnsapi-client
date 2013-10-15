package com.discoverydns.dnsapiclient.command.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class AccountGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String idOrIdentifier = "idOrIdentifier";
        AccountGetCommand command =
                new AccountGetCommand.Builder()
                        .withIdOrIdentifier(idOrIdentifier)
                        .build();

        assertEquals(idOrIdentifier, command.getIdOrIdentifier());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new AccountGetCommand.Builder().build(),
                new AccountGetCommand.Builder().build());
    }
}

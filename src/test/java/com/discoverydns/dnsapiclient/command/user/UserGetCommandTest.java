package com.discoverydns.dnsapiclient.command.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class UserGetCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String idOrUsername = "idOrUsername";
        UserGetCommand command =
                new UserGetCommand.Builder()
                        .withIdOrUsername(idOrUsername)
                        .build();

        assertEquals(idOrUsername, command.getIdOrUsername());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new UserGetCommand.Builder().build(),
                new UserGetCommand.Builder().build());
    }
}

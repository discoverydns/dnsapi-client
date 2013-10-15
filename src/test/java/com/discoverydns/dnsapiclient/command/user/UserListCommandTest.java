package com.discoverydns.dnsapiclient.command.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class UserListCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String searchName = "searchName";
        String searchUsername = "searchUsername";
        String searchStatus = "searchStatus";
        UserListCommand command =
                new UserListCommand.Builder()
                        .withSearchName(searchName)
                        .withSearchUsername(searchUsername)
                        .withSearchStatus(searchStatus)
                        .build();

        assertEquals(searchName, command.getSearchName());
        assertEquals(searchUsername,
                command.getSearchUsername());
        assertEquals(searchStatus, command.getSearchStatus());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new UserListCommand.Builder().build(),
                new UserListCommand.Builder().build());
    }
}

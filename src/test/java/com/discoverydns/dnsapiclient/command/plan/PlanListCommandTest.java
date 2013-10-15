package com.discoverydns.dnsapiclient.command.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class PlanListCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String searchName = "searchName";
        String searchStatus = "searchStatus";
        PlanListCommand command =
                new PlanListCommand.Builder()
                        .withSearchName(searchName)
                        .withSearchStatus(searchStatus)
                        .build();

        assertEquals(searchName, command.getSearchName());
        assertEquals(searchStatus, command.getSearchStatus());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new PlanListCommand.Builder().build(),
                new PlanListCommand.Builder().build());
    }
}

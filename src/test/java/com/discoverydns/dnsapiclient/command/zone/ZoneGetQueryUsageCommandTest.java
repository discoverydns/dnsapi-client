package com.discoverydns.dnsapiclient.command.zone;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ZoneGetQueryUsageCommandTest {

    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        LocalDateTime searchStartDate = LocalDateTime.now();
        LocalDateTime searchEndDate = LocalDateTime.now();
        ZoneQueryUsageGranularity searchGranularity = ZoneQueryUsageGranularity.daily;

        ZoneGetQueryUsageCommand command =
                new ZoneGetQueryUsageCommand.Builder()
                    .withId(id)
                    .withSearchStartDate(searchStartDate)
                    .withSearchEndDate(searchEndDate)
                    .withSearchGranularity(searchGranularity)
                    .build();

        assertEquals(id, command.getId());
        assertEquals(searchStartDate, command.getSearchStartDate());
        assertEquals(searchEndDate, command.getSearchEndDate());
        assertEquals(searchGranularity, command.getSearchGranularity());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        ZoneGetQueryUsageCommand.Builder builder = new ZoneGetQueryUsageCommand.Builder();
        assertNotSame(builder.build(), builder.build());
    }
}

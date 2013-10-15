package com.discoverydns.dnsapiclient.command.zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;

import java.util.Set;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.xbill.DNS.Record;

public class ZoneUpdateResourceRecordsCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String id = "id";
        Long version = 1L;
        Set<Record> records = Sets.newHashSet(mock(Record.class), mock(Record.class));
        ZoneUpdateResourceRecordsCommand command =
                new ZoneUpdateResourceRecordsCommand.Builder()
                        .withId(id)
                        .withVersion(version)
                        .withResourceRecords(records)
                        .build();

        assertEquals(id, command.getId());
        assertEquals(version, command.getVersion());
        assertEquals(records, command.getRecords());
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneUpdateResourceRecordsCommand.Builder().build(),
                new ZoneUpdateResourceRecordsCommand.Builder().build());
    }
}

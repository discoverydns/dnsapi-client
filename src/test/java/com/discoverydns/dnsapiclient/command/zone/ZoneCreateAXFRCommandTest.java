package com.discoverydns.dnsapiclient.command.zone;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ZoneCreateAXFRCommandTest {
    @Test
    public void shouldBuildExpectedCommand() {
        String name = "name";
        String nameServerSetId = "nameServerSetId";
        String planId = "planId";
        String group = "group";
        List<String> axfrServers = Lists.newArrayList("firstAXFRServer", "secondAXFRServer");
        String tsigKey = "tsigKey";
        String tsigName = "tsigName";
        String tsigAlgorithm = "tsigAlgorithm";
        ZoneCreateAXFRCommand command =
                new ZoneCreateAXFRCommand.Builder()
                        .withName(name)
                        .withNameServerSetId(nameServerSetId)
                        .withPlanId(planId)
                        .withGroup(group)
                        .withAXFRServers(axfrServers)
                        .withTsigKey(tsigKey)
                        .withTsigName(tsigName)
                        .withTsigAlgorithm(tsigAlgorithm)
                        .build();

        assertThat(command.getName(), is(name));
        assertThat(command.getNameServerSetId(), is(nameServerSetId));
        assertThat(command.getPlanId(), is(planId));
        assertThat(command.getGroup(), is(group));
        assertThat(command.getTsigKey(), is(tsigKey));
        assertThat(command.getTsigName(), is(tsigName));
        assertThat(command.getTsigAlgorithm(), is(tsigAlgorithm));
        assertThat(command.getAxfrServers(), hasItems("firstAXFRServer", "secondAXFRServer"));
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneCreateAXFRCommand.Builder().build(),
                new ZoneCreateAXFRCommand.Builder().build());
    }
}

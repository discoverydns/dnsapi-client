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
        List<String> axfrServers = Lists.newArrayList("1.2.3.4", "1::2");
        String tsigKey = "tsigKey";
        String tsigName = "tsigName";
        String tsigAlgorithm = "tsigAlgorithm";
        Boolean dnssecSigned = true;
        ZoneCreateAXFRCommand command = new ZoneCreateAXFRCommand.Builder()
                                                                 .withName(name)
                                                                 .withNameServerSetId(nameServerSetId)
                                                                 .withDnssecSigned(dnssecSigned)
                                                                 .withPlanId(planId)
                                                                 .withGroup(group)
                                                                 .withAXFRServers(axfrServers)
                                                                 .withTsigKey(tsigKey)
                                                                 .withTsigName(tsigName)
                                                                 .withTsigAlgorithm(tsigAlgorithm)
                                                                 .build();

        assertThat(command.getName(), is(name));
        assertThat(command.getNameServerSetId(), is(nameServerSetId));
        assertThat(command.isDnssecSigned(), is(dnssecSigned));
        assertThat(command.getPlanId(), is(planId));
        assertThat(command.getGroup(), is(group));
        assertThat(command.getTsigKey(), is(tsigKey));
        assertThat(command.getTsigName(), is(tsigName));
        assertThat(command.getTsigAlgorithm(), is(tsigAlgorithm));
        assertThat(command.getAxfrServers(), hasItems("1.2.3.4", "1::2"));
    }

    @Test
    public void shouldBuildANewCommandEveryTime() {
        assertNotSame(new ZoneCreateAXFRCommand.Builder().build(), new ZoneCreateAXFRCommand.Builder().build());
    }
}

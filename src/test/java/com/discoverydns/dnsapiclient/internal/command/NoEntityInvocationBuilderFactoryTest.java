package com.discoverydns.dnsapiclient.internal.command;

import static org.mockito.Mockito.verify;

import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoEntityInvocationBuilderFactoryTest {
    @Mock
    private WebTarget mockWebTarget;

    private NoEntityInvocationBuilderFactory noEntityInvocationBuilderFactory;

    @Before
    public void setup() throws Throwable {
        noEntityInvocationBuilderFactory = new NoEntityInvocationBuilderFactory();
    }

    @Test
    public void shouldPerformDefaultRequestOnWebTarget() {
        noEntityInvocationBuilderFactory.buildInvocationBuilder(mockWebTarget);

        verify(mockWebTarget).request();
    }
}

package com.discoverydns.dnsapiclient.internal.command;

import static org.mockito.Mockito.verify;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WithEntityInvocationBuilderFactoryTest {
    @Mock
    private WebTarget mockWebTarget;
    @Mock
    private MediaType mockMediaType;

    private WithEntityInvocationBuilderFactory withEntityInvocationBuilderFactory;

    @Before
    public void setup() throws Throwable {
        withEntityInvocationBuilderFactory = new WithEntityInvocationBuilderFactory(mockMediaType);
    }

    @Test
    public void shouldPerformRequestWithMediaTypeOnWebTarget() {
        withEntityInvocationBuilderFactory.buildInvocationBuilder(mockWebTarget);

        verify(mockWebTarget).request(mockMediaType);
    }
}

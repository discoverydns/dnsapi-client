package com.discoverydns.dnsapiclient.command.message;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class MessagePollCommandTest {

	@Test
	public void shouldReturnANewCommandEveryTime() {
		MessagePollCommand.Builder builder = new MessagePollCommand.Builder();
		assertNotSame(builder.build(), builder.build());
	}
}
